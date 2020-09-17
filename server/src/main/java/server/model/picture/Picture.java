package server.model.picture;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@NamedQuery(name = "Picture.findAll", query = "Select p FROM Picture p")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "picture_id")
    private int id;

    @Lob
    @Column()
    private byte [] picture;


    public Picture() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture1 = (Picture) o;
        return id == picture1.id &&
                Arrays.equals(picture, picture1.picture);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }
}
