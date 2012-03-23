package bunny.game;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
<<<<<<< HEAD
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import bunny.state.HomeState;
=======
import org.newdawn.slick.state.StateBasedGame;

import bunny.state.HomeState;
import bunny.state.IntroSceneState;
>>>>>>> e313a5ce819d554875bf256103ac7f477adf021a
import bunny.state.TrainingState;
 
public class BunnyGame extends StateBasedGame
{
<<<<<<< HEAD
    public BunnyGame()
=======
	private int lastStateId; 
	private int width = 750; 
	private int height = 600; 
	
    public int getLastStateId() {
		return lastStateId;
	}

	public void setLastStateId(int lastStateId) {
		this.lastStateId = lastStateId;
	}

	public BunnyGame()
>>>>>>> e313a5ce819d554875bf256103ac7f477adf021a
    {
        super("Bunny Math Warrior");
    }
 
    @Override // all slick games need an init that sets the initial configurations
    public void initStatesList(GameContainer gc) 
			throws SlickException 
	{
<<<<<<< HEAD
    	addState(new HomeState());
    	addState(new TrainingState());
=======
    	addState(new IntroSceneState());
    	addState(new HomeState());
    	addState(new TrainingState());
    	
    	lastStateId = IntroSceneState.ID;
    
>>>>>>> e313a5ce819d554875bf256103ac7f477adf021a
	}
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new BunnyGame());
 
         app.setDisplayMode(750, 600, false); // false means no fullscreen
         app.start();
    }
    
    public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}