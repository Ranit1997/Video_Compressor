package receiving;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

public class FramesToVideo 
{
	 private static Dimension screenBounds;
	    public static int indexVideo = 1;
	    private static final String OUTPUT_FILE = "C:\\Mini_project\\Codes\\Receiving\\Media\\bin\\silence.mp4";

	    public static void main(String[] arguments) throws IOException 
	    {
	    	FileInputStream fis=new FileInputStream("C:\\Mini_project\\Codes\\Receiving\\Media\\bin\\frame_count.txt");
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(isr);
			 String readline = br.readLine();
			 int framecount=Integer.parseInt(readline);
	        br.close();isr.close();fis.close();
			 final IMediaWriter writer = ToolFactory.makeWriter(OUTPUT_FILE);
	        screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
	        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4,
	                screenBounds.width / 2, screenBounds.height / 2);
	        long startTime = System.nanoTime();

	        for (int index = 0; index < framecount; index++) {

	            BufferedImage bgrScreen = getVideoImage();
	            //System.out.println("time stamp = "+ (System.nanoTime() - startTime));
	            bgrScreen = convertToType(bgrScreen, BufferedImage.TYPE_3BYTE_BGR);
	            // encode the image to stream #0
	            //writer.encodeVideo(0, bgrScreen, (System.nanoTime() - startTime)/2,TimeUnit.NANOSECONDS);
	            // encode the image to stream #0
	            writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime,
	                    TimeUnit.NANOSECONDS);
	            // sleep for frame rate milliseconds
	            try {
	                Thread.sleep((long) (15));
	            } catch (InterruptedException e) {
	                // ignore
	            }
	        }
	        writer.close();
	    }

	    private static BufferedImage getVideoImage() {

	        File imgLoc = new File("C:/Mini_project/Codes/Receiving/Media/bin/final_frames/" + indexVideo + ".jpg");
	        BufferedImage img = null;
	        try {
	            img = ImageIO.read(imgLoc);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        //System.out.println(imgLoc.getName());
	        indexVideo++;
	        return img;
	    }

	    public static BufferedImage convertToType(BufferedImage sourceImage,
	            int targetType) {

	        BufferedImage image;

	        // if the source image is already the target type, return the source image
	        if (sourceImage.getType() == targetType) {
	            image = sourceImage;
	        }
	        // otherwise create a new image of the target type and draw the new image
	        else {
	            image = new BufferedImage(sourceImage.getWidth(),
	                    sourceImage.getHeight(), targetType);
	            image.getGraphics().drawImage(sourceImage, 0, 0, null);
	        }

	        return image;

	    }

}

