package com.example.mothupii_.ilocatefriends;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Login Activity - Login Form
 */
class LoginActivity extends Activity
{

    private static String ACTIVITY = "LoginActivity";
    private EditText emailAddress;
    private EditText password;
    private static Context context;
    private Context applicationContext;

    /**
     * onCreate()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getName(), "onCreate() called");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
    }

    /**
     * onResume()
     */
    public void onResume() {
        super.onResume();
        if(!isNetworkAvailable()) {
            this.toastIt("Network is unavailable! \nMake sure you are connected to the internet.");
        }
    }

    /**
     * onSignInClick() - Sign In button clicked
     * @param view
     */
    public void onSignInClick(View view) {
        Log.d(getClass().getName(), "onSignInClick() clicked");
        StringBuilder validationErrorMessage = new StringBuilder("");
        this.emailAddress = (EditText) findViewById(R.id.emailAddress);
        this.password = (EditText) findViewById(R.id.password);
        String username = this.emailAddress.getText().toString();
        String password = this.password.getText().toString();
        boolean formIsValid = true;

        if(emailAddress.length() == 0) {
            formIsValid = false;
            String errorMessage = "Email address is empty.\n";
            validationErrorMessage.append(errorMessage);
        }
        if(password.length() == 0) {
            formIsValid = false;
            String errorMessage = "Password is empty.\n";
            validationErrorMessage.append(errorMessage);
        }

        if(formIsValid) {
            if(isNetworkAvailable()) {
                ParseUser.logInInBackground(username, password, new LogInCallback()
                {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e != null) {
                            toastIt(e.getLocalizedMessage());
                        } else {
                            Intent intent = new Intent(LoginActivity.this, Dispatcher.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
            }
            else {
                this.toastIt("Network is unavailable! \nMake sure you are connected to the internet.");
            }
        }
        else {
            this.toastIt(validationErrorMessage.toString());
        }
    }

    /**
     * capitalizeString()
     * @param str
     * @return String
     */
    public String capitalizeString(String str) {
        String newString = str.substring(0, 1).toUpperCase() + str.substring(1);
        return newString;
    }

    /**
     * toastIt() - toast used for form verification
     * @param errorMessage
     */
    private void toastIt(String errorMessage) {
        Log.d(ACTIVITY, errorMessage);
        Toast t = Toast.makeText(getApplicationContext(), this.capitalizeString(errorMessage), Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }

    /**
     * onPasswordRecoverClick() - Password Recover link clicked
     * @param view
     */
    public void onPasswordRecoverClick(View view) {
        Log.d(getClass().getName(), "onPasswordRecoverClick() clicked");
        Intent intent = new Intent(this, PasswordRecoveryActivity.class);
        startActivity(intent);

    }

    /**
     * getActivityContext() - gets activity context, good to use in inner classes
     * @return Context
     */
    public static Context getActivityContext() {
        return LoginActivity.context;
    }

    /**
     * isNetworkAvailable()
     * @return boolean
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivitymanager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivitymanager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }

    public Context getApplicationContext()
    {
        return applicationContext;
    }
}
