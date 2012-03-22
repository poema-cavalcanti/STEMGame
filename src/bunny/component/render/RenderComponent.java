package bunny.component.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
 
import bunny.component.Component;
 
public class RenderComponent extends Component {	
	Image image;
	
    public RenderComponent(String id)
    {
    	this.id = id;
    }
 
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr) 
    {
		Vector2f pos = owner.getPosition();
		image = owner.getSprite().getCurrentFrame();
		if (image == null) {
			System.out.println(pos);
			System.out.println(image);
			System.out.println("------------------");
			System.out.println("Aha!");
		}
		image.draw(pos.x, pos.y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		image = owner.getSprite().getCurrentFrame();
	}
}