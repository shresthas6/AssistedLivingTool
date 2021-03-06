package entities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ALT {

	public static int ahr1;
	public static int amin1;

	public static int ahr2;
	public static int amin2;

	public static int ahr3;
	public static int amin3;

	public static ArrayList<String> contacts = new ArrayList<>();
	public String cName, cNum;

	public static void ShowImage(String filepath) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filepath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel lbl = new JLabel();
		lbl.setIcon(new ImageIcon(img));
		frame.getContentPane().add(lbl, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {

		Thread object1 = new Thread(new AlarmThread1());
		Thread object2 = new Thread(new AlarmThread2());
		Thread object3 = new Thread(new AlarmThread3());

		JFrame f = new JFrame("Assisted Living Tool");
		JPanel panel = new JPanel(new GridBagLayout());
		f.getContentPane().add(panel, BorderLayout.NORTH);

		GridBagConstraints c = new GridBagConstraints();
		String filepath = "src\\mediafiles\\image1.jpg";
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filepath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLabel lbl = new JLabel();
		lbl.setIcon(new ImageIcon(img));
		JLabel lb2 = new JLabel();
		lbl.setIcon(new ImageIcon(img));
		c.gridx = 4;
		c.gridy = 6;

		JLabel im = new JLabel(new ImageIcon(((new ImageIcon("src\\mediafiles\\image1.jpg")).getImage())
				.getScaledInstance(50, 50, java.awt.Image.SCALE_FAST)));

		JLabel l1 = new JLabel("My Alarms");

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);

		panel.add(l1, c);
		c.gridx = 3;
		c.gridy = 2;
		panel.add(lbl, c);
		panel.add(im, c);
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 4;
		c.ipady = 200;
		c.ipadx = 200;
		c.fill = GridBagConstraints.VERTICAL;

		JTextArea tf = new JTextArea(10, 20);
		tf.setPreferredSize(new Dimension(450, 150));

		panel.add(tf, c);
		c.gridy = 2;
		c.ipady = 5;
		c.ipadx = 20;

		panel.add(new VisualClock(), c);

		JButton ab1 = new JButton("Alarm 1");
		JButton ab2 = new JButton("Alarm 2");
		JButton ab3 = new JButton("Alarm 3");

		JButton b1 = new JButton("Click Me For Fun");
		JButton b2 = new JButton("Set New Alarm");

		JButton b4 = new JButton("View Contacts");

		b2.setBounds(4, 3, 1000, 1000);
		JButton b3 = new JButton("Add Contacts");

		c.ipady = 400;
		c.ipadx = 300;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		b1.setSize(new Dimension(5000, 5000));
		panel.add(b1, c);
		c.gridx = 0;
		c.gridy = 2;

		panel.add(b2, c);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(b3, c);
		c.gridx = 1;
		c.gridy = 2;
		panel.add(b4, c);

		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("Pharmacies");
			}
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Creating the Frame
				JFrame frame = new JFrame("Set Up Alarm");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(400, 400);

				JPanel pnl = new JPanel(new GridBagLayout());
				frame.getContentPane().add(pnl, BorderLayout.NORTH);

				GridBagConstraints cn = new GridBagConstraints();

				cn.ipady = 600;
				cn.ipadx = 300;
				cn.gridx = 0;
				cn.gridy = 0;
				pnl.add(ab1, cn);

				cn.gridx = 1;
				pnl.add(ab2, cn);

				cn.gridx = 3;
				pnl.add(ab3, cn);

				frame.setVisible(true);

				ab1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFrame f1 = new JFrame();

						String shr = JOptionPane.showInputDialog(f1, "Enter Hour:");
						String smin = JOptionPane.showInputDialog(f1, "Enter Min:");

						ahr1 = Integer.parseInt(shr);
						amin1 = Integer.parseInt(smin);
						f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						object1.start();

					}

				});

				ab2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFrame f1 = new JFrame();

						String shr = JOptionPane.showInputDialog(f1, "Enter Hour:");
						String smin = JOptionPane.showInputDialog(f1, "Enter Min:");

						ahr2 = Integer.parseInt(shr);
						amin2 = Integer.parseInt(smin);
						f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						object2.start();

					}

				});

				ab3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFrame f1 = new JFrame();

						String shr = JOptionPane.showInputDialog(f1, "Enter Hour:");
						String smin = JOptionPane.showInputDialog(f1, "Enter Min:");

						ahr3 = Integer.parseInt(shr);
						amin3 = Integer.parseInt(smin);
						f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						object3.start();

					}

				});

			}
		});

		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame f1 = new JFrame("Add New Contact");

				String cName = JOptionPane.showInputDialog(f1, "Enter Name: ");
				String cNum = JOptionPane.showInputDialog(f1, "Enter Phone Number: ");

				contacts.add(cName + " : " + cNum);
			}
		});

		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String all = "";
				for (int i = 0; i < contacts.size(); i++) // cars name of arraylist
				{
					all = all + "\r\n" + contacts.get(i);
				}
				System.out.println(all);
				tf.setText(all);
			}
		});

		f.pack();

		f.setSize(100, 100);

		f.setVisible(true);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
