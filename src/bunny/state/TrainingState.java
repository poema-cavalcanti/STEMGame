package bunny.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import bunny.component.attack.EnemyAttack;
import bunny.component.attack.HeroAttack;
import bunny.component.movement.ArrowKeyMovement;
import bunny.component.render.RenderComponent;
import bunny.entity.EntityType;
import bunny.entity.characters.Bunny;
import bunny.entity.characters.BunnyStates;
import bunny.entity.characters.Wolf;
import bunny.game.GamePlayState;
import bunny.game.Direction;

public class TrainingState extends BasicGameState
{
	public static final int ID = 2; 

	private GamePlayState currentState;
	private StateBasedGame game; 
	private TiledMap trainingMap; 
	private float bx = 75f, by = 450f; // used for bunny's initial position;
	protected Bunny bunny;
	private float wx = 525f, wy = 150f; // used for wolf's initial position;
	private Wolf wolf;	
	private Wolf wolf2;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		trainingMap = new TiledMap("data/training_room.tmx");  // sets the homeMap from file.
    	this.game = game; 
    	String up = "data/rabbit_back.bmp"; 			// only gets the file name strings instead of creating image
    	String down = "data/rabbit_forward.bmp";
    	String side = "data/rabbit_side.bmp";
    	String attack = "data/bunny_attack_strip.bmp";
    	
    	Color Transparent = (new Image(up)).getColor(0, 0);   // this gets the color from the top-left pixel so we know which color to make transparent
    	bunny = new Bunny("bunny"); // create our bunny object
    	bunny.setImages(up, down, side, Transparent); // inside of setImages is where the actual image loading happens and we pass the color
    	
    	Transparent = (new Image(attack)).getColor(0, 0);
    	bunny.setAttack(attack, Transparent);
    	bunny.setCurrentState(BunnyStates.WALKING);
    	
    	bunny.setBlocked(trainingMap);
    	bunny.AddComponent(new ArrowKeyMovement("BunnyControl")); // add movement
    	bunny.AddComponent(new RenderComponent("BunnyRender")); // add render (almost like a toString, but not)
    	bunny.AddComponent(new HeroAttack("BunnyAttack")); // add attack
    	bunny.setPosition(new Vector2f(bx,by)); // where the bunny will first appear on screen
    	
    	up = "data/wolf_back_run_strip.bmp";
    	down = "data/wolf_front_run_strip.bmp";
    	side = "data/wolf_side_run_strip.bmp";
    	attack ="data/wolf_attack_strip.bmp";
    	
    	Transparent = (new Image(side)).getColor(0,0);
    	wolf = new Wolf("wolf1");
    	wolf.setImages(up, down, side, Transparent);
    	
    	Transparent = (new Image(attack)).getColor(0, 0);
    	wolf.setAttack(attack, Transparent);
    	wolf.setBanner(new Image("data/banner.bmp", Transparent));
    	
    	wolf.setPosition(new Vector2f(wx,wy));
    	wolf.setType(EntityType.CONST_WOLF);
    	wolf.setBlocked(trainingMap);
    	wolf.AddComponent(new RenderComponent("WolfRender"));
    	wolf.AddComponent(new EnemyAttack("WolfAttack"));
    	
    	Transparent = (new Image(side)).getColor(0,0);
    	wolf2 = new Wolf("wolf2");
    	wolf2.setImages(up, down, side, Transparent);
    	
    	Transparent = (new Image(attack)).getColor(0, 0);
    	wolf2.setAttack(attack, Transparent);
    	wolf2.setBanner(new Image("data/banner.bmp", Transparent));
    	
    	wolf2.setPosition(new Vector2f(wx,375));
    	wolf2.setType(EntityType.LIN_WOLF);
    	wolf2.setBlocked(trainingMap);
    	wolf2.AddComponent(new RenderComponent("Wolf2Render"));
    	wolf2.AddComponent(new EnemyAttack("Wolf2Attack"));
    	
    	bunny.setBounds();
    	wolf.setBounds();
    	wolf2.setBounds();
    	wolf.bunny = bunny;
    	wolf2.bunny = bunny;
    	bunny.enemy = wolf;
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		trainingMap.render(0,0); // homeMap is rendered first so it stays in the background
		g.drawString("press A to attack when near a wolf", 10, 30);
    	bunny.render(container, null, g); // bunny is second so it stays on top of homeMap
    	if (wolf != null) {
    		wolf.render(container, null, g);
    		wolf.getBanner().draw(wolf.getPosition().x, wolf.getPosition().y - 75);
    		g.drawString("10", wolf.getPosition().x + 30, wolf.getPosition().y - 50);
    		if (bunny.getPosition().distance(wolf.getPosition()) <= 100) {
    			g.drawString("Careful! This constant wolf would have done you " + wolf.getAttackValue() + " damage out on the surface!", 10, 50);
    		}
    	}
    	if (wolf2 != null) {
    		wolf2.render(container, null, g);
    		wolf2.getBanner().draw(wolf2.getPosition().x, wolf2.getPosition().y - 75);
    		g.drawString("5x", wolf2.getPosition().x + 30, wolf2.getPosition().y - 50);
    		if (bunny.getPosition().distance(wolf2.getPosition()) <= 100) {
    			g.drawString("Careful! This linear wolf would have done you " + wolf2.getAttackValue() + " damage out on the surface!", 10, 70);
    		}
    	}
    	
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		bunny.update(container, null, delta);
		bunny.updateBounds();
		
		if (wolf != null && wolf2 != null) {
			if (bunny.getPosition().distance(wolf.getPosition()) <= bunny.getPosition().distance(wolf2.getPosition())) {
				bunny.enemy = wolf;
			}
			else {
				bunny.enemy = wolf2;
			}
			wolf.update(container, null, delta);
			wolf.updateBounds();
			bunny.setTagetedEnemy(wolf.getPosition());
			bunny.setNearEnemy(wolf);
			if (wolf.getHealth() <= 0) {
				wolf = null;
			}
			wolf2.update(container, null, delta);
			wolf2.updateBounds();
			bunny.setTagetedEnemy(wolf2.getPosition());
			bunny.setNearEnemy(wolf2);		
			if (wolf2.getHealth() <= 0) {
				wolf2 = null;
			}
		}
		
		else if (wolf != null && wolf2 == null) {
			bunny.enemy = wolf;
			wolf.update(container, null, delta);
			wolf.updateBounds();
			bunny.setTagetedEnemy(wolf.getPosition());
			bunny.setNearEnemy(wolf);		
			if (wolf.getHealth() <= 0) {
				wolf = null;
			}
		}
		
		else if (wolf2 != null && wolf == null) {
			bunny.enemy = wolf2;
			wolf2.update(container, null, delta);
			wolf2.updateBounds();
			bunny.setTagetedEnemy(wolf2.getPosition());
			bunny.setNearEnemy(wolf2);		
			if (wolf2.getHealth() <= 0) {
				wolf2 = null;
			}
		}
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}