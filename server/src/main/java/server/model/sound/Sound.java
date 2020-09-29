package server.model.sound;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name= "Sound.findAllSounds", query = "SELECT s FROM Sound s")
public class Sound {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Column(name = "sound")
    private String soundName;

    @Lob
    private byte [] soundWave;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public byte[] getSoundWave() {
        return soundWave;
    }

    public void setSoundWave(byte[] soundWave) {
        this.soundWave = soundWave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sound sound = (Sound) o;
        return Objects.equals(soundName, sound.soundName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(soundName);
    }
}
