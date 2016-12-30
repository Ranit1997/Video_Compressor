package transmission;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

public class VideoToFrames 
{
	public static void main(String[] args) throws IOException
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture capture=new VideoCapture();
	
		capture.open("C:\\Mini_project\\Codes\\Transmission\\Media\\bin\\video.mp4");         //target video path
		Mat frame=new Mat();int i=1;String str,strr;          
		 boolean stat;  
		for(;;){
			str=Integer.toString(i);
			strr="C:\\Mini_project\\Codes\\Transmission\\Media\\bin\\pics\\"+str+".jpg";            //path of frames
			i++;
		   stat=capture.read(frame);
		     if(stat==false)        //Marks the end of video
			   break;//reads captured frame into the Mat image
		   Highgui.imwrite(strr,frame);
		}
		FileOutputStream fos=new FileOutputStream("C:\\Mini_project\\Codes\\transmission\\Media\\bin\\frame_count.txt");
		OutputStreamWriter osr=new OutputStreamWriter(fos);
		BufferedWriter bw=new BufferedWriter(osr);
		bw.write(Integer.toString(i-2));
		bw.close();
		osr.close();
		fos.close();
	}

}
