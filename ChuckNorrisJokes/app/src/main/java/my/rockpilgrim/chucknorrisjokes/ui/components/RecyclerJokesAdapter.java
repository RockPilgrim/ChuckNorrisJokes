package my.rockpilgrim.chucknorrisjokes.ui.components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import my.rockpilgrim.chucknorrisjokes.R;
import my.rockpilgrim.chucknorrisjokes.presenter.interfaces.IJokesHolder;

public class RecyclerJokesAdapter extends RecyclerView.Adapter<RecyclerJokesHolder> {

    private IJokesHolder jokesHolder;

    public RecyclerJokesAdapter(IJokesHolder jokesHolder) {
        this.jokesHolder = jokesHolder;
    }

    @NonNull
    @Override
    public RecyclerJokesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joke, parent, false);
        return new RecyclerJokesHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerJokesHolder holder, int position) {
        holder.bind(position,jokesHolder);
    }

    @Override
    public int getItemCount() {
        return jokesHolder.size();
    }
}
