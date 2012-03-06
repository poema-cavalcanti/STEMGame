package bunny.game;


import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
 
public class BunnyGame extends BasicGame
{
	private TiledMap grassMap;
	private Animation sprite, up, down, left, right;
	
    public BunnyGame()
    {
        super("Bunny Math Warrior");
    }
 
    @Override // all slick games need an init that sets the initial configurations
    public void init(GameContainer gc) 
			throws SlickException 
	{
    	grassMap = new TiledMap("bunny/data/grassy_tile_map.tmx");
    	int [] duration = {300, 300};
    	
    	/*
    	* false variable means do not auto update the animation.
    	* By setting it to false animation will update only when
    	* the user presses a key.
    	*/
    	Image [] movementUp = {new Image("data/bunny_back.gif"), new Image("data/bunny_back.gif")};
    	up = new Animation(movementUp, duration, false);
    	
    	Image [] movementDown = {new Image("data/bunny_front.gif"), new Image("data/bunny_front.gif")};
    	down = new Animation(movementDown, duration, false);
    	
    	Image [] movementLeft = {new Image("data/bunny_side.gif"), new Image("data/bunny_side.gif")};
    	left = new Animation(movementLeft, duration, false);
    	
    	Image [] movementRight = {new Image("data/bunny_side.gif"), new Image("data/bunny_side.gif")};
    	right = new Animation(movementRight, duration, false); 

    	// Original orientation of the sprite. It will look right.
    	sprite = right; 
    	 
    }
 
    @Override // all slick games need an update that updates the goings on of 
    		  // the game and is where our game logic goes
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
 
    }
    
    // renders what is on screen
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	grassMap.render(0,0);
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new BunnyGame());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}