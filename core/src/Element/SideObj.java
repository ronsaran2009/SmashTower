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
    int limitfloor;

    public SideObj(int random){
        if (random == 0) { // left
            SideObj = new Texture("hlv1-2.png");
            this.x = 0;
            this.y = 850;
            this.w = 400;
            this.h = 200;
            this.check = 0;
        }
        else if (random == 1) { // center
            SideObj = new Texture("hlv1-1.png");
            this.x = 200;
            this.y = 850;
            this.w = 200;
            this.h = 200;
            this.check = 1;
        }
        else if (random == 2) { // right
            SideObj = new Texture("hlv1-2 - Copy.png");
            this.x = 200;
            this.y = 850;
            this.w = 400;
            this.h = 200;
            this.check = 2;
        }
        else if (random == 3) { // end
            SideObj = new Texture("hlv4-1.png");
            this.x = 200;
            this.y = 850;
            this.w = 200;
            this.h = 200;
            this.check = 3;
        }
        rect = new Rectangle(this.x, this.y, this.w, this.h);
    }

    public void setLimitfloor(int limitfloor) {
        this.limitfloor = limitfloor;
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
        batch.draw(SideObj, x, y, w, h);
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
