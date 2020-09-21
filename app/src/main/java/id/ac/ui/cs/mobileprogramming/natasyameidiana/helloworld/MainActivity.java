package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int add = 0;
    Button count;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.angka);

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add++;
                text.setText(String.valueOf(add));
            }
        });
    }
}