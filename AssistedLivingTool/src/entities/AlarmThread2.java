package entities;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JTextArea;
import javax.swing.Timer;

class AlarmThread2 implements Runnable // comments added
{
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
				if (here.getMinute() == ALT.amin2 && here.getHour() == ALT.ahr2) // 0-23 hours
				{
					JFrame f = new JFrame("Reminder Alarm");
					JTextArea tf = new JTextArea(10, 20);
					tf.setPreferredSize(new Dimension(450, 150));
					tf.setText("Its Time to Have your Dinner / Lunch" + "\n" + "The Food is inside the Refridgerator");
					tf.setBounds(50, 50, 150, 20);
					JButton b = new JButton("DONE!");
					b.setBounds(50, 100, 95, 30);
					BufferedImage img = null;
					String filepath = "src\\mediafiles\\food.jpg";
					play();
					try {
						img = ImageIO.read(new File(filepath));
					} catch (Exception e) {
						e.printStackTrace();
					}

					JLabel lbl = new JLabel();
					lbl.setIcon(new ImageIcon(img));
					f.getContentPane().add(lbl, BorderLayout.SOUTH);

					Timer timer = new Timer(10000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							f.dispose();
							try {
								FileWriter fw = new FileWriter(new File("src\\thelogs", "log2.txt"), true);
								fw.write("Event 2 NOT ATTEMPTED: \n" + LocalTime.now());

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
								FileWriter fw = new FileWriter(new File("src\\thelogs", "log2.txt"), true);
								fw.write("Event 2 Completed at:" + LocalTime.now() + "\n");

								// System.out.println("Writing successful");

								fw.close();
							} catch (IOException e1) {

								e1.printStackTrace();
							}
							tf.setText("Your Response Has been Received!");
						}
					});
					f.add(b);
					f.add(tf);

					f.setSize(400, 400);

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
