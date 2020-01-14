package my.rockpilgrim.chucknorrisjokes.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import my.rockpilgrim.chucknorrisjokes.pojo.Joke;
import my.rockpilgrim.chucknorrisjokes.presenter.interfaces.IJokesHolder;
import my.rockpilgrim.chucknorrisjokes.server.ServerConnector;
import my.rockpilgrim.chucknorrisjokes.ui.interfaces.MvpJokesView;

@InjectViewState
public class JokesPresenter extends MvpPresenter<MvpJokesView> implements IJokesHolder {

    public static final String TAG = "JokePresenter";
    private List<Joke> jokeList;

    public JokesPresenter() {
        jokeList = new ArrayList();
    }

    public void connectToServer(int jokesCount) {
        Disposable disposable = ServerConnector.request(jokesCount)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(jokePackage -> {
                    jokeList = jokePackage.getJokeList();
                    getViewState().updateList();
                }, throwable ->{
                    getViewState().makeToast("Connection error");
                    Log.e(TAG, "connectToServer", throwable);
                });
    }

    @Override
    public int size() {
        return jokeList.size();
    }

    @Override
    public String getJoke(int index) {
        String joke = jokeList.get(index).getJoke().replace("&quot;", "\"");
        return joke;
    }
}
