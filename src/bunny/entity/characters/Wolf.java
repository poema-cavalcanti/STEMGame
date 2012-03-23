package bunny.entity.characters;

import bunny.entity.Entity;
import bunny.entity.EntityType;
import bunny.entity.characters.AttackState;
import bunny.entity.characters.Bunny;

public class Wolf extends Entity{

	private EntityType type;
	private int healthPoints;
	private int attackValue;
	private boolean targeted;
	private int turnNumber;
	AttackState currentState;
	private Bunny bunny;
	
	public Wolf(String id) {
		super(id);
		healthPoints = 100;
		targeted = false;
		currentState = AttackState.WAITING;
		turnNumber = 1;
	}
	
	// GET
	public int getHealth() {
		return healthPoints;
	}
	
	public int getAttackValue()	{
		switch (type) {
		case CONST_WOLF:
			attackValue = 10;
			break;
		case LIN_WOLF:
			attackValue = 5 * turnNumber;
			break;
		case QUAD_WOLF:
			attackValue = turnNumber * turnNumber;
			break;
		default:
			System.out.println("The wolf has no attack type.");
			break;
		}
		return attackValue;
	}
	
	public AttackState getCurrentState() {
		return currentState;
	}
	
	public EntityType getType() {
		return type;
	}
	
	public Bunny getBunny() {
		return bunny;
	}
	
	public int getTurnNumber() {
		return turnNumber;
	}
	
	// SET
	public void setHealth(int health) {
    	healthPoints = health;
    }
	
	public void setType(EntityType type) {
		this.type = type;
	}
	
	public void setCurrentState(AttackState state) {
		currentState = state;
	}
	
	public void incrementTurnNumber() {
		turnNumber++;
	}
	
	public void setBunny(Bunny bunny) {
		this.bunny = bunny;
	}
    
    // INFO HANDLING
    public boolean takeDamage(int damage) throws Throwable
    {
    	if(targeted) {
    		healthPoints -= damage;
    		if (healthPoints <= 0)
    			super.finalize();
    		return true;
    	}
    		
    	return false;
    }
    
}

