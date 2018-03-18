package fr.wilcodeschool.wildtweetv2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static String EXTRA_FIRSTNAME="EXTRA_FIRSTNAME";
    public static String EXTRA_LASTNAME = "EXTRA_LASTNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imagelogo = findViewById(R.id.image_logo);
        Drawable logo = ContextCompat.getDrawable(this, R.drawable.rockilla_25);
        imagelogo.setImageDrawable(logo);

        final Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //créer une variable et récuperer l'Id de l'éléments
                EditText editlogin = findViewById(R.id.edit_login);
                //créer une variable
                String loginValue= editlogin.getText().toString();
                EditText editpassword = findViewById(R.id.edit_password);
                String passwordValue = editpassword.getText().toString();
                Toast.makeText(MainActivity.this, "Hello " + loginValue+" "+ passwordValue, Toast.LENGTH_LONG).show();
                if (loginValue.isEmpty()||passwordValue.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill your firstname ans lastname", Toast.LENGTH_SHORT).show();
                }else{
                    Intent goToTweetlist = new Intent(MainActivity.this,ListTweetActivity.class);
                    goToTweetlist.putExtra(EXTRA_FIRSTNAME, loginValue);
                    goToTweetlist.putExtra("lastname", passwordValue);
                    MainActivity.this.startActivity(goToTweetlist);
                }
            }
        });
        
        CheckBox checkboxCGU = findViewById(R.id.checkbox_cgu);
        checkboxCGU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
               EditText editlogin = findViewById(R.id.edit_login);
               EditText editpassword= findViewById(R.id.edit_password);

               editlogin.setEnabled(isChecked);
               editpassword.setEnabled(isChecked);
               buttonLogin.setEnabled(isChecked);
            }
        });
        


    }
}
