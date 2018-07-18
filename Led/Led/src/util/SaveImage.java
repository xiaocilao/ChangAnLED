package util;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class SaveImage {
private Integer wait_time;
	
	public SaveImage(Integer wait_time) {
		this.wait_time=wait_time;
		
	}

	public boolean getImage() throws MalformedURLException, IOException, URISyntaxException, AWTException {
			 Robot robot = new Robot();  
			    robot.delay(100);  
			    Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());  
			    int width = (int) d.getWidth();  
			    int height = (int) d.getHeight();  
			    //最大化浏览器  
			    robot.delay(wait_time);  
			    Image image = robot.createScreenCapture(new Rectangle(179, 86, width-358,  
			    		height-210)); 
			    BufferedImage bi = new BufferedImage(width-358, height-210,  
			            BufferedImage.TYPE_3BYTE_BGR);  
			    Graphics g = bi.createGraphics();  
			    g.drawImage(image, 0, 0, width-358, height-210, null);  
			    //保存图片  
			    ImageIO.write(bi, "jpg", new File("factory.bmp"));
		return true;
	}

}
