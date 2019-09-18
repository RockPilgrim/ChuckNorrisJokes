package my.rockpilgrim.chucknorrisjokes.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import my.rockpilgrim.chucknorrisjokes.pojo.JokePackage;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerConnector {

    public static Observable<JokePackage> request(int count) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        GetApi api = new Retrofit.Builder()
                .baseUrl("https://api.icndb.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(GetApi.class);
        return api.loadJoke(count).subscribeOn(Schedulers.io());
    }
}
