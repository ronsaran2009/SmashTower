package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

import Element.Timer;
import Element.BOB;
import Element.SideObj;

public class Playstate extends State implements InputProcessor {

	BOB bob;
	SpriteBatch batch;
	Stage character;
	Texture background;
	SideObj build[] = new SideObj[1000000];
	Stage buildingstage;
	SpriteBatch errorbatch;
	Texture error;
	int buildround = 4;
	int floor;
	int countfloor = 0;
	boolean endgame = true;
	Timer time;
	Stage timer;

	public Playstate(GameStateManager gsm) {
		super(gsm);
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		background = new Texture("Bg.png");
		errorbatch = new SpriteBatch();
		error = new Texture("clearbg.png");
		/// BOB///
		bob = new BOB(0);
		character = new Stage();
		character.addActor(bob);
		////////
		/// Time///
		time = new Timer(10);// sec
		timer = new Stage();
		timer.addActor(time);
		//////////
		////// SetCondo/////
		build[0] = new SideObj(1);
		build[1] = new SideObj(1);
		build[2] = new SideObj(1);
		build[3] = new SideObj(2);
		build[4] = new SideObj(0);
		build[0].y = 50;
		build[1].y = 250;
		build[2].y = 450;
		build[3].y = 650;
		build[4].y = 850;
		buildingstage = new Stage();
		////////////////////
		buildingstage.addActor(build[0]);
		buildingstage.addActor(build[1]);
		buildingstage.addActor(build[2]);
		buildingstage.addActor(build[3]);
		cam.setToOrtho(false, MyGdxGame.Width / 2, MyGdxGame.Heigh / 2);
	}

	@Override
	public void handleinput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		/////// clear/////
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		////////////////
		/////// Background///////
		batch.begin();
		batch.setProjectionMatrix(cam.combined);
		batch.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
		// batch.draw(background,0,0,600,800);
		batch.end();
		///////////////////////
		character.draw();
		timer.draw();
		buildingstage.draw();
		batch.begin();
		batch.draw(error, 0, 0, 300, 400);
		batch.end();
		////// TimeOut//////
		if (time.isEnd() == false) {
			endgame = false;
			error = new Texture("error4.png");
			if (bob.getx() == 0) {
				bob.setCheck(2);
			} else {
				bob.setCheck(3);
			}
		}
		//////////////////////////
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		background.dispose();
		error.dispose();
		bob.dispose();
		timer.dispose();
		for (int i = 0; i <= 3; i++) {
			build[i].dispose();
		}
		System.out.println("PlayState Disposed");
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		////// RandomFloorType//////
		floor = (int) (Math.random() * 3);
		///////////////////////////
		if (endgame == true) {
			if (keycode == Input.Keys.LEFT) {
				// bob.x = 0;
				time.setCheck(true);// start timer
				bob.setCheck(0);// side of bob. True is left.
				///// CheckForCreateNewNextNoe////
				if (build[buildround - 1].y <= 650) {
					build[buildround] = new SideObj(floor);
					buildingstage.addActor(build[buildround]);
					buildround += 1;
				}
				/////////////////////////////////
				// System.out.println(floor);
				for (int i = 0; i < buildround; i++) {
					build[i].y -= 25;
					build[i].brakedown = true;
					if (build[i].y < 250 && build[i].y >= 50 && build[i].check == 0) {
						error = new Texture("error4.png");
						/////// SetCondoErrorChange////////
						build[i].SideObj = new Texture("condo.png");
						build[i].x = 200;
						build[i].y = 50;
						build[i].h = 200;
						build[i].w = 200;
						buildingstage.addActor(build[buildround - 1]);
						//////////////////////////////
						time.check = false; // Stop timer.
						endgame = false;
						bob.setCheck(2); // 2 is bob death left.
					}
				}

				countfloor += 1;
				// *********Endlessmode
				// ti
			}
			if (keycode == Input.Keys.RIGHT) {
				// bob.x = 400;
				time.check = true; // start timer.
				bob.setCheck(1);// side of bob. 1 is right.
				///// CheckForCreateNewNextNoe////
				if (build[buildround - 1].y <= 650) {
					build[buildround] = new SideObj(floor);
					buildingstage.addActor(build[buildround]);
					buildround += 1;
				}
				/////////////////////////////////
				/////// BuildingBreakdown//////
				for (int i = 0; i < buildround; i++) {
					build[i].y -= 25;
					build[i].brakedown = true;
					//////////////////////////////
					if (build[i].y < 250 && build[i].y >= 50 && build[i].check == 2) {
						error = new Texture("error4.png");
						/////// SetCondoErrorChange////////
						build[i].SideObj = new Texture("condo.png");
						build[i].x = 200;
						build[i].y = 50;
						build[i].h = 200;
						build[i].w = 200;
						buildingstage.addActor(build[buildround - 1]);
						//////////////////////////////
						time.check = false; // Stop timer.
						endgame = false;
						bob.setCheck(3); // 3 is bob death right.
					}
				}

				countfloor += 1;// Score
				// *********Endlessmode
				// time.time += 0.15; // +time per move.
			}
			System.out.println("score : " + countfloor);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
