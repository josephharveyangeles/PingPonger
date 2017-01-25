package pingponger;

public interface Collider {
	public void collisionReactionX(Ball ball);
	
	public void collisionReactionY(Ball ball);
	
	public void collisionReaction(Ball ball);
}
