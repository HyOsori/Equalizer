package com.equalizer.hyosori.equalizer.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.equalizer.hyosori.equalizer.R;


public class GetterActivity extends Activity implements View.OnClickListener, GetterView {

    InputMethodManager imm;
    ConstraintLayout c_layout;

    EditText editText;

    Button[] buttons;
    int[] button_id = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3};

    TextView[] textViews;
    int[] textView_id = {R.id.textView_state_result0, R.id.textView_state_result1, R.id.textView_state_result2, R.id.textView_state_result3};

    Button R_button;

    int i;

    public void checkEnb(TextView[] tvs, EditText et) {
        if (tvs[0].getText().toString().equals("측정") && tvs[1].getText().toString().equals("측정")
                && tvs[2].getText().toString().equals("측정") && tvs[3].getText().toString().equals("측정")
                && et.getText().toString().length() != 0) {
            R_button.setEnabled(true);
        }
    }

    public void setResult(TextView[] tvs, int i) {
        tvs[i].setText("측정");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_0:
                setResult(textViews, 0);
                checkEnb(textViews, editText);
                break;
            case R.id.button_1:
                setResult(textViews, 1);
                checkEnb(textViews, editText);
                break;
            case R.id.button_2:
                setResult(textViews, 2);
                checkEnb(textViews, editText);
                break;
            case R.id.button_3:
                setResult(textViews, 3);
                checkEnb(textViews, editText);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getter);

        imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        c_layout = (ConstraintLayout) findViewById(R.id.c_layout_wrapper_content);

        editText = (EditText) findViewById(R.id.editText_name);

        textViews = new TextView[4];
        for (i = 0; i < 4; i++) {
            textViews[i] = (TextView) findViewById(textView_id[i]);
        }

        buttons = new Button[4];
        for (i = 0; i < buttons.length; i++) {
            buttons[i] = (Button) findViewById(button_id[i]);
            buttons[i].setOnClickListener(this);
        }

        R_button = (Button) findViewById(R.id.button_register);
        R_button.setEnabled(false);

        c_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

            }
        });

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //String name = editText.getText().toString();
                if (s.length() <= 0) {
                    R_button.setEnabled(false);
                } else {
                    checkEnb(textViews, editText);
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
