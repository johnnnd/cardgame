package com.john.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.john.main.Game;
import com.john.main.GameStateManager;
import com.john.main.sprites.Character;
import com.john.main.sprites.Hoop;
import com.john.main.sprites.Platform;

import java.util.ArrayList;

import static com.john.main.sprites.Character.CHAR_OFFSET;

public class PlayState extends State {

    private Texture bg;
    private Character pug;
    private Queue<Hoop> hoops;
    private ArrayList<Platform> platforms;

    public PlayState(GameStateManager gsm){
        super(gsm);
        bg = new Texture("Playbg.png");
        pug = new Character(0, 0); // start pug in middle of screen
        cam.setToOrtho(false, Game.WIDTH, Game.HEIGHT);
        hoops = new Queue<Hoop>();
        platforms = new ArrayList<Platform>();

        hoops.addLast(new Hoop(600));
        hoops.addLast(new Hoop(1200));
        hoops.addLast(new Hoop(1800));

        platforms.add(new Platform(400, 150));



    }
    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            pug.moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            pug.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            pug.jump(platforms.get(0).getPosition());
        }



    }

    @Override
    public void update(float dt) {
        handleInput();
        pug.update(dt);
        cam.update();

        if(hoops.size > 0) {
            if (pug.getPosition().x > hoops.get(0).getPosition().x) // remove hoop from queue after
                hoops.removeFirst();                                // passing it
        }

        if(hoops.size > 0) {
            if (hoops.get(0).collide(pug.getBounds())) { // collision detection for hoops
                gsm.set(new MenuState(gsm));
            }
        }

        if(platforms.get(0).collideSideLeft(pug.getBounds())){
            pug.leftIntercepted();
        }

        if(platforms.get(0).collideSideRight(pug.getBounds())){
            pug.rightIntercepted();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.draw(pug.getTexture(), pug.getPosition().x, pug.getPosition().y);
        for(int i = 0; i < hoops.size; i++) {
            sb.draw(hoops.get(i).getHoopTexture(), hoops.get(i).getPosition().x, 0);
        }
        for(int i = 0; i < platforms.size(); i++) {
            sb.draw(platforms.get(i).getPlatformTexture(), platforms.get(i).getPosition().x,
                    platforms.get(i).getPosition().y);
        }
        cam.position.x = pug.getPosition().x + CHAR_OFFSET;
        sb.end();
    }

    @Override
    public void dispose() {
        pug.dispose();
        bg.dispose();
        for(int i = 0; i < hoops.size; i++) {
            hoops.get(i).dispose();
        }
        for(int i = 0; i < platforms.size(); i++) {
            platforms.get(i).dispose();
        }
    }
}
