package bunny.game;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import bunny.state.HomeState;
import bunny.state.TrainingState;
 
public class BunnyGame extends StateBasedGame
{
	private int lastStateId; 
	
    public int getLastStateId() {
		return lastStateId;
	}

	public void setLastStateId(int lastStateId) {
		this.lastStateId = lastStateId;
	}

	public BunnyGame()
    {
        super("Bunny Math Warrior");
    }
 
    @Override // all slick games need an init that sets the initial configurations
    public void initStatesList(GameContainer gc) 
			throws SlickException 
	{
    	addState(new HomeState());
    	addState(new TrainingState());
    	
    	lastStateId = HomeState.ID;
    
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