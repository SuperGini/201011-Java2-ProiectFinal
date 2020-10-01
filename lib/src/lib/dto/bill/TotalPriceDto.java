package lib.dto.bill;

public class TotalPriceDto {

    private final String totalPrice;

    public TotalPriceDto(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "\n" + "\n" + "\n" +
                "Total:" + "                                                                              " + totalPrice;
    }
}
