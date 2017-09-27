package hanabi.game.Objects;


import com.badlogic.gdx.graphics.Texture;
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
        setTexture(new Texture("images/BlueBackground.png"));
        setPosition(50,50);
        setSize(30,30);
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

    }

    @Override
    public void dispose() {
        super.dispose();
        if(getTexture()!=null)
        {
            getTexture().dispose();
        }
    }
}
