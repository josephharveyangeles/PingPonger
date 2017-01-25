package pingponger;

import java.awt.Color;

public class Brick extends Square {

	private final int PURGATORY = -100;

	public Brick(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	private void destroyed() {
		setXpos(PURGATORY);
		setYpos(PURGATORY);
	}

	@Override
	public void collisionReactionX(Ball ball) {
		ball.flipDirectionY();
		destroyed();
	}

	@Override
	public void collisionReactionY(Ball ball) {
		ball.flipDirectionX();
		destroyed();
	}

	@Override
	public void collisionReaction(Ball ball) {
		ball.flipDirectionY();
	}

}
