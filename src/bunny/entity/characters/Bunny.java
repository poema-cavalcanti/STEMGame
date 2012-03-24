package bunny.entity.characters;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import bunny.entity.Entity;
import bunny.entity.characters.BunnyStates;
import bunny.entity.characters.Wolf;
import bunny.game.Direction;

public class Bunny extends Entity{

	private int healthPoints;
	private int level;
	private int defense;
	private int swordPower;
	private boolean nearEnemy;
	private BunnyStates currentState;
	private Vector2f targetedEnemy;
	private boolean hit;
	public Wolf enemy;
	Rectangle bounds;
	
	public Bunny(String id) {
		super(id);
		healthPoints = 100;
		defense = 10;
		swordPower = 25;
		level = 0;
		currentState = BunnyStates.WALKING;
		hit = true;
	}
	
	// GET
	public int getHealth() {
		return healthPoints;
	}
	
	public int getDefense()	{
		return defense;
	}
	
	public int getSwordPower() {
		return swordPower;
	}
	
	public int getLevel() {
		return level;
	}
	
	public BunnyStates getCurrentState() {
		return currentState;
	}
	
	public boolean getNearEnemy() {
		return nearEnemy;
	}
	
	public Vector2f getTargetedEnemy() {
		return targetedEnemy;
	}
	
	public boolean getHit() {
		return hit;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	// SET
	public void setHealth(int health) {
    	healthPoints = health;
    }
    
    public void setLevel(int level) {
    	this.level = level;
    }
    
    public void setCurrentState(BunnyStates state) {
    	currentState = state;
    }
    
    public void setNearEnemy(Wolf near) {
    	if (bounds.intersects(near.getBounds())) {
    		setCurrentState(BunnyStates.ATTACKING);
    		nearEnemy = true;
    		return;
    	}
    	else {
    		nearEnemy = false;
    	}
    }
    
    public void toggleNearEnemy() {
    	nearEnemy = false;
    }
    
    public void updateBounds() {
    	bounds.setLocation(getPosition().x, getPosition().y);
    }
    
    public void setTagetedEnemy(Vector2f enemy) {
    	targetedEnemy = enemy;
    }
    
    public void setHit(boolean hit) {
    	this.hit = hit;
    }
    
    public void setBounds() {
    	bounds = new Rectangle(getPosition().x, getPosition().y, 75, 75);
    }
    
    public boolean takeDamage(int damage) {
    	return false;
    }
      
    public void attack(Wolf wolf)
	{
    	wolf.setTargeted(nearEnemy);
    	if (hit) {
			try {
				if (getDirection() == Direction.UP || getDirection() == Direction.RIGHT) {
					setSprite(Direction.ATTACK_RIGHT);
				}
				else {
					setSprite(Direction.ATTACK_LEFT);
				}
				wolf.takeDamage(getSwordPower());
			} catch (Throwable e) {
				e.printStackTrace();
			}
    	}
	}
}
