package com.uxui.equalizer.hyosori.ux_ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kimjihong on 2017. 7. 25..
 */


public class SubActivity extends Activity {

    InputMethodManager imm;
    ConstraintLayout c_layout;

    EditText editText;

    Button[] buttons;
    int[] button_id = {R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5};

    TextView[] textViews;
    int[] textView_id = {R.id.text_view_state_result1, R.id.text_view_state_result2, R.id.text_view_state_result3, R.id.text_view_state_result4,
                         R.id.text_view_state_result5};

    Button R_button;

    int i;
    boolean enb;

    public boolean checkEnb(TextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tv5, EditText et){
        if(tv1.getText().toString().equals("측정") && tv2.getText().toString().equals("측정")
                && tv3.getText().toString().equals("측정") && tv4.getText().toString().equals("측정")
                && tv5.getText().toString().equals("측정") && et.getText().toString().length() != 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        c_layout = (ConstraintLayout)findViewById(R.id.c_layout_wrapper_content);

        editText = (EditText)findViewById(R.id.editText_name);

        textViews = new TextView[5];

        for(i = 0; i < 5; i++){
            textViews[i] = (TextView)findViewById(textView_id[i]);
        }

        buttons = new Button[5];

        for(i = 0; i < buttons.length; i++){
            buttons[i] = (Button)findViewById(button_id[i]);
        }

        R_button = (Button) findViewById(R.id.button_register);
        R_button.setEnabled(false);


        buttons[0].setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViews[0].setText("측정");

                enb = checkEnb(textViews[0],textViews[1],textViews[2],textViews[3],textViews[4],editText);
                if(enb == true){
                    R_button.setEnabled(true);
                }

            }
        });

        buttons[1].setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViews[1].setText("측정");

                enb = checkEnb(textViews[0],textViews[1],textViews[2],textViews[3],textViews[4],editText);
                if(enb == true){
                    R_button.setEnabled(true);
                }

            }

        });

        buttons[2].setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViews[2].setText("측정");

                enb = checkEnb(textViews[0],textViews[1],textViews[2],textViews[3],textViews[4],editText);
                if(enb == true){
                    R_button.setEnabled(true);
                }
            }
        });

        buttons[3].setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViews[3].setText("측정");

                enb = checkEnb(textViews[0],textViews[1],textViews[2],textViews[3],textViews[4],editText);
                if(enb == true){
                    R_button.setEnabled(true);
                }
            }
        });

        buttons[4].setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViews[4].setText("측정");

                enb = checkEnb(textViews[0],textViews[1],textViews[2],textViews[3],textViews[4],editText);
                if(enb == true){
                    R_button.setEnabled(true);
                }
            }
        });

        c_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(editText.getWindowToken(),0);

            }
        });

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //String name = editText.getText().toString();
                if(s.length() <= 0){
                    R_button.setEnabled(false);
                }
                else{
                    enb = checkEnb(textViews[0],textViews[1],textViews[2],textViews[3],textViews[4],editText);
                    if(enb == true){
                        R_button.setEnabled(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        R_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });
        //String name = editText.getText().toString();

    }

}
