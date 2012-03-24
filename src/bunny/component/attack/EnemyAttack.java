package bunny.component.attack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import bunny.component.Component;
import bunny.entity.Entity;
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
	
	@Override
	public void setOwnerEntity(Entity owner)
    {
    	this.owner = (Wolf) owner;
    }
	
	public boolean getHit ()
	{
		return hit;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		
//		if (owner.getCurrentState() == AttackState.ATTACKING) {
			owner.attack(owner.bunny);
		}
//	}
}
