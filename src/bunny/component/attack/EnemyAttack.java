package bunny.component.attack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import bunny.component.Component;
import bunny.entity.characters.Bunny;
import bunny.entity.characters.BunnyStates;
import bunny.entity.characters.Wolf;
import bunny.entity.characters.AttackState;

public class EnemyAttack extends Component{
	protected Wolf owner;
	private boolean hit;
	
	public EnemyAttack (String id) {
		this.id = id;
	}
	
	public boolean getHit ()
	{
		return hit;
	}
	
	public void attack(Bunny hero)
	{
		try {
			hero.takeDamage(owner.getAttackValue());
			owner.incrementTurnNumber();
			owner.setCurrentState(AttackState.WAITING);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Input input = gc.getInput();
    	if (input.isKeyDown(Input.KEY_A))
    	{
    		if(owner.getCurrentState() == AttackState.ATTACKING)
    		{
	    		if((owner.getPosition()).distance((owner.getBunny()).getPosition()) <= 100) {
	    			if (owner.getBunny().getCurrentState() != BunnyStates.TALKING)
	    				attack(owner.getBunny());
	    		}
    		}
    	}
	}
}
