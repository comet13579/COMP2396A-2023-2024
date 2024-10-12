import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * This class provides a simple GUI for visualizing RegularPolygon objects.
 * Users can draw regular n-sided polygons by simply clicking and dragging the
 * left mouse button in the drawing canvas. Users can also select and move a
 * polygon by simply clicking and dragging the right mouse button in the drawing
 * canvas. Finally, users can remove a polygon by simply clicking the right
 * mouse button while holding down the ctrl-key.
 * 
 * @author Kenneth Wong
 *
 */
public class Assign2_GUI {
	private ArrayList<RegularPolygon> list = new ArrayList<RegularPolygon>(); // list of polygons
	private JFrame frame; // main frame of the App
	private JSlider rSlider; // slider for the red channel
	private JSlider gSlider; // slider for the green channel
	private JSlider bSlider; // slider for the blue channel
	private JSlider nSlider; // slider for the number of sides
	private boolean mouseButton1 = false; // mouse button 1 status
	private boolean mouseButton2 = false; // mouse button 2 status
	private int xm, ym; // local coordinates of current mouse pointer
	private RegularPolygon selectedPolygon = null; // selected polygon
	private RegularPolygon newPolygon = null; // new polygon
	private boolean randomColor = true; // random color flag
	private boolean randomSides = true; // random sides flag

	/**
	 * Creates an instance of the Assign2_GUI class and calls its start() method to
	 * starting the ball rolling.
	 * 
	 * @param args not being used in this application.
	 */
	public static void main(String[] args) {
		Assign2_GUI assign2 = new Assign2_GUI();
		assign2.start();
	}

	/**
	 * Constructor of the Assign1_GUI class.
	 */
	public Assign2_GUI() {
	}

	/**
	 * Builds the GUI and allows users to start drawing regular n-sided polygons.
	 */
	public void start() {
		frame = new JFrame();
		frame.setTitle("COMP2396 Assignment 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create the drawing canvas
		MyDrawingPanel2 canvas = new MyDrawingPanel2();
		// create the tools panel
		MyToolsPanel2 tools = new MyToolsPanel2();

		frame.add(canvas, BorderLayout.CENTER);
		frame.add(tools, BorderLayout.WEST);
		frame.setSize(680, 400);
		frame.setVisible(true);
	} // start()

	private class MyDrawingPanel2 extends JPanel implements MouseListener, MouseMotionListener {
		private static final long serialVersionUID = 6363436636193872007L;

		public MyDrawingPanel2() {
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
		}

		// draw the list of shapes
		public void paintComponent(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			for (RegularPolygon polygon : list) {
				// set the pen color
				g.setColor(polygon.getColor());

				// draw a polygon
				if (polygon.getFilled()) {
					g.fillPolygon(polygon.getX(), polygon.getY(), polygon.getNumOfSides());
				} else {
					g.drawPolygon(polygon.getX(), polygon.getY(), polygon.getNumOfSides());
				}
			}

			// draw the new polygon with a black resizing line
			if (newPolygon != null) {
				g.setColor(newPolygon.getColor());

				// draw a polygon
				if (newPolygon.getFilled()) {
					g.fillPolygon(newPolygon.getX(), newPolygon.getY(), newPolygon.getNumOfSides());
				} else {
					g.drawPolygon(newPolygon.getX(), newPolygon.getY(), newPolygon.getNumOfSides());
				}
				g.setColor(Color.BLACK);
				g.drawLine((int) Math.round(newPolygon.getXc()), (int) Math.round(newPolygon.getYc()), xm, ym);
			}
		}

		// implement MouseListener
		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			xm = e.getX();
			ym = e.getY();

			if (e.getButton() == MouseEvent.BUTTON1) {
				mouseButton1 = true;

				if (randomSides) {
					// generate a random number of sides between 3 and 12
					nSlider.setValue((int) (Math.random() * 10) + 3);
				}

				if (randomColor) {
					// generate a random color
					rSlider.setValue((int) (Math.random() * 256));
					gSlider.setValue((int) (Math.random() * 256));
					bSlider.setValue((int) (Math.random() * 256));
				}

				// create a regular n-sided polygon
				newPolygon = new RegularPolygon(nSlider.getValue());
				Color color = new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue());
				newPolygon.setColor(color);
				newPolygon.setFilled(true);
				newPolygon.setTheta(0);
				newPolygon.setXc(xm);
				newPolygon.setYc(ym);
			} else if (e.getButton() == MouseEvent.BUTTON2 || e.getButton() == MouseEvent.BUTTON3) {
				mouseButton2 = true;

				// search for the top-most polygon that contains (xm, ym)
				for (int i = list.size() - 1; i >= 0; i--) {
					RegularPolygon polygon = list.get(i);
					if (polygon.contains(xm, ym)) {
						polygon.setFilled(false);
						selectedPolygon = polygon;
						// put the selected polygon on top
						list.remove(selectedPolygon);
						list.add(selectedPolygon);
						break;
					}
				}

			}
			frame.repaint();
		}

		public void mouseReleased(MouseEvent e) {
			xm = e.getX();
			ym = e.getY();

			if (e.getButton() == MouseEvent.BUTTON1) {
				mouseButton1 = false;
				if (newPolygon != null) {
					// remove newly created polygon if its scale is zero
					if (newPolygon.getRadius() != 0) {
						list.add(newPolygon);
					}
					newPolygon = null;
				}
			} else if (e.getButton() == MouseEvent.BUTTON2 || e.getButton() == MouseEvent.BUTTON3) {
				mouseButton2 = false;
				if (selectedPolygon != null) {
					if (e.isControlDown()) {
						list.remove(selectedPolygon);
					} else {
						selectedPolygon.setFilled(true);
					}
					selectedPolygon = null;
				}
			}
			frame.repaint();
		}

		// implement MouseMotionListener
		public void mouseDragged(MouseEvent e) {
			if (mouseButton1) {
				if (newPolygon != null) {
					xm = e.getX();
					ym = e.getY();

					// update the size and orientation of the newly created
					// polygon
					double dx = xm - newPolygon.getXc();
					double dy = ym - newPolygon.getYc();
					if (e.isShiftDown()) {
						// restrict the orientation to 0, 90, 180, 270 degrees
						if (Math.abs(dx) > Math.abs(dy)) {
							dy = 0.0;
						} else {
							dx = 0.0;
						}
					}

					int n = newPolygon.getNumOfSides();
					double alpha = n % 2 == 0 ? Math.PI / n : 0.0;

					newPolygon.setRadius(Math.hypot(dx, dy) / Math.cos(alpha));
					if (dx >= 0) {
						newPolygon.setTheta(Math.atan(dy / dx));
					} else {
						newPolygon.setTheta(Math.atan(dy / dx) + Math.PI);
					}
				}
			} else if (mouseButton2) {
				if (selectedPolygon != null) {
					// translate the selected polygon
					selectedPolygon.translate(e.getX() - xm, e.getY() - ym);
					xm = e.getX();
					ym = e.getY();
				}
			}
			frame.repaint();
		}

		public void mouseMoved(MouseEvent e) {
		}
	} // MyDrawingPanel2 class

	private class MyToolsPanel2 extends JPanel implements ItemListener, ChangeListener {
		private static final long serialVersionUID = -464143961045315224L;

		JCheckBox randomSidesCheckBox; // check box for random sides
		JCheckBox randomColorCheckBox; // check box for random color
		ColorPatch colorPatch; // a patch for showing a selected color
		JLabel sidesLabel; // label showing number of sides selected

		public MyToolsPanel2() {
			// create the sides selection panel
			randomSidesCheckBox = new JCheckBox("Random");
			randomSidesCheckBox.setSelected(true);
			randomSidesCheckBox.addItemListener(this);
			nSlider = new JSlider(JSlider.HORIZONTAL, 3, 12, (int) (Math.random() * 10 + 3));
			nSlider.setEnabled(false);
			nSlider.setPreferredSize(new Dimension(80, 20));
			nSlider.addChangeListener(this);
			sidesLabel = new JLabel("N = " + nSlider.getValue());
			sidesLabel.setPreferredSize(new Dimension(60, 20));
			JPanel sidesPanel = new JPanel();
			sidesPanel.setLayout(new GridBagLayout());
			GridBagConstraints c;
			c = new GridBagConstraints(0, 0, 2, 1, 0, 1, GridBagConstraints.WEST, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0);
			sidesPanel.add(randomSidesCheckBox, c);
			c = new GridBagConstraints(0, 1, 1, 1, 0.6, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0);
			sidesPanel.add(sidesLabel, c);
			c = new GridBagConstraints(1, 1, 1, 1, 0.8, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 0, 0), 0, 0);
			sidesPanel.add(nSlider, c);
			sidesPanel.setBorder(BorderFactory.createTitledBorder("Number of sides"));

			// create the color selection panel
			randomColorCheckBox = new JCheckBox("Random");
			randomColorCheckBox.setSelected(true);
			randomColorCheckBox.addItemListener(this);
			rSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, (int) (Math.random() * 256));
			gSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, (int) (Math.random() * 256));
			bSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, (int) (Math.random() * 256));
			rSlider.setEnabled(false);
			gSlider.setEnabled(false);
			bSlider.setEnabled(false);
			rSlider.setPreferredSize(new Dimension(80, 20));
			gSlider.setPreferredSize(new Dimension(80, 20));
			bSlider.setPreferredSize(new Dimension(80, 20));
			rSlider.addChangeListener(this);
			gSlider.addChangeListener(this);
			bSlider.addChangeListener(this);
			colorPatch = new ColorPatch();
			colorPatch.setPreferredSize(new Dimension(60, 60));
			JPanel colorPanel = new JPanel();
			colorPanel.setLayout(new GridBagLayout());
			c = new GridBagConstraints(0, 0, 2, 1, 0, 1, GridBagConstraints.WEST, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0), 0, 0);
			colorPanel.add(randomColorCheckBox, c);
			c = new GridBagConstraints(0, 1, 1, 3, 0.6, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0);
			colorPanel.add(colorPatch, c);
			c = new GridBagConstraints(1, 1, 1, 1, 0.8, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 0, 0), 0, 0);
			colorPanel.add(rSlider, c);
			c = new GridBagConstraints(1, 2, 1, 1, 0.8, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 0, 0), 0, 0);
			colorPanel.add(gSlider, c);
			c = new GridBagConstraints(1, 3, 1, 1, 0.8, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 0, 0), 0, 0);
			colorPanel.add(bSlider, c);
			colorPanel.setBorder(BorderFactory.createTitledBorder("Color"));

			// create a dummy panel to fill up the remaining vertical space
			JPanel dummyPanel = new JPanel();
			dummyPanel.setPreferredSize(new Dimension(1, 100000));

			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.add(sidesPanel);
			this.add(colorPanel);
			this.add(dummyPanel);
		}

		// implement ItemListener
		public void itemStateChanged(ItemEvent e) {
			randomSides = randomSidesCheckBox.isSelected(); // update the random
															// sides flag
			if (randomSides) {
				// disable the slider for choosing number of sides
				nSlider.setEnabled(false);
			} else {
				// enable the slider for choosing number of sides
				nSlider.setEnabled(true);
			}

			randomColor = randomColorCheckBox.isSelected(); // update the random
															// color flag
			if (randomColor) {
				// disable the sliders for choosing color
				rSlider.setEnabled(false);
				gSlider.setEnabled(false);
				bSlider.setEnabled(false);
			} else {
				// enable the sliders for choosing color
				rSlider.setEnabled(true);
				gSlider.setEnabled(true);
				bSlider.setEnabled(true);
			}
		}

		// implement ChangeListener
		public void stateChanged(ChangeEvent e) {
			// update the displayed number of sides
			sidesLabel.setText("N = " + nSlider.getValue());
			frame.repaint();
		}

		public class ColorPatch extends JPanel {
			private static final long serialVersionUID = 2981915065239520451L;

			public void paintComponent(Graphics g) {
				int width = this.getWidth();
				int height = this.getHeight();
				int length = width < height ? width : height;
				int arc = (int) Math.round(length / 4.0);
				g.setColor(new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue()));
				g.fillRoundRect((int) Math.round((width - length) / 2.0), (int) Math.round((height - length) / 2.0),
						length, length, arc, arc);
			}
		}
	} // MyToolsPanel2 class
}
