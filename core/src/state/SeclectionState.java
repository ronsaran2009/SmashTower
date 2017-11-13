package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

import Element.Button;

public class SeclectionState extends State implements InputProcessor {

    private Texture background;
    Button easy;
    Button normal;
    Button hard;
    Button freedom;
    Stage buttonstage;

    public SeclectionState(GameStateManager gsm) {
        super(gsm);
        Gdx.input.setInputProcessor(this);

        background = new Texture("menu.jpg");
        easy = new Button("easy.png",190, 500,200, 100);
        normal = new Button("normal.png",190, 400, 200, 100);
        hard = new Button("hard.png",190, 300, 200, 100);
        freedom = new Button("freedom.png",190 ,200, 200, 100);
        buttonstage = new Stage();
        buttonstage.addActor(easy);
        buttonstage.addActor(normal);
        buttonstage.addActor(hard);
        buttonstage.addActor(freedom);


    }
    @Override
    public void handleinput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

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
        System.out.println("input!!!!!!");
        if (button == Input.Buttons.LEFT)
            if(easy.click(screenX, screenY)){
                gsm.set(new Playstate(gsm,10,30, false));
                System.out.println("EASY");
            }
            else if (normal.click(screenX, screenY)){
                gsm.set(new Playstate(gsm,10,50, false));
                System.out.println("NORMAL");
            }
            else if (hard.click(screenX, screenY)){
                gsm.set(new Playstate(gsm,10,70, false));
                System.out.println("HARD");
            }
            else if (freedom.click(screenX, screenY)){
                gsm.set(new Playstate(gsm,10,1000, true));
                System.out.println("FREEDOM");
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
