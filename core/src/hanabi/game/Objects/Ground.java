package hanabi.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by 2SMILE2 on 28/09/2017.
 */

public class Ground extends Object{

    public static final short GROUND_BIT = 1;

    public Ground(World world) {
        super(world);

        //set Texture
        setTexture(new Texture("images/BlueBackground.png"));

        setPosition(100,100);

        setSize(200f,50f);

        usePixelPerMeter();

        defineObject();
    }

    @Override
    protected void defineObject() {
        //body definition
        BodyDef bDef = new BodyDef();
        bDef.position.set(this.getX()+this.getWidth()/2,this.getY()+this.getHeight()/2);
        bDef.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bDef);

        //create the shape of body
        FixtureDef fDef = new FixtureDef();
        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(this.getWidth()/2,this.getHeight()/2);
        fDef.shape = bodyShape;
        fDef.filter.categoryBits = GROUND_BIT;
        fDef.filter.maskBits = Player.PLAYER_BIT;
        body.createFixture(fDef).setUserData(this);
    }

    @Override
    public void handleInput(float dt) {

    }

    @Override
    public void update(float dt) {
        handleInput(dt);

        //update texture position
        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);

        //body.setAngularVelocity(1f);

    }


    @Override
    public void dispose() {
        super.dispose();
    }
}
