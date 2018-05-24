package azamat.kz.bitcoinpriceindex.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import azamat.kz.bitcoinpriceindex.App;
import azamat.kz.bitcoinpriceindex.interactor.CurrencyInteractor;
import azamat.kz.bitcoinpriceindex.models.Currency;
import azamat.kz.bitcoinpriceindex.models.CurrencyResponse;
import azamat.kz.bitcoinpriceindex.mvp.view.IConverterView;

/**
 * Created by Asus on 24.05.2018.
 */
@InjectViewState
public class ConverterPresenter extends MvpPresenter<IConverterView> {

    private static final String TAG = "ConverterPresenter";

    @Inject
    CurrencyInteractor mInteractor;
    private String currencyCode = "";

    public ConverterPresenter() {
        App.applicationComponent().plus().inject(this);
    }

    public void getByCurrencyPrice(String code) {
        getViewState().showProgress();
        currencyCode = code;
        mInteractor.getByCurrencyBitcoin(code)
                .subscribe(this::handlerSuccess, this::handlerError);

    }

    private void handlerSuccess(CurrencyResponse response) {
        getViewState().hideProgress();
        for (Currency currency : response.getBpi().values()) {
            if (currency.getCode().equals(currencyCode)) {
                getViewState().onSuccessCurrentPrice(currency);
            }
        }
    }

    private void handlerError(Throwable throwable) {
        getViewState().hideProgress();
        getViewState().onError(throwable.getLocalizedMessage());
    }
}
