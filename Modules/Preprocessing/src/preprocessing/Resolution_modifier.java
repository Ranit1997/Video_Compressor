package preprocessing;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class Resolution_modifier {

	private static final int IMG_WIDTH = 512;
	private static final int IMG_HEIGHT = 256;

	public static void main(String [] args) throws IOException{
		FileInputStream fis=new FileInputStream("C:\\Mini_project\\Codes\\Preprocessing\\Media\\bin\\frame_count.txt");
		InputStreamReader isr=new InputStreamReader(fis);
		BufferedReader br=new BufferedReader(isr);
		 String readline = br.readLine();
		 int framecount=Integer.parseInt(readline);int i;
	try{
        
        for(i=1;i<=framecount;i++)
        {
		BufferedImage originalImage = ImageIO.read(new File("C:\\Mini_project\\Codes\\Preprocessing\\Media\\bin\\pics\\"+i+".jpg"));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

		BufferedImage resizeImageJpg = resizeImage(originalImage, type);
		ImageIO.write(resizeImageJpg, "jpg", new File("C:\\Mini_project\\Codes\\Preprocessing\\Media\\bin\\resized_pics\\"+i+".jpg"));

       readline=br.readLine();
        }

	}catch(IOException e){
		System.out.println(e.getMessage());
	}
	}

    

    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();

	return resizedImage;
    }
}