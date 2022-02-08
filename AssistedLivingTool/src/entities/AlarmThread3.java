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

class AlarmThread3 implements Runnable // comments added
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
				if (here.getMinute() == ALT.amin3 && here.getHour() == ALT.ahr3) // 0-23 hours
				{
					JFrame f = new JFrame("Reminder Alarm");
					JTextArea tf = new JTextArea(10, 20);
					tf.setPreferredSize(new Dimension(450, 150));
					tf.setText("Time to charge your GPS Tracker and Phone" + "\n"
							+ "Plug in the charging port as shown in the picture");
					tf.setBounds(50, 50, 150, 20);
					JButton b = new JButton("DONE!");
					b.setBounds(50, 100, 95, 30);
					BufferedImage img = null;
					String filepath = "src\\mediafiles\\charge.jpg";
					play();
					try {
						img = ImageIO.read(new File(filepath));
					} catch (Exception e) {
						e.printStackTrace();
					}

					JLabel lbl = new JLabel();
					lbl.setIcon(new ImageIcon(img));
					f.getContentPane().add(lbl, BorderLayout.SOUTH);
					b.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								FileWriter fw = new FileWriter(new File("src\\thelogs", "log3.txt"));
								fw.write("Write Time");

								//System.out.println("Writing successful");

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
