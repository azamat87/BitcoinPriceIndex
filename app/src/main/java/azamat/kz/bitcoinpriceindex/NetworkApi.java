package azamat.kz.bitcoinpriceindex;

import java.util.List;
import java.util.Map;

import azamat.kz.bitcoinpriceindex.models.CurrencyResponse;
import azamat.kz.bitcoinpriceindex.models.HistoryResponse;
import azamat.kz.bitcoinpriceindex.models.Transaction;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Asus on 23.05.2018.
 */

public interface NetworkApi {

    @GET("/v1/bpi/currentprice/{cur}.json")
    Observable<CurrencyResponse> getBitcoinByCurrenccy(@Path("cur") String currency);

    @GET("/v1/bpi/historical/close.json")
    Observable<HistoryResponse> getHistoricalPrice(@QueryMap Map<String, String> options);

    @GET
    Observable<List<Transaction>> getTransaction(@Url String url);
}
