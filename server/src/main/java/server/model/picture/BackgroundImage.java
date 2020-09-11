package server.model.picture;

import javax.persistence.*;

@Entity
public class BackgroundImage {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "picture_id")
    private int id;

    @Lob
    @Column(nullable = false)
    private byte [] picture;

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
