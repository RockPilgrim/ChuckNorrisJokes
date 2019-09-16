package my.rockpilgrim.chucknorrisjokes.ui.components;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import my.rockpilgrim.chucknorrisjokes.ui.ApiInfoFragment;
import my.rockpilgrim.chucknorrisjokes.ui.JokesFragment;

public class JokesPagerAdapter extends FragmentPagerAdapter {

    public static final int JOKES_FRAGMENT = 0;
    public static final int API_FRAGMENT = 1;

    public JokesPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.i("Pager", "/" + position);
        if (position == JOKES_FRAGMENT) {
            return new JokesFragment();
        } else {
            return new ApiInfoFragment();
        }
    }



    @Override
    public int getCount() {
        return 2;
    }
}
