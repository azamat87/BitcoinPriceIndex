package azamat.kz.bitcoinpriceindex.mvp.view;

import com.anychart.anychart.DataEntry;
import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import azamat.kz.bitcoinpriceindex.models.Currency;

/**
 * Created by Asus on 23.05.2018.
 */

public interface ICurrencyView extends MvpView {

    void onSuccessCurrentPrice(Currency currency);

    void onSuccessHistory(ArrayList<DataEntry> seriesData);

    void showProgress();

    void hideProgress();
}
