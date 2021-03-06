package entities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
//Ventolin Alarm
class AlarmThread1 implements Runnable {
	private boolean exit = false;
	String aSound = "src\\mediafiles\\sound1.wav";
	Clip clip;

	public void setFile(String soundName) {
		try {
			File file = new File(soundName);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(sound);
		} catch (Exception e) {

		}
	}

	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}

	public void run() {
		setFile(aSound);
		try {
			while (exit == false) {
				LocalTime here = LocalTime.now();
				if (here.getMinute() == ALT.amin1 && here.getHour() == ALT.ahr1) // 0-23 hours
				{
					JFrame f = new JFrame("Reminder Alarm");
					JPanel panel = new JPanel(new GridBagLayout());
					f.getContentPane().add(panel, BorderLayout.NORTH);

					GridBagConstraints c = new GridBagConstraints();

					c.ipadx = 200;
					c.ipady = 300;
					c.gridx = 0;
					c.gridy = 0;

					JTextArea tf = new JTextArea(10, 20);
					tf.setPreferredSize(new Dimension(450, 150));
					tf.setText("Take Ventolin Enhaler" + "\n" + "Enhale twice for 30 seconds");
					tf.setBounds(50, 50, 150, 20);

					JButton b = new JButton("DONE!");
					b.setBounds(50, 100, 95, 30);

					panel.add(tf, c);
					c.gridy = 1;
					panel.add(b, c);

					BufferedImage img = null;
					String filepath = "src\\mediafiles\\ventolin.jpg";
					play();
					try {
						img = ImageIO.read(new File(filepath));
					} catch (Exception e) {
						e.printStackTrace();
					}

					JLabel lbl = new JLabel();
					lbl.setIcon(new ImageIcon(img));
					c.gridx = 1;
					panel.add(lbl, c);

					Timer timer = new Timer(10000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							f.dispose();
							try {
								FileWriter fw = new FileWriter(new File("src\\thelogs", "log1.txt"), true);
								fw.write("Event 1 NOT ATTEMPTED: \n" + LocalTime.now());

								System.out.println("Writing successful");

								fw.close();
							} catch (IOException e1) {

								e1.printStackTrace();
							}
						}
					});
					timer.start();

					b.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								FileWriter fw = new FileWriter(new File("src\\thelogs", "log1.txt"), true);
								fw.write("Event 1 Completed at:" + LocalTime.now() + "\n");

								// System.out.println("Writing successful");

								fw.close();
							} catch (IOException e1) {

								e1.printStackTrace();
							}
							tf.setText("Your Response Has been Received!");
						}
					});

					f.setSize(900, 900);

					f.setVisible(true);

					exit = true;

					break;

				}

			}

		} catch (Exception e) {

			System.out.println(e.getClass().getCanonicalName());
		}
	}
}
