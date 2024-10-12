import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class provides a simple GUI for visualizing the Square, Circle and
 * Triangle objects. When the user clicks on a shape, the shape will rotate
 * clockwise 360 degrees.
 * 
 * @author Kenneth Wong
 *
 */
public class Assign1_GUI {
	private Square square = null; // a square
	private Circle circle = null; // a circle
	private Triangle triangle = null; // a triangle
	private JFrame frame; // main frame of the App

	/**
	 * Creates an instance of the Assign1_GUI class and calls its start() method to
	 * starting the ball rolling.
	 * 
	 * @param args not being used in this application.
	 */
	public static void main(String[] args) {
		Assign1_GUI assign1 = new Assign1_GUI();
		assign1.start();
	}

	/**
	 * Constructor of the Assign1_GUI class.
	 */
	public Assign1_GUI() {
		// create a square
		square = new Square();
		square.color = new Color(250, 0, 0);
		square.filled = true;
		square.theta = 0;
		square.xc = 0;
		square.yc = 0;
		square.translate(100, 100);
		square.setVertices(50);

		// create a circle
		circle = new Circle();
		circle.color = new Color(0, 250, 0);
		circle.filled = true;
		circle.theta = 0;
		circle.xc = 0;
		circle.yc = 0;
		circle.translate(250, 100);
		circle.setVertices(50);

		// create a triangle
		triangle = new Triangle();
		triangle.color = new Color(0, 0, 250);
		triangle.filled = true;
		triangle.theta = -Math.PI / 2;
		triangle.xc = 0;
		triangle.yc = 0;
		triangle.translate(400, 100);
		triangle.setVertices(50);
	}

	/**
	 * Builds the GUI.
	 */
	public void start() {
		frame = new JFrame();
		frame.setTitle("COMP2396 Assignment 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create the drawing canvas
		MyCanvas canvas = new MyCanvas();
		canvas.setPreferredSize(new Dimension(500, 200));

		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * An inner class for the drawing canvas and mouse event handling
	 * 
	 * @author Kenneth Wong
	 *
	 */
	private class MyCanvas extends JPanel implements MouseListener {
		private static final long serialVersionUID = 3434052834963106098L;

		public MyCanvas() {
			this.addMouseListener(this);
		}

		// draws the shapes
		public void paintComponent(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			// draws the square
			g.setColor(square.color);
			int[] x = square.getX();
			int[] y = square.getY();
			if (square.filled) {
				g.fillPolygon(x, y, x.length);
			} else {
				g.drawPolygon(x, y, x.length);
			}

			// draws the circle
			g.setColor(circle.color);
			x = circle.getX();
			y = circle.getY();
			if (circle.filled) {
				g.fillOval(x[0], y[0], x[1] - x[0], y[1] - y[0]);
			} else {
				g.drawOval(x[0], y[0], x[1] - x[0], y[1] - y[0]);
			}
			g.setColor(Color.BLACK);
			g.drawLine((int) Math.round(circle.xc), (int) Math.round(circle.yc),
					(int) Math.round(50 * Math.cos(circle.theta) + circle.xc),
					(int) Math.round(50 * Math.sin(circle.theta) + circle.yc));

			// draws the triangle
			g.setColor(triangle.color);
			x = triangle.getX();
			y = triangle.getY();
			if (triangle.filled) {
				g.fillPolygon(x, y, x.length);
			} else {
				g.drawPolygon(x, y, x.length);
			}
		}

		// implements the MouseListener
		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();

			// checks if the user clicks on the square
			if (mx >= (square.xc - 50) && mx <= (square.xc + 50) && my >= (square.yc - 50) && my <= (square.yc + 50)
					&& square.theta == 0) {
				Thread squareThread = new Thread(new SquareThread());
				squareThread.start();
				return;
			}

			// checks if the user clicks on the circle
			if (mx >= (circle.xc - 50) && mx <= (circle.xc + 50) && my >= (circle.yc - 50) && my <= (circle.yc + 50)
					&& circle.theta == 0) {
				Thread circleThread = new Thread(new CircleThread());
				circleThread.start();
				return;
			}

			// checks if the user clicks on the triangle
			if (mx >= (triangle.xc - 50) && mx <= (triangle.xc + 50) && my >= (triangle.yc - 50)
					&& my <= (triangle.yc + 50) && triangle.theta == (-Math.PI / 2)) {
				Thread triangleThread = new Thread(new TriangleThread());
				triangleThread.start();
				return;
			}
		}
	}

	/**
	 * An inner class for rotating the square.
	 * 
	 * @author Kenneth Wong
	 *
	 */
	private class SquareThread implements Runnable {
		public void run() {
			for (int i = 0; i < 360; i++) {
				square.rotate(Math.PI / 180.0);
				frame.repaint();

				try {
					Thread.sleep(5);
				} catch (Exception ex) {
				}
			}
			square.theta = 0;
			frame.repaint();
		}
	}

	/**
	 * An inner class for rotating the circle.
	 * 
	 * @author Kenneth Wong
	 *
	 */
	private class CircleThread implements Runnable {
		public void run() {
			for (int i = 0; i < 360; i++) {
				circle.rotate(Math.PI / 180.0);
				frame.repaint();

				try {
					Thread.sleep(5);
				} catch (Exception ex) {
				}
			}
			circle.theta = 0;
			frame.repaint();
		}
	}

	/**
	 * An inner class for rotating the triangle.
	 * 
	 * @author Kenneth Wong
	 *
	 */
	class TriangleThread implements Runnable {
		public void run() {
			for (int i = 0; i < 360; i++) {
				triangle.rotate(Math.PI / 180.0);
				frame.repaint();

				try {
					Thread.sleep(5);
				} catch (Exception ex) {
				}
			}
			triangle.theta = -Math.PI / 2;
			frame.repaint();
		}
	}
}
