package hanabi.game.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by 2SMILE2 on 25/09/2017.
 */

//parent class of any other objects, such as player, enemy, etc.
public class Object extends Sprite {

    protected World world;
    protected Body body;

    public Object(World world) {
        this.world = world;
        defineObject();

    }

    protected void defineObject()
    {

    }


    public void handleInput(float dt)
    {

    }

    public void update(float dt)
    {
        handleInput(dt);
    }

    public Body getBody()
    {
        return body;
    }

    public void dispose()
    {

    }


}
