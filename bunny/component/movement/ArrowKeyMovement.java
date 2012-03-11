package bunny.component.movement;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
 
import bunny.component.Component;

public class ArrowKeyMovement extends Component {

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
		// 0 - up, 1 - down, 2 - left, 3 - right
		Input input = gc.getInput();
		Vector2f position = owner.getPosition();
		
    	if (input.isKeyDown(Input.KEY_UP))
    	{
    	    owner.setSprite(0);
    	    position.y -= delta * 0.1f;
    	}
    	else if (input.isKeyDown(Input.KEY_DOWN))
    	{
    	    owner.setSprite(1);
    	    position.y += delta * 0.1f;
    	}
    	else if (input.isKeyDown(Input.KEY_LEFT))
    	{
    	    owner.setSprite(2);
    	    position.x -= delta * 0.1f;
    	}
    	else if (input.isKeyDown(Input.KEY_RIGHT))
    	{
    	    owner.setSprite(3);
    	    position.x += delta * 0.1f;
    	}
    	
    	owner.setPosition(position);
	}

}