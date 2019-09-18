package my.rockpilgrim.chucknorrisjokes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatFragment;
import my.rockpilgrim.chucknorrisjokes.R;

public class ApiInfoFragment extends MvpAppCompatFragment {

    @BindView(R.id.api_webView)
    public WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.api_info_fragment, container, false);
        ButterKnife.bind(this, root);
        initWebView();
        return root;
    }

    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.icndb.com/api/");
    }
}
