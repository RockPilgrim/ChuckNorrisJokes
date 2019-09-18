package my.rockpilgrim.chucknorrisjokes.ui.components;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.rockpilgrim.chucknorrisjokes.R;
import my.rockpilgrim.chucknorrisjokes.presenter.interfaces.IJokesHolder;

public class RecyclerJokesHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.joke_textView)
    public TextView jokeTextView;

    public RecyclerJokesHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(int index, IJokesHolder jokesHolder) {
        jokeTextView.setText(jokesHolder.getJoke(index));
    }
}
