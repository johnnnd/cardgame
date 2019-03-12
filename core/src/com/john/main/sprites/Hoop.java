package com.john.main.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Rectangle;

public class Hoop {
    private Vector2 position;
    private Rectangle bounds;
    private Texture hoop;

    public Hoop(int x){
        position = new Vector2(x, 0);
        hoop = new Texture("hoop.png");

        bounds = new Rectangle(position.x, 0, hoop.getWidth(), hoop.getHeight());
    }

    public boolean collide(Rectangle player){
        return player.overlaps(bounds);
    }

    public Texture getHoopTexture(){
        return hoop;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void dispose(){
        hoop.dispose();
    }
}

