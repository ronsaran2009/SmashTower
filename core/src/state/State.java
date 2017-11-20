package state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

	protected OrthographicCamera cam;
	protected Vector3 mouse;
	protected GameStateManager gsm;
	public static int score;
	public static float timeleft;
	public static boolean endbydeath = false;
	public static boolean freedommode = false;
	public static int bestscore = 0;

	protected State(GameStateManager gsm) {
		this.gsm = gsm;
		cam = new OrthographicCamera();
		mouse = new Vector3();
	}

	public static void setScore(int score) {
		State.score = score;
	}

	public static int getScore() {
		return score;
	}

	public static float getTimeleft() {
		return timeleft;
	}

	public static void setTimeleft(float timeleft) {
		State.timeleft = timeleft;
	}

	public static boolean isEndbydeath() {
		return endbydeath;
	}

	public static void setEndbydeath(boolean endbydeath) {
		State.endbydeath = endbydeath;
	}

	public static boolean isFreedommode() {
		return freedommode;
	}

	public static void setFreedommode(boolean freedommode) {
		State.freedommode = freedommode;
	}

	public static int getBestscore() {
		return bestscore;
	}

	public static void setBestscore(int bestscore) {
		State.bestscore = bestscore;
	}

	public abstract void handleinput();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	public abstract void dispose();
	
}
