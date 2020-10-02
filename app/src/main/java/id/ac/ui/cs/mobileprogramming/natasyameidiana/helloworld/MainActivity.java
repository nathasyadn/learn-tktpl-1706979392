package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private int number = 0;
    private Handler handler = new Handler();
    private Runnable runnable;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pressStart(View view) {
        if (!isRunning) {
            number = 0;
            runnable = new Runnable() {
                @Override
                public void run() {
                    TextView textView = findViewById(R.id.txtTime);
                    textView.setText(String.valueOf(number));
                    number++;
                    textView.setText(String.valueOf(number));
                    handler.postDelayed(this, 1000);
                }
            };
            handler.post(runnable);
            isRunning = true;
        }
    }

    public void pressEnd(View view) {
        handler.removeCallbacks(runnable);
        number = 0;
        TextView textView = findViewById(R.id.txtTime);
        textView.setText(String.valueOf(number));
        isRunning = false;
    }

    public void pressBack(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Press Exit", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResumed called", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        Toast.makeText(getApplicationContext(), "onPause called", Toast.LENGTH_LONG).show();
        super.onPause();
    }
}