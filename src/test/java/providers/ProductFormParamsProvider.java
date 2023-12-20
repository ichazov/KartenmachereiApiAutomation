package providers;

import entities.*;

import java.util.*;

public class ProductFormParamsProvider {

    private ProductFormParamsProvider() {
    }

    public static Map<String, String> provideProductFormParams(EnvelopeProduct product) {
        return Map.of(
                "product", product.getItemID(),
                "format", product.getFormatID(),
                "color", product.getColorID(),
                "quantity", product.getQuantity()
        );
    }
}
