package bunny.component.attack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import bunny.component.Component;
import bunny.entity.Entity;
import bunny.entity.characters.Bunny;
import bunny.entity.characters.BunnyStates;
import bunny.entity.characters.Wolf;
import bunny.game.Direction;

public class HeroAttack extends Component{
	protected Bunny owner;
	private boolean hit;
	
	public HeroAttack (String id) {
		this.id = id;
	}
	
	public void setOwnerEntity(Bunny owner)
    {
    	this.owner = owner;
    }
	
	public boolean getHit ()
	{
		return hit;
	}
	
	public void attack(Wolf wolf)
	{
		try {
			if (owner.getDirection() == Direction.UP || owner.getDirection() == Direction.RIGHT) {
				owner.setSprite(Direction.ATTACK_RIGHT);
			}
			else {
				owner.setSprite(Direction.ATTACK_LEFT);
			}
			wolf.takeDamage(owner.getSwordPower());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Input input = gc.getInput();
    	if (input.isKeyDown(Input.KEY_A))
    	{
    		if(owner.getCurrentState() == BunnyStates.ATTACKING)
    		{
    			owner.startAttack();
	    		if (owner.getNearEnemy())
	    		{
	    			if (owner.getTargetedEnemy() != null)
	    				attack(owner.getTargetedEnemy());
	    		}
    		}
    	}
	}
}
