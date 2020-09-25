package lib.dto.autovehicle;

import java.io.Serializable;

public class PartDto implements Serializable {

    private int id;

    private String partName;

    private double price;

    private int count;

    public PartDto(int id, String partName, double price, int count) {
        this.id = id;
        this.partName = partName;
        this.price = price;
        this.count = count;
    }

    public PartDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return  "Part:........................" + partName + "\n" +
                "price:......................." + price + "\n" +
                "count:......................." + count;
    }
}
