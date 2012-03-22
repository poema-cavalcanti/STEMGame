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
 
import bunny.component.Component;
import bunny.component.render.RenderComponent;
 
public class Entity {
 
    String id;
 
    Vector2f position;
    float scale;

    int [] duration = {100, 100, 100, 100, 100, 100};
	
	/*
	* false variable means do not auto update the animation.
	* By setting it to false animation will update only when
	* the user presses a key.
	*/
    
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
	
	private int directionIndex; // 0 - up, 1 - down, 2 - left, 3 - right
	
    RenderComponent renderComponent = null;
 
    ArrayList<Component> components = null;
 
    public Entity(String id)
    {
        this.id = id;
 
        components = new ArrayList<Component>();
 
        position = new Vector2f(0,0);
        scale = 1;
        
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
        
        directionIndex = 3;
    }
 
    public void AddComponent(Component component)
    {
        if(RenderComponent.class.isInstance(component))
            renderComponent = (RenderComponent)component;
 
        component.setOwnerEntity(this);
        components.add(component);
    }
 
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
 
    public float getScale()
    {
    	return scale;
    }
 
    public Animation getSprite()
    {
    	return sprite;
    }
    
    public int getDirection()
    {
    	return directionIndex;
    }
 
    public String getId()
    {
    	return id;
    }
    
    public void setImages(String upD, String downD, String sideD, Color t)
    {
    	try {
			upStrip = new Image (upD, t);
			downStrip = new Image (downD, t);
	    	sideStrip = new Image (sideD, t);
		} catch (SlickException e) {
			e.printStackTrace();
		}
    	
    	for (int i = 0; i < 6; i++)
    	{
    		movementUp[i] = upStrip.getSubImage((75*i), 0, 75, 75);
    		movementDown[i] = downStrip.getSubImage((75*i), 0, 75, 75);
    		movementLeft[i] = (sideStrip.getSubImage((75*i), 0, 75, 75)).getFlippedCopy(true, false);
    		movementRight[i] = sideStrip.getSubImage((75*i), 0, 75, 75);
    	}
    	
    	for (int i = 0; i<= 5; i++) {
    		System.out.println("Up Anim Frame");
    		System.out.println(movementUp[i]);
    	}
    	up = new Animation(movementUp, duration, false);
    	down = new Animation(movementDown, duration, false);
    	left = new Animation(movementLeft, duration, false);
    	right = new Animation(movementRight, duration, false); 
    	
    	sprite = right;
    }
 
    public void setPosition(Vector2f position) 
    {
    	this.position = position;
    }
 
    public void setSprite(int change) // 0 - up, 1 - down, 2 - left, 3 - right
    {
        if (change == 0) {
        	sprite = up;
        	directionIndex = 0;
        }
        if (change == 1) {
        	sprite = down;
        	directionIndex = 1;
        }
        if (change == 2) {
        	sprite = left;
        	directionIndex = 2;
        }
        if (change == 3) {
        	sprite = right;
        	directionIndex = 3;
        }
    }
 
    public void setScale(float scale) 
    {
    	this.scale = scale;
    }
 
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        for(Component component : components)
        {
            component.update(gc, sb, delta);
        }
    }
 
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr)
    {
        if(renderComponent != null)
            renderComponent.render(gc, sb, gr);
    }
}