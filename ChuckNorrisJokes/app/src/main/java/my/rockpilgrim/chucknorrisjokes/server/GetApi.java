package my.rockpilgrim.chucknorrisjokes.server;

import io.reactivex.Observable;
import my.rockpilgrim.chucknorrisjokes.pojo.JokePackage;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetApi {

    @GET("/jokes/random/{count}")
    Observable<JokePackage> loadJoke(@Path("count") int count);
}
