package com.example.kimjihong.eq_test;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import android.text.TextWatcher;
import android.text.Editable;

//public class SubActivity extends Activity{
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sub);
//    }
//}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        final InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

        ConstraintLayout c_layout = (ConstraintLayout)findViewById(R.id.c_layout_wrapper_content);

        final EditText editText = (EditText)findViewById(R.id.editText_name);

//        final String name = editText.getText().toString();

        final TextView textView1 = (TextView) findViewById(R.id.text_view_state_result1);
        final TextView textView2 = (TextView) findViewById(R.id.text_view_state_result2);
        final TextView textView3 = (TextView) findViewById(R.id.text_view_state_result3);
        final TextView textView4 = (TextView) findViewById(R.id.text_view_state_result4);
        final TextView textView5 = (TextView) findViewById(R.id.text_view_state_result5);

        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Button button3 = (Button) findViewById(R.id.button_3);
        Button button4 = (Button) findViewById(R.id.button_4);
        Button button5 = (Button) findViewById(R.id.button_5);

        final Button R_button = (Button) findViewById(R.id.button_register);
        R_button.setEnabled(false);


        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView1.setText("측정");

                if(textView1.getText().toString().equals("측정") && textView2.getText().toString().equals("측정")
                        && textView3.getText().toString().equals("측정") && textView4.getText().toString().equals("측정")
                        && textView5.getText().toString().equals("측정")){
                    if(editText.getText().toString().length() != 0){
                        R_button.setEnabled(true);
                    }
                }

            }
        });

        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText("측정");

                if(textView1.getText().toString().equals("측정") && textView2.getText().toString().equals("측정")
                        && textView3.getText().toString().equals("측정") && textView4.getText().toString().equals("측정")
                        && textView5.getText().toString().equals("측정")){
                    if(editText.getText().toString().length() != 0){
                        R_button.setEnabled(true);
                    }
                }
            }

        });

        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView3.setText("측정");

                if(textView1.getText().toString().equals("측정") && textView2.getText().toString().equals("측정")
                        && textView3.getText().toString().equals("측정") && textView4.getText().toString().equals("측정")
                        && textView5.getText().toString().equals("측정")){
                    if(editText.getText().toString().length() != 0){
                        R_button.setEnabled(true);
                    }
                }
            }
        });

        button4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView4.setText("측정");

                if(textView1.getText().toString().equals("측정") && textView2.getText().toString().equals("측정")
                        && textView3.getText().toString().equals("측정") && textView4.getText().toString().equals("측정")
                        && textView5.getText().toString().equals("측정")){
                    if(editText.getText().toString().length() != 0){
                        R_button.setEnabled(true);
                    }
                }
            }
        });

        button5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView5.setText("측정");

                if(textView1.getText().toString().equals("측정") && textView2.getText().toString().equals("측정")
                        && textView3.getText().toString().equals("측정") && textView4.getText().toString().equals("측정")
                        && textView5.getText().toString().equals("측정")){
                    if(editText.getText().toString().length() != 0){
                        R_button.setEnabled(true);
                    }
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
                    if(textView1.getText().toString().equals("측정") && textView2.getText().toString().equals("측정")
                            && textView3.getText().toString().equals("측정") && textView4.getText().toString().equals("측정")
                            && textView5.getText().toString().equals("측정")){
                        R_button.setEnabled(true);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER || event.getKeyCode() == KeyEvent.KEYCODE_BACK){
//                    imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
//                    if(name.length() == 0){
//                        R_button.setEnabled(false);
//                    }
//                    else{
//                        if(textView1.getText().toString().equals("측정") && textView2.getText().toString().equals("측정")
//                                && textView3.getText().toString().equals("측정") && textView4.getText().toString().equals("측정")
//                                && textView5.getText().toString().equals("측정")){
//                            R_button.setEnabled(true);
//                        }
//                    }
//                    return true;
//                }
//                return false;
//            }
//        });


//        editText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                if(keyCode == event.KEYCODE_ENTER || keyCode == event.KEYCODE_BACK){
////                    String name = editText.getText().toString();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
//                    String name = editText.getText().toString();
//                    if(name.length() == 0){
//                        R_button.setEnabled(false);
//                    } else{
//                        if(textView1.getText().toString().equals("측정") && textView2.getText().toString().equals("측정")
//                                && textView3.getText().toString().equals("측정") && textView4.getText().toString().equals("측정")
//                                && textView5.getText().toString().equals("측정")) {
//                            R_button.setEnabled(true);
//                        }
//                    }
//                    return true;
//                }
//
//                return false;
//            }
//        });

        R_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
