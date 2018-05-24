package azamat.kz.bitcoinpriceindex.mvp.view;

import com.arellomobile.mvp.MvpView;

import azamat.kz.bitcoinpriceindex.models.Currency;

/**
 * Created by Asus on 24.05.2018.
 */

public interface IConverterView extends MvpView {

    void onSuccessCurrentPrice(Currency currency);

    void showProgress();

    void hideProgress();

    void onError(String m);
}
