package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class MenuState extends State {
	
	private Texture background;
	private Texture playBtn;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		background = new Texture("Bg.jpg");
		playBtn = new Texture("play2.png");
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
		sb.draw(background, 0, 0, MyGdxGame.Width, MyGdxGame.Heigh);
		sb.draw(playBtn, (MyGdxGame.Width/2) - (playBtn.getWidth()/2), (MyGdxGame.Heigh/2) - (playBtn.getHeight()/2)
				);
		//sb.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		background.dispose();
		playBtn.dispose();
		System.out.println("MenuState Disposed");
	}

}
