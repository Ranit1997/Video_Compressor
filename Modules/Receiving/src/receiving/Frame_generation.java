package receiving;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
public class Frame_generation
{
	public static void main(String[] args)throws IOException
	{
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	FileInputStream fis=new FileInputStream("C:\\Mini_project\\Codes\\Receiving\\Media\\bin\\metadata.txt");
	InputStreamReader isr=new InputStreamReader(fis);
	BufferedReader br=new BufferedReader(isr);
		Mat frame=new Mat();int i=0,j=0;String str;double pix1,pix2,pix3;int freq,c=1;String readline;
		frame = Highgui.imread("C:\\Mini_project\\Codes\\Receiving\\Media\\bin\\black.jpg"); 
		int size =(int)(frame.total()*frame.channels());System.out.println(size);
		readline=br.readLine();
		while(readline!=null)
		{
		String[] content=readline.split(" ");
		FileInputStream fis_1=new FileInputStream("C:\\Mini_project\\Codes\\Receiving\\Media\\bin\\pixel_values\\pixel"+content[0]+".txt");
		InputStreamReader isr_1=new InputStreamReader(fis_1);
		BufferedReader br_1=new BufferedReader(isr_1);
		 Mat frame_1=new Mat();frame_1=frame.clone();
		 //frame = Highgui.imread("G:\\Mini_project\\Media\\bin\\pics\\1600.jpg"); 
		 frame.convertTo(frame,CvType.CV_64FC3);
		
		frame_1=frame.clone();
		double[] data=new double[size];
		 String readline_1 = br_1.readLine();
		 str="C:\\Mini_project\\Codes\\Receiving\\Media\\bin\\selected_frames\\"+content[0]+".jpg";
		 frame.get(0,0,data);
		 while(readline_1!=null)
		 {
			 String[] content_1 =readline_1.split(" ");
			 pix1=Double.parseDouble(content_1[0]);
			 pix2=Double.parseDouble(content_1[1]);
			 pix3=Double.parseDouble(content_1[2]);
			 freq=Integer.parseInt(content_1[3]);
			 
				 for(j=1;j<=freq;j++)
				 {
					 data[i]=pix1;data[i+1]=pix2;data[i+2]=pix3;
					 i+=3;
				 }
			  
			  
				 readline_1 = br_1.readLine();
			 }
	
		 
		 System.out.println(i);
			 frame_1.put(0,0,data);
			 Highgui.imwrite(str,frame_1);i=0;
			
			    
			 readline = br.readLine();
		 
		 }
	}
}
