package hanabi.game.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import hanabi.game.GameManager;

/**
 * Created by 2SMILE2 on 25/09/2017.
 */

//parent class of any other objects, such as player, enemy, etc.
public class Object extends Sprite {

    protected World world;
    protected Body body;
    protected Array<Transform> positions;
    protected Array<Vector2> velocities;
    protected boolean isRewinding;

    public Object(World world) {
        this.world = world;
        positions = new Array<Transform>();
        velocities = new Array<Vector2>();
        isRewinding = false;
    }

    //this function resize this object to be used more appropriate with Box2D
    protected void usePixelPerMeter() {

        setPosition(getX() / GameManager.PPM, getY() / GameManager.PPM);
        setSize(getWidth() / GameManager.PPM, getHeight() / GameManager.PPM);

    }

    protected void recordPositions(float dt) {
        if(isRewinding == false) {

            if(positions.size > (int)(5f/dt))
            {
                positions.removeIndex(positions.size-1);
                velocities.removeIndex(velocities.size-1);
            }
            positions.insert(0, new Transform(body.getPosition(), body.getAngle()));
            velocities.insert(0, new Vector2(body.getLinearVelocity()));
        }
    }

    protected void startRewinding () {
        if (positions.size >0) {
            isRewinding = true;
            //body.setType(BodyDef.BodyType.KinematicBody);
            body.setTransform(positions.first().getPosition(), positions.first().getRotation());
            body.setLinearVelocity(velocities.first());
            positions.removeIndex(0);
            velocities.removeIndex(0);
        }
        else
        {
            stopRewinding();
        }
    }

    protected void stopRewinding () {
        isRewinding = false;
        //body.setType(BodyDef.BodyType.DynamicBody);
    }

    protected void defineObject()
    {

    }


    public void handleInput(float dt)
    {

    }

    public void update(float dt)
    {
        recordPositions(dt);
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
