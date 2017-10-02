package hanabi.game.ControllerSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hanabi.game.GameManager;

/**
 * Created by 2SMILE2 on 02/10/2017.
 */

public class MobileController {

    //manage on-screen buttons
    Stage stage;
    //viewport
    Viewport viewPort;
    //camera
    OrthographicCamera camera;

    boolean leftScreenPressed = false;

    public MobileController(GameManager gameManager)
    {
        //set up
        camera = new OrthographicCamera();
        viewPort = new StretchViewport(GameManager.WORLDWIDTH,GameManager.WORLDHEIGHT, camera);
        stage = new Stage(viewPort,gameManager.batch);
        //set this stage that will receive all touch events
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.left();
        table.addListener(new InputListener()
        {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftScreenPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftScreenPressed = false;
            }
        });

        stage.addActor(table);

    }


    public void draw()
    {
        stage.draw();
    }

    public boolean isLeftScreenPressed()
    {
        return leftScreenPressed;
    }

    public void dispose()
    {

    }

}
