package com.jiuzhang.guojing.yo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO find EditText by id
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.text_anim);
        final EditText editText = (EditText) findViewById(R.id.name); // final declare it is a fixed reference
        // TODO set up Buttons
        // find the Buttons you declared in the layout file by their ids
        // set up the click event listeners of the buttons
        // in each of the click event functions, display a toast that shows the text of the EditText

        final Button btn1 = (Button) findViewById(R.id.btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.startAnimation(animation);
                Toast.makeText(MainActivity.this, btn1.getText() + " " + editText.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        final Button btn2 = (Button) findViewById(R.id.btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2.startAnimation(animation);
                Toast.makeText(MainActivity.this, btn2.getText() + " " + editText.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        final Button btn3 = (Button) findViewById(R.id.btn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn3.startAnimation(animation);
                Toast.makeText(MainActivity.this, btn3.getText() + " " + editText.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        final Button btn4 = (Button) findViewById(R.id.btn_4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn4.startAnimation(animation);
                Toast.makeText(MainActivity.this, btn4.getText() + " " + editText.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
