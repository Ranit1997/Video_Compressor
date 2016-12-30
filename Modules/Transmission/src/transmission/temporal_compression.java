package transmission;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
public class temporal_compression
{
	public static void main(String[] args)throws IOException
	{
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	FileOutputStream fos = new FileOutputStream("C:\\Mini_project\\Codes\\Transmission\\Media\\bin\\metadata.txt");
	OutputStreamWriter osw=new OutputStreamWriter(fos);
	BufferedWriter bw=new BufferedWriter(osw);
	FileInputStream fis = new FileInputStream("C:\\Mini_project\\Codes\\Transmission\\Media\\bin\\frame_count.txt");
	InputStreamReader isr=new InputStreamReader(fis);
	BufferedReader br=new BufferedReader(isr);int framecount=Integer.parseInt(br.readLine());
		Mat frame=new Mat();int i=0,c;String strr,str,strrr;
		 Mat frame_1=new Mat();
		 frame = Highgui.imread("C:\\Mini_project\\Codes\\Transmission\\Media\\bin\\pics\\"+1+".jpg"); 
		 int size =(int)(frame.total()*frame.channels());
		 double[] temp=new double[size]; int[] freq=new int[framecount];int[] frameid=new int[framecount];int k=0;double diff=0;
		 for(c=1;c<=framecount;c++)
		 {
			 str=Integer.toString(c);
			 strr="C:\\Mini_project\\Codes\\Transmission\\Media\\bin\\pics\\"+str+".jpg";
	        frame = Highgui.imread(strr); 
			frame_1= frame.clone();
			frame.convertTo(frame,CvType.CV_64FC3);
			int size_1 =(int)(frame_1.total()*frame_1.channels());
			double[] data =new double[size_1];
			frame.get(0,0,data);
			      
			if(c==1)
			{
				frame.get(0,0,temp);
				frame_1.put(0,0,temp);
				 strrr="C:\\Mini_project\\Codes\\Transmission\\Media\\bin\\selected_frames\\"+str+".jpg";
				 Highgui.imwrite(strrr,frame_1);
				 frameid[k]=c;
				 freq[k]++;
			}
			else
			  {
				for(i=0;i<size;i++)
				{
			        diff=diff+(data[i]-temp[i]);
			     }
				
				if((diff<100000)&&(diff>-100000))
				{
					
					freq[k]++;
				}
				else
				{
					frame.get(0,0,temp);
			  frame_1.put(0,0,data);
			 strrr="C:\\Mini_project\\Codes\\Transmission\\Media\\bin\\selected_frames\\"+str+".jpg";
			 Highgui.imwrite(strrr,frame_1);
			 k++;freq[k]++;frameid[k]=c;
			  }
		 diff=0;
		 }
	}
		 k++;frameid[k]=frameid[k-1]+freq[k-1];
		 freq[k]=c-frameid[k-1];
		 for(i=0;i<k;i++)
		 {
			 System.out.println(frameid[i]+" "+freq[i]);
			 bw.write(frameid[i]+" "+freq[i]);
		     bw.newLine();
			 
		 }
		 bw.flush();
		 bw.close();
		 osw.close();
		 fos.close();
		 
}
}

