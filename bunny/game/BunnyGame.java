package bunny.game;


import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import bunny.component.movement.ArrowKeyMovement;
import bunny.component.render.RenderComponent;
import bunny.entity.Entity;
 
public class BunnyGame extends BasicGame
{
	private TiledMap grassMap;
	private float x = 34f, y = 34f;
	Entity bunny;
	
    public BunnyGame()
    {
        super("Bunny Math Warrior");
    }
 
    @Override // all slick games need an init that sets the initial configurations
    public void init(GameContainer gc) 
			throws SlickException 
	{
    	Image up = new Image("bunny/data/test_saved_in_paint.bmp");
    	Image down = new Image("bunny/data/test_saved_in_paint.bmp");
    	Image side = new Image("bunny/data/test_saved_in_paint.bmp");
    	grassMap = new TiledMap("bunny/data/grassy_tile_map.tmx");
    	bunny = new Entity("bunny");
    	bunny.AddComponent(new ArrowKeyMovement("BunnyControl"));
    	bunny.AddComponent(new RenderComponent("BunnyRender"));
    	bunny.setImages(up,down,side);
    	bunny.setPosition(new Vector2f(x,y));
    }
 
    @Override // all slick games need an update that updates the goings on of 
    		  // the game and is where our game logic goes
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	bunny.update(gc,null,delta);
    }
    
    // renders what is on screen
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	grassMap.render(0,0);
    	bunny.render(gc, null, g);
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