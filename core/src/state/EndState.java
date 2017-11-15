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
	Button toSelect;
	Stage buttonstage;
	Score score1, score2, score3;
	Stage scorestage;
	//	----For set Playstate------
	int time;
	int limitfloor;
	boolean freedommode;
	int levelCheck;
	//	---------------------------
	int allscore;
	int num1;
	int num2;
	int num3;
	Texture star;

	protected EndState(GameStateManager gsm, int time, int limitfloor, boolean freedommode ,int levelCheck) {
		super(gsm);
		// TODO Auto-generated constructor stub

		endscreen = new Texture("bgendgame.jpg");
		retry = new Button("retry.png",240, 175, 120, 100);
		toSelect = new Button("baackmenu.png", 260, 75, 80, 80);
		this.time = time;
		this.limitfloor = limitfloor;
		this.freedommode = freedommode;
		this.levelCheck = levelCheck;
		Gdx.input.setInputProcessor(this);

		allscore = EndState.getScore();
		System.out.println("getscore : "+allscore);

		num1 = (allscore%10);
		num2 = (allscore%100)/10;
		num3 = allscore/100;
		scorestage = new Stage();
		score1 = new Score(num1,150,350,100,170);
		score2 = new Score(num2,250,350,100,170);
		score3 = new Score(num3,350,350,100,170);
		scorestage.addActor(score1);
		scorestage.addActor(score2);
		scorestage.addActor(score3);

		buttonstage = new Stage();
		buttonstage.addActor(retry);
		buttonstage.addActor(toSelect);
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
		sb.setProjectionMatrix(cam.combined);
		sb.draw(endscreen, 0, 0, 600, 800);
		sb.end();
		if(isFreedommode()){
			scorestage.draw();
		}
		else {
			sb.begin();
			sb.draw(star, 150, 350, 300, 170);
			sb.end();
		}
		//sb.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
		buttonstage.draw();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		endscreen.dispose();
		retry.dispose();
		toSelect.dispose();
		buttonstage.dispose();
		star.dispose();
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
		System.out.println("Screen clicked");
		if (button == Input.Buttons.LEFT) {
			if(retry.click(screenX, screenY)){
//				System.out.println("Retry button clicked");
				gsm.set(new Playstate(gsm, this.time, this.limitfloor, this.freedommode, this.levelCheck));
			}else
			if(toSelect.click(screenX, screenY)) {
//				System.out.println("toSelect button clicked");
				gsm.set(new SeclectionState(gsm));
			}
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
		if (retry.click(screenX,screenY)){
			retry = new Button("retrydown.png",240, 175, 120, 100);
			toSelect = new Button("baackmenu.png", 260, 75, 80, 80);
			buttonstage.addActor(retry);
			buttonstage.addActor(toSelect);
		}
		else if (toSelect.click(screenX,screenY)){
			retry = new Button("retry.png",240, 175, 120, 100);
			toSelect = new Button("baackmenudown	.png", 260, 75, 80, 80);
			buttonstage.addActor(retry);
			buttonstage.addActor(toSelect);
		}
		else{
			retry = new Button("retry.png",240, 175, 120, 100);
			toSelect = new Button("baackmenu.png", 260, 75, 80, 80);
			buttonstage.addActor(retry);
			buttonstage.addActor(toSelect);
		}

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}