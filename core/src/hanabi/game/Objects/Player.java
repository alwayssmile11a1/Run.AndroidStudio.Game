package hanabi.game.Objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by 2SMILE2 on 27/09/2017.
 */

public class Player extends Object{

    public static final short PLAYER_BIT = 2;

    //put this here to easier set these variable
    float leftLinearVelocity = -1.5f;
    float rightLinearVelocity = 1.5f;
    float upLinearVelocity = 4f;

    public Player(World world) {
        super(world);

        //set Texture
        setTexture(new Texture("images/WhiteRectangle.png"));
        setColor(0f,0.4f,1f,1f);

        //set Position
        setPosition(200,200);

        //set Size
        setSize(30f,30f);

        usePixelPerMeter();

        //set this to rotate object in the center
        setOriginCenter();

        //defineObject
        defineObject();
    }


    @Override
    protected void defineObject() {
        //body definition
        BodyDef bDef = new BodyDef();
        bDef.position.set(this.getX()+this.getWidth()/2,this.getY()+this.getHeight()/2);
        bDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bDef);

        //create the shape of body
        FixtureDef fDef = new FixtureDef();
        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(this.getWidth()/2,this.getHeight()/2);
        fDef.shape = bodyShape;
        fDef.filter.categoryBits = PLAYER_BIT;
        fDef.filter.maskBits = Ground.GROUND_BIT;
        fDef.density = 2f;
        body.createFixture(fDef).setUserData(this);

    }

    @Override
    public void handleInput(float dt) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.W))
        {
            body.setLinearVelocity(body.getLinearVelocity().x,upLinearVelocity);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            body.setLinearVelocity(rightLinearVelocity,body.getLinearVelocity().y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            body.setLinearVelocity(leftLinearVelocity,body.getLinearVelocity().y);
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            startRewinding();
        }
        else
        {
            stopRewinding();
        }

    }

    @Override
    public void update(float dt) {

        super.update(dt);

        handleInput(dt);

        //update texture position
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);

        //rotate box2d
        body.setAngularVelocity(-body.getLinearVelocity().x * 3);


        setRotation(body.getAngle() * MathUtils.radiansToDegrees);

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
