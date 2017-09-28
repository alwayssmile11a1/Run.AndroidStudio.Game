package hanabi.game.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import hanabi.game.GameManager;

/**
 * Created by 2SMILE2 on 25/09/2017.
 */

//parent class of any other objects, such as player, enemy, etc.
public class Object extends Sprite {

    protected World world;
    protected Body body;

    public Object(World world) {
        this.world = world;

    }

    //this function resize this object to be used more appropriate with Box2D
    protected void usePixelPerMeter() {

        setPosition(getX() / GameManager.PPM, getY() / GameManager.PPM);
        setSize(getWidth() / GameManager.PPM, getHeight() / GameManager.PPM);

    }


    protected void defineObject()
    {

    }


    public void handleInput(float dt)
    {

    }

    public void update(float dt)
    {


    }

    public Body getBody()
    {
        return body;
    }

    public void dispose()
    {
        if(getTexture()!=null)
        {
            getTexture().dispose();
        }
    }


}
