package rlh35.cs262.calvin.edu.homework02;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {
    EditText mIDInput;
    TextView mPlayerInfo;

    /**
     * This method runs when the activity is run, in this case when the app is run.
     * It sets up the LoaderManager which works with the PlayerLoader class to retrieve
     * information about players from the monopoly database.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        mIDInput = (EditText) findViewById(R.id.filter_text);
        mPlayerInfo = (TextView) findViewById(R.id.player_info);
    }

    /**
     * I don't know what this method does.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * I don't know what this method does.
     * @param item
     * @return
     */
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

    /**
     * This method is supposed to retrieve information about players from the PlayerLoader.
     * @param view
     */
    public void fetchPlayer(View view) {
        mPlayerInfo.setText("I can't figure out how to get the data from the database and display it here.");

        /*String queryString = mIDInput.getText().toString();
        List<String> queries = new ArrayList<String>(3);
        queries.add("1");
        queries.add("2");
        queries.add("3");

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queries.contains(queryString)) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            //mPlayerInfo.setText(R.string.loading);
        } else {
            if (queryString.length() == 0) {
                mPlayerInfo.setText("Please enter a search term");
            } else {
                mPlayerInfo.setText("Please check your network connection and try again.");
            }
        }*/
    }

    /**
     * I don't know what this method does.
     * @param i
     * @param args
     * @return
     */
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle args) {
        return new PlayerLoader(this, args.getString("queryString"));
    }

    /**
     * I don't know what this method does.
     * @param loader
     * @param s
     */
    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject person = itemsArray.getJSONObject(i);
                String playerID = null;
                JSONObject volumeInfo = person.getJSONObject("playerInfo");

                try {
                    playerID = volumeInfo.getString("title");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (playerID != null) {
                    mPlayerInfo.setText(playerID);
                    return;
                }
            }
        } catch (Exception e) {
            mPlayerInfo.setText("No Results Found");
            e.printStackTrace();
        }
    }

    /**
     * I don't know what this method does.
     * @param loader
     */
    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
