package my.rockpilgrim.chucknorrisjokes.ui.interfaces;

import moxy.MvpView;

public interface MvpJokesView extends MvpView {

    void updateList();
    void makeToast(String text);

}
