package receiving;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Audio_Merger 
{
	  public static void main(String[] args) throws Exception 
	  {
            File file=new File("C:\\Mini_project\\Codes\\Receiving\\Media\\bin\\audio.txt");
            File file_1=new File("C:\\Mini_project\\Codes\\Receiving\\Media\\bin\\audio.mp3");
            boolean success=file.renameTo(file_1);              //text file to mp3
            if(success==true)
            {
	        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c","ffmpeg -i silence.mp4 -i audio.mp3 -codec copy -shortest output.mp4");
	        ProcessBuilder pb=builder.directory(new File("C:\\Mini_project\\Codes\\Receiving\\Media\\bin"));
	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	        }
	    }
            else
            {
            	System.out.println("Error while processing audio file");
            }
}
}
