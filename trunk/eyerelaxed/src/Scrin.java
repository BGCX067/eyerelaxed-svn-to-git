import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import javax.swing.JFrame;

public class Scrin extends JFrame implements Runnable {
	Image screenImage; // downloaded image 
	int w, h; // Display height and width
	int curPos = 0;
File dir;
	public Scrin() {
	}

	// Program entry
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Scrin());
		t.start();
	}

	// Class constructor 
	public void drawScrin() throws MalformedURLException {
		final JFrame par = this;
		this.setAlwaysOnTop(true);
		par.requestFocus();
		par.setEnabled(false);
		// Exiting program on window close
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				super.windowDeactivated(arg0);
				par.requestFocus();
			}

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// Exitig program on mouse click
		addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { //System.exit(0);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
		// remove window frame 
		this.setUndecorated(true);
		// window should be visible
		this.setVisible(true);
		// switching to fullscreen mode
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
		// getting display resolution: width and height
		w = this.getWidth();
		h = this.getHeight();
	}

	public void getImage(String source) throws MalformedURLException {
		screenImage = Toolkit.getDefaultToolkit().getImage(source); // otherwise - file
	}

	public void paint(Graphics g) {
		if (screenImage != null) // if screenImage is not null (image loaded and ready)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			//  g.fillRect(arg0, arg1, arg2, arg3)
			g.drawImage(screenImage, // draw it 
					w / 2 - screenImage.getWidth(this) / 2, // at the center 
					h / 2 - screenImage.getHeight(this) / 2, // of screen
					this);
		}
		// to draw image at the center of screen
		// we calculate X position as a half of screen width minus half of image width
		// Y position as a half of screen height minus half of image height
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		try {
		prop.load(new FileInputStream(new File("screen.properties")));
		dir = new File(prop.getProperty("imagepath",new File(".").getCanonicalPath()));
		long sleeptime= Long.valueOf(prop.getProperty("sleeptime","10000"));
			drawScrin();
			while (true) {
				String fileName = getNextFile();
				getImage(fileName);
				repaint();
				Thread.currentThread().sleep(sleeptime);
			}
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getNextFile() {
		String[] children = dir.list();
		curPos = (int) Math.ceil(Math.random() * children.length);
		curPos = curPos >= children.length ? 0 : curPos;
		return dir.getAbsolutePath() + "/" + children[curPos];
	}
}
