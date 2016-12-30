package preprocessing;

import java.io.File;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;

public class Audio_Extractor 
{
	 public static void main(String[] args) {
	        File source = new File("C:\\Mini_project\\Codes\\Preprocessing\\Media\\bin\\vid.mp4");
		File target = new File("C:\\Mini_project\\Codes\\Preprocessing\\Media\\bin\\audio.mp3");
		
		 AudioAttributes audio = new AudioAttributes();
	        audio.setCodec("libmp3lame");
	        audio.setBitRate(new Integer(128000));
	        audio.setChannels(new Integer(2));
	        audio.setSamplingRate(new Integer(44100));
	        EncodingAttributes attrs = new EncodingAttributes();
	        attrs.setFormat("mp3");
	        attrs.setAudioAttributes(audio);
	        Encoder encoder = new Encoder();
	        try {
	            encoder.encode(source, target, attrs);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	    }
}
