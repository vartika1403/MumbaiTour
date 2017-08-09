package com.example.vartikasharma.mumbaitour;

import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListGridActivity extends AppCompatActivity {


    public Button FeedbackButton;
    @BindView(R.id.content_frame)
    public FrameLayout weatherFragment;

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
