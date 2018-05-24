package azamat.kz.bitcoinpriceindex.repository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import azamat.kz.bitcoinpriceindex.NetworkApi;
import azamat.kz.bitcoinpriceindex.models.CurrencyResponse;
import azamat.kz.bitcoinpriceindex.models.HistoryResponse;
import azamat.kz.bitcoinpriceindex.models.Transaction;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Asus on 23.05.2018.
 */

public class CurrencyRepositoryImpl implements CurrencyRepository {

    NetworkApi mApi;

    @Inject
    public CurrencyRepositoryImpl(NetworkApi api) {
        this.mApi = api;
    }

    @Override
    public Observable<CurrencyResponse> requestGetByCurrency(String c) {
        return mApi.getBitcoinByCurrenccy(c)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<HistoryResponse> requestGetHistory(Map<String, String> map) {
        return mApi.getHistoricalPrice(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Transaction>> requestTransaction() {
        return mApi.getTransaction("https://www.bitstamp.net/api/v2/transactions/btcusd")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
