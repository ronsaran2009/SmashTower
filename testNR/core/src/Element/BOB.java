package Element;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BOB extends Actor {
    Texture bob;
    float x;
    float y = 50;
    float width = 200;
    float height = 200;
    Rectangle rect;


    public BOB(int check) {
    	if (check == 0){
            bob = new Texture("2.png");
            this.x = 0;
        }
        else if (check == 2){
            bob = new Texture("2.png");
            this.x = 0;
        }
        else{
            bob = new Texture("gravestoneleft.png");
            this.x = 400;
        }
        rect = new Rectangle(this.x, y, 200, 200);
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(bob, x, y, width, height);
        rect.setY(y);
    }
    
    public void setCheck(int check) { // check left or right character.
    	if (check == 0){ //0 is left.
            bob = new Texture("2.png");
            this.x = 0;
        }
        else if (check == 1){//1 is right.
            bob = new Texture("man2rights.png");
            this.x = 400;
        }
        else if (check == 2){//2 is dead left.
            bob = new Texture("gravestoneleft.png");
            this.x = 0;
        }
        else if (check == 3){//3 is dead right.
            bob = new Texture("gravestoneright.png");
            this.x = 400;
        }
        rect = new Rectangle(this.x, y, 200, 200);
    }
    
    public float getx() {
    	return x;
    }
    
    public void dispose() {
    	bob.dispose();
    }
}
