package v3;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AnimationGUI extends JFrame {
	private JFrame startFrame;

	/**
	 * Constructs the GUI that will show the attack animations.
	 * @param fileName The file name for the gif on the left side.
	 * @param fileName2 The file name for the gif on the right side.
	 * @throws InterruptedException 
	 */
	public AnimationGUI(String fileName, String fileName2) {
		startFrame = this;
		startFrame.setLayout(new GridLayout(1, 2));
		startFrame.setSize(new Dimension(1000, 500));
		startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			placeGif(fileName, fileName2);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startFrame.repaint();
		setVisible(true);
		
	}

	
	/**
	 * Adds the animations to the GUI.
	 * @param fileName The file name for the gif on the left side.
	 * @param fileName2 The file name for the gif on the right side.
	 */
	public void placeGif(String fileName, String fileName2) throws MalformedURLException {
		try {
			URL gifURL = new File(fileName).toURI().toURL();
			Icon icon = new ImageIcon(gifURL);
			JLabel titleGif = new JLabel(icon);

			URL gifURL2 = new File(fileName2).toURI().toURL();
			Icon icon2 = new ImageIcon(gifURL2);
			JLabel titleGif2 = new JLabel(icon2);

			
			titleGif.updateUI();
			titleGif2.updateUI();
			startFrame.add(titleGif);
			startFrame.add(titleGif2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the GUI.
	 */
	public void close(){
		  startFrame.setVisible(false);
		  startFrame.dispose();
		 }

}
