package bunny.component.movement;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
 
import bunny.entity.characters.Bunny;
import bunny.game.Direction;
import bunny.component.Component;

public class ArrowKeyMovement extends Component {
	protected Bunny owner;
	private static final int SIZE = 75;
	float direction;
	float speed;
 
	public ArrowKeyMovement( String id )
	{
		this.id = id;
	}
 
	public float getSpeed()
	{
		return speed;
	}
 
	public float getDirection()
	{
		return direction;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta)
	{
		Input input = gc.getInput();
		Vector2f position = owner.getPosition();
    	if (input.isKeyDown(Input.KEY_UP))
    	{
    	    owner.setSprite(Direction.UP);
    	    if (owner.getSprite().isStopped()) {
    	    	owner.getSprite().start();
    	    }
    	    if (!owner.isBlocked(position.x, position.y - delta * 0.1f)) {
    	    	position.y -= delta * 0.1f;
    	    }
    	    owner.getSprite().update(delta);
    	}
    	else if (input.isKeyDown(Input.KEY_DOWN))
    	{
    	    owner.setSprite(Direction.DOWN);
    	    if (owner.getSprite().isStopped()) {
    	    	owner.getSprite().start();
    	    }
    	    if (!owner.isBlocked(position.x, position.y + SIZE + delta * 0.1f)) {
    	    	position.y += delta * 0.1f;
    	    }
    	    owner.getSprite().update(delta);
    	}
    	else if (input.isKeyDown(Input.KEY_LEFT))
    	{
    	    owner.setSprite(Direction.LEFT);
    	    if (owner.getSprite().isStopped()) {
    	    	owner.getSprite().start();
    	    }
    	    if (!owner.isBlocked(position.x - delta * 0.1f, position.y)) {
    	    	position.x -= delta * 0.1f;
    	    }
    	    owner.getSprite().update(delta);
    	}
    	else if (input.isKeyDown(Input.KEY_RIGHT))
    	{
    	    owner.setSprite(Direction.RIGHT);
    	    if (owner.getSprite().isStopped()) {
    	    	owner.getSprite().start();
    	    }
    	    if (!owner.isBlocked(position.x + SIZE + delta * 0.1f, position.y)) {
    	    	position.x += delta * 0.1f;
    	    }
    	    owner.getSprite().update(delta);
    	}
   
    	owner.setPosition(position);
	}

}
