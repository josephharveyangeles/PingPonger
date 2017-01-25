package pingponger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main extends JFrame {
	Board board;

	Racket paddle;
	Square topBound;
	Square leftBound;
	Square rightBound;

	Ball ball;
	GameEngine ge;
	KeyboardAdapter myKeyAdapter;

	final Point minBounds;
	final Point maxBounds;

	public Main() {
		minBounds = new Point(-5, -5);
		maxBounds = new Point(500, 270);
		initAll();
		buildUI();
	}

	public static void main(String[] args) {
		new Main();
	}

	private void initAll() {
		initActors();
		myKeyAdapter = new KeyboardAdapter();
		addKeyListener(myKeyAdapter);
		board.registerEngine(ge);
		ball.setDirectionXY(true, false);
		ball.addCollisionObject(paddle);
		ball.addCollisionObject(topBound);
		ball.addCollisionObject(leftBound);
		ball.addCollisionObject(rightBound);
		ge.registerRacket(paddle);
		ge.addActor(topBound);
		ge.addActor(leftBound);
		ge.addActor(rightBound);
		ge.registerBall(ball);

		ArrayList<Brick> theBricks = generateBricks(70, 40, 10);
		ball.addCollisionObjects(theBricks);
		ge.addActors(theBricks);
		ge.start();
	}

	private void initActors() {
		board = new Board(500, 300, Color.white);
		paddle = new Racket(0, 250, 100, 10, Color.blue);
		topBound = new Racket(0, 0, 500, 5);
		leftBound = new Racket(0, 0, 5, 250);
		rightBound = new Racket(495, 0, 5, 250);
		ball = new Ball(20, 200, 10, minBounds, maxBounds, Color.red);
		ge = new GameEngine(board);
	}

	private void buildUI() {
		setTitle("PingPonger");
		getContentPane().setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);
		setSize(505, 300);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFrameToCenter();
	}

	private ArrayList<Brick> generateBricks(int num, int width, int height) {
		ArrayList<Brick> bricks = new ArrayList<Brick>();
		int columnLimit = (maxBounds.x - 100) / width;
		System.out.println(columnLimit);
		int rowLimit = (maxBounds.y - 30) / height;
		int column = 0;
		int row = 0;
		for (int i = 0, x = 18, y = 10; i < num; i++) {
			if (row > rowLimit) {
				break;
			}
			if (column > columnLimit) {
				column = 0;
				y += height + 2;
				row++;
				x = 18;
			}
			Brick brick = new Brick(x, y, width, height, generateColor(x, y, i + (x + y)));
			column++;
			x += width + 2;
			bricks.add(brick);
		}
		return bricks;
	}

	private Color generateColor(int red, int blue, int green) {
		int r = red >= 255 ? (red / 255) + (red % 255) : red;
		int g = blue >= 255 ? (blue / 255) + (blue % 255) : blue;
		int b = green >= 255 ? (green / 255) + (green % 255) : green;

		return new Color(r, g, b);
	}

	private void setFrameToCenter() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	private class KeyboardAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent key) {
			if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
				paddle.moveRight();
			}
			if (key.getKeyCode() == KeyEvent.VK_LEFT) {
				paddle.moveLeft();
			}
			// System.out.println("paddlePos:(" + paddle.getXpos() + ","
			// + paddle.getYpos() + ")");
		}
	}

}
