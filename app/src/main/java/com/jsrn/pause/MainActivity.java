package com.jsrn.pause;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Intent;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            openSettingsScreen();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openSettingsScreen() {
        Intent myIntent = new Intent(getBaseContext(), SettingsActivity.class);
        startActivityForResult(myIntent, 0);
    }

    public void hitIt(View view) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String host = sharedPref.getString("host", "");
        String [] args = {host, "p"};
        new SocketSender().execute(args);
    }

    public void volUp(View view) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String host = sharedPref.getString("host", "");
        String [] args = {host, "u"};
        new SocketSender().execute(args);
    }

    public void volDown(View view) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String host = sharedPref.getString("host", "");
        String [] args = {host, "d"};
        new SocketSender().execute(args);
    }
}

class SocketSender extends AsyncTask<String, Void, Void> {
    private Exception exception;

    @Override
    protected Void doInBackground(String... params) {
        try {
            System.out.println(params[0]);
            Socket socket = new Socket(params[0], 8080);
            try {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                System.out.println(params[1]);
                dos.writeChars(params[1]);
                socket.close();
            } catch(Exception e) {
                e.printStackTrace();
                socket.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

