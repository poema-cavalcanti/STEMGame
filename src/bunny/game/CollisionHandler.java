package bunny.game;

import org.newdawn.slick.geom.Vector2f;

import bunny.component.collision.CollidableEntity;
import bunny.entity.EntityType;
import bunny.entity.Entity;

public abstract class CollisionHandler {
	
	private static final int SIZE = 75;
		 
	public abstract EntityType getCollider1Type();
 
	public abstract EntityType getCollider2Type();
 
	public void performCollision(CollidableEntity collidable1, CollidableEntity collidable2) {
		if(!collidable1.isCollidingWith(collidable2)){
			return;
		}
		Direction direction = collidable1.getEntity().getDirection(); 
		
		
		Vector2f position = collidable1.getEntity().getPosition();
		do {
		// move in opposite direction
			switch (direction) {
	    	case UP:
	    		if (!collidable1.getEntity().isBlocked(position.x, position.y + SIZE + 0.2f)) {
	    	    	position.y += 0.2f;
	    	    	collidable1.getEntity().setPosition(position);
	    	    }
	    		break;
	    	case DOWN:
	    		if (!collidable1.getEntity().isBlocked(position.x, position.y - 0.2f)) {
	    	    	position.y -= 0.2f;
	    	    	collidable1.getEntity().setPosition(position);
	    	    }
	    		break;
	    	case LEFT:
	    		if (!collidable1.getEntity().isBlocked(position.x + SIZE + 0.2f, position.y)) {
	    	    	position.y += 0.2f;
	    	    	collidable1.getEntity().setPosition(position);
	    	    }
	    		break;
	    	case RIGHT:
	    		if (!collidable1.getEntity().isBlocked(position.x - 0.2f, position.y)) {
	    	    	position.y -= 0.2f;
	    	    	collidable1.getEntity().setPosition(position);
	    	    }
	    		break;
	    	default:
	    		System.out.println("What's going on?!");
	    		break;
			}
		}while(collidable1.isCollidingWith(collidable2)); 
	}	
}
