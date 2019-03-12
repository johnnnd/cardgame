package com.john.main.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Platform {
    private Vector2 position;
    private Texture platform;
    private Rectangle sideBoundsLeft;
    private Rectangle sideBoundsRight;
    private Rectangle topBounds;
    private Rectangle botBounds;

    public Platform(int x, int y){
        position = new Vector2(x, y);
        platform = new Texture("platform.png");

        sideBoundsLeft = new Rectangle(position.x, position.y, platform.getWidth()/10f, platform.getHeight());
        sideBoundsRight = new Rectangle(position.x + platform.getWidth() - platform.getWidth() /10f, position.y, platform.getWidth()/10f, platform.getHeight());
        topBounds = new Rectangle(position.x + 1, position.y + platform.getHeight(), platform.getWidth(), 1);
        botBounds = new Rectangle(position.x + 5, position.y, platform.getWidth(), 1);

    }


    public Texture getPlatformTexture(){
        return platform;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void dispose(){
        platform.dispose();
    }

    public boolean collideSideLeft(Rectangle player){
        return player.overlaps(sideBoundsLeft);
    }
    public boolean collideSideRight(Rectangle player){
        return player.overlaps(sideBoundsRight);
    }
    public boolean collideTop(Rectangle player){
        return player.overlaps(topBounds);
    }
    public boolean collideBot(Rectangle player){
        return player.overlaps(botBounds);
    }
}
