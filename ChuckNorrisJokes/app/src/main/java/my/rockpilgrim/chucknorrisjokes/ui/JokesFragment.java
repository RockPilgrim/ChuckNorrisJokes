package my.rockpilgrim.chucknorrisjokes.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import my.rockpilgrim.chucknorrisjokes.R;
import my.rockpilgrim.chucknorrisjokes.presenter.JokesPresenter;
import my.rockpilgrim.chucknorrisjokes.ui.components.RecyclerJokesAdapter;
import my.rockpilgrim.chucknorrisjokes.ui.interfaces.MvpJokesView;

public class JokesFragment extends MvpAppCompatFragment implements MvpJokesView {


    public static final String TAG = "JokesFragment";
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    @BindView(R.id.count_editText)
    public EditText countEditText;

    @BindView(R.id.reload_button)
    public Button reloadButton;

    private RecyclerJokesAdapter recyclerAdapter;

    private InputMethodManager imm;


    @InjectPresenter
    public JokesPresenter presenter;

    @ProvidePresenter
    public JokesPresenter providePresenter() {
        return new JokesPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        closeKeyboard();
    }

    private void closeKeyboard() {
        Log.i(TAG, "closeKeyboard");
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.jokes_fragment, container, false);
        ButterKnife.bind(this, root);
        initRecycler(root);
        setListeners();
        return root;
    }

    private void setListeners() {
        countEditText.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    sendCount();
                    return true;
                }
            }
            return false;
        });
    }

    private void initRecycler(View root) {
        recyclerAdapter = new RecyclerJokesAdapter(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(recyclerAdapter);
    }


    @Override
    public void updateList() {
        recyclerAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.reload_button)
    public void onClickReload() {
        sendCount();
    }

    private void sendCount() {
        countEditText.clearFocus();
        closeKeyboard();
        if (countEditText.getText().toString().equals("")) {
            makeToast("Insert count");
            return;
        }
        int count = Integer.parseInt(countEditText.getText().toString());
        if (count > 619 || count == 0) {
            makeToast("Count should be from 1 to 619");
            return;
        }
        presenter.connectToServer(Integer.parseInt(countEditText.getText().toString()));
        countEditText.setText("");
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
