package Element;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Button extends Actor {
	public Texture button;
	public float x;
	public float y;
	public float w;
	public float h;
	public boolean check = false;

	public Button(String name, float x, float y, float w, float h) {
		button = new Texture(name);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public boolean click(float inputX, float inputY) {
		if (inputX >= x && inputX <= x + w) {
			if (inputY >= 800 - y - h && inputY <= 800 - y) {
				check = true;
			}
		}
		return check;
	}

	public void draw(Batch batch, float alpha) {
		batch.draw(button, x, y, w, h);
	}

	public void dispose() {
		button.dispose();
	}
}
