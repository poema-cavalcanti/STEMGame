package bunny.entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class NPC extends Entity {

	Rectangle bounds;
	String[] messages; 
	int messageIndex = 0; 

	public NPC(String id, String[] messages, Rectangle bounds) {
		super(id);
		this.messages = messages; 
		this.bounds = bounds; 
	}

	public String getNextMessage() {
		int lastMessage = messageIndex; 
		messageIndex++; 
		if(messageIndex > messages.length - 1) {
			messageIndex = 0;
		}
		return messages[lastMessage]; 
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public Rectangle getBounds() {
		return bounds; 
	}

	public boolean isNear(Entity ent) {
		// if to the Right of NPC 
		Rectangle nearBounds = new Rectangle(this.bounds.getX() - 75 - 5, 
				this.bounds.getY() - 75 -5, 
				this.bounds.getX() + (75*3) + 10, 
				this.bounds.getY() + (75*3) + 10);

		if(nearBounds.contains(ent.getPosition().x, ent.getPosition().y)){
			System.out.println(nearBounds.toString() + " || " + ent.getPosition().toString());
			return true; 
		} else { 
			return false;
		}

	}
}
