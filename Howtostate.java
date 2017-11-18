package state;
import Element.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;
public class Howtostate extends State implements InputProcessor{
	Texture background ;
	Button menu ;
	Stage buttonstage ;
	public Howtostate(GameStateManager gsm) {
		super(gsm);
		Gdx.input.setInputProcessor(this);
		
		background = new Texture("howtonobuttonpng.png");
		menu = new Button("menuhowto.png", 190, 0, 224, 112);
		 buttonstage = new Stage();
	     buttonstage.addActor(menu);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
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
		if (button == Input.Buttons.LEFT) {
            if(menu.click(screenX, screenY)){
                gsm.set(new SeclectionState(gsm));
                System.out.println("MENU");
            }
		}
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
        buttonstage.getViewport().update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.Width, MyGdxGame.Heigh);
        sb.end();
        buttonstage.draw();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		background.dispose();
		menu.dispose();
        System.out.println("How to state Disposed");
	}

}
