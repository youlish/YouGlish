package nqc.henry.youlish.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;

import nqc.henry.youlish.R;
import nqc.henry.youlish.frament.VideoListFrament;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {
    /**
     * The duration of the animation sliding up the video in portrait.
     */
    private static final int ANIMATION_DURATION_MILLIS = 300;
    /**
     * The padding between the video list and the video in landscape orientation.
     */
    private static final int LANDSCAPE_VIDEO_PADDING_DP = 5;

    /**
     * The request code when calling startActivityForResult to recover from an API service error.
     */
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private VideoListFrament listFragment;

    private View videoBox;
    private SearchView searchView;
    private TabHost tabHost;

    private static void setLayoutSize(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

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

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("ALL");
        tabSpec.setContent(R.id.layoutAllVideo);
        tabSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_home_selected));
        tabHost.addTab(tabSpec);


        tabSpec = tabHost.newTabSpec("US");
        tabSpec.setContent(R.id.layoutUSVideo);
        tabSpec.setIndicator("US");
        tabSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_us));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("UK");
        tabSpec.setContent(R.id.layoutUKVideo);
        tabSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_uk));
        tabHost.addTab(tabSpec);


        tabSpec = tabHost.newTabSpec("AUS");
        tabSpec.setContent(R.id.layoutAUSVideo);
        tabSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_aus));
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);
        setTabColor();
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

    public void setTabColor() {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colorWhite)); // unselected
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(getResources().getColor(R.color.colorPrimary)); // selected
        TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        setTabIcon(tabHost.getCurrentTab());

        tv.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    public void setTabIcon(int i) {
        ImageView im = (ImageView) tabHost.getCurrentTabView().findViewById(android.R.id.icon);
        ImageView im0 = (ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.icon);
        ImageView im1 = (ImageView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.icon);
        ImageView im2 = (ImageView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.icon);
        ImageView im3 = (ImageView) tabHost.getTabWidget().getChildAt(3).findViewById(android.R.id.icon);

        switch (i) {
            case 0:
                im0.setImageResource(R.drawable.ic_home_selected);
                im1.setImageResource(R.drawable.ic_us);
                im2.setImageResource(R.drawable.ic_uk);
                im3.setImageResource(R.drawable.ic_aus);
                break;
            case 1:
                im0.setImageResource(R.drawable.ic_home);
                im.setImageResource(R.drawable.ic_us_selected);
                im2.setImageResource(R.drawable.ic_uk);
                im3.setImageResource(R.drawable.ic_aus);
                break;
            case 2:
                im0.setImageResource(R.drawable.ic_home);
                im.setImageResource(R.drawable.ic_uk_selected);
                im3.setImageResource(R.drawable.ic_aus);
                im1.setImageResource(R.drawable.ic_us);
                break;
            case 3:
                im0.setImageResource(R.drawable.ic_home);
                im.setImageResource(R.drawable.ic_aus_selected);
                im2.setImageResource(R.drawable.ic_uk);
                im1.setImageResource(R.drawable.ic_us);
                break;
        }
    }

    @Override
    public void onTabChanged(String s) {
        setTabColor();

        switch (s) {
            case "ALL":
                break;
            case "US":
                break;
            case "UK":
                break;
            case "AUS":
                break;
        }
    }
}
