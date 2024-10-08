// for playing sound clips
import javax.sound.sampled.*;
import java.io.*;
import java.util.HashMap;				// for storing sound clips


public class SoundManager {
    HashMap<String, Clip> clips;

    private static SoundManager instance = null;
    private float volume;

    private SoundManager(){
        clips = new HashMap <String, Clip>();

        Clip clip = loadClip("background.wav");
        clips.put("background", clip);

        clip = loadClip("cookieDunk.wav");
        clips.put("cookieDunk", clip);

        clip = loadClip("gameOver.wav");
        clips.put("gameOver", clip);

        clip = loadClip("boing.wav");
        clips.put("boing", clip);

        clip = loadClip("levelUP.wav");
        clips.put("levelUP", clip);

    }

    public static SoundManager getInstance() {	
		if (instance == null)
			instance = new SoundManager();
		
		return instance;
	}
    
    public Clip loadClip (String fileName) {	// gets clip from the specified file
        AudioInputStream audioIn;
       Clip clip = null;

       try {
               File file = new File(fileName);
               audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL()); 
               clip = AudioSystem.getClip();
               clip.open(audioIn);
       }
       catch (Exception e) {
            System.out.println ("Error opening sound files: " + e);
       }
           return clip;
       }

       public Clip getClip (String title) {

		return clips.get(title);
	}


    public void playClip(String title, boolean looping) {
		Clip clip = getClip(title);
		if (clip != null) {
			clip.setFramePosition(0);
			if (looping)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			else
				clip.start();
		}
    	}

        public void stopClip(String title) {
            Clip clip = getClip(title);
            if (clip != null) {
                clip.stop();
            }
            }
    
        public void setVolume (String title, float volume) {
            Clip clip = getClip(title);
    
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
    
            gainControl.setValue(gain);
        }
}
