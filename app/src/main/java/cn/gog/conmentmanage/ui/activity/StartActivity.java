package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.UserInfo;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class StartActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        ImageView back_gr  = (ImageView)findViewById(R.id.back_gr);
        back_gr.postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent goMain = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(goMain);

                finish();
            }
        },3000);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }


}
