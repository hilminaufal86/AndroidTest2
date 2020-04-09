package com.example.app_test1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    String UserName;
    TextView UserField;
    Button NextButton;
    Button PalindromeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_one);

        NextButton = findViewById(R.id.nextButton);
        UserField = findViewById(R.id.nameField);
        PalindromeButton = findViewById(R.id.polindromButton);

        UserField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                UserName = s.toString();
            }
        });
    }

    public void onClickBtn(View v) {
        if (UserName!=null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("USERNAME",UserName);
            startActivity(intent);
        }
    }

    public void onClickPalindromeBtn(View v) {
        if (UserName!=null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            PalindromeButton.setBackground(getDrawable(R.drawable.btn_signup_selected));
            if (isPalindrome(UserName)) {
                builder.setMessage("is Palindrom");
            } else {
                builder.setMessage("not Palindrom");
            }
            builder.setNeutralButton("OK",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    PalindromeButton.setBackground(getDrawable(R.drawable.btn_signup_normal));
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }

    private Boolean isPalindrome(String s) {
        int j = s.length()-1;
        int i=0;
        Boolean palindrome = true;
        while (i<=j ) {
            if (s.substring(i,i+1).equals(s.substring(j,j+1))) {
                j -= 1;
                i += 1;
            }
            else {
                return false;
            }
        }

        return palindrome;
    }
}
