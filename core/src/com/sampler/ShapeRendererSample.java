package com.sampler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

public class ShapeRendererSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ShapeRendererSample.class);

    private static final float WORLD_HEIGHT = 20f;
    private static final float WORLD_WIDTH = 40f;


    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private boolean drawGrid = true;
    private boolean drawCircles = true;
    private boolean drawRectangles = true;
    private boolean drawPoints = true;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        renderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void resize(int width, int height) {
        // not centering our camera
        viewport.update(width, height);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        renderer.setProjectionMatrix(camera.combined);

        if(drawGrid){
            drawGrid();
        }
        if(drawCircles){
            drawCircles();
        }
        if(drawRectangles){
            drawRectangles();
        }
        if(drawPoints){
            drawPoints();
        }
    }

    public void drawGrid(){
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);

        int worldWidth = (int) WORLD_WIDTH;
        int worldHeight = (int) WORLD_HEIGHT;

        for(int x = - worldWidth ; x < worldWidth ; x++ ){
            renderer.line(x,-worldHeight,x, worldHeight);
        }

        for(int y = - worldHeight ; y < worldHeight ; y++ ){
            renderer.line(-worldHeight, y,worldHeight, y);
        }


        renderer.setColor(Color.RED);
        // draw hori line
        renderer.line(-worldWidth,0,worldWidth,0);
        // draw verti line
        renderer.line(0,-worldHeight,0,worldHeight);

        renderer.end();



    }
    public void drawCircles(){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GREEN);
        renderer.circle(2,2,2,30);

        renderer.circle(-5,-5,1);

        renderer.end();
    }
    public void drawRectangles(){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(-8,4,4,2);
        renderer.rect(-11,3,1,5);

        renderer.end();
    }
    public void drawPoints(){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.MAGENTA);

        renderer.point(-5,0,0);
        renderer.point(5,-3,0);
        renderer.point(8,6,1);

        renderer.end();


        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.x(-10,0,0.25f);

        renderer.end();
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.G){
            drawGrid =! drawGrid;
        }
        if(keycode == Input.Keys.C){
            drawCircles =! drawCircles;
        }
        if(keycode == Input.Keys.R){
            drawRectangles =! drawRectangles;
        }
        if(keycode == Input.Keys.P){
            drawPoints =! drawPoints;
        }

        return true;
    }

    @Override
    public void dispose() {

    }
}