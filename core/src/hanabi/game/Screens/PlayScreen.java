package hanabi.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hanabi.game.GameManager;

/**
 * Created by 2SMILE2 on 25/09/2017.
 */

//Game activity mostly here
public class PlayScreen implements Screen{

    //GameManager
    GameManager gameManager;

    //-----------------VIEW RELATED VARIABLES-----------------//
    //how well we want to see our map
    private Viewport gameViewPort;
    //world width and height
    private int worldWidth;
    private int worldHeight;
    //a camera to view our world
    private OrthographicCamera mainCamera;


    //----------------TEXTURE RELATED VARIABLES------------//
    //the background image
    Sprite backgroundSprite;


    //----------------OBJECT RELATED VARIABLES------------//



    public PlayScreen(GameManager gameManager, int worldWidth, int worldHeight)
    {
        //set up constructor variables
        this.gameManager = gameManager;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;

        //clear background color to a specified color
        Gdx.gl.glClearColor(1f,0.7f,0.5f,0f);

        //-----------------VIEW RELATED VARIABLES-----------------//
        //initialize a new camera
        mainCamera = new OrthographicCamera();
        //initialze gameViewPort
        gameViewPort = new StretchViewport(worldWidth,worldHeight,mainCamera);
        //gameViewPort = new FitViewport(worldWidth,worldHeight,mainCamera);
        //set mainCamera position to the center of gameviewport
        mainCamera.position.set(gameViewPort.getWorldWidth()/2,gameViewPort.getWorldHeight()/2,0);



        //----------------TEXTURE RELATED VARIABLES------------//
        //initialize background example
        backgroundSprite = new Sprite( new Texture("images/BlueBackground.png"));
        backgroundSprite.setSize(30,30);


    }

    public void handleInput(float delta)
    {

    }

    public void update(float delta)
    {
        handleInput(delta);
    }

    @Override
    public void render(float delta) {

        update(delta);

        //clear background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //set camera to be used by this batch
        gameManager.batch.setProjectionMatrix(mainCamera.combined);

        //draw things to batch
        gameManager.batch.begin();

        backgroundSprite.draw(gameManager.batch);

        gameManager.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        //resize viewport if we resize our game world
        gameViewPort.update(width,height);
    }

    @Override
    public void show() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        if(gameManager!=null)
        {
            gameManager.dispose();
        }

        if(backgroundSprite.getTexture()!=null)
        {
            backgroundSprite.getTexture().dispose();
        }

    }
}
