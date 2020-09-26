package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        done = (Button) findViewById(R.id.button);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name  = (EditText) findViewById(R.id.inputName);
                EditText phone = (EditText) findViewById(R.id.inputPhone);
                TextView res = (TextView) findViewById(R.id.result);
                res.setText(ActivityHelper.giveResult(name.getText().toString(), phone.getText().toString()));
            }
        });
            }
}