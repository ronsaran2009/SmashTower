package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class EndState extends State{
	
	Texture endscreen, REbtn;
	
	protected EndState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		endscreen = new Texture("error4.png");
		REbtn = new Texture("retry_logo.png");
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
			gsm.set(new Playstate(gsm));
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		sb.draw(endscreen, 0, 0, 600, 800);
		sb.draw(REbtn, (MyGdxGame.Width/2) - (REbtn.getWidth()/2), (MyGdxGame.Heigh/2)
				- (REbtn.getHeight()/2) - 200);
		//sb.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		endscreen.dispose();
		REbtn.dispose();
		System.out.println("EndState Disposed");
	}

	
	
}
