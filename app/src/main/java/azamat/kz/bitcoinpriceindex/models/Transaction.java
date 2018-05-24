package azamat.kz.bitcoinpriceindex.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Asus on 23.05.2018.
 */

public class Transaction implements Parcelable {

    private String date;
    private String tid;
    private String price;
    private String type;
    private String amount;

    public Transaction() {
    }

    protected Transaction(Parcel in) {
        date = in.readString();
        tid = in.readString();
        price = in.readString();
        type = in.readString();
        amount = in.readString();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(tid);
        dest.writeString(price);
        dest.writeString(type);
        dest.writeString(amount);
    }
}
