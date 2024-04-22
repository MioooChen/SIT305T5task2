package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextFullName, editTextUsernameSignup, editTextPasswordSignup, editTextConfirmPassword;

    private Button buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextFullName = findViewById(R.id.editTextFullName);
        editTextUsernameSignup = findViewById(R.id.editTextUsernameSignup);
        editTextPasswordSignup = findViewById(R.id.editTextPasswordSignup);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        buttonCreateAccount.setOnClickListener(v -> {
            String fullName = editTextFullName.getText().toString().trim();
            String username = editTextUsernameSignup.getText().toString().trim();
            String password = editTextPasswordSignup.getText().toString().trim();
            String confirmPassword = editTextConfirmPassword.getText().toString().trim();

            // 验证密码和确认密码是否匹配且长度不小于6位
            if (password.equals(confirmPassword) && password.length() >= 6) {
                User user = new User(fullName, username, password);
                Database.addUser(user);


                Toast.makeText(SignUpActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                // 返回
                finish();
            } else {
                Toast.makeText(SignUpActivity.this, "Passwords do not match or are too short", Toast.LENGTH_SHORT).show();
            }
        });
    }
}