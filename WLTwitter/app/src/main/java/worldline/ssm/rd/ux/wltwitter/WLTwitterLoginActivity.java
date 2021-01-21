package worldline.ssm.rd.ux.wltwitter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

public class WLTwitterLoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mLoginEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        mLoginEditText = (EditText)findViewById(R.id.loginEditText);
        mPasswordEditText = (EditText)findViewById(R.id.passwordEditText);

        findViewById(R.id.loginButton).setOnClickListener(this);
        final String login = PreferenceUtils.getLogin();
        if(!TextUtils.isEmpty(login)){
            startActivity(getHomeIntent(login));
        }
    }

    @Override
    public void onClick(View v) {
        String login;
        if(TextUtils.isEmpty(mLoginEditText.getText())){
            Toast.makeText(this, R.string.error_no_login, Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(mPasswordEditText.getText())){
            Toast.makeText(this, R.string.error_no_password, Toast.LENGTH_LONG).show();
            return;
        }

        login = mLoginEditText.getText().toString();
        PreferenceUtils.setLogin(login);
        startActivity(getHomeIntent(login));

    }

    private Intent getHomeIntent(String userName){
        Intent intent = new Intent(this, WLTwitterActivity.class);
        final Bundle extras = new Bundle();
        extras.putString(Constants.Login.EXTRA_LOGIN, userName);
        intent.putExtras(extras);
        return intent;
    }
}
