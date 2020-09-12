package lib.dto.picture;

import java.io.Serializable;

public class PictureDto implements Serializable {

    private int id;

    private byte [] picture;

    public PictureDto(int id, byte[] picture) {
        this.id = id;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "BackgroundImageDto{" +
                "id=" + id +
                '}';
    }
}
