package entities;

import lombok.*;

public class EnvelopeProduct {

    @Getter
    private final String itemID;

    @Getter
    private final String formatID;

    @Getter
    private final String colorID;

    @Getter
    private final String quantity;

    public EnvelopeProduct(String itemID, String formatID, String colorID, String quantity) {
        this.itemID = itemID;
        this.formatID = formatID;
        this.colorID = colorID;
        this.quantity = quantity;
    }
}
