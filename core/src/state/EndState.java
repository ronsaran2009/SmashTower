package state;

import Element.Button;
import Element.Score;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

public class EndState extends State implements InputProcessor {

	Texture endscreen;
	Button retry;
	Button toSelect;
	Stage buttonstage;
	Score score1, score2, score3;
	Stage scorestage;
	// ----For set Playstate------
	int time;
	int limitfloor;
	boolean freedommode;
	int levelCheck;
	// ---------------------------
	int allscore;
	int num1;
	int num2;
	int num3;
	Texture star;
	Texture scorebg;
	Texture highscore;
	// ---------------------------
	int bestscore = 0;
	int best1;
	int best2;
	int best3;
	Score scorebest1, scorebest2, scorebest3;
	// ---------------------------
	Sound fail, win, click;

	protected EndState(GameStateManager gsm, int time, int limitfloor, boolean freedommode, int levelCheck) {
		super(gsm);
		click = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		fail = Gdx.audio.newSound(Gdx.files.internal("sound/fail1.mp3"));
		win = Gdx.audio.newSound(Gdx.files.internal("sound/win1.mp3"));
		endscreen = new Texture("bgendgame.jpg");
		scorebg = new Texture("freescore.png");
		highscore = new Texture("score.png");
		retry = new Button("retry.png", 240, 175, 120, 100);
		toSelect = new Button("baackmenu.png", 260, 75, 80, 80);
		this.time = time;
		this.limitfloor = limitfloor;
		this.freedommode = freedommode;
		this.levelCheck = levelCheck;
		Gdx.input.setInputProcessor(this);

		allscore = EndState.getScore();
		bestscore = EndState.getBestscore();
		System.out.println("getscore : " + allscore);
		if (bestscore < allscore && freedommode == true) {
			bestscore = allscore;
			EndState.setBestscore(bestscore);
		}

		num1 = (allscore % 10);
		num2 = (allscore % 100) / 10;
		num3 = allscore / 100;
		scorestage = new Stage();
		score1 = new Score(num1, 320, 375, 80, 100);
		score2 = new Score(num2, 260, 375, 80, 100);
		score3 = new Score(num3, 200, 375, 80, 100);
		scorestage.addActor(score1);
		scorestage.addActor(score2);
		scorestage.addActor(score3);
		best1 = (bestscore % 10);
		best2 = (bestscore % 100) / 10;
		best3 = bestscore / 100;
		scorebest1 = new Score(best1, 405, 295, 40, 50);
		scorebest2 = new Score(best2, 375, 295, 40, 50);
		scorebest3 = new Score(best3, 345, 295, 40, 50);
		scorestage.addActor(scorebest1);
		scorestage.addActor(scorebest2);
		scorestage.addActor(scorebest3);

		buttonstage = new Stage();
		buttonstage.addActor(retry);
		buttonstage.addActor(toSelect);
		cam.setToOrtho(false, MyGdxGame.Width, MyGdxGame.Heigh);
		MenuState.stop();
		if (isEndbydeath() == true) {// 0star(no star)
			star = new Texture("0s.png");
			fail.play();
			System.out.println("play");
			if (isFreedommode() == true) {
				// Show score
			}
		} else {
			if (timeleft >= 3) {// 3star
				star = new Texture("3s.png");
				win.play();
			} else if (timeleft >= 1 && timeleft < 3) {// 2star
				star = new Texture("2s.png");
				win.play();
			} else if (timeleft > 0 && timeleft < 1) {// 1star
				star = new Texture("1s.png");
				win.play();
			} else if (timeleft <= 0) {// 0star
				star = new Texture("0s.png");
				fail.play();
				System.out.println("play");
			}
		}

	}

	@Override
	public void handleinput() {
	}

	@Override
	public void update(float dt) {
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		sb.draw(endscreen, 0, 0, 600, 800);
		sb.end();
		if (isFreedommode()) {
			sb.begin();
			sb.draw(scorebg, 150, 350, 300, 170);
			sb.draw(highscore, 140, 280, 320, 80);
			sb.end();
			scorestage.draw();
		} else {
			sb.begin();
			sb.draw(star, 150, 350, 300, 170);
			sb.end();

		}
		// sb.draw(region, x, y, originX, originY, width, height, scaleX, scaleY,
		// rotation)
		buttonstage.draw();

	}

	@Override
	public void dispose() {
		fail.stop();
		win.stop();
		endscreen.dispose();
		retry.dispose();
		star.dispose();
		toSelect.dispose();
		buttonstage.clear();
		buttonstage.dispose();
		scorestage.clear();
		scorestage.dispose();
		System.out.println("EndState Disposed");
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
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
		System.out.println("Screen clicked");
		click.play();
		if (button == Input.Buttons.LEFT) {
			if (retry.click(screenX, screenY)) {
				// System.out.println("Retry button clicked");
				MenuState.play();
				gsm.set(new Playstate(gsm, this.time, this.limitfloor, this.freedommode, this.levelCheck));
			} else if (toSelect.click(screenX, screenY)) {
				// System.out.println("toSelect button clicked");
				MenuState.stop();
				MenuState.play();
				gsm.set(new SeclectionState(gsm));
			}
		}
		return true;
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
		if (retry.click(screenX, screenY)) {
			retry = new Button("retrydown.png", 240, 175, 120, 100);
			toSelect = new Button("baackmenu.png", 260, 75, 80, 80);
			buttonstage.clear();
			buttonstage.addActor(retry);
			buttonstage.addActor(toSelect);
		} else if (toSelect.click(screenX, screenY)) {
			retry = new Button("retry.png", 240, 175, 120, 100);
			toSelect = new Button("baackmenudown.png", 260, 75, 80, 80);
			buttonstage.clear();
			buttonstage.addActor(retry);
			buttonstage.addActor(toSelect);
		} else {
			retry = new Button("retry.png", 240, 175, 120, 100);
			toSelect = new Button("baackmenu.png", 260, 75, 80, 80);
			buttonstage.clear();
			buttonstage.addActor(retry);
			buttonstage.addActor(toSelect);
		}
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
