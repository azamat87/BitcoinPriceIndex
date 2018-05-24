package azamat.kz.bitcoinpriceindex.di.moduls;

import azamat.kz.bitcoinpriceindex.NetworkApi;
import azamat.kz.bitcoinpriceindex.di.scope.CurrencyScope;
import azamat.kz.bitcoinpriceindex.interactor.CurrencyInteractor;
import azamat.kz.bitcoinpriceindex.interactor.CurrencyInteractorImpl;
import azamat.kz.bitcoinpriceindex.repository.CurrencyRepository;
import azamat.kz.bitcoinpriceindex.repository.CurrencyRepositoryImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Asus on 23.05.2018.
 */
@Module
public class CurrencyModule {

    @Provides
    @CurrencyScope
    public CurrencyRepository provideCurrencyRepository(NetworkApi api)  {
        return new CurrencyRepositoryImpl(api);
    }

    @Provides
    @CurrencyScope
    public CurrencyInteractor provideCurrencyInteractor(CurrencyRepository repository) {
        return new CurrencyInteractorImpl(repository);

    }

}
