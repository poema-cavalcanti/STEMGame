package bunny.component.attack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import bunny.component.Component;
import bunny.entity.characters.Bunny;
import bunny.entity.characters.BunnyStates;
import bunny.entity.characters.Wolf;

public class HeroAttack extends Component{
	protected Bunny owner;
	private boolean hit;
	
	public HeroAttack (String id) {
		this.id = id;
	}
	
	public boolean getHit ()
	{
		return hit;
	}
	
	public void attack(Wolf wolf)
	{
		try {
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
	    		if (owner.getNearEnemy())
	    		{
	    			attack(owner.getTargetedEnemy());
	    		}
    		}
    	}
	}
}
