package com.john.main.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import javax.sound.midi.SysexMessage;


public class Character {
    private Vector3 position;
    private Vector3 velocity;

    public static int MOVEMENT = 10;
    public static final int CHAR_OFFSET = 365;
    private static final int GRAVITY = -50;
    private boolean leftIntercepted;
    private boolean rightIntercepted;
    private boolean topIntercepted;
    private boolean botIntercepted;


    private Texture pug;
    private Rectangle bounds;

    public Character(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        pug = new Texture("pug.png");

        leftIntercepted = false;
        rightIntercepted = false;
        bounds = new Rectangle((int) position.x, (int) position.y, pug.getWidth(), pug.getHeight());
    }

    public void update(float dt) {
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(0, velocity.y, 0);

        velocity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);
        if (position.y < 0) {
            position.y = 0;
        }
    }

    public Texture getTexture() {
        return pug;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void leftIntercepted() {
        leftIntercepted = true;
    }

    public void rightIntercepted() {
        rightIntercepted = true;
    }

    public void topIntercepted() {
        topIntercepted = true;
    }

    public void botIntercepted() {
        botIntercepted = true;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void moveRight() {
        if (!leftIntercepted) {
            position.x += MOVEMENT;
        }
        rightIntercepted = false;
    }

    public void moveLeft() {
        if (!rightIntercepted) {
            position.x -= MOVEMENT;
        }
        leftIntercepted = false;
    }

    public void jump(Vector2 posPlat) {
        bounds.y += 500;
        if(bounds.y > posPlat.y && bounds.x > posPlat.x){
            bounds.y = (int) position.y;
            if(position.y == 0){
                velocity.y += posPlat.y;
            }
        }
        else {
            if (position.y == 0) {
                velocity.y += 500;
            }
        }
    }

    public void fakeJump(Vector2 posPlat){
        velocity.y += posPlat.y;
    }
        public void dispose () {
            pug.dispose();
        }
    }

