package state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

	protected OrthographicCamera cam;
	protected Vector3 mouse;
	protected GameStateManager gsm;
	public static int score;
	
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

	public abstract void handleinput();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	public abstract void dispose();
	
}
