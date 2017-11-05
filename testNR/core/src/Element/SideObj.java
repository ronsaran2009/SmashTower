package Element;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SideObj extends Actor {
    public Texture SideObj;
    public float x ;
    public float y ;
    public float w ;
    public float h ;
    public int check;
    Rectangle rect;
    public boolean brakedown = false;
    public boolean brakedownround1 ;

    public SideObj(int random){
        if (random == 0) { // left
            SideObj = new Texture("condooL.png");
            this.x = 0;
            this.y = 650;
            this.w = 400;
            this.h = 200;
            this.check = 0;
        }
        else if (random == 1) { // center
            SideObj = new Texture("condo.png");
            this.x = 200;
            this.y = 650;
            this.w = 200;
            this.h = 200;
            this.check = 1;
        }
        else if (random == 2) { // right
            SideObj = new Texture("condooR.png");
            this.x = 200;
            this.y = 650;
            this.w = 400;
            this.h = 200;
            this.check = 2;
        }
        rect = new Rectangle(this.x, this.y, this.w, this.h);
    }
    public void draw(Batch batch, float alpha){
        //y -= 200;
    	if(brakedown == true){
            if (y == 50 ){
                brakedown = false;
                brakedownround1 = false;
            }
            else if (y == 250 ){
                brakedown = false;
                brakedownround1 = false;
            }
            else if (y == 450 ){
                brakedown = false;
                brakedownround1 = false;
            }
            else if (y == 650 ){
                brakedown = false;
                brakedownround1 = false;
            }
            else{
                y -= 25;
                brakedownround1 = true;
            }
        }
        batch.draw(SideObj, x,y, w, h);
        rect.setY(y);
    }
    
    public boolean isBrakedownround1() {
        return brakedownround1;
    }
    
    public void setBreakdown() {
    	
    }
    
    public void dispose() {
    	SideObj.dispose();
    }
}
