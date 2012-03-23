package bunny.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import bunny.entity.SceneImage;
import bunny.game.BunnyGame;

public class IntroSceneState extends BasicGameState {

	public static final int ID = 3; 
	
	private BunnyGame game; 
	private SceneImage slide1; 
	private SceneImage slide2; 
	private SceneImage slide3; 
	private SceneImage slide4; 
	private SceneImage slide5; 
	private SceneImage slide6;
	private SceneImage slide7; 
	private enum SLIDE {SLIDE1, SLIDE2, SLIDE3, SLIDE4, SLIDE5, SLIDE6, SLIDE7, SLIDE8};
	private SLIDE currentSlide = SLIDE.SLIDE1;
	private int timeElapsed = 0; 
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = (BunnyGame) game; 
		this.slide1 = new SceneImage("data/introscene/slide_1.gif");
		this.slide1.setHeight(this.game.getHeight()); 
		this.slide1.setWidth(this.game.getWidth()); 
		this.slide1.setMoveBy(.03f);
		this.slide1.setX(0); 
		this.slide1.setY(0);
		
		this.slide2 = new SceneImage("data/introscene/slide_2.gif"); 
		this.slide2.setHeight(this.game.getHeight()); 
		this.slide2.setWidth(this.game.getWidth()); 
		this.slide2.setMoveBy(0); 
		this.slide2.setX(240);
		this.slide2.setY(0);
		
		this.slide3 = new SceneImage("data/introscene/slide_3.gif"); 
		this.slide3.setHeight(this.game.getHeight()); 
		this.slide3.setWidth(this.game.getWidth()); 
		this.slide3.setMoveBy(0.03f); 
		this.slide3.setX(300); 
		this.slide3.setY(0);
		
		this.slide4 = new SceneImage("data/introscene/slide_4.gif"); 
		this.slide4.setHeight(this.game.getHeight()); 
		this.slide4.setWidth(this.game.getWidth()); 
		this.slide4.setMoveBy(0.07f); 
		this.slide4.setX(75); 
		this.slide4.setY(0);
		
		this.slide5 = new SceneImage("data/introscene/slide_5.gif");
		this.slide5.setHeight(this.game.getHeight()); 
		this.slide5.setWidth(this.game.getWidth()); 
		this.slide5.setMoveBy(.03f);
		this.slide5.setX(150); 
		this.slide5.setY(0);
		
		this.slide6 = new SceneImage("data/introscene/slide_6.gif");
		this.slide6.setHeight(this.game.getHeight()); 
		this.slide6.setWidth(this.game.getWidth()); 
		this.slide6.setMoveBy(0f);
		this.slide6.setX(446); 
		this.slide6.setY(0);
		
		this.slide7 = new SceneImage("data/introscene/slide_7.gif");
		this.slide7.setHeight(this.game.getHeight()); 
		this.slide7.setWidth(this.game.getWidth()); 
		this.slide7.setMoveBy(.03f);
		this.slide7.setX(0); 
		this.slide7.setY(0);
		
		this.game.setLastStateId(this.ID);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if(currentSlide == SLIDE.SLIDE1) {
			this.slide1.draw(0, 0, 750, 600, this.slide1.getX(), this.slide1.getY(), 
					this.slide1.getX() + this.slide1.getWidth(), this.slide1.getY() + this.slide1.getHeight());
		} else if (currentSlide == SLIDE.SLIDE2) {
			this.slide2.draw(0, 0, 750, 600, this.slide2.getX(), this.slide2.getY(),
					this.slide2.getX() + this.slide2.getWidth(), this.slide2.getY() + this.slide2.getHeight());
		} else if (currentSlide == SLIDE.SLIDE3) {
			this.slide3.draw(0, 0, 750, 600, this.slide3.getX(), this.slide3.getY(), 
					this.slide3.getX() + this.slide3.getWidth(), this.slide3.getY() + this.slide3.getHeight());
		} else if (currentSlide == SLIDE.SLIDE4) {
			this.slide4.draw(0, 0, 750, 600, this.slide4.getX(), this.slide4.getY(), 
					this.slide4.getX() + this.slide4.getWidth(), this.slide4.getY() + this.slide4.getHeight());
		} else if (currentSlide == SLIDE.SLIDE5) {
			this.slide5.draw(0, 0, 750, 600, this.slide5.getX(), this.slide5.getY(),
					this.slide5.getX() + this.slide5.getWidth(), this.slide5.getY() + this.slide5.getHeight());
		} else if (currentSlide == SLIDE.SLIDE6) {
			this.slide6.draw(0, 0, 750, 600, this.slide6.getX(), this.slide6.getY(),
					this.slide6.getX() + this.slide6.getWidth(), this.slide6.getY() + this.slide6.getHeight());
		} else if(currentSlide == SLIDE.SLIDE7) {
			this.slide7.draw(0, 0, 750, 600, this.slide7.getX(), this.slide7.getY(), 
					this.slide7.getX() + this.slide7.getWidth(), this.slide7.getY() + this.slide7.getHeight());
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if(this.currentSlide == SLIDE.SLIDE1) {
			if(this.slide1.getX() < 250) {
				this.slide1.setX(this.slide1.getX() + this.slide1.getMoveBy()*delta);
			} else { // start slide 2 
				this.currentSlide = SLIDE.SLIDE2; 
			} 
		} else if(currentSlide == SLIDE.SLIDE2) {
			timeElapsed += delta; 
			if(timeElapsed > 5000) {
				this.currentSlide = SLIDE.SLIDE3;
				timeElapsed =0; 
			}
		} else if (currentSlide == SLIDE.SLIDE3) {
			if(this.slide3.getX() > 0) {
				this.slide3.setX(this.slide3.getX() - this.slide3.getMoveBy()*delta);
			} else {
				this.currentSlide = SLIDE.SLIDE4;
			}
		} else if (currentSlide == SLIDE.SLIDE4) {
			if(this.slide4.getX() < 450) {
				this.slide4.setX(this.slide4.getX() + this.slide4.getMoveBy()*delta);
			} else {
				this.currentSlide = SLIDE.SLIDE5;
			}
		} else if(currentSlide == SLIDE.SLIDE5) {
			if(this.slide5.getX() < 450) {
				this.slide5.setX(this.slide5.getX() + this.slide5.getMoveBy()*delta);
			} else {
				this.currentSlide = SLIDE.SLIDE6; 
			} 	
		} else if (currentSlide == SLIDE.SLIDE6) {
			timeElapsed += delta; 
			if(timeElapsed > 4000) {
				this.currentSlide = SLIDE.SLIDE7;
			}
		} else if(currentSlide == SLIDE.SLIDE7) {
			if(this.slide7.getX() < 450) {
				this.slide7.setX(this.slide7.getX() + this.slide7.getMoveBy()*delta);
			} else {
				game.enterState(HomeState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
			} 
		}
		
	}

	@Override
	public int getID() {
		return ID;
	}

}
