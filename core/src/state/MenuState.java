package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class MenuState extends State implements InputProcessor{
	
	private Texture background;
	private Texture playBtn;
	boolean click = false;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		Gdx.input.setInputProcessor(this);
		background = new Texture("begin.png");
		playBtn = new Texture("play.png");

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
		sb.draw(playBtn, 250, 100, 100, 100);
		//sb.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		sb.end();
		if (click == true){
			gsm.set(new SeclectionState(gsm));
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		background.dispose();
		playBtn.dispose();
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
			if (screenX >= 250 && screenX <= 250 +100){
				if (screenY >= 600 && screenY <= 600+ 100){
					click = true;
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
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
