package com.jsrn.pause.players;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jsrn.pause.MainActivity;
import com.jsrn.pause.R;

public class NetflixPlayerActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netflix_player);
    }
}
