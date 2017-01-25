package pingponger;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameEngine implements Runnable {
	private Thread theThread;

	private JPanel thePanel;

	private Ball myBall;
	private Racket myRacket;

	private ArrayList<Object> actorList;
	private boolean running;

	public GameEngine(JPanel stagePanel) {
		thePanel = stagePanel;
		actorList = new ArrayList<>();
		theThread = new Thread(this);
	}

	public synchronized void start() {
		running = true;
		theThread.start();
	}

	public synchronized void stop() {
		running = false;
	}

	public void addActor(Object actor) {
		actorList.add(actor);
	}
	
	public void addActors(ArrayList<Brick> actors){
		actorList.addAll(actors);
	}

	public void registerBall(Ball ball) {
		myBall = ball;
	}

	public void registerRacket(Racket racket) {
		myRacket = racket;
	}

	public void removeActor(Object actor) {
		actorList.remove(actor);
	}

	public void paint(Graphics2D graphics) {
		paintBall(graphics);
		paintRacket(graphics);
		
		graphics.setColor(Color.black);
		
		for (Object actor : actorList) {
			if (actor instanceof Circle) {
				Circle circle = (Circle) actor;
				paintCircleActor(graphics, circle);
			} else if (actor instanceof Square) {
				Square square = (Square) actor;
				paintSquareActor(graphics, square);
			}
		}
		thePanel.repaint();
	}

	private void paintSquareActor(Graphics2D graphics, Square square) {
		graphics.setColor(square.getColor());
		graphics.fillRect(square.getXpos(), square.getYpos(),
				square.getWidth(), square.getHeight());
	}

	private void paintBall(Graphics2D graphics) {
		graphics.setColor(myBall.getColor());
		graphics.fillOval(myBall.getXpos(), myBall.getYpos(),
				myBall.getDiameter(), myBall.getDiameter());
	}

	private void paintRacket(Graphics2D graphics) {
		graphics.setColor(myRacket.getColor());
		graphics.fillRect(myRacket.getXpos(), myRacket.getYpos(),
				myRacket.getWidth(), myRacket.getHeight());
	}
	
	private void paintCircleActor(Graphics2D graphics, Circle circle){
		graphics.setColor(circle.getColor());
		graphics.fillOval(circle.getXpos(), circle.getYpos(),
				circle.getDiameter(), circle.getDiameter());
	}

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(myBall.getVelocity());
				myBall.move();
				myBall.resolveCollision();
				if (myBall.isOutOfBounds()) {
					System.out.println("GameOver");
					running = false;
					JOptionPane.showMessageDialog(thePanel, "Loser. haha!",
							"Game Over", JOptionPane.WARNING_MESSAGE);
				}

			} catch (Exception e) {
			}
			thePanel.repaint();
			;
		}
	}

}
