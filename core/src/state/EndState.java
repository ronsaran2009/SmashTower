package state;

import Element.Button;
import Element.Score;
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
	Texture star;
	Score score1, score2, score3;
	Stage scorestage;
	int allscore;
	int num1;
	int num2;
	int num3;
	float timeleft;

	
	protected EndState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		timeleft = EndState.getTimeleft();
		endscreen = new Texture("bgendgame.jpg");
		retry = new Button("retry.png",200, 150, 200, 200);
		Gdx.input.setInputProcessor(this);

		allscore = EndState.getScore();
		System.out.println("getscore : "+allscore);
		
		buttonstage = new Stage();
		buttonstage.addActor(retry);
		cam.setToOrtho(false, MyGdxGame.Width, MyGdxGame.Heigh);
		if (isEndbydeath() == true){//0star(no star)
			star = new Texture("0s.png");
			if (isFreedommode() == true){
				//Show score
			}
		}
		else{
			if (timeleft >= 3){//3star
				star = new Texture("3s.png");
			}
			else if (timeleft >= 1 && timeleft < 3){//2star
				star = new Texture("2s.png");
			}
			else if (timeleft >= 0 && timeleft < 1){//1star
				star = new Texture("1s.png");
			}
		}



		/*num1 = (allscore%10);
		System.out.println("getNUM1 : "+num1);
		num2 = (allscore%100)/10;
		System.out.println("getNUM2 : "+num2);
		num3 = (allscore%1000)/100;
		System.out.println("getNUM3 : "+num3);
		score1 = new Score(num1,360,250, 120,100);
		score2 = new Score(num2,240,250, 120,100);
		score3 = new Score(num3,120,250, 120,100);
		scorestage.addActor(score1);
		scorestage.addActor(score2);
		scorestage.addActor(score3);*/
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
		sb.setProjectionMatrix(cam.combined);
		sb.draw(endscreen, 0, 0, 600, 800);
		sb.draw(star, 150, 350, 300, 170);
		//sb.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		sb.end();
		buttonstage.draw();
		//scorestage.draw();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		endscreen.dispose();
		//scorestage.dispose();
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
