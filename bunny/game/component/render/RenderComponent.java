package bunny.component.render;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
 
import bunny.component.Component;
 
public class RenderComponent extends Component {	
	
    public RenderComponent(String id)
    {
    	this.id = id;
    }
 
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr) 
    {
		Vector2f pos = owner.getPosition();
		owner.getSprite().draw(pos.x, pos.y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		
	}
}