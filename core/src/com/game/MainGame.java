package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MainGame extends ApplicationAdapter implements InputProcessor {
	BOB bob ;
	SpriteBatch batch;
	Stage character;
	Texture background;
	SideObj build[] = new SideObj[1000000];
	Stage buildingstage;
	SpriteBatch errorbatch;
	Texture error;
	int buildround = 4;
	int floor;
	int countfloor = 0; // Score
	boolean endgame = true; //For can't move.If True is can(gameplay).
	Timer time;
	Stage timer;
	int second = 1000;


	@Override
	public void create () {
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		background = new Texture("Bg.png");
		errorbatch = new SpriteBatch();
		error = new Texture("clearbg.png");
		/////// BOB ////////
		bob = new BOB(0);
		character = new Stage();
		character.addActor(bob);
		///////////////////
		/////// Time //////
		time = new Timer(second);//sec
		timer = new Stage();
		timer.addActor(time);
		////////////////
		////////SetCondoStart//////
		build[0] = new SideObj(1);
		build[1] = new SideObj(2);
		build[2] = new SideObj(1);
		build[3] = new SideObj(3);
		build[4] = new SideObj(0);
		build[0].y = 50;
		build[1].y = 250;
		build[2].y = 450;
		build[3].y = 650;
		build[4].y = 850;
		//////////////////////////
		buildingstage = new Stage();
		buildingstage.addActor(build[0]);
		buildingstage.addActor(build[1]);
		buildingstage.addActor(build[2]);
		buildingstage.addActor(build[3]);

	}

	@Override
	public void render () {
		///////clear/////
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		////////////////
		///////Background///////
		batch.begin();
		batch.draw(background,0,0,600,800);
		batch.end();
		///////////////////////
		character.draw();
		buildingstage.draw();
		timer.draw();
		batch.begin();
		batch.draw(error,0,0,600,800);
		batch.end();
		/////////TimeOut//////////
		if (time.isEnd() == false){
			endgame = false;
			error = new Texture("error4.png");
			if (bob.x==0){
				bob.setCheck(2);
			}
			else{
				bob.setCheck(3);
			}
		}
		//////////////////////////

	}
	
	@Override
	public void dispose () {

	}

	@Override
	public boolean keyDown(int keycode) {
		//////floortyperandom//////
		floor = 1;//(int) (Math.random() * 4);
		///////////////////////////
		if(endgame == true) {
			if (keycode == Input.Keys.LEFT) {
				//bob.x = 0;
				time.check = true; // start timer
				bob.setCheck(0); //side of bob. 0 is left.
				/////////CheckForBuiltNewNextOne///////
				if(build[buildround-1].y <= 650) {
					build[buildround] = new SideObj(floor);
					buildingstage.addActor(build[buildround]);
					buildround += 1;
				}
				///////////////////////////////////////
				for (int i = 0; i < buildround; i++) {
					build[i].y -= 25;
					build[i].brakedown = true;
					if (build[i].y < 0 && build[i].y > -200){
						build[i].dispose();
					}
					if (build[i].y < 250 && build[i].y >= 50 && build[i].check == 0) {
						error = new Texture("error4.png");
						///////SetCondoErrorChange////////
						build[i].SideObj = new Texture("condo.png");
						build[i].x = 200;
						build[i].y = 50;
						build[i].h = 200;
						build[i].w = 200;
						buildingstage.addActor(build[buildround-1]);
						//////////////////////////////
						time.check = false;	// Stop timer.
						endgame = false;
						bob.setCheck(2); // 2 is bob death left.
					}
				}

				countfloor += 1;
				//*********Endlessmode
				//time.time += 0.15; // +time per move.
			}
			if (keycode == Input.Keys.RIGHT) {
				//bob.x = 400;
				time.check = true; // start timer.
				bob.setCheck(1); //side of bob. 1 is right.
				/////////CheckForBuiltNewNextOne///////
				if(build[buildround-1].y <= 650) {
					build[buildround] = new SideObj(floor);
					buildingstage.addActor(build[buildround]);
					buildround += 1;
				}
				///////////////////////////////////////
				///////BuildingBreakdown//////
				for (int i = 0; i < buildround; i++) {
					build[i].y -= 25;
					build[i].brakedown = true;
					if (build[i].y < 0 && build[i].y > -200){
						build[i].dispose();
					}

					//////////////////////////////
					if (build[i].y < 250 && build[i].y >= 50 && build[i].check == 2) {
						error = new Texture("error4.png");
						///////SetCondoErrorChange////////
						build[i].SideObj = new Texture("condo.png");
						build[i].x = 200;
						build[i].y = 50;
						build[i].h = 200;
						build[i].w = 200;
						buildingstage.addActor(build[buildround-1]);
						//////////////////////////////
						time.check = false; // Stop timer.
						endgame = false;
						bob.setCheck(3); // 3 is bob death right.

					}
				}

				countfloor += 1;//Score
				//*********Endlessmode
				//time.time += 0.15; // +time per move.
			}
			System.out.println("score : " + countfloor);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
