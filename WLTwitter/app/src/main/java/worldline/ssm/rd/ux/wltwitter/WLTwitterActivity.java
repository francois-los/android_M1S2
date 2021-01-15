package worldline.ssm.rd.ux.wltwitter;

import androidx.appcompat.app.AppCompatActivity;

import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class WLTwitterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = getIntent();
        if(intent!=null){
            final Bundle extras = intent.getExtras();
            if(null != extras){
                final String login = extras.getString(Constants.Login.EXTRA_LOGIN);
                getSupportActionBar().setSubtitle(login);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
