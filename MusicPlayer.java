import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {

    public static void playMusic(String filePath, boolean loop) {
        try {
            InputStream audioSrc = MusicPlayer.class.getClassLoader().getResourceAsStream(filePath);
            if (audioSrc == null) {
                System.out.println("Music file not found: " + filePath);
                return;
            }
            InputStream bufferedIn = new java.io.BufferedInputStream(audioSrc);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(bufferedIn);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);

            if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
