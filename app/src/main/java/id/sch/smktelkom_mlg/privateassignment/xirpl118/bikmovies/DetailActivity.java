package id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.model.Fav;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class DetailActivity extends AppCompatActivity
{
    public byte[] gambar = new byte[2048];
    Fav fav;
    boolean isPressed = true;
    ArrayList<Fav> fItem;

    public static byte[] getBytes(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

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

        setTitle(rTitle);
        tvOverview.setText(rOverview);
        tvReleaseDate.setText(rReleaseDate);
        tvAverage.setText(rAverage);
        tvCount.setText(rCount);
        tvPopularity.setText(rPopularity);
        tvLanguage.setText(rLanguage);


        Glide.with(getApplication()).load("http://image.tmdb.org/t/p/w500" + rBackdrop).into(ivBackdrop);

        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground(Void... params)
            {
                try
                {
                    Bitmap bitmap = Glide.
                            with(getApplicationContext()).
                            load("https://image.tmdb.org/t/p/w500" + getIntent().getStringExtra(MainActivity.RESULTBACKDROP)).
                            asBitmap().
                            into(500, 500).get();
                    gambar = getBytes(bitmap);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                catch (ExecutionException e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        new MaterialShowcaseView.Builder(this).setTarget(fab).setDismissText("OK").setContentText("You can add film to my favorite list by tap STAR button!").setDelay(200) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse("Yes") // provide a unique ID used to ensure it is only shown once
                .show();

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (isPressed)
                {

                    Snackbar.make(view, "Are you sure to add this film to my favorite list?, IF YES TAP AGAIN!", Snackbar.LENGTH_LONG)

                            .setAction("Action", null).show();
                }
                else
                {
                    doSimpan();
                    Snackbar.make(view, "You have successfully added my favorite list, look at my favorite navigation", Snackbar.LENGTH_LONG)

                            .setAction("Action", null).show();
                }
                isPressed = !isPressed;
            }
        });

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

    private void doSimpan()
    {
        String overview = getIntent().getStringExtra(MainActivity.RESULTOVERVIEW);
        String title = getIntent().getStringExtra(MainActivity.RESULTTITLE);
        byte[] backdrop_path = gambar;

        fav = new Fav(overview, title, backdrop_path);
        fav.save();
    }

    private void konvert()
    {
        Bitmap bitmap = null;
        try
        {
            bitmap = Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500" + getIntent().getStringExtra(MainActivity.RESULTBACKDROP)).asBitmap().into(500, 500).get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        gambar = getBytes(bitmap);
    }
}
