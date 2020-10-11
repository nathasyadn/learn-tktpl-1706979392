package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.userView.UserViewModel;
import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.util.Helper;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    UserViewModel viewModel = new UserViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListenerButton();
    }

    private void setListenerButton() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = findViewById(R.id.editText2);
                EditText email = findViewById(R.id.editText3);
                EditText description = findViewById(R.id.editText4);
                if (Helper.isNotNullAndNotEmpty(name.getText().toString()) && Helper.isNotNullAndNotEmpty(email.getText().toString())
                        && Helper.isNotNullAndNotEmpty(description.getText().toString())) {
                    viewModel.addUser(getApplicationContext(),
                            name.getText().toString(),
                            email.getText().toString(),
                            description.getText().toString());
                    Toast.makeText(getApplicationContext(), name.getText().toString() + " Added to database", LENGTH_LONG).show();
                    name.setText("");
                    email.setText("");
                    description.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Semua field harus diisi", LENGTH_LONG).show();
                }
            }
        });

        Button viewButton = findViewById(R.id.button5);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FragmentActivityManager.class);
                startActivity(intent);
            }
        });
    }
}