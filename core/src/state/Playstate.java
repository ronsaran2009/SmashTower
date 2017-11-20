package state;

import Element.BOB;
import Element.SideObj;
import Element.Timer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Playstate extends State implements InputProcessor {

	BOB bob;
	SpriteBatch batch;
	Stage character;
	Texture background;
	SideObj build[] = new SideObj[1100];
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
	boolean freedommode;
	float timeleft;
	int level;// 0easy 1normal 2hard 3freedom
	Texture bom, bomclr, bombom;
	String center;
	Sound hammer;
	public static Sound click;

	public Playstate(GameStateManager gsm, int time, int limitfloor, boolean freedommode, int level) {
		super(gsm);
		this.limitfloor = limitfloor + 1;
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
		bomclr = new Texture("bomclear.png");
		bombom = new Texture("comic-boom-explosion-2-8-1.png");
		bom = bomclr;
		////// SetCondo/////
		build[0] = new SideObj(4, this.level);
		build[1] = new SideObj(1, this.level);
		build[2] = new SideObj(1, this.level);
		build[3] = new SideObj(2, this.level);
		build[this.limitfloor] = new SideObj(3, this.level);
		build[0].y = 50;
		build[1].y = 250;
		build[2].y = 450;
		build[3].y = 650;
		build[this.limitfloor].y = (200 * limitfloor) + 50;
		buildingstage = new Stage();
		////////////////////
		buildingstage.addActor(build[0]);
		buildingstage.addActor(build[1]);
		buildingstage.addActor(build[2]);
		buildingstage.addActor(build[3]);
		buildingstage.addActor(build[this.limitfloor]);
		hammer = Gdx.audio.newSound(Gdx.files.internal("sound/hammer.mp3"));
		click = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		// cam.setToOrtho(false, MyGdxGame.Width / 2, MyGdxGame.Heigh / 2);
	}

	@Override
	public void handleinput() {
	}

	@Override
	public void update(float dt) {
		for (int i = 0; i < buildround; i++) {
			if (build[i].y <= 50 && build[i].check == 3) {
				Playstate.setTimeleft(timeleft);
				Playstate.setEndbydeath(false);
				Playstate.setScore(countfloor);
				gsm.set(new EndState(gsm, this.seconed, this.limitfloor, this.freedommode, this.level));
			}
		}
		timeleft = time.getTime();
		Playstate.setScore(countfloor);
		Playstate.setTimeleft(timeleft);
		for (int i = 0; i < buildround; i++) {
			if (build[i].y < -100) {
				build[i].remove();
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		/////// clear/////
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		////////////////
		///////// Set Screen/////////
		character.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		buildingstage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		timer.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		//////////////////
		/////// Background///////
		batch.begin();
		// batch.setProjectionMatrix(cam.combined);
		// batch.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
		batch.draw(background, -600, 0, 1200, 1600);
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
		batch.draw(bom, 50, -75, 475, 450);
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
			Playstate.setScore(countfloor);
			Playstate.setEndbydeath(true);
			gsm.set(new EndState(gsm, this.seconed, this.limitfloor, this.freedommode, this.level));
		}
		//////////////////////////
	}

	@Override
	public void dispose() {
		background.dispose();
		error.dispose();
		bob.dispose();
		batch.dispose();
		character.clear();
		buildingstage.clear();
		character.dispose();
		buildingstage.dispose();
		System.out.println("PlayState Disposed");
	}

	@Override
	public boolean keyDown(int keycode) {
		////// RandomFloorType//////
		click.play();
		if (floor == 0 || floor == 2) {
			floor = 1;
		} else {
			floor = (int) (Math.random() * 3);
		}
		// System.out.println(floor);
		///////////////////////////
		///// CheckForCreateNewNextOne////
		if (buildround < limitfloor) {
			if (build[buildround - 1].y <= 650) {
				build[buildround] = new SideObj(floor, level);
				buildingstage.addActor(build[buildround]);
				buildround += 1;
			}
		} else {
			if (builtmore == true) {
				if (build[buildround - 1].y <= 650) {
					build[buildround] = new SideObj(3, level);
					buildingstage.addActor(build[buildround]);
					buildround += 1;
					builtmore = false;
				}
			}
		}

		/////////////////////////////////
		if (endgame == true) {
			if (keycode == Input.Keys.LEFT) {
				// bob.x = 0;
				hammer.play();
				bom = bombom;
				time.setCheck(true);// start timer
				bob.setCheck(4);
				// System.out.println(floor);
				/////// BuildingBreakdown//////
				for (int i = 0; i < buildround; i++) {
					build[i].y -= 25;
					build[i].brakedown = true;
					if (build[i].y < 250 && build[i].y >= 50 && build[i].check == 0) {
						Playstate.setScore(countfloor);
						Playstate.setEndbydeath(true);
						if (freedommode == true) {
							gsm.set(new EndState(gsm, this.seconed, this.limitfloor, this.freedommode, this.level));
							time.check = false; // Stop timer.
							endgame = false;
							bob.setCheck(2);
						} else {
							error = new Texture("redbg.png");
							time.time -= 1;
							center = build[i].getcenter();
							build[i].SideObj = new Texture(center);
							build[i].x = 200;
							build[i].y = 50;
							build[i].h = 200;
							build[i].w = 200;
							buildingstage.addActor(build[i]);
						}
					}
				}

				countfloor += 1;
				if (freedommode == true) {
					// *********Endlessmode
					time.time += 0.15; // +time per move.
				}
			}
			if (keycode == Input.Keys.RIGHT) {
				hammer.play();
				// bob.x = 400;
				bom = bombom;
				time.check = true; // start timer.
				bob.setCheck(5);

				/////// BuildingBreakdown//////
				for (int i = 0; i < buildround; i++) {
					build[i].y -= 25;
					build[i].brakedown = true;
					//////////////////////////////
					if (build[i].y < 250 && build[i].y >= 50 && build[i].check == 2) {
						Playstate.setScore(countfloor);
						Playstate.setEndbydeath(true);
						if (freedommode == true) {
							gsm.set(new EndState(gsm, this.seconed, this.limitfloor, this.freedommode, this.level));
							time.check = false; // Stop timer.
							endgame = false;
							// bob.setCheck(3);
						} else {
							error = new Texture("redbg.png");
							time.time -= 1;
							center = build[i].getcenter();
							build[i].SideObj = new Texture(center);
							build[i].x = 200;
							build[i].y = 50;
							build[i].h = 200;
							build[i].w = 200;
							buildingstage.addActor(build[i]);
						}
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
		if (keycode == Input.Keys.LEFT) {
			bob.setCheck(0);
			bom = bomclr;
			error = new Texture("clearbg3.png");
		} else if (keycode == Input.Keys.RIGHT) {
			bob.setCheck(1);
			bom = bomclr;
			error = new Texture("clearbg3.png");
		}
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
