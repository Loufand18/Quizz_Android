package com.example.appresume.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appresume.Model.User;
import com.example.appresume.R;

public class MainActivity extends AppCompatActivity {
      private EditText editext;
      private TextView textView;
      private Button button;
     private User user1;
     public static final int gameActivity_id=12;
    private SharedPreferences mPreferences;
    public static final String Score="Score";
    public static final String Firstname="Firstname";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("A2V application");
        editext=(EditText)findViewById(R.id.editext);
        textView=(TextView)findViewById(R.id.textview);
        button=(Button)findViewById(R.id.button);
        button.setEnabled(false);
        user1 =new User();
        mPreferences=getPreferences(MODE_PRIVATE);

        editext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               button.setEnabled(s.toString().length()!=0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname=editext.getText().toString();
                user1.setFirstname(firstname);
                mPreferences.edit().putString(Firstname,user1.getFirstname()).apply();
                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivity,gameActivity_id);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (gameActivity_id == requestCode && RESULT_OK == resultCode) {
            int score = data.getIntExtra(GameActivity.Bundle_Extra_Score, 0);
            mPreferences.edit().putInt(Score,score).apply();
            greetUser();
        }
    }
    private void greetUser() {
        String firstname = mPreferences.getString(Firstname, null);

        if (null != firstname) {
            int score = mPreferences.getInt(Score, 0);

            String fulltext = "Bon retour, " + firstname
                    + "!\nTon dernier Score " + score
                    + ", Veux tu faire mieux ?";
            textView.setText(fulltext);
            editext.setText(firstname);
            editext.setSelection(firstname.length());
            button.setEnabled(true);
        }
    }
}
