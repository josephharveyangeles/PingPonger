package pingponger;

import java.awt.Color;

public class Racket extends Square {

	private static int VELOCITY = 15;

	public Racket(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public Racket(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	public void moveRight() {
		setXpos(getXpos() + VELOCITY);
	}

	public void moveLeft() {
		setXpos(getXpos() - VELOCITY);
	}

	@Override
	public void collisionReactionX(Ball ball) {
		ball.flipDirectionY();
	}

	@Override
	public void collisionReactionY(Ball ball) {
		ball.flipDirectionX();
	}

	@Override
	public void collisionReaction(Ball ball) {
		ball.flipDirectionY();
	}
}
