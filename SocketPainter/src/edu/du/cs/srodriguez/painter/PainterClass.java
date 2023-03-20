package edu.du.cs.srodriguez.painter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PainterClass extends JFrame {

	char currentShape;
	JPanel paintingPanel;
	Color currentColor;
	Socket s = null;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	PaintingPrimitive newShape;
	JTextField input = null;

	public PainterClass(Socket s) {
		this.s = s;
	}

	public PainterClass() {
		int width = 500;
		int height = 550;

		JFrame frame = new JFrame();

		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// String name = JOptionPane.showInputDialog("Enter your name");

		JPanel holder = new JPanel();
		holder.setLayout(new BorderLayout());

		// Create the paints

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(3, 1)); // 3 by 1

		// add red paint button
		JButton redPaint = new JButton();
		redPaint.setBackground(Color.RED);
		redPaint.setOpaque(true);
		redPaint.setBorderPainted(false);
		leftPanel.add(redPaint); // Added in next open cell in the grid

		// similar for green and blue
		JButton greenPaint = new JButton();
		greenPaint.setBackground(Color.GREEN);
		greenPaint.setOpaque(true);
		greenPaint.setBorderPainted(false);
		leftPanel.add(greenPaint); // Added in next open cell in the grid

		JButton bluePaint = new JButton();
		bluePaint.setBackground(Color.BLUE);
		bluePaint.setOpaque(true);
		bluePaint.setBorderPainted(false);
		leftPanel.add(bluePaint); // Added in next open cell in the grid

		// add the panels to the overall panel, holder
		// note that holder's layout should be set to BorderLayout
		holder.add(leftPanel, BorderLayout.WEST);

		// use similar code to add topPanel buttons to the NORTH region
		// omit the center panel for now
		// after finishing the PaintingPanel class (described later) add a
		// new object of this class as the CENTER panel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 2)); // 1 by 3

		JButton line = new JButton("Line");
		JButton circle = new JButton("Circle");

		topPanel.add(circle);
		topPanel.add(line);

		holder.add(topPanel, BorderLayout.NORTH);

		// paint panel
		paintingPanel = new PaintingPanel();

		paintingPanel.setLayout(new GridLayout());

		holder.add(paintingPanel, BorderLayout.CENTER);

		JButton submit = new JButton("Submit");

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(1, 2));

		JTextArea name = new JTextArea("Welcome to javatpoint");

		JTextArea textArea = new JTextArea(7, 10);

		textPanel.add(textArea);
		// textPanel.add(name, BorderLayout.SOUTH);

		// textArea.setBounds(500, 100, width, 100);

		JPanel textInput = new JPanel();
		textInput.setLayout(new GridLayout(1, 2));

		input = new JTextField(15);

		textInput.add(input);
		textInput.add(submit);

		textPanel.add(textInput);

		holder.add(textInput, BorderLayout.SOUTH);

		// And later you will add the chat panel to the SOUTH

		// Lastly, connect the holder to the JFrame
		setContentPane(holder);

		// And make it visible to layout all the components on the screen
		setVisible(true);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Submit button pressed");
				String message = input.getText();
				System.out.println("Submit button pressed: " + message);
				input.setText("");

			}

		});

		currentColor = Color.red;
		currentShape = 'c';

		// setting listeners
		redPaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Red button pressed");
				currentColor = Color.red;
			}
		});

		bluePaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Blue button pressed");
				currentColor = Color.blue;
			}
		});

		greenPaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Green button pressed");
				currentColor = Color.green;
			}
		});

		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Line button pressed");
				currentShape = 'l';
			}
		});

		circle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Circle button pressed");
				currentShape = 'l';
			}
		});

		// mouse listener

		paintingPanel.addMouseListener((MouseListener) new MouseAdapter() {
			int tuneX = 0;
			int tuneY = 0;
			PaintingPrimitive shape;
			Point pointOne;
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("X: " + e.getX() + " Y: " + e.getY());
				pointOne = new Point();
				pointOne.x = e.getX() + tuneX;
				pointOne.y = e.getY() + tuneY;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("X: " + e.getX() + " Y: " + e.getY());
				Point pointTwo = new Point();
				pointTwo.x = e.getX() + tuneX;
				pointTwo.y = e.getY() + tuneY;
				if (currentShape == 'c') {
					shape = new Circle(currentColor, pointOne, pointTwo);
					((PaintingPanel) paintingPanel).addPrimitive(shape);
				} else if (currentShape == 'l') {
					shape = new Line(currentColor, pointOne, pointTwo);
					((PaintingPanel) paintingPanel).addPrimitive(shape);
				}

				// writing to the hub PRec thread

				try {
					oos.writeObject(shape);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		// Connecting to the central hub
		try {
			System.out.println("About to call");
			s = new Socket("localhost", 9000);
			System.out.println("Connected to port");
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Canvas refresh thread
		Thread panelThread = new Thread(new CanvasRefresh((PaintingPanel) paintingPanel, ois));
		panelThread.start();
	}

	public static void main(String[] args) {
		PainterClass painter = new PainterClass();
	}
}
