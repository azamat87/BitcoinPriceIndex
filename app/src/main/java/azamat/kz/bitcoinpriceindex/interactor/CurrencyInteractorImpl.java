package azamat.kz.bitcoinpriceindex.interactor;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import azamat.kz.bitcoinpriceindex.models.CurrencyResponse;
import azamat.kz.bitcoinpriceindex.models.HistoryResponse;
import azamat.kz.bitcoinpriceindex.models.Transaction;
import azamat.kz.bitcoinpriceindex.repository.CurrencyRepository;
import io.reactivex.Observable;

/**
 * Created by Asus on 23.05.2018.
 */

public class CurrencyInteractorImpl implements CurrencyInteractor {

    private CurrencyRepository mRepository;

    @Inject
    public CurrencyInteractorImpl(CurrencyRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public Observable<CurrencyResponse> getByCurrencyBitcoin(String code) {
        return mRepository.requestGetByCurrency(code);
    }

    @Override
    public Observable<HistoryResponse> getHistoryByDate(Map<String, String> map) {
        return mRepository.requestGetHistory(map);
    }

    @Override
    public Observable<List<Transaction>> getHistoryTransaction() {
        return mRepository.requestTransaction();
    }


}
