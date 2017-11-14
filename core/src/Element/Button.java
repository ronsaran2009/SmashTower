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
    boolean check = false;

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
//              ----Tell everything about mouse input to Debug--------
//				System.out.println(inputX);    
//				System.out.println(inputY);		
//				System.out.println("positionX : "+x);
//				System.out.println("postionY : "+y);
//				System.out.println("Width : "+w);
//				System.out.println("Height : "+h);
//				System.out.println(800-x+w);
//				System.out.println(800-y);
//				System.out.println(800-y-h);
//              -------------------------------------------------------
            }
        }
        return check;
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(button, x, y, w, h);
    }

    public void dispose() {
        button.dispose();;
    }
}
