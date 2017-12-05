package sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    public static final Sound level = new Sound("/res/audio/level.wav");
    public static final Sound menu = new Sound("/res/audio/menu.wav");
    public static final Sound gameover = new Sound("/res/audio/gameover.wav");

    private AudioClip sound;

    private Sound(String name) {
        sound = Applet.newAudioClip(Sound.class.getResource(name));
    }

    public void play(boolean loop) {
        if (loop)
            sound.loop();
        else
            sound.play();
    }

    public void stop() {
        sound.stop();
    }

    public static void stopAll() {
        level.stop();
    }
}
