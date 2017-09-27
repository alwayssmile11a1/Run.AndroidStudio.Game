package hanabi.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hanabi.game.GameManager;
import hanabi.game.Objects.Player;

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
    //world simulate collision, physics, etc.
    World world;

    Player player;

    //this variable helps us to see the virtual shape of our world (virtual shape of all objects for example)
    //this variable should be eliminated when public the game
    private Box2DDebugRenderer b2DebugRenderer;

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


        //----------------OBJECT RELATED VARIABLES------------//
        //initialize world with the gravity of -9.8f
        world = new World(new Vector2(0f,-9.8f),true);

        //initialize player
        player = new Player(world);

        //initialize box2D
        b2DebugRenderer = new Box2DDebugRenderer();

    }

    public void handleInput(float delta)
    {

    }

    public void update(float delta)
    {
        handleInput(delta);

        //update world
        world.step(1/60f,6,2);

        //update player
        player.update(delta);

        //
    }

    @Override
    public void render(float delta) {
        //call update
        update(delta);

        //set camera to be used by this batch
        gameManager.batch.setProjectionMatrix(mainCamera.combined);

        //-----------DRAW-----------------//
        //clear background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw things to batch
        gameManager.batch.begin();

        //backgroundSprite.draw(gameManager.batch);
        player.draw(gameManager.batch);

        //end of draw
        gameManager.batch.end();


        //render box2DDebug
        b2DebugRenderer.render(world,mainCamera.combined);

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

        if(player!=null)
        {
            player.dispose();
        }
    }
}
