package state;

import Element.BOB;
import Element.SideObj;
import Element.Timer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Playstate extends State implements InputProcessor {

	BOB bob;
	SpriteBatch batch;
	Stage character;
	Texture background;
//	SideObj build[] = new SideObj[1500];
	ArrayList<SideObj> testbuild = new ArrayList<SideObj>();
	Stage buildingstage;
	Texture error;
	int seconed = 10;
	int buildround = 4;
	int floor;
	int countfloor = 0;
	boolean endgame = true;
	Timer time;
	Stage timer;
	Texture timebg;
	Texture timewhitebg;
	int limitfloor;
    boolean builtmore = true;
    boolean freedommode ;
    float timeleft ;
	int level;//0easy 1normal 2hard 3freedom
	Texture bom;

	public Playstate(GameStateManager gsm, int time, int limitfloor, boolean freedommode, int level) {
		super(gsm);
		this.limitfloor = limitfloor+1;
		this.freedommode = freedommode;
		this.level = level;
		Playstate.setFreedommode(this.freedommode);
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		background = new Texture("newbg2.png");
		error = new Texture("clearbg3.png");
		/// BOB///
		bob = new BOB(0);
		character = new Stage();
		character.addActor(bob);
		////////
		/// Time///
		seconed = time;
		this.time = new Timer(seconed);// sec
		timer = new Stage();
		timer.addActor(this.time);
		timebg = new Texture("bartime.png");
		timewhitebg = new Texture("bartimebg.png");
		//////////
		bom = new Texture("comic-boom-explosion-2-8-1.png");
		////// SetCondo/////
		testbuild.add(0, new SideObj(4, this.level));
		testbuild.add(1, new SideObj(1, this.level));
		testbuild.add(2, new SideObj(1, this.level));
		testbuild.add(3, new SideObj(2, this.level));
		testbuild.add(4, new SideObj(3, this.level));
		testbuild.get(0).y = 50;
		testbuild.get(1).y = 250;
		testbuild.get(2).y = 450;
		testbuild.get(3).y = 650;
		testbuild.get(4).y = (200*limitfloor)+50;
		buildingstage = new Stage();
		////////////////////
		buildingstage.addActor(testbuild.get(0));
		buildingstage.addActor(testbuild.get(1));
		buildingstage.addActor(testbuild.get(2));
		buildingstage.addActor(testbuild.get(3));
        buildingstage.addActor(testbuild.get(4));
		//cam.setToOrtho(false, MyGdxGame.Width / 2, MyGdxGame.Heigh / 2);
	}

	@Override
	public void handleinput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
        for (int i = 0; i < buildround; i++) {
            if (testbuild.get(i).y <=50 && testbuild.get(i).check == 3){
				Playstate.setTimeleft(timeleft);
				Playstate.setEndbydeath(false);
				Playstate.setScore(countfloor);
				gsm.set(new EndState(gsm, this.seconed, this.limitfloor, this.freedommode, this.level));
            }
        }
        timeleft = time.getTime();
		Playstate.setScore(countfloor);
		Playstate.setTimeleft(timeleft);
//		for(int i = 0; i < buildround; i++){
//			if (testbuild.get(i).y < -100){
//				testbuild.get(i).remove();
//			}
//		}
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		/////// clear/////
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		////////////////
		/////////Set Screen/////////
		character.getViewport().update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
		buildingstage.getViewport().update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
		timer.getViewport().update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
		//////////////////
		/////// Background///////
		batch.begin();
		//batch.setProjectionMatrix(cam.combined);
		//batch.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
		batch.draw(background,-600,0,1200,1600);
		batch.end();
		///////////////////////
		character.draw();
		buildingstage.draw();
		batch.begin();
		batch.draw(timewhitebg, 82, 750, 500, 25);
		batch.end();
		timer.draw();
		batch.begin();
		batch.draw(timebg, 0, 726, 600, 74);
		batch.draw(error, 0, 0, 600, 800);
		batch.end();
		if (testbuild.get(countfloor).brakedown == true){
			batch.begin();
			batch.draw(bom, 50, -75, 475, 450);
			batch.end();
		}
		////// TimeOut//////
		if (time.isEnd() == false) {
			endgame = false;
			error = new Texture("error4.png");
			if (bob.getx() == 0) {
				bob.setCheck(2);
			} else {
				bob.setCheck(3);
			}
			Playstate.setScore(countfloor);
			Playstate.setEndbydeath(true);
			gsm.set(new EndState(gsm, this.seconed, this.limitfloor, this.freedommode, this.level));
		}
		//////////////////////////
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		background.dispose();
		error.dispose();
		bob.dispose();
		System.out.println("PlayState Disposed");
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		////// RandomFloorType//////
        if (floor == 0 ||floor == 2){
            floor = 1;
        }
        else{
            floor = (int) (Math.random() * 3);
        }
        System.out.println(floor);
		///////////////////////////
		if (endgame == true) {
			if (keycode == Input.Keys.LEFT) {
				// bob.x = 0;
				time.setCheck(true);// start timer
				bob.setCheck(0);// side of bob. True is left.
				///// CheckForCreateNewNextOne////
				if (buildround < limitfloor){
					if (testbuild.get(buildround - 1).y <= 650) {
						testbuild.add(buildround, new SideObj(floor, level));
						buildingstage.addActor(testbuild.get(buildround));
						buildround += 1;
					}
				}
				else{
				    if (builtmore == true) {
                        if (testbuild.get(buildround - 1).y <= 650) {
                        	testbuild.set(buildround, new SideObj(3, level));
                            buildingstage.addActor(testbuild.get(buildround));
                            buildround += 1;
                            builtmore = false;
                        }
                    }
                }

				/////////////////////////////////
				// System.out.println(floor);
				for (int i = 0; i < buildround; i++) {
					testbuild.get(i).y -= 25;
					testbuild.get(i).brakedown = true;
					if (testbuild.get(i).y < 250 && testbuild.get(i).y >= 50 && testbuild.get(i).check == 0) {
						Playstate.setScore(countfloor);
						Playstate.setEndbydeath(true);
						gsm.set(new EndState(gsm, this.seconed, this.limitfloor, this.freedommode, this.level));
						error = new Texture("error4.png");
						/////// SetCondoErrorChange////////
//						build[i].SideObj = new Texture("condo.png");
						testbuild.get(i).SideObj = new Texture("condo.png");
						testbuild.get(i).x = 200;
						testbuild.get(i).y = 50;
						testbuild.get(i).h = 200;
						testbuild.get(i).w = 200;
						buildingstage.addActor(testbuild.get(buildround -1));
						//////////////////////////////
					build[i].y -= 25;



					build[i].brakedown = true;
					if (build[i].y < 250 && build[i].y >= 50 && build[i].check == 0) {
						bob.setCheck(2); // 2 is bob death left.
						Playstate.setScore(countfloor);
						Playstate.setEndbydeath(true);
						gsm.set(new EndState(gsm, this.seconed, this.limitfloor, this.freedommode, this.level));
						time.check = false; // Stop timer.
						endgame = false;

					}
				}

				countfloor += 1;
				if (freedommode == true) {
                    // *********Endlessmode
                    time.time += 0.15; // +time per move.
                }
			}
			if (keycode == Input.Keys.RIGHT) {
				// bob.x = 400;
				time.check = true; // start timer.
				bob.setCheck(1);// side of bob. 1 is right.
				///// CheckForCreateNewNextNoe////
				if (buildround <= limitfloor){
					if (testbuild.get(buildround -1).y <= 650) {
						testbuild.add(buildround, new SideObj(floor, level));
						buildingstage.addActor(testbuild.get(buildround));
						buildround += 1;
					}
				}
                else{
                    if (builtmore == true) {
                        if (testbuild.get(buildround -1).y <= 650) {
                        	testbuild.set(buildround, new SideObj(3, level));
    						buildingstage.addActor(testbuild.get(buildround));
    						buildround += 1;
                            builtmore = false;
                        }
                    }
                }

				/////////////////////////////////
				/////// BuildingBreakdown//////
				for (int i = 0; i < buildround; i++) {
					testbuild.get(i).y -= 25;
					testbuild.get(i).brakedown = true;
					//////////////////////////////
					if (build[i].y < 250 && build[i].y >= 50 && build[i].check == 2) {
						bob.setCheck(3); // 3 is bob death right.
						Playstate.setScore(countfloor);
						Playstate.setEndbydeath(true);
						gsm.set(new EndState(gsm, this.seconed, this.limitfloor, this.freedommode, this.level));
						time.check = false; // Stop timer.
						endgame = false;

					}
				}

				countfloor += 1;// Score
                if (freedommode == true) {
                    // *********Endlessmode
                    time.time += 0.15; // +time per move.
                }
			}
			System.out.println("score : " + countfloor);
		}
		/*
		 * for(int i = 0; i < buildround;i++){ build[i].y -= 200; }
		 */
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
