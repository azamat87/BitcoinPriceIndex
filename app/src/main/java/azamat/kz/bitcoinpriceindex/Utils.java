package azamat.kz.bitcoinpriceindex;

import android.content.Context;
import android.net.ParseException;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Asus on 24.05.2018.
 */

public class Utils {
    public static String parseLongDateToString(long date){
        String dateString = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault());
            Date netDate = new  Date(date);
            dateString = sdf.format(netDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
