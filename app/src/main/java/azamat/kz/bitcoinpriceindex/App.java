package azamat.kz.bitcoinpriceindex;

import android.app.Application;

import azamat.kz.bitcoinpriceindex.di.components.AppComponent;
import azamat.kz.bitcoinpriceindex.di.components.DaggerAppComponent;
import azamat.kz.bitcoinpriceindex.di.moduls.AppModule;

/**
 * Created by Asus on 22.05.2018.
 */

public class App extends Application {

    private static AppComponent graph;

    @Override
    public void onCreate() {
        super.onCreate();

        graph = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent applicationComponent() {
        return graph;
    }
}
