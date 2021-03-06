package ar.com.gazer.letsrun;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import ar.com.gazer.letsrun.game.LetsRunGame;

public class GameActivity extends AndroidApplication {
    private View mDecorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDecorView = getWindow().getDecorView();

        if (Constants.SUPPORT_HONEYCOMB) {
            hideSystemUi();

            getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(
                    new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if (visibility == 0) {
                                mHideHandler.postDelayed(mHideRunnable, 2000);
                            }
                        }
                    }
            );
        }

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new LetsRunGame(), config);
    }

    private void hideSystemUi() {

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }


    Handler mHideHandler = new Handler();

    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hideSystemUi();
        }
    };
}
