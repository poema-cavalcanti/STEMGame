package bunny.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bunny.component.collision.CollidableEntity;
import bunny.game.Direction;
import bunny.entity.EntityType;

public class CollisionManager {
	// the list of objects per type
	private Map<EntityType, List<CollidableEntity>> collidables;
	// the list of collisions per type
	private Map<EntityType, List<EntityType>> collisionsTypes;
	// the list handlers for collisions
	private Map<String, CollisionHandler> collisionHandlers;
	
	public CollisionManager(){
		collidables 		= new HashMap<EntityType, List<CollidableEntity>>() ;
		collisionHandlers 	= new HashMap<String, CollisionHandler>() ;
	}
	
	public void addCollidable(CollidableEntity collidable){ 
		// obtain type
		List<CollidableEntity> collidableList = collidables.get(collidable.getCollisionType());
 
		//if there is no list for this type make one
		if(collidableList == null){
			collidableList = new ArrayList<CollidableEntity>();
			collidables.put(collidable.getCollisionType(), collidableList);
		}
 
		// and an entry to the list
		collidableList.add(collidable);
	}
	
	public void removeCollidable(CollidableEntity collidable){
		// obtain the entry for this type
		List<CollidableEntity> collidableList = collidables.get(collidable.getCollisionType());
 
		// if the entry exists remove the object from the list (if possible)
		if(collidableList != null){
			collidableList.remove(collidable);
		}
	}
	
	public void addHandler(CollisionHandler handler){
		// generate the key
		String key = getKey(handler.getCollider1Type(), handler.getCollider2Type());
 
		// add the handler to the map
		collisionHandlers.put( key, handler );
 
		// add the collision type1 to type 2
		addTypesToCollision(handler.getCollider1Type(), handler.getCollider2Type());
		// add the collision type2 to type 1
		addTypesToCollision(handler.getCollider2Type(), handler.getCollider1Type());
	}
	
	private void addTypesToCollision(EntityType type1, EntityType type2){
		// obtain collision type entry
		List<EntityType> typeCollisions = collisionsTypes.get(type1);
 
		// if there is no entry create one
		if(typeCollisions == null){
			typeCollisions = new ArrayList<EntityType>();
			collisionsTypes.put(type1, typeCollisions);
		}
		// add collision to list
		typeCollisions.add(type2);
	}
	
	public static String getKey(EntityType type, EntityType collidingType){
		// generate key <smaller type>-<bigger type>
		return type+"-"+collidingType; 
	}
 
	public void processCollisions(){
 
		// prepare a set of all keys to collide
		Set<String> allCollisionKeys = new HashSet<String>();
 
		// prepare a list of collisions to handle
		List<CollisionData> collisions = new ArrayList<CollisionData>();
 
		Set<EntityType> types = collisionsTypes.keySet();
 
		// obtain every type for collision
		for(EntityType type : types){
			// obtain for each type the type it collides with
			List<EntityType> collidesWithTypes = collisionsTypes.get(type);
 
			for(EntityType collidingType : collidesWithTypes){
				// if the pair was already treated ignore it else treat it
				if( !allCollisionKeys.contains(getKey(type, collidingType)) ){
					// obtain all object of type
					List<CollidableEntity> collidableForType = collidables.get(type);
					// obtain all object of collidingtype
					List<CollidableEntity> collidableForCollidingType = collidables.get(collidingType);
 
					// for each pair from type1 -> type2 verify if the collision happens 
					for (CollidableEntity collidable : collidableForType ){
						for (CollidableEntity collidesWith : collidableForCollidingType ){
							if(collidable.isCollidingWith(collidesWith)){
								// if collision happens add the collision handler to the array
								CollisionData cd = new CollisionData();
								cd.handler = collisionHandlers.get(getKey(type, collidingType));
								cd.object1 = collidable;
								cd.object2 = collidesWith;
 
								collisions.add(cd);
							}
						}
					}
 
					// set these both types as processed
					allCollisionKeys.add(getKey(type, collidingType));
				}				
			}
		}
 
		// process all collisions
		for(CollisionData cd : collisions){
			cd.handler.performCollision(cd.object1, cd.object2);
		}
	}
 
	class CollisionData{
		public CollisionHandler handler;
		public CollidableEntity object1;
		public CollidableEntity object2;
	}
}

