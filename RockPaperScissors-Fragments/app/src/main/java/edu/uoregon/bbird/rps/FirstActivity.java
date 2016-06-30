package edu.uoregon.bbird.rps;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Brian Bird on 7/15/2015.
 */

public class FirstActivity extends Activity {

    private RpsGame game;   // Created and managed in FirstFragment

    public RpsGame getGame() {
        return game;
    }

    public void setGame(RpsGame game) {
        this.game = game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.first_activity);
    }
}
