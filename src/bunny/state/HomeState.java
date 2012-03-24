package bunny.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import bunny.component.Component;
import bunny.component.movement.ArrowKeyMovement;
import bunny.component.render.RenderComponent;
import bunny.entity.Entity;
import bunny.entity.NPC;
import bunny.entity.characters.Bunny;
import bunny.entity.characters.BunnyStates;
import bunny.game.BunnyGame;

public class HomeState extends BasicGameState 
{
	// ID for given state 
	public static final int ID = 1; 
	// game holding this state 

	public TiledMap homeMap; 
	private float x = 75f, y = 450f; // used for bunny's initial position
	private Bunny bunny;
	private BunnyGame game;
	private NPC mom; 
	private Image momImage; 

    public int getId()
    {
        return ID; 
    }
 
    @Override // all slick games need an init that sets the initial configurations
    public void init(GameContainer gc, StateBasedGame game) 
			throws SlickException 
	{
    	homeMap = new TiledMap("data/home_room.tmx", "data");  // sets the homeMap from file.
    	this.game = (BunnyGame) game; 
    	String up = "data/rabbit_back.bmp"; 			// only gets the file name strings instead of creating image
    	String down = "data/rabbit_forward.bmp";
    	String side = "data/rabbit_side.bmp";
    	String momBmp = "data/lady_rabbit.bmp";
    	Color Transparent = (new Image(up)).getColor(0, 0);   // this gets the color from the top-left pixel so we know which color to make transparent
    	
    	bunny = new Bunny("bunny"); // create our bunny object
    	bunny.setImages(up, down, side, Transparent); // inside of setImages is where the actual image loading happens and we pass the color
    	bunny.setBlocked(homeMap);
    	bunny.setPosition(new Vector2f(x,y));
    	bunny.setCurrentState(BunnyStates.WALKING);
    	bunny.AddComponent(new ArrowKeyMovement("BunnyControl")); // add movement
    	bunny.AddComponent(new RenderComponent("BunnyRender")); // add render (almost like a toString, but not)
    	bunny.AddComponent(new NPCInteraction("BunnyNPCControl"));
    	bunny.setBounds();
    	
    	String[] momMessages = {"Hi", "Be careful out there!"}; 
    	Rectangle momBounds = new Rectangle(230f, 226f, 75f, 75f); 
    	mom = new NPC("mom", momMessages, momBounds);
    	mom.setImages(momBmp, momBmp, momBmp, Transparent); 
    	mom.setBlocked(homeMap);
    	mom.setPosition(new Vector2f(x,y));
    	mom.AddComponent(new RenderComponent("MomRender"));
    	mom.setPosition(new Vector2f(220, 215));
    	momImage = new Image(momBmp, (new Image(momBmp)).getColor(0, 0));
    	if(this.game.getLastStateId() == TrainingState.ID) {
    		this.x = 600; 
    		this.y = 275;
    	}
    	
    	bunny.setPosition(new Vector2f(x,y)); // where the bunny will first appear on screen
    	
    	this.game.setLastStateId(this.ID);
    }

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		homeMap.render(0,0); // homeMap is rendered first so it stays in the background
		g.drawString("Use arrow keys to move", 10, 30);
    	bunny.render(container, null, g); // bunny is second so it stays on top of homeMap
    	g.drawImage(momImage, mom.getPosition().x, mom.getPosition().y);

	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		bunny.update(container,null,delta);
		if(bunny.getPosition().x > 655) {
			if(bunny.getPosition().y > 246 && bunny.getPosition().y < 311) {
				this.x = 600; 
	    		this.y = 275;
	    		bunny.setPosition(new Vector2f(x,y));
				game.enterState(TrainingState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
			}
		}
	}


	public class NPCInteraction extends Component {

		private String id;

		public NPCInteraction(String string) {
			this.id = id;
		}

		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta) {
			Input input = gc.getInput();
			input.disableKeyRepeat();
			if (input.isKeyDown(Input.KEY_E)){
				if(mom.isNear(bunny)) {
					System.out.println(mom.getNextMessage());
				} else {
					System.out.println("not near");
				}

			}

		}

	}


}
