package worldline.ssm.rd.ux.wltwitter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.fragments.TweetsFragment;
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

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container, new TweetsFragment()).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wltwitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.actionLogout){
            PreferenceUtils.setLogin(null);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
