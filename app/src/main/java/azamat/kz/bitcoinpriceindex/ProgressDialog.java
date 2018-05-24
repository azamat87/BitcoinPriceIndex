package azamat.kz.bitcoinpriceindex;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Asus on 23.05.2018.
 */

public class ProgressDialog{
    public Dialog showProgressDialog(Context context) {
        Dialog dialog = new Dialog(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.loader, null, false);
        dialog.setContentView(inflate);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.create();
        dialog.show();
        return dialog;
    }
}
