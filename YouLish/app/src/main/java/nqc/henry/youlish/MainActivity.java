package nqc.henry.youlish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    private SearchView searchView;
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadTabs();
    }

    public void loadTabs() {
        tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        //Tab contacts
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("ALL");
        tabSpec.setContent(R.id.layoutAllVideo);
        tabSpec.setIndicator("ALL");
        tabHost.addTab(tabSpec);


        //Tab Gallery
        tabSpec = tabHost.newTabSpec("US");
        tabSpec.setContent(R.id.layoutUSVideo);
        tabSpec.setIndicator("US");
        tabHost.addTab(tabSpec);

        //Tab Phone
        tabSpec = tabHost.newTabSpec("UK");
        tabSpec.setContent(R.id.layoutUKVideo);
        tabSpec.setIndicator("UK");
        tabHost.addTab(tabSpec);
        //Tab list songs
        tabSpec = tabHost.newTabSpec("AUS");
        tabSpec.setContent(R.id.layoutAUSVideo);
        tabSpec.setIndicator("AUS");
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_hint));
        return true;
    }

    @Override
    public void onTabChanged(String s) {
        switch (s) {
            case "ALL":
                Toast.makeText(this, "ALL", Toast.LENGTH_SHORT).show();
                break;
            case "US":
                Toast.makeText(this, "US", Toast.LENGTH_SHORT).show();
                break;
            case "Contacts":

                break;
            case "UK":
                Toast.makeText(this, "UK", Toast.LENGTH_SHORT).show();
                break;
            case "AUS":
                Toast.makeText(this, "AUS", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
