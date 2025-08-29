import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class MusicPlayer {

    public static void playMusic(String filePath, boolean loop) {
        try {
            File musicPath = new File(filePath);
            if (!musicPath.exists()) {
                System.out.println("Music file not found: " + filePath);
                return;
            }

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);

            if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
