package bunny.entity.characters;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

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
	public Bunny bunny;
	Rectangle bounds;
	private Image banner;
	
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
	
	public int getTurnNumber() {
		return turnNumber;
	}
	
	public boolean getTargeted() {
		return targeted;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Image getBanner() {
		return banner;
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
	
	public void setTargeted(boolean target) {
		targeted = target;
	}
	
	public void setBanner(Image image) {
		banner = image;
	}
	
	public void setBounds() {
    	bounds = new Rectangle(getPosition().x, getPosition().y, 75, 75);
    }
    
	public void updateBounds() {
    	bounds.setLocation(getPosition().x, getPosition().y);
    }
	
    public boolean takeDamage(int damage) throws Throwable
    {
    	if(targeted) {
    		healthPoints -= damage;
    		return true;
    	}
    		
    	return false;
    }
    
	public void incrementTurnNumber() {
		turnNumber++;
	}
	
	public void attack(Bunny hero)
	{
		try {
			if (getCurrentState() == AttackState.ATTACKING) {
				if (hero.getNearEnemy()) {
					hero.takeDamage(getAttackValue());
					incrementTurnNumber();
					hero.setAttackState(AttackState.ATTACKING);
				}
			}
			setCurrentState(AttackState.WAITING);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

