package com.example.teareport;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LandActivity extends AppCompatActivity {

    Button plusbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_land);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    plusbtn = findViewById(R.id.btnplus);

        plusbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showInputDialog(new Firstinterface.InputDialogCallback() {
                @Override
                public void onInputText(String inputText) {
                    LinearLayout linearLayout = findViewById(R.id.linearLayout);

                    // Create a new Button
                    Button button = new Button(LandActivity.this);
                    button.setText(inputText); // Set the input text as button text
                    button.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Intent intent = new Intent(Firstinterface.this, LandActivity.class);
                            //startActivity(intent);
                            finish();
                        }
                    });

                    // Add the button to the layout
                    linearLayout.addView(button);
                }
            });
        }
    });
}

private void showInputDialog(Firstinterface.InputDialogCallback callback) {
    // Inflate the custom layout/view
    LayoutInflater inflater = LayoutInflater.from(this);
    View dialogView = inflater.inflate(R.layout.dialog_input, null);

    // Create an AlertDialog Builder
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setView(dialogView);

    // Add action buttons
    builder.setPositiveButton("OK", (dialog, which) -> {
        // Find the input field
        EditText editTextInput = dialogView.findViewById(R.id.editTextInput);
        String inputText = editTextInput.getText().toString();
        // Return the input text through the callback
        callback.onInputText(inputText);
    });

    builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

    // Create and show the dialog
    AlertDialog dialog = builder.create();
    dialog.show();
}

// Define the callback interface
public interface InputDialogCallback {
    void onInputText(String inputText);
}
}