package azamat.kz.bitcoinpriceindex.repository;

import java.util.List;
import java.util.Map;

import azamat.kz.bitcoinpriceindex.models.CurrencyResponse;
import azamat.kz.bitcoinpriceindex.models.HistoryResponse;
import azamat.kz.bitcoinpriceindex.models.Transaction;
import io.reactivex.Observable;

/**
 * Created by Asus on 23.05.2018.
 */

public interface CurrencyRepository {

    Observable<CurrencyResponse> requestGetByCurrency(String c);

    Observable<HistoryResponse> requestGetHistory(Map<String, String> map);

    Observable<List<Transaction>> requestTransaction();
}
