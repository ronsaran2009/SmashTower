package state;

import Element.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

import Element.Button;

public class MenuState extends State implements InputProcessor{
	
	private Texture background;
	Stage buttonstage;
	Button play;


	public MenuState(GameStateManager gsm) {
		super(gsm);
		Gdx.input.setInputProcessor(this);
		buttonstage = new Stage();
		background = new Texture("begin.png");
		play = new Button("play.png", 200, 50, 200, 200);
		buttonstage.addActor(play);
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
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		sb.begin();
		sb.draw(background, 0, 0, MyGdxGame.Width, MyGdxGame.Heigh);
		//sb.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		sb.end();
		buttonstage.draw();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		background.dispose();
		play.dispose();
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
		System.out.println("input!!!!!!");
		if (button == Input.Buttons.LEFT)
			if (play.click(screenX, screenY)){
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
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
