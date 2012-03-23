package bunny.entity;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
 
import bunny.game.Direction;
import bunny.component.Component;
import bunny.component.render.RenderComponent;


public class Entity {
	/*************
	 * INSTANCES
	 *************/
    String id; // id
 
    // MOVING RELATED VARIABLES
    protected Vector2f position;
    protected boolean[][] blocked;
	protected static final int SIZE = 75;
	
// to use for tile to tile movement. commented out for now.
//	private boolean movingOnPath = false;

	// SPRITE ANIMATION RELATED VARIABLES
    int [] duration = {100, 100, 100, 100, 100, 100};
    
	Image upStrip;
	Image downStrip;
	Image sideStrip;

	private Image [] movementUp;
	private Animation up;
	
	private Image [] movementDown;
	private Animation down;
	
	private Image [] movementLeft;
	private Animation left;
	
	private Image [] movementRight;
	private Animation right; 

	private Animation sprite = null;
	protected Direction direction;
	
	// COMPONENTS
    RenderComponent renderComponent = null;
    ArrayList<Component> components = null;
 
    // CONSTRUCTOR
    public Entity(String id)
    {
        this.id = id;
 
        components = new ArrayList<Component>();
 
        position = new Vector2f(0,0);
        
    	upStrip = null;
    	downStrip = null;
    	sideStrip = null;

    	movementUp = new Image[6];
    	up = null;
    	
    	movementDown = new Image[6];
    	down = null;
    	
    	movementLeft = new Image[6];
    	left = null;
    	
    	movementRight = new Image[6];
    	right = null;
    	
        sprite = right;
        direction = Direction.RIGHT;
        
// to use for tile to tile movement. commented out for now.        
//        movingOnPath = false;

    }
 
    // ADD COMPONENTS
    public void AddComponent(Component component)
    {
        if(RenderComponent.class.isInstance(component))
            renderComponent = (RenderComponent)component;
 
        component.setOwnerEntity(this);
        components.add(component);
    }

    /********************************
     * GETS FOR OUR MANY INSTANCES
     ********************************/
    public Component getComponent(String id)
    {
        for(Component comp : components)
        {
        	if ( comp.getId().equalsIgnoreCase(id) )
	        return comp;
        }
 
        return null;
    }
 
    public Vector2f getPosition()
    {
    	return position;
    }
 
    public Animation getSprite()
    {
    	return sprite;
    }
    
    public Direction getDirection()
    {
    	return direction;
    }
 
    public String getId()
    {
    	return id;
    }

	public boolean isBlocked(float x, float y)
	{
	    int xBlock = (int)x / SIZE;
	    int yBlock = (int)y / SIZE;
	    return blocked[xBlock][yBlock];
	}
	
// to use for tile to tile movement. commented out for now.
//	public boolean getMoving()
//	{
//		return movingOnPath;
//	}
	
	/********************************
	 * SET FUNCTIONS FOR INSTANCES
	 ********************************/
    public void setImages(String upD, String downD, String sideD, Color t)
    {
    	// load images and set background transparent
    	try {
			upStrip = new Image (upD, t); 
			downStrip = new Image (downD, t);
	    	sideStrip = new Image (sideD, t);
		} catch (SlickException e) {
			e.printStackTrace();
		}
    	
    	// from the sprite sheet get the right sub images in an array
    	for (int i = 0; i < 6; i++)
    	{
    		movementUp[i] = upStrip.getSubImage((75*i), 0, 75, 75);
    		movementDown[i] = downStrip.getSubImage((75*i), 0, 75, 75);
    		movementLeft[i] = (sideStrip.getSubImage((75*i), 0, 75, 75)).getFlippedCopy(true, false);
    		movementRight[i] = sideStrip.getSubImage((75*i), 0, 75, 75);
    	}
    	
    	// use array images to create animations. each frame will have duration and animations do not autoupdate
    	up = new Animation(movementUp, duration, false);
    	down = new Animation(movementDown, duration, false);
    	left = new Animation(movementLeft, duration, false);
    	right = new Animation(movementRight, duration, false); 
    	
    	sprite = right;
    }
    
    // stores where the entity's movement is/isn't blocked
    public void setBlocked(TiledMap Map)
    {
		blocked = new boolean[Map.getWidth()][Map.getHeight()];
		for (int xAxis=0;xAxis<Map.getWidth(); xAxis++)
		{
			for (int yAxis=0; yAxis < Map.getHeight(); yAxis++) {
				int tileID = Map.getTileId(xAxis, yAxis, 0);
				String value = Map.getTileProperty(tileID, "blocked", "false");
				if ("true".equals(value)) {
					blocked[xAxis][yAxis] = true;
				}
			}
		}
    }
 
    public void setPosition(Vector2f position) 
    {
    	this.position = position;
    }
 
    public void setSprite(Direction change)
    {
    	switch (change) {
    	case UP:
    		if (direction != Direction.UP) {
    			sprite = up;
    			direction = Direction.UP;
    		}
    		break;
    	case DOWN:
    		if (direction != Direction.DOWN) {
    			sprite = down;
    			direction = Direction.DOWN;
    		}
    		break;
    	case LEFT:
    		if (direction != Direction.LEFT) {
    			sprite = left;
    			direction = Direction.LEFT;
    		}
    		break;
    	case RIGHT:
    		if (direction != Direction.RIGHT) {
    			sprite = right;
    			direction = Direction.RIGHT;
    		}
    		break;
    	default:
    		System.out.println("What's going on?!");
    		break;
    	}
    }
    
 // to use for tile to tile movement. commented out for now.
//    public void setMoving(boolean moving)
//    {
//    	movingOnPath = moving;
//    }
 
    /**********
     * UPDATE
     **********/
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        for(Component component : components)
        {
            component.update(gc, sb, delta);
        }
    }
 
    /**********
     * RENDER
     **********/
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr)
    {
        if(renderComponent != null)
            renderComponent.render(gc, sb, gr);
    }
}