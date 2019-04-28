package com.mumbaitourist.tourist.mumbaitour;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListGridActivity extends AppCompatActivity {
    private static final String LOG_TAG = ListGridActivity.class.getSimpleName();

    private String[] drawerTitles;
    @BindView(R.id.content_frame)
    public FrameLayout weatherFragment;
    @BindView(R.id.left_drawer)
    public ListView drawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_grid);
        ButterKnife.bind(this);
    }

    private void openWeatherFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.addToBackStack("tag");
        ft.replace(R.id.content_frame, new FragmentWeatherDisplay()).commit();
    }
}
