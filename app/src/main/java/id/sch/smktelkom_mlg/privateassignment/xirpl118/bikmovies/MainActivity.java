package id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.adapter.NowPlayingAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.adapter.TopRatedAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.fragment.FavFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.fragment.NowPlayingFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.fragment.TopRatedFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NowPlayingAdapter.INowPlayingAdapter, TopRatedAdapter.ITopRatedAdapter
{

    public static final String RESULTTITLE = "resultTitle";
    public static final String RESULTOVERVIEW = "resultOverview";
    public static final String RESULTBACKDROP = "resultBackdrop";
    public static final String RESULTPOSTER = "resultPoster";
    public static final String RESULTRELEASE = "resultRelease";
    public static final String RESULTAVERAGE = "resultAverage";
    public static final String RESULTCOUNT = "resultCount";
    public static final String RESULTPOPULARITY = "resultPopuarity";
    public static final String RESULTLANGUAGE = "resultLanguage";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        changePage(R.id.nav_movie);
        navigationView.setCheckedItem(R.id.nav_movie);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        changePage(id);

        return true;
    }

    private void changePage(int id)
    {
        Fragment fragment = null;

        if (id == R.id.nav_movie)
        {
            fragment = new NowPlayingFragment();
            setTitle("Now Playing");
        }
        else if (id == R.id.nav_tv)
        {
            fragment = new TopRatedFragment();
            setTitle("Top Rated");
        }
        else if (id == R.id.nav_fav)
        {
            fragment = new FavFragment();
            setTitle("Favourite");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitNow();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showNowPlaying(String poster_path, String title, String overview, String vote_average, String release_date, String backdrop_path, String vote_count, String popularity, String original_language)
    {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(RESULTTITLE, title);
        intent.putExtra(RESULTOVERVIEW, overview);
        intent.putExtra(RESULTBACKDROP, backdrop_path);
        intent.putExtra(RESULTPOSTER, poster_path);
        intent.putExtra(RESULTRELEASE, release_date);
        intent.putExtra(RESULTAVERAGE, vote_average);
        intent.putExtra(RESULTCOUNT, vote_count);
        intent.putExtra(RESULTPOPULARITY, popularity);
        intent.putExtra(RESULTLANGUAGE, original_language);

        startActivity(intent);
    }

    @Override
    public void showTopRated(String poster_path, String title, String overview, String vote_average, String release_date, String backdrop_path, String vote_count, String popularity, String original_language)
    {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(RESULTTITLE, title);
        intent.putExtra(RESULTOVERVIEW, overview);
        intent.putExtra(RESULTBACKDROP, backdrop_path);
        intent.putExtra(RESULTPOSTER, poster_path);
        intent.putExtra(RESULTRELEASE, release_date);
        intent.putExtra(RESULTAVERAGE, vote_average);
        intent.putExtra(RESULTCOUNT, vote_count);
        intent.putExtra(RESULTPOPULARITY, popularity);
        intent.putExtra(RESULTLANGUAGE, original_language);

        startActivity(intent);
    }

}
