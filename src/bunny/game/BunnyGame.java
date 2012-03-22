package bunny.game;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import bunny.component.movement.ArrowKeyMovement;
import bunny.component.render.RenderComponent;
import bunny.entity.Entity;
 
public class BunnyGame extends BasicGame
{
	private TiledMap homeMap; 
	private float x = 75f, y = 450f; // used for bunny's initial position
	private Entity bunny;
	
    public BunnyGame()
    {
        super("Bunny Math Warrior");
    }
 
    @Override // all slick games need an init that sets the initial configurations
    public void init(GameContainer gc) 
			throws SlickException 
	{
    	homeMap = new TiledMap("data/home_room.tmx");  // sets the homeMap from file.
    	
    	String up = "data/rabbit_back.bmp"; 			// only gets the file name strings instead of creating image
    	String down = "data/rabbit_forward.bmp";
    	String side = "data/rabbit_side.bmp";
    	Color Transparent = (new Image(up)).getColor(0, 0);   // this gets the color from the top-left pixel so we know which color to make transparent
    	
    	bunny = new Entity("bunny"); // create our bunny object
    	bunny.setImages(up, down, side, Transparent); // inside of setImages is where the actual image loading happens and we pass the color
    	bunny.setBlocked(homeMap);
    	bunny.AddComponent(new ArrowKeyMovement("BunnyControl")); // add movement
    	bunny.AddComponent(new RenderComponent("BunnyRender")); // add render (almost like a toString, but not)
    	bunny.setPosition(new Vector2f(x,y)); // where the bunny will first appear on screen
    }
 
    @Override // all slick games need an update that updates the goings on of 
    		  // the game and is where our game logic goes
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	bunny.update(gc,null,delta); // update our bunny in case of button presses
    }
    
    // renders what is on screen in the order it is listed
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	homeMap.render(0,0); // homeMap is rendered first so it stays in the background
    	bunny.render(gc, null, g); // bunny is second so it stays on top of homeMap
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new BunnyGame());
 
         app.setDisplayMode(750, 600, false); // false means no fullscreen
         app.start();
    }
}