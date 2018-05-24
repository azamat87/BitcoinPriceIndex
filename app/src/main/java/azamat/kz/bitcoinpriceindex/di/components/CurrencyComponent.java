package azamat.kz.bitcoinpriceindex.di.components;

import azamat.kz.bitcoinpriceindex.di.moduls.CurrencyModule;
import azamat.kz.bitcoinpriceindex.di.scope.CurrencyScope;
import azamat.kz.bitcoinpriceindex.mvp.presenter.ConverterPresenter;
import azamat.kz.bitcoinpriceindex.mvp.presenter.CurrencyPresenter;
import azamat.kz.bitcoinpriceindex.mvp.presenter.TransactionPresenter;
import dagger.Subcomponent;

/**
 * Created by Asus on 23.05.2018.
 */
@Subcomponent(modules = CurrencyModule.class)
@CurrencyScope
public interface CurrencyComponent {
    void inject(CurrencyPresenter presenter);

    void inject(TransactionPresenter presenter);

    void inject(ConverterPresenter presenter);
}
