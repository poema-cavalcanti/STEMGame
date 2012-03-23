package bunny.entity.characters;

import bunny.entity.Entity;
import bunny.entity.characters.BunnyStates;
import bunny.entity.characters.Wolf;

public class Bunny extends Entity{

	private int healthPoints;
	private int level;
	private int defense;
	private int swordPower;
	private boolean nearEnemy;
	BunnyStates currentState;
	private Wolf targetedEnemy;
	
	public Bunny(String id) {
		super(id);
		healthPoints = 100;
		defense = 10;
		swordPower = 25;
		level = 0;
		currentState = BunnyStates.WALKING;
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
	
	public Wolf getTargetedEnemy() {
		return targetedEnemy;
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
    
    public void setNearEnemy(boolean near) {
    	nearEnemy = near;
    }
    
    public void setTagetedEnemy(Wolf enemy) {
    	targetedEnemy = enemy;
    }
    
    // INFO HANDLING
    public boolean takeDamage(int damage) {
    	return false;
    }
    
}
