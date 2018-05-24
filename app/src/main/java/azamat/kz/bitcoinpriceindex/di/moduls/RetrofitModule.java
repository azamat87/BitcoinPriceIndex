package azamat.kz.bitcoinpriceindex.di.moduls;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Asus on 23.05.2018.
 */
@Module
public class RetrofitModule {

    private final static long TIMEOUT_SECONDS = 40L;
    private final static String BASE_URL = "https://api.coindesk.com";

    @Singleton
    @Provides
    public Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder
                .baseUrl(BASE_URL)
                .build();
    }

    @Singleton
    @Provides
    public Retrofit.Builder provideRetrofitBuilder(OkHttpClient client, Gson converter ) {
        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(converter))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client);
    }

    @Singleton
    @Provides
    public OkHttpClient provideClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    public Gson provideConverter()  {
        return new GsonBuilder().create();
    }

}
