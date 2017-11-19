package state;

import Element.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

public class MenuState extends State implements InputProcessor {

	private Texture background;
	private Stage buttonstage;
	private Button play;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		Gdx.input.setInputProcessor(this);
		buttonstage = new Stage();
		background = new Texture("begin.jpg");
		play = new Button("play.png", 200, 50, 200, 200);
		buttonstage.addActor(play);
	}

	@Override
	public void handleinput() {
	}

	@Override
	public void update(float dt) {
	}

	@Override
	public void render(SpriteBatch sb) {
		buttonstage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		sb.begin();
		sb.draw(background, 0, 0, MyGdxGame.Width, MyGdxGame.Heigh);
		sb.end();
		buttonstage.draw();
	}

	@Override
	public void dispose() {
		background.dispose();
		play.dispose();
		buttonstage.clear();
		buttonstage.dispose();
		System.out.println("MenuState Disposed");
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
		System.out.println("Screen Clicked");
		if (button == Input.Buttons.LEFT)
			if (play.click(screenX, screenY)) {
				gsm.set(new SeclectionState(gsm));
				System.out.println("SeclectionState");
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
		// System.out.println("X : "+ screenX + " Y : " + screenY );
		if (play.click(screenX, screenY)) {
			play = new Button("playdown.png", 200, 50, 200, 200);
			buttonstage.addActor(play);
		} else {
			play = new Button("play.png", 200, 50, 200, 200);
			buttonstage.addActor(play);
		}
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
