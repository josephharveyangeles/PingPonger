package pingponger;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Ball extends Circle {

	private final static int STEP = 1;
	private final static int VELOCITY = 7;

	private final int minXbounds;
	private final int maxXbounds;
	private final int minYbounds;
	private final int maxYbounds;

	// true - to the right
	private boolean directionX;

	// true is up
	private boolean directionY;

	private ArrayList<Square> collisionObjects;

	public Ball(int x, int y, int diameter, Point minBounds, Point maxBounds) {
		super(x, y, diameter);
		minXbounds = minBounds.x;
		minYbounds = minBounds.y;
		maxXbounds = maxBounds.x;
		maxYbounds = maxBounds.y;
		collisionObjects = new ArrayList<>();
	}

	public Ball(int x, int y, int diameter, Point minBounds, Point maxBounds,
			Color color) {
		super(x, y, diameter, color);
		minXbounds = minBounds.x;
		minYbounds = minBounds.y;
		maxXbounds = maxBounds.x;
		maxYbounds = maxBounds.y;
		collisionObjects = new ArrayList<>();
	}

	public void addCollisionObject(Object obj) {
		if (obj instanceof Square) {
			Square sq = (Square) obj;
			collisionObjects.add(sq);
		}

	}

	public void addCollisionObjects(ArrayList<Brick> objects) {
		for (Object o : objects) {
			addCollisionObject(o);
		}
	}

	public void setDirectionXY(boolean dirX, boolean dirY) {
		directionX = dirX;
		directionY = dirY;
	}

	public void flipDirectionX() {
		directionX = !directionX;
	}

	public void flipDirectionY() {
		directionY = !directionY;
	}

	public void move() {
		if (directionX)
			setXpos(getXpos() + STEP);
		else
			setXpos(getXpos() - STEP);

		if (directionY)
			setYpos(getYpos() - STEP);
		else
			setYpos(getYpos() + STEP);
	}

	public int getVelocity() {
		return VELOCITY;
	}

	public void resolveCollision() {
		for (Square square : collisionObjects) {
			Collider collider = (Collider) square;
			if (intersectsYEdge(square)) {
				// System.out.println("Collision-Y detected");
				collider.collisionReactionY(this);
			} else if (intersectsXEdge(square)) {
				// System.out.println("Collision-X detected");
				collider.collisionReactionX(this);
			}
			// else if(intersects(square)){
			// collider.collisionReaction(this);
			// }
		}
	}

	public boolean intersects(Square square) {
		// occupies same pixels both x and y components
		boolean xclearance = intersectsX(square);
		boolean yclearance = intersectsY(square);

		return xclearance && yclearance;
	}

	public boolean intersectsYEdge(Square square) {
		boolean xclearance = (getXpos() + getDiameter() == square.getXpos())
				|| (getXpos() == (square.getXpos() + square.getWidth()));
		return xclearance && intersectsY(square);
	}

	public boolean intersectsXEdge(Square square) {
		boolean yclearance = (getYpos() + getDiameter() == square.getYpos())
				|| (getYpos() == (square.getYpos() + square.getHeight()));
		return yclearance && intersectsX(square);
	}

	private boolean intersectsY(Square square) {
		boolean yclearance = (getYpos() + getDiameter() >= square.getYpos())
				&& (getYpos() <= (square.getYpos() + square.getHeight()));
		return yclearance;
	}

	private boolean intersectsX(Square square) {
		boolean xclearance = (getXpos() + getDiameter() >= square.getXpos())
				&& (getXpos() <= (square.getXpos() + square.getWidth()));
		return xclearance;
	}

	public boolean isOutOfBounds() {

		boolean xout = (getXpos() <= minXbounds)
				|| (getXpos() + getDiameter() >= maxXbounds);
		boolean yout = (getYpos() <= minYbounds)
				|| (getYpos() + getDiameter() >= maxYbounds);
		return xout || yout;
	}
}
