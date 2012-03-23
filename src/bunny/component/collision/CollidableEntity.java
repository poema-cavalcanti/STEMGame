package bunny.component.collision;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import bunny.entity.Entity;
import bunny.entity.EntityType;
import bunny.game.Direction;
import bunny.component.Component;

public class CollidableEntity extends Component{
	String id;
	private Shape collisionShape;
	private EntityType collisionType;

	/*****************
	 * GET FUNCTIONS
	 *****************/
	
	public Shape getNormalCollisionShape() {
		return collisionShape;
	}
	
	public Shape getCollisionShape() {
		return collisionShape.transform(Transform.createTranslateTransform(owner.getPosition().x, owner.getPosition().y));
	}
	
	public EntityType getCollisionType() {
		return collisionType;
	}
	
	public Entity getEntity() {
		return owner;
	}

	/*****************
	 * SET FUNCTIONS
	 *****************/

	public void setCollidableEntity(EntityType type) {
		Vector2f position = new Vector2f(owner.getPosition());
		Shape shape = new Rectangle(position.x, position.y, 75, 75);
		collisionShape = shape;
		collisionType = type;
	}
	
	public void setCollisionShape(Shape collisionShape)
	{
		this.collisionShape = collisionShape;
	}
	
	public boolean isCollidingWith(CollidableEntity collidable){
		return 		this.getCollisionShape().intersects(collidable.getCollisionShape());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		// TODO Auto-generated method stub
		
	}
	
}