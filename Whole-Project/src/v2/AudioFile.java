import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioFile implements LineListener {
 
 File soundFile;
 AudioInputStream ais;
 AudioFormat format;
 DataLine.Info info;
 Clip clip;
 boolean playing;
 
 /**
  * Plays audio filename that is passed through.
  * @param fileName
  */
 public AudioFile(String fileName){
  soundFile = new File(fileName);
  try {
   
   ais = AudioSystem.getAudioInputStream(soundFile);
   format = ais.getFormat();
   info = new DataLine.Info(Clip.class, format);
   clip = (Clip) AudioSystem.getLine(info);
   clip.addLineListener(this);
   clip.open(ais);
   
   
  } catch (Exception e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
   System.exit(1);
  }
  
  
 }
 
 /**
  * Checks whether or not a song is playing.
  * @return
  */
 public boolean isPlaying(){
  return playing;
 }
 
 /**
  * Plays song and modifies the boolean check for the variable 'playing'.
  */
 public void play(){
  clip.start();
  playing = true;
 }

 /**
  * Used to update the songs. Flushes clip to get next song. Is not necessarily needed.
  */
 @Override
 public void update(LineEvent event) {
  // TODO Auto-generated method stub
  if(event.getType() == LineEvent.Type.START){
   playing = true;
  }
  else if(event.getType() == LineEvent.Type.STOP){
   clip.stop();
   clip.flush();
   clip.setFramePosition(0);
   playing = false;
  }
 }

}