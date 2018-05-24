package azamat.kz.bitcoinpriceindex.models;

import com.anychart.anychart.ValueDataEntry;

/**
 * Created by Asus on 23.05.2018.
 */

public class CustomDataEntry extends ValueDataEntry {

    private String x;

    public CustomDataEntry(String x, Number value) {
        super(x, value);
        this.x = x;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
}
