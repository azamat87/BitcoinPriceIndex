package azamat.kz.bitcoinpriceindex.models;

import java.util.HashMap;

/**
 * Created by Asus on 23.05.2018.
 */

public class CurrencyResponse {

    private HashMap<String, Currency> bpi;

    public CurrencyResponse() {

    }

    public HashMap<String, Currency> getBpi() {
        return bpi;
    }

    public void setBpi(HashMap<String, Currency> bpi) {
        this.bpi = bpi;
    }
}
