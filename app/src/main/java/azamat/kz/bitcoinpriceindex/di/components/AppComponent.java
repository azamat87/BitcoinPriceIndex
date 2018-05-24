package azamat.kz.bitcoinpriceindex.di.components;

import javax.inject.Singleton;

import azamat.kz.bitcoinpriceindex.di.moduls.ApiModule;
import azamat.kz.bitcoinpriceindex.di.moduls.AppModule;
import dagger.Component;

/**
 * Created by Asus on 23.05.2018.
 */

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    CurrencyComponent plus();
}
