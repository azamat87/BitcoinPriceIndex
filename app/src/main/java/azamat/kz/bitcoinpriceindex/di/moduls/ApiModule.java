package azamat.kz.bitcoinpriceindex.di.moduls;

import javax.inject.Singleton;

import azamat.kz.bitcoinpriceindex.NetworkApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Asus on 23.05.2018.
 */
@Module(includes = RetrofitModule.class)
public class ApiModule {

    @Singleton
    @Provides
    public NetworkApi provideApi(Retrofit retrofit) {
        return retrofit.create(NetworkApi.class);
    }
}
