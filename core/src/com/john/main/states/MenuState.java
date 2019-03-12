package com.john.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.john.main.Game;
import com.john.main.GameStateManager;

public class MenuState extends State {
    private Texture bg;

    public MenuState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, Game.WIDTH, Game.HEIGHT);
        bg = new Texture("whitebackground.png");

    }
    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}
