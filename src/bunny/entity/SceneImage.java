package bunny.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SceneImage extends Image{

	private float x; 
	private float y; 
	private int width; 
	private int height; 
	private float moveBy; 
	private float  bottomRightX; 
	private float bottomRightY;
	
	public SceneImage(String src) throws SlickException {
		super(src);
	}
	
	public float getBottomRightX() {
		return bottomRightX;
	}
	public void setBottomRightX(float bottomRightX) {
		this.bottomRightX = bottomRightX;
	}
	public float getBottomRightY() {
		return bottomRightY;
	}
	public void setBottomRightY(float bottomRightY) {
		this.bottomRightY = bottomRightY;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float startX) {
		this.x = startX;
	}
	public float getY() {
		return y;
	}
	public void setY(float startY) {
		this.y = startY;
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
	public float getMoveBy() {
		return moveBy;
	}
	public void setMoveBy(float moveBy) {
		this.moveBy = moveBy;
	}
	
	
}
