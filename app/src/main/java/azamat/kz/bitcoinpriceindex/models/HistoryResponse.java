package azamat.kz.bitcoinpriceindex.models;

import java.util.HashMap;

/**
 * Created by Asus on 23.05.2018.
 */

public class HistoryResponse {

    private HashMap<String, Float> bpi;

    public HistoryResponse() {
    }

    public HashMap<String, Float> getBpi() {
        return bpi;
    }

    public void setBpi(HashMap<String, Float> bpi) {
        this.bpi = bpi;
    }
}
