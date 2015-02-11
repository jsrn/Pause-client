package com.jsrn.pause;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jsrn.pause.players.NetflixPlayerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlayerSelection extends Activity {

    List<Map<String, String>> playerList = new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);

        initList();

        // We get the ListView component from the layout
        ListView lv = (ListView) findViewById(R.id.playerListView);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
                Intent myIntent = null;

                switch((int)id) {
                    case 0:
                        myIntent = new Intent(getBaseContext(), MainActivity.class);
                        break;
                    case 1:
                        myIntent = new Intent(getBaseContext(), NetflixPlayerActivity.class);
                        break;
                }

                startActivityForResult(myIntent, 0);
            }
        });

        // This is a simple adapter that accepts as parameter
        // Context
        // Data list
        // The row layout that is used during the row creation
        // The keys used to retrieve the data
        // The View id used to show the data. The key number and the view id must match
        SimpleAdapter simpleAdpt = new SimpleAdapter(this, playerList, android.R.layout.simple_list_item_1, new String[] {"player"}, new int[] {android.R.id.text1});

        lv.setAdapter(simpleAdpt);
    }

    private void initList() {
        playerList.add(createPlanet("player", "Default"));
        playerList.add(createPlanet("player", "Netflix"));
    }

    private HashMap<String, String> createPlanet(String key, String name) {
        HashMap<String, String> player = new HashMap<String, String>();
        player.put(key, name);

        return player;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
