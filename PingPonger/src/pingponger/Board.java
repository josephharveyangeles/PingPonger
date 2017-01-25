package pingponger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Board extends JPanel {
	private int myWidth;
	private int myHeight;
	private GameEngine theEngine;
	private Color myColor;

	public Board(int width, int height) {
		myWidth = width;
		myHeight = height;

		initBoard();
	}
	
	public Board(int width, int height, Color backgroundColor){
		myWidth = width;
		myHeight = height;
		myColor = backgroundColor;
		initBoard();
	}

	private void initBoard() {
		setSize(myWidth, myHeight);
		setBackground(myColor);
		// setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBounds(0, 0, myWidth, myHeight);
		setPreferredSize(new Dimension(myWidth, myHeight));
	}

	public void registerEngine(GameEngine ge) {
		theEngine = ge;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		theEngine.paint(g2d);
	}
}
