package id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String rBackdrop = getIntent().getStringExtra(MainActivity.RESULTBACKDROP);
        String rPoster = getIntent().getStringExtra(MainActivity.RESULTPOSTER);
        String rTitle = getIntent().getStringExtra(MainActivity.RESULTTITLE);
        String rOverview = getIntent().getStringExtra(MainActivity.RESULTOVERVIEW);
        String rReleaseDate = getIntent().getStringExtra(MainActivity.RESULTRELEASE);
        String rAverage = getIntent().getStringExtra(MainActivity.RESULTAVERAGE);
        String rCount = getIntent().getStringExtra(MainActivity.RESULTCOUNT);
        String rPopularity = getIntent().getStringExtra(MainActivity.RESULTPOPULARITY);
        String rLanguage = getIntent().getStringExtra(MainActivity.RESULTLANGUAGE);

        TextView tvOverview = (TextView) findViewById(R.id.textViewOverview);
        TextView tvReleaseDate = (TextView) findViewById(R.id.textViewReleaseDate);
        TextView tvAverage = (TextView) findViewById(R.id.textViewVoteAverage);
        TextView tvCount = (TextView) findViewById(R.id.textViewVoteCount);
        TextView tvPopularity = (TextView) findViewById(R.id.textViewPopularity);
        TextView tvLanguage = (TextView) findViewById(R.id.textViewOriginalLanguage);

        ImageView ivBackdrop = (ImageView) findViewById(R.id.imageViewBackdrop);
        ImageView ivPoster = (ImageView) findViewById(R.id.imageViewPoster);

        setTitle(rTitle);
        tvOverview.setText(rOverview);
        tvReleaseDate.setText(rReleaseDate);
        tvAverage.setText(rAverage);
        tvCount.setText(rCount);
        tvPopularity.setText(rPopularity);
        tvLanguage.setText(rLanguage);


        Glide.with(getApplication()).load("http://image.tmdb.org/t/p/w500" + rBackdrop).into(ivBackdrop);
        Glide.with(getApplication()).load("http://image.tmdb.org/t/p/w500" + rPoster).into(ivPoster);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
    }
}
