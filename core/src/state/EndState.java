package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

public class EndState extends State implements InputProcessor{
	
	Texture endscreen;
	Button retry;
	Stage buttonstage;
	
	protected EndState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		endscreen = new Texture("error4.png");
		retry = new Button("retry_logo.png",120, 140, 360, 120);
		buttonstage = new Stage();
		buttonstage.addActor(retry);
		cam.setToOrtho(false, MyGdxGame.Width, MyGdxGame.Heigh);
	}

	@Override
	public void handleinput() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()) {
			gsm.set(new SeclectionState(gsm));
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		sb.draw(endscreen, 0, 0, 600, 800);
		//sb.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		sb.end();
		buttonstage.draw();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		endscreen.dispose();
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
		System.out.println("input!!!!!!");
		if (button == Input.Buttons.LEFT)
			if(retry.click(screenX, screenY)){
				gsm.set(new SeclectionState(gsm));
			}
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
