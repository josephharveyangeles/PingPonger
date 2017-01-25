package pingponger;

import java.awt.Color;

public abstract class Square implements Collider {

	private int x;
	private int y;

	private int width;
	private int height;
	
	private Color myColor;

	public Square(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Square(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		myColor = color;
	}

	public int getXpos() {
		return x;
	}

	public int getYpos() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Color getColor() {
		return myColor == null ? Color.black : myColor;
	}


	public void setXpos(int xpos) {
		x = xpos;
	}

	public void setYpos(int ypos) {
		y = ypos;
	}

	public void setWidth(int w) {
		width = w;
	}

	public void setHeight(int h) {
		height = h;
	}
	
	public void setColor(Color color){
		myColor = color;
	}

}
