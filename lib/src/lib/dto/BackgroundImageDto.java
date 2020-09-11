package lib.dto;

import java.io.Serializable;

public class BackgroundImageDto implements Serializable {

    private int id;

    private byte [] picture;

    public BackgroundImageDto(int id, byte[] picture) {
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
}
