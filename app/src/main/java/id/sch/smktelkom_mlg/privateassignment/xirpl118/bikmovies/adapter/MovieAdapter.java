package id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.model.Result;

/**
 * Created by MNArifin on 16/05/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>
{
    public static final String IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500";
    ArrayList<Result> list;
    IMovieAdapter mIMovieAdapter;
    Context context;

    public MovieAdapter(Context context, ArrayList<Result> list, Fragment fragment)
    {
        this.list = list;
        this.context = context;
        mIMovieAdapter = (IMovieAdapter) fragment;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie, parent, false);
        MovieAdapter.ViewHolder vh = new MovieAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Result result = list.get(position);
        holder.movie_tvTitle.setText(result.title);
        holder.movie_tvOverview.setText(result.overview);
        holder.movie_tvRating.setText((result.vote_average).toString());
        holder.movie_tvDate.setText(result.release_date);
        holder.movie_tvWatch.setText(result.vote_count);
        Glide.with(context).load(IMAGE_URL_BASE_PATH + result.poster_path).into(holder.movie_ivPoster);
    }

    @Override
    public int getItemCount()
    {
        if (list != null)
        {
            return list.size();
        }
        return 0;
    }

    public interface IMovieAdapter
    {
        void showNowPlaying(String poster_path, String title, String overview, String vote_average, String release_date, String backdrop_path, String vote_count, String popularity, String original_language);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView movie_ivPoster;
        TextView movie_tvTitle;
        TextView movie_tvOverview;
        TextView movie_tvRating;
        TextView movie_tvDate;
        TextView movie_tvWatch;

        public ViewHolder(View itemView)
        {
            super(itemView);
            movie_ivPoster = (ImageView) itemView.findViewById(R.id.movie_imageViewPoster);
            movie_tvTitle = (TextView) itemView.findViewById(R.id.movie_textViewTitle);
            movie_tvOverview = (TextView) itemView.findViewById(R.id.movie_textViewOverview);
            movie_tvRating = (TextView) itemView.findViewById(R.id.movie_textViewRating);
            movie_tvWatch = (TextView) itemView.findViewById(R.id.movie_textViewWatch);
            movie_tvDate = (TextView) itemView.findViewById(R.id.movie_textViewDate);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Result result = list.get(getAdapterPosition());
                    mIMovieAdapter.showNowPlaying(result.poster_path, result.title, result.overview, result.vote_average, result.release_date, result.backdrop_path, result.vote_count, result.popularity, result.original_language);
                }
            });
        }
    }
}
