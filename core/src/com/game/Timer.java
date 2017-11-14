package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Timer extends Actor{
    boolean check = false;
    Texture bar;
    float x ;
    float y ;
    float w ;
    float fixw;// final w use for calculate %.
    float h ;
    float timer;// final time.
    float time;
    float mid = 150;
    float min = 50;
    boolean end = true;

    public Timer (float time){
        bar = new Texture("fulltimebar.png");
        this.x = 50;
        this.y = 750;
        this.w = 500;
        this.h = 25;
        this.fixw = this.w;
        this.time = time;
        this.timer = time;


    }
    public void draw(Batch batch, float alpha){
        if (check == true){
            time -= Gdx.graphics.getDeltaTime();
            w = (time/timer)*fixw; // find % of bar.

            if (w <= min) {
                bar = new Texture("mintimebar.png");// Red bar.
            }
            else if (w <= mid){
                bar = new Texture("medtimebar.png");// Yellow bar.
            }
            else if (w <= fixw){
                bar = new Texture("fulltimebar.png");// Green bar.
            }
            if (w >= fixw){ // if bar over maximum.
                w = fixw;
                time = timer;
            }

            if (time <= 0){ // Time out.
                check = false;
                end = false;
            }
        }
        batch.draw(bar, x,y, w, h);

    }

    public boolean isEnd() {
        return end;
    }
}
