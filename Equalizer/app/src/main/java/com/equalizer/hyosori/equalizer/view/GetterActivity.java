package com.equalizer.hyosori.equalizer.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.equalizer.hyosori.equalizer.R;


public class GetterActivity extends Activity implements GetterView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getter);

        final EditText editText = (EditText)findViewById(R.id.editText_name);

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


        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == event.KEYCODE_ENTER){
//                    String name = editText.getText().toString();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
                    String name = editText.getText().toString();
                    if(name.length() == 0){
                        R_button.setEnabled(false);
                    } else{
                        if(textView1.getText().toString().equals("측정") && textView2.getText().toString().equals("측정")
                                && textView3.getText().toString().equals("측정") && textView4.getText().toString().equals("측정")
                                && textView5.getText().toString().equals("측정")) {
                            R_button.setEnabled(true);
                        }
                    }
                    return true;
                }

                return false;
            }
        });



    }
}
