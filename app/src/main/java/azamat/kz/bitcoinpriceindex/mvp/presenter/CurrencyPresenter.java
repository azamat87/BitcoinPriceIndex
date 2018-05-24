package azamat.kz.bitcoinpriceindex.mvp.presenter;

import android.util.Log;

import com.anychart.anychart.DataEntry;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import azamat.kz.bitcoinpriceindex.App;
import azamat.kz.bitcoinpriceindex.interactor.CurrencyInteractor;
import azamat.kz.bitcoinpriceindex.models.Currency;
import azamat.kz.bitcoinpriceindex.models.CurrencyResponse;
import azamat.kz.bitcoinpriceindex.models.CustomDataEntry;
import azamat.kz.bitcoinpriceindex.models.HistoryResponse;
import azamat.kz.bitcoinpriceindex.mvp.view.ICurrencyView;

/**
 * Created by Asus on 23.05.2018.
 */
@InjectViewState
public class CurrencyPresenter extends MvpPresenter<ICurrencyView> {


    private static final String TAG = "CurrencyPresenter";

    @Inject
    CurrencyInteractor mInteractor;

    private String getCur = "";

    public CurrencyPresenter() {
        App.applicationComponent().plus().inject(this);
    }

    public void getByCurrencyPrice(String code) {
        getViewState().showProgress();
        getCur = code;
        mInteractor.getByCurrencyBitcoin(code)
                .subscribe(this::handlerSuccessByCurrency,this::handlerError);
    }

    public void getHistory(Map<String, String> map) {
        getViewState().showProgress();
        mInteractor.getHistoryByDate(map)
                .subscribe(this::handlerSuccessHistory,this::handlerError);
    }

    private void handlerSuccessHistory(HistoryResponse response) {
        getViewState().hideProgress();
        ArrayList<DataEntry> seriesData = new ArrayList<>();

        HashMap<String, Float> map = response.getBpi();
        Map<String, Float> mapSort = new TreeMap<String, Float>(map);
        for (Map.Entry<String, Float> entry : mapSort.entrySet()) {

            seriesData.add(new CustomDataEntry(entry.getKey(), entry.getValue()));
        }

        getViewState().onSuccessHistory(seriesData);
    }

    private void handlerSuccessByCurrency(CurrencyResponse response) {
        getViewState().hideProgress();
        for (Currency currency : response.getBpi().values()) {
            if (currency.getCode().equals(getCur)) {
                getViewState().onSuccessCurrentPrice(currency);
            }
        }
    }

    private void handlerError(Throwable throwable) {
        getViewState().hideProgress();
        Log.e(TAG, "getLocalizedMessage " + throwable.getLocalizedMessage());
        Log.e(TAG, "getMessage " + throwable.getMessage());
    }
}
