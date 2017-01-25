package pingponger;

import java.awt.Color;

public class Circle {

	private int x;
	private int y;

	private int diameter;
	private Color myColor;

	public Circle(int x, int y, int diameter) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
	}
	
	public Circle(int x, int y, int diameter, Color color) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		myColor = color;
	}

	public int getRadius() {
		return diameter / 2;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setXpos(int xpos) {
		x = xpos;
	}

	public void setYpos(int ypos) {
		y = ypos;
	}
	
	public void setColor(Color color) {
		myColor = color;
	}

	public int getXpos() {
		return x;
	}

	public int getYpos() {
		return y;
	}
	
	public Color getColor() {
		return myColor == null ? Color.black : myColor;
	}


}
