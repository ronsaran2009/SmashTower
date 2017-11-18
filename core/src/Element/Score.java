package Element;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Score extends Actor {
    Texture score ;
    float x ;
    float y ;
    float w ;
    float h ;
    String name;
    String pic[] = new String[10];//{"num0.png", "num1.png", "num2.png", "num3.png", "num4.png", "num5.png", "num6.png", "num7.png", "num8.png", "num9.png"};
    
    public Score(int num , float x, float y, float w, float h){

        pic[0] = "num0.png";
        pic[1] = "num1.png";
        pic[2] = "num2.png";
        pic[3] = "num3.png";
        pic[4] = "num4.png";
        pic[5] = "num5.png";
        pic[6] = "num6.png";
        pic[7] = "num7.png";
        pic[8] = "num8.png";
        pic[9] = "num9.png";

        if (num == 0){
            name = pic[0];
            score = new Texture(name);
        }
        else if (num == 1){
            name = pic[1];
            score = new Texture(name);
        }
        else if (num == 2){
            name = pic[2];
            score = new Texture(name);
        }
        else if (num == 3){
            name = pic[3];
            score = new Texture(name);
        }
        else if (num == 4){
            name = pic[4];
            score = new Texture(name);
        }
        else if (num == 5){
            name = pic[5];
            score = new Texture(name);
        }
        else if (num == 6){
            name = pic[6];
            score = new Texture(name);
        }
        else if (num == 7){
            name = pic[7];
            score = new Texture(name);
        }
        else if (num == 8){
            name = pic[8];
            score = new Texture(name);
        }
        else if (num == 9){
            name = pic[9];
            score = new Texture(name);
        }

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(Batch batch, float alpha) {
        batch.draw(score, x, y, w, h);
    }
    public void dispose() {
        score.dispose();
    }
}
