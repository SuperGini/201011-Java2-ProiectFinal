package lib.dto.sound;

import java.io.Serializable;

public class SoundDto implements Serializable {

    private int id;

    private String nameSound;

    private byte [] soundWave;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSound() {
        return nameSound;
    }

    public void setNameSound(String nameSound) {
        this.nameSound = nameSound;
    }

    public byte[] getSoundWave() {
        return soundWave;
    }

    public void setSoundWave(byte[] soundWave) {
        this.soundWave = soundWave;
    }
}
