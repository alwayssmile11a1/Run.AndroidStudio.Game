package hanabi.game.Objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        //setTexture(new Texture("images/BlueBackground.png"));

        //set Position
        setPosition(200,200);

        //set Size
        setSize(30f,30f);

        usePixelPerMeter();

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
    }

    @Override
    public void update(float dt) {

        handleInput(dt);

        //update texture position
        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);

        body.setAngularVelocity(-body.getLinearVelocity().x*3);

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
