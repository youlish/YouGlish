package nqc.henry.youlish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;

import nqc.henry.youlish.frament.VideoListFrament;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {
    /** The duration of the animation sliding up the video in portrait. */
    private static final int ANIMATION_DURATION_MILLIS = 300;
    /** The padding between the video list and the video in landscape orientation. */
    private static final int LANDSCAPE_VIDEO_PADDING_DP = 5;

    /** The request code when calling startActivityForResult to recover from an API service error. */
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private VideoListFrament listFragment;

    private View videoBox;
    private SearchView searchView;
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listFragment = (VideoListFrament) getFragmentManager().findFragmentById(R.id.list_fragment);
        listFragment.setActivity(MainActivity.this);
        /*listFragment.getView().setVisibility(isFullscreen ? View.GONE : View.VISIBLE);
        listFragment.setLabelVisibility(isPortrait);*/
        setLayoutSize(listFragment.getView(), MATCH_PARENT, MATCH_PARENT);




        checkYouTubeApi();
        loadTabs();
    }

    private static void setLayoutSize(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    private void checkYouTubeApi() {
        YouTubeInitializationResult errorReason =
                YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else if (errorReason != YouTubeInitializationResult.SUCCESS) {
            String errorMessage =
                    String.format("error", errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
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
