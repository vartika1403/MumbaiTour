package com.example.vartikasharma.mumbaitour;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListGridActivity extends AppCompatActivity {

    private String[] itemList = {"Weather", "Pubs and Bars", "Hotels", "Train Route", "Must Visit", "Feedback"};

    /*@BindView(R.id.vertical_linear_layout)
    public LinearLayout VerticalLinearLayout;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_grid);
        ButterKnife.bind(this);

       /* VerticalLinearLayout.removeAllViews();
        for(int i = 0; i < 2; i++) {
          LinearLayout linearHorizontal  =  addItemsToLayout(i);
            VerticalLinearLayout.addView(linearHorizontal);
        }*/
    }

    private LinearLayout addItemsToLayout(int i) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
        LinearLayout horizontalLinearLayout = (LinearLayout)view.findViewById(R.id.horizontal_linerar_layout);

        horizontalLinearLayout.removeAllViews();
       int x;
        if (i == 0 ) {
            x = 0;
        } else {
            x = 3;
        }
        for(int j = x; j < x + 2; i++) {
            TextView itemText = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(40, 40, 40, 40);
            itemText.setLayoutParams(params);
            itemText.setText(itemList[j]);
            itemText.setTextSize(15);
          //  itemText.setTextColor(Color.parseColor("#0B5345"));
            horizontalLinearLayout.addView(itemText);
        }
        return horizontalLinearLayout;
    }
}
