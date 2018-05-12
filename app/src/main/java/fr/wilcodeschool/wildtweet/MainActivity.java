package fr.wilcodeschool.wildtweet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    public static String EXTRA_LOGIN = "EXTRA_LOGIN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editlogin = findViewById(R.id.edit_login);
        //initialiser les shared preferences
        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        //récupérer l'username du cache
        String usernameCache = sharedPref.getString("username","");
        editlogin.setText(usernameCache);

        ImageView imagelogo = findViewById(R.id.image_logo);
        Drawable logo = ContextCompat.getDrawable(this, R.drawable.rockilla_25);
        imagelogo.setImageDrawable(logo);

        final Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //créer une variable et récuperer l'Id de l'éléments

                //créer une variable
                String loginValue= editlogin.getText().toString();
                EditText editpassword = findViewById(R.id.edit_password);
                String passwordValue = editpassword.getText().toString();

                if (loginValue.isEmpty()||passwordValue.isEmpty()){
                    Toast.makeText(MainActivity.this, R.string.fill_login_password, Toast.LENGTH_SHORT).show();
                }else{
                    // enregistrer dans le cache de l'application
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username",loginValue);
                    editor.commit();

                    //TODO : initialiser l'utilisateur
                    WilderModel wilder = new WilderModel(loginValue,passwordValue);

                    Intent goToTweetlist = new Intent(MainActivity.this,ListTweetActivity.class);
                    goToTweetlist.putExtra(EXTRA_LOGIN, wilder.getUsername());

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
