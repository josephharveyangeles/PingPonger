import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PingPongerBox extends JFrame implements KeyListener{
	Board board = new Board(300,500);
	public PingPongerBox(){
		buildUI();
	}
	
	private void buildUI(){
		this.add(board);
		this.setSize(300, 500);
		this.addKeyListener(this);
		this.setVisible(true);
	}
	
	
	public static void main(String args[]){
		new PingPongerBox();
	}
	
	public class Board extends JPanel{
		
		int x,y,r1,r2;
		int width, height;
		public Board(int width, int height){
			setSize(width, height);
			setBorder(BorderFactory.createTitledBorder("width:"+getWidth()+"; height:"+getHeight()));
			this.width = width;
			this.height = height;
			x = 1;
			y = 1;
			r1 = 10;
			r2 = 10;
			System.out.println(this.getWidth());
		}
		
		public void paint(Graphics g){
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.fillOval(x, y, r1, r2);
			g2d.drawRect(10, 10, 100, 10);
		}
		
		public void moveRight(){
			for(int i = x; i<this.width-r1*2; i++){
				x++;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
			
		}
		public void moveLeft(){
			for(;x>0; x--){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("pressed key"+e.getKeyCode());
		System.out.println("released key"+e.getKeyCode());
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			new Thread(new Runnable(){
				public void run(){
					board.moveRight();
				}
				
			}).start();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			new Thread(new Runnable(){
				public void run(){
					board.moveLeft();
				}
				
			}).start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
