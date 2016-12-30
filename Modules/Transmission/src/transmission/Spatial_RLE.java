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
public class Spatial_RLE
{
	public static void main(String[] args)throws IOException
	{
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	
	FileInputStream fis=new FileInputStream("C:\\Mini_project\\Codes\\Transmission\\Media\\bin\\metadata.txt");
	InputStreamReader isr=new InputStreamReader(fis);
	BufferedReader br=new BufferedReader(isr);
	 String readline = br.readLine();
		Mat frame=new Mat();int i=0,c;String strr,strrr;int s=0;
		int freq=0;double diff=0;double [] temp=new double[3];
		
		while(readline!=null)
		{
			String[] content =readline.split(" ");
		 strr="C:\\Mini_project\\Codes\\Transmission\\Media\\bin\\selected_frames\\"+content[0]+".jpg";System.out.println(content[0]);
		 FileOutputStream fos = new FileOutputStream("C:\\Mini_project\\Codes\\Transmission\\Media\\bin\\pixel_values\\pixel"+content[0]+".txt");
			OutputStreamWriter osw=new OutputStreamWriter(fos);
			BufferedWriter bw=new BufferedWriter(osw);
		String k1,k2,k3,l;
		 
			
			 
	        frame = Highgui.imread(strr); 
			
			frame.convertTo(frame,CvType.CV_64FC3);
			int size =(int)(frame.total()*frame.channels());//System.out.println(size);
			double[] data =new double[size];
			frame.get(0,0,data);
			temp[0]=data[0];
			temp[1]=data[1];
			temp[2]=data[2];
			freq=1;
			for(i=3;i<(size-3);i+=3)
			{
				
				diff=diff+(data[i]-temp[0])+(data[i+1]-temp[1])+(data[i+2]-temp[2]);
				if((diff<25)&&(diff>-25))
				{
					freq++;
				}
				else
				{
					
					s=s+freq;
					
					k1=Integer.toString(((int)temp[0]));
					k2=Integer.toString((int)(temp[1]));
					k3=Integer.toString((int)(temp[2]));
				l=Integer.toString(freq);
				bw.write(k1+" "+k2+" "+k3+" "+l+" ");
				 bw.newLine();
				 freq=1;
				    temp[0]=data[i];
					temp[1]=data[i+1];
					temp[2]=data[i+2];
				}
				diff=0;
			}
			
			
			k1=Integer.toString((int)(temp[0]));
			k2=Integer.toString((int)(temp[1]));
			k3=Integer.toString((int)(temp[2]));
			
			l=Integer.toString(freq);s=s+freq;
			bw.write(k1+" "+k2+" "+k3+" "+l+" ");
			
			bw.newLine();
			
			 bw.close();
			 osw.close();
			 fos.close();
			 readline=br.readLine();
		}
		
		
		
		br.close();
		 
		
		 
}
}