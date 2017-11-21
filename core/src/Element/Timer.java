package Element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Timer extends Actor {

	public boolean check = false;
	Texture bar;
	Texture minBar;
	Texture midBar;
	Texture fullBar;
	public float x;
	public float y;
	public float w;
	public float fixw;// final w use for calculate %.
	public float h;
	public float timer;// final time.
	public float time;
	public float mid = 150;
	public float min = 50;
	public boolean end = true;

	public Timer(float time) {
		bar = new Texture("fulltimebar.png");
		minBar = new Texture("mintimebar.png");
		midBar = new Texture("medtimebar.png");
		fullBar = new Texture("fulltimebar.png");
		this.x = 82;
		this.y = 725;
		this.w = 500;
		this.h = 45;
		this.fixw = this.w;
		this.time = time;
		this.timer = time;
	}

	public void draw(Batch batch, float alpha) {
		if (check == true) {
			time -= Gdx.graphics.getDeltaTime();
			w = (time / timer) * fixw; // find % of bar.
			if (w <= min) {
				bar = minBar;// Red bar.
				// System.out.println("red : "+time);
			} else if (w <= mid) {
				bar = midBar;// Yellow bar.
				// System.out.println("Yello : "+time);
			} else if (w <= fixw) {
				bar = fullBar;// Green bar.
			}
			if (w >= fixw) { // if bar over maximum.
				w = fixw;
				time = timer;
			}
			if (time <= 0) { // Time out.
				check = false;
				end = false;
			}
		}
		batch.draw(bar, x, y, w, h);
	}

	public float getTime() {
		return time;
	}

	public void setCheck(boolean x) {
		check = x;
	}

	public boolean isEnd() {
		return end;
	}

	public void dispose() {
		bar.dispose();
	}
}
