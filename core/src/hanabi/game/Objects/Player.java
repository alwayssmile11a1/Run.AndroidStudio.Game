package hanabi.game.Objects;


import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by 2SMILE2 on 27/09/2017.
 */

public class Player extends Object{


    public Player(World world) {
        super(world);



    }

    @Override
    protected void defineObject() {
        //body definition
        BodyDef bDef = new BodyDef();
        bDef.position.set(this.getX(),this.getY());
        bDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bDef);

        //create the shape of body
        FixtureDef fDef = new FixtureDef();
        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(this.getWidth()/2,this.getHeight()/2);
        fDef.shape = bodyShape;
        body.createFixture(fDef).setUserData(this);
        
    }

    @Override
    public void handleInput(float dt) {

    }

    @Override
    public void update(float dt) {
        super.update(dt);

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
