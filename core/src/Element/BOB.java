package Element;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BOB extends Actor {
	Texture bob;
	Texture bobLeft;
	Texture bobRight;
	Texture bobHitLeft;
	Texture bobHitRight;
	Texture graveLeft;
	Texture graveRight;
	TextureRegion textureregion;
	TextureAtlas textureatlas;

	public float x;
	public float y = 50;
	private float width = 200;
	private float height = 200;
	Rectangle rect;

	public BOB(int check) {
		bobLeft = new Texture("bobLeft1.png");
		bobRight = new Texture("bobRight1.png");
		bobHitLeft = new Texture("bobLeft2.png");
		bobHitRight = new Texture("bobRight2.png");
		graveLeft = new Texture("gravestoneleft.png");
		graveRight = new Texture("gravestoneright.png");
		if (check == 0) {
			bob = bobLeft;
			this.x = 0;
		} else if (check == 2) {
			bob = bobRight;
			this.x = 0;
		} else {
			bob = graveLeft;
			this.x = 400;
		}
		rect = new Rectangle(this.x, y, 200, 200);
	}

	public void draw(Batch batch, float alpha) {
		batch.draw(bob, x, y, width, height);
		rect.setY(y);
	}

	public void setCheck(int check) { // check left or right character.
		if (check == 0) { // 0 is left.
			bob = bobLeft;
			this.x = 0;
		} else if (check == 1) {// 1 is right.
			bob = bobRight;
			this.x = 400;
		} else if (check == 2) {// 2 is dead left.
			bob = graveLeft;
			this.x = 0;
		} else if (check == 3) {// 3 is dead right.
			bob = graveRight;
			this.x = 400;
		} else if (check == 4) {// 4 is des left.
			bob = new Texture("bobLeft2.png");
			this.x = 0;
			// System.out.println("outL");
		} else if (check == 5) {// 5 is des right.
			bob = new Texture("bobRight2.png");
			this.x = 400;
			// System.out.println("outR");
		}
		rect = new Rectangle(this.x, y, 200, 200);
	}

	public float getx() {
		return x;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	public void dispose() {
		bob.dispose();
	}
}
