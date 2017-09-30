package hanabi.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import hanabi.game.GameManager;
import hanabi.game.Objects.Player;
import hanabi.game.WorldCreator.MapCreator;

/**
 * Created by 2SMILE2 on 25/09/2017.
 */

//Game activity mostly here
public class PlayScreen implements Screen{

    //GameManager
    private GameManager gameManager;

    //-----------------VIEW RELATED VARIABLES-----------------//
    //how well we want to see our map
    private Viewport gameViewPort;
    //world width and height
    private float worldWidth;
    private float worldHeight;
    //a camera to view our world
    private OrthographicCamera mainCamera;


    //----------------TEXTURE RELATED VARIABLES------------//
    //the background image
    private Sprite backgroundSprite;


    //----------------OBJECT RELATED VARIABLES------------//
    //world simulate collision, physics, etc.
    private World world;

    private Player player;


    //this variable helps us to see the virtual shape of our world (virtual shape of all objects for example)
    //this variable should be eliminated when public the game
    private Box2DDebugRenderer b2DebugRenderer;


    //----------------MAP RELATED VARIABLES------------//
    private MapCreator mapCreator;

    RayHandler rayHandler;
    PointLight pointLight;

    public PlayScreen(GameManager gameManager, int V_Width, int V_Height, float PPM)
    {

        //set up constructor variables
        this.gameManager = gameManager;
        this.worldWidth = V_Width/PPM;
        this.worldHeight = V_Height/PPM;

        //clear background color to a specified color
        //Gdx.gl.glClearColor(0,0,0,1f);
        Gdx.gl.glClearColor(0.85f,0.85f,0.85f,0);

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

        //initialize box2DDebugRenderer
        b2DebugRenderer = new Box2DDebugRenderer();

        //initialize player
        player = new Player(world);
//        grounds = new Array<Ground>();
//        //ground
//        for (int i=1;i<20;i++)
//        {
//            grounds.add(new Ground(world,
//                    MathUtils.random(100f,200f) + 200*i, MathUtils.random(100f,300f),MathUtils.random(150f,250f),
//                    MathUtils.random(15f,30f),MathUtils.random(0.1f,2f)));
//        }


        //----------------MAP RELATED VARIABLES------------//
       mapCreator = new MapCreator(world,"maps/Map.tmx");

        rayHandler = new RayHandler(world);
        //light = new PointLight();
        //light.

        pointLight = new PointLight(rayHandler,500,new Color(0.84f,0.84f,0.84f,0.84f),100,4f,4f);
        pointLight.setSoftnessLength(40f);
        //System.out.print(pointLight.getSoftShadowLength());


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

//        for(Ground ground:grounds)
//        {
//            ground.update(delta);
//        }

        //update camera to follow thí player
        mainCamera.position.x = player.getBody().getPosition().x + 1;
        mainCamera.update();

        mapCreator.update(mainCamera);

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

        mapCreator.renderMap();

        //draw things to batch
        gameManager.batch.begin();

        //backgroundSprite.draw(gameManager.batch);
        player.draw(gameManager.batch);

//        for(Ground ground:grounds)
//        {
//            ground.draw(gameManager.batch);
//        }

        //end of draw
        gameManager.batch.end();



        //render box2DDebug
        b2DebugRenderer.render(world,mainCamera.combined);

        rayHandler.setCombinedMatrix(mainCamera);
        rayHandler.updateAndRender();
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

//        if(grounds!=null)
//        {
//            for(Ground ground:grounds)
//            {
//                ground.dispose();
//            }
//        }

        mapCreator.dispose();

        rayHandler.dispose();
    }
}
