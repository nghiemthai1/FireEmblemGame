import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music implements Runnable{
 ArrayList<AudioFile> musicFiles;
 int currentSongIndex;
 boolean running;
 
 /**
  * Takes any amount of filenames and adds them to an array list.
  * @param files
  */
 public Music(String...files){
  musicFiles = new ArrayList<AudioFile>();
  for(String file: files){
   musicFiles.add(new AudioFile(file));
  }
 }
 

/**
 * Plays songs in musicFiles arraylist in an infinite loop in order.
 */
 @Override
 public void run() {
  // TODO Auto-generated method stub
  running = true;
  AudioFile song = musicFiles.get(currentSongIndex);
  song.play();
  while(running){
   if(!song.isPlaying()){
    currentSongIndex++;
    if(currentSongIndex >= musicFiles.size()){
     currentSongIndex = 0;
    }
    song = musicFiles.get(currentSongIndex);
    song.play();
   }
   try{
    Thread.sleep(1);
   }
   catch (InterruptedException e){
    e.printStackTrace();
   } 
  }
 } 
}