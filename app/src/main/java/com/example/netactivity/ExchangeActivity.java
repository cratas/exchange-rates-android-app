package com.example.netactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ExchangeActivity extends Activity {

    //UI components
    TextView country;
    TextView code;
    TextView rate;
    ImageView image;
    EditText editTextFrom;
    EditText editTextTo;
    float foreignRate;

    //shared lock on listners
    private boolean isChangingText = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        //String recieved from last Intent
        String dataString = getIntent().getStringExtra("data");
        //Parsing string into String array
        String[] dataStringArray = dataString.split(" ");

        //Initializiton of UI components
        country = (TextView)findViewById(R.id.countryEx);
        code = (TextView)findViewById(R.id.codeEx);
        rate = (TextView)findViewById(R.id.rateEx);
        image = (ImageView)findViewById(R.id.imageEx);
        editTextFrom = (EditText)findViewById(R.id.editTextFrom);
        editTextTo = (EditText)findViewById(R.id.editTextTo);

        //Setting values of UI components
        code.setText(dataStringArray[0]);
        rate.setText(dataStringArray[1]);
        country.setText(dataStringArray[2]);
        image.setImageResource(getBaseContext()
                .getResources()
                .getIdentifier("flag_" + dataStringArray[0].toLowerCase(Locale.ROOT)
                        , "drawable"
                        , getBaseContext().getPackageName()));

//        Toast.makeText( getBaseContext(),dataString, Toast.LENGTH_LONG).show();

        try{
            foreignRate = Float.parseFloat(dataStringArray[1].replace(',','.'));
        }catch (Exception e) {
            e.printStackTrace();
        }



        editTextFrom.addTextChangedListener(new TextWatcher() {
            //Not used methods, gotta be defined
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            //Listener checking changes in TextField
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float crownsValue = getFloatFrom(editTextFrom);
                float resultValue = crownsValue / foreignRate;

                if (isChangingText) {
                    isChangingText = false;
                    editTextTo.setText(Float.toString(resultValue));
                } else {
                    isChangingText = true;
                }
            }
        });

        editTextTo.addTextChangedListener(new TextWatcher() {
            //Not used methods, gotta be defined
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            //Listener checking changes in TextField
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float crownsValue = getFloatFrom(editTextTo);
                float resultValue = crownsValue * foreignRate;

                if (isChangingText) {
                    isChangingText = false;
                    editTextFrom.setText(Float.toString(resultValue));
                } else {
                    isChangingText = true;
                }
            }
        });

    }

    float getFloatFrom(EditText txt) {
        try {
            return NumberFormat.getInstance().parse(txt.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
}