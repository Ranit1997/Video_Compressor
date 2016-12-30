package receiving;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
public class Temporal_Decompression
{
	public static void main(String[] args)throws IOException
	{
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	FileInputStream fis=new FileInputStream("C:\\Mini_project\\Codes\\Receiving\\Media\\bin\\metadata.txt");
	InputStreamReader isr=new InputStreamReader(fis);
	BufferedReader br=new BufferedReader(isr);
		Mat frame=new Mat();int i=0,j=0;String strr,str;double row,col;int freq,c=1;
		 Mat frame_1=new Mat();
		 String readline = br.readLine();
		 while(readline!=null)
		 {
			 String[] content =readline.split(" ");
			 strr="C:\\Mini_project\\Codes\\Receiving\\Media\\bin\\selected_frames\\"+content[0]+".jpg";
			 freq=Integer.parseInt(content[1]);
	        frame = Highgui.imread(strr); 
			frame_1= frame.clone();
			for(i=1;i<=freq;i++)
			{
				str=Integer.toString(c); 
				strr="C:\\Mini_project\\Codes\\Receiving\\Media\\bin\\final_frames\\"+str+".jpg";
				 Highgui.imwrite(strr,frame_1);c++;
			}
			readline = br.readLine();
		 }
	}
}

