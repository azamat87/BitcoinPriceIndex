package azamat.kz.bitcoinpriceindex.models;

/**
 * Created by Asus on 23.05.2018.
 */

public class Currency {

    private String code;
    private String rate;
    private String description;
    private float rate_float;

    public Currency() {
    }

    public Currency(String code, String rate, String description, float rate_float) {
        this.code = code;
        this.rate = rate;
        this.description = description;
        this.rate_float = rate_float;
    }

    public String getCode() {
        return code;
    }

    public String getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
    }

    public float getRate_float() {
        return rate_float;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRate_float(float rate_float) {
        this.rate_float = rate_float;
    }

}
