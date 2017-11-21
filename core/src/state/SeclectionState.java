package state;

import Element.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

public class SeclectionState extends State implements InputProcessor {

	private Texture background;
	Button easy;
	Button normal;
	Button hard;
	Button freedom;
	Button how_to;
	Stage buttonstage;
	public static Sound click;

	public SeclectionState(GameStateManager gsm) {
		super(gsm);
		Gdx.input.setInputProcessor(this);
		click = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		background = new Texture("menu.jpg");
		easy = new Button("easy.png", 190, 500, 224, 112);
		normal = new Button("normal.png", 190, 375, 224, 112);
		hard = new Button("hard.png", 190, 250, 224, 112);
		freedom = new Button("freedom.png", 190, 125, 224, 112);
		how_to = new Button("howtoplay.png", 202, 25, 206, 88);
		buttonstage = new Stage();
		buttonstage.addActor(easy);
		buttonstage.addActor(normal);
		buttonstage.addActor(hard);
		buttonstage.addActor(freedom);
		buttonstage.addActor(how_to);
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
		easy.dispose();
		normal.dispose();
		hard.dispose();
		freedom.dispose();
		how_to.dispose();
		buttonstage.clear();
		buttonstage.dispose();
		System.out.println("SeclectionState Disposed");
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
		click.play();
		if (button == Input.Buttons.LEFT)
			if (easy.click(screenX, screenY)) {
				gsm.set(new Playstate(gsm, 10, 20, false, 0));
				System.out.println("EASY");
			} else if (normal.click(screenX, screenY)) {
				gsm.set(new Playstate(gsm, 10, 30, false, 1));
				System.out.println("NORMAL");
			} else if (hard.click(screenX, screenY)) {
				gsm.set(new Playstate(gsm, 10, 40, false, 2));
				System.out.println("HARD");
			} else if (freedom.click(screenX, screenY)) {
				gsm.set(new Playstate(gsm, 10, 1000, true, 3));
				System.out.println("FREEDOM");
			} else if (how_to.click(screenX, screenY)) {
				gsm.set(new Howtostate(gsm));
				System.out.println("How to");
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
		if (easy.click(screenX, screenY)) {
			easy = new Button("easydown.png", 190, 500, 224, 112);
			normal = new Button("normal.png", 190, 375, 224, 112);
			hard = new Button("hard.png", 190, 250, 224, 112);
			freedom = new Button("freedom.png", 190, 125, 224, 112);
			how_to = new Button("howtoplay.png", 202, 25, 206, 88);
			buttonstage.clear();
			buttonstage.addActor(easy);
			buttonstage.addActor(normal);
			buttonstage.addActor(hard);
			buttonstage.addActor(freedom);
			buttonstage.addActor(how_to);
		} else if (normal.click(screenX, screenY)) {
			easy = new Button("easy.png", 190, 500, 224, 112);
			normal = new Button("normaldown.png", 190, 375, 224, 112);
			hard = new Button("hard.png", 190, 250, 224, 112);
			freedom = new Button("freedom.png", 190, 125, 224, 112);
			how_to = new Button("howtoplay.png", 202, 25, 206, 88);
			buttonstage.clear();
			buttonstage.addActor(easy);
			buttonstage.addActor(normal);
			buttonstage.addActor(hard);
			buttonstage.addActor(freedom);
			buttonstage.addActor(how_to);
		} else if (hard.click(screenX, screenY)) {
			easy = new Button("easy.png", 190, 500, 224, 112);
			normal = new Button("normal.png", 190, 375, 224, 112);
			hard = new Button("harddown.png", 190, 250, 224, 112);
			freedom = new Button("freedom.png", 190, 125, 224, 112);
			how_to = new Button("howtoplay.png", 202, 25, 206, 88);
			buttonstage.clear();
			buttonstage.addActor(easy);
			buttonstage.addActor(normal);
			buttonstage.addActor(hard);
			buttonstage.addActor(freedom);
			buttonstage.addActor(how_to);
		} else if (freedom.click(screenX, screenY)) {
			easy = new Button("easy.png", 190, 500, 224, 112);
			normal = new Button("normal.png", 190, 375, 224, 112);
			hard = new Button("hard.png", 190, 250, 224, 112);
			freedom = new Button("freedomdown.png", 190, 125, 224, 112);
			how_to = new Button("howtoplay.png", 202, 25, 206, 88);
			buttonstage.clear();
			buttonstage.addActor(easy);
			buttonstage.addActor(normal);
			buttonstage.addActor(hard);
			buttonstage.addActor(freedom);
			buttonstage.addActor(how_to);
		} else if (how_to.click(screenX, screenY)) {
			easy = new Button("easy.png", 190, 500, 224, 112);
			normal = new Button("normal.png", 190, 375, 224, 112);
			hard = new Button("hard.png", 190, 250, 224, 112);
			freedom = new Button("freedom.png", 190, 125, 224, 112);
			how_to = new Button("howtoplaybk.png", 202, 25, 206, 88);
			buttonstage.clear();
			buttonstage.addActor(easy);
			buttonstage.addActor(normal);
			buttonstage.addActor(hard);
			buttonstage.addActor(freedom);
			buttonstage.addActor(how_to);
		} else {
			easy = new Button("easy.png", 190, 500, 224, 112);
			normal = new Button("normal.png", 190, 375, 224, 112);
			hard = new Button("hard.png", 190, 250, 224, 112);
			freedom = new Button("freedom.png", 190, 125, 224, 112);
			how_to = new Button("howtoplay.png", 202, 25, 206, 88);
			buttonstage.clear();
			buttonstage.addActor(easy);
			buttonstage.addActor(normal);
			buttonstage.addActor(hard);
			buttonstage.addActor(freedom);
			buttonstage.addActor(how_to);
		}
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
