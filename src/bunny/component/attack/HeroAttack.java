package bunny.component.attack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import bunny.component.Component;
import bunny.entity.Entity;
import bunny.entity.characters.AttackState;
import bunny.entity.characters.Bunny;
import bunny.entity.characters.BunnyStates;
import bunny.entity.characters.Wolf;
import bunny.game.Direction;

public class HeroAttack extends Component{
	protected Bunny owner;
	
	public HeroAttack (String id) {
		this.id = id;
	}
	
	@Override
	public void setOwnerEntity(Entity owner)
    {
    	this.owner = (Bunny) owner;
    }
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		
		Input input = gc.getInput();
    	if (input.isKeyPressed(Input.KEY_A))
    	{
    		if(owner.getCurrentState() == BunnyStates.ATTACKING)
    		{
	    		if (owner.getNearEnemy())
	    		{
	    			owner.setHit(true);	
	    			owner.attack(owner.enemy);
	    			owner.enemy.setCurrentState(AttackState.ATTACKING);
	    		}
    		}
    	}
		
		owner.setHit(false);
		System.out.println("Bunny health: " + owner.getHealth());
		System.out.println("Bunny state: " + owner.getCurrentState());
		System.out.println("Bunny position: " + owner.getPosition());
	}
}
