package my.rockpilgrim.chucknorrisjokes.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import my.rockpilgrim.chucknorrisjokes.R;
import my.rockpilgrim.chucknorrisjokes.ui.components.JokesPagerAdapter;

public class MainActivity extends MvpAppCompatActivity {

    public static final String TAG = "MainActivity";
    @BindView(R.id.fragments_viewPager)
    public ViewPager viewPager;

    @BindView(R.id.jokes_button)
    public ToggleButton jokesButton;
    @BindView(R.id.apiInfo_button)
    public ToggleButton apiInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initPager();
    }

    private void initPager() {
        JokesPagerAdapter pagerAdapter = new JokesPagerAdapter(getSupportFragmentManager()
                , FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(pagerAdapter);
        setChangePageListener();
    }

    private void setChangePageListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == JokesPagerAdapter.JOKES_FRAGMENT) {
                    jokesButton.setChecked(true);
                    apiInfoButton.setChecked(false);
                } else {
                    jokesButton.setChecked(false);
                    apiInfoButton.setChecked(true);
                }
                Log.i(TAG, "onPageSelected" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setPage(int index) {
        viewPager.setCurrentItem(index);
    }

    @OnClick({R.id.jokes_button, R.id.apiInfo_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jokes_button:
                setPage(JokesPagerAdapter.JOKES_FRAGMENT);
                jokesButton.setChecked(true);
                apiInfoButton.setChecked(false);
                break;
            case R.id.apiInfo_button:
                setPage(JokesPagerAdapter.API_FRAGMENT);
                jokesButton.setChecked(false);
                apiInfoButton.setChecked(true);
                break;
        }
    }
}
