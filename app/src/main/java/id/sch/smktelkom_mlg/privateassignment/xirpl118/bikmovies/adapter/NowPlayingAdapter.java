package id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.adapter;

import android.content.Context;
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

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>
{
    public static final String IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500";
    ArrayList<Result> list = new ArrayList<>();
    INowPlayingAdapter mINowPlayingAdapter;
    Context context;

    public NowPlayingAdapter(Context context, ArrayList<Result> list)
    {
        this.list = list;
        this.context = context;
        mINowPlayingAdapter = (NowPlayingAdapter.INowPlayingAdapter) context;
    }

    @Override
    public NowPlayingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_now_playing, parent, false);
        NowPlayingAdapter.ViewHolder vh = new NowPlayingAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Result result = list.get(position);
        holder.np_tvTitle.setText(result.title);
        holder.np_tvOverview.setText(result.overview);
        holder.np_tvRating.setText((result.vote_average).toString());
        holder.np_tvWatch.setText(result.vote_count);
        Glide.with(context).load(IMAGE_URL_BASE_PATH + result.poster_path).into(holder.np_ivPoster);
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

    public interface INowPlayingAdapter
    {
        void showNowPlaying(String poster_path, String title, String overview, String vote_average, String release_date, String backdrop_path, String vote_count, String popularity, String original_language);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView np_ivPoster;
        TextView np_tvTitle;
        TextView np_tvOverview;
        TextView np_tvRating;
        TextView np_tvWatch;

        public ViewHolder(View itemView)
        {
            super(itemView);
            np_ivPoster = (ImageView) itemView.findViewById(R.id.np_imageViewPoster);
            np_tvTitle = (TextView) itemView.findViewById(R.id.np_textViewTitle);
            np_tvOverview = (TextView) itemView.findViewById(R.id.np_textViewOverview);
            np_tvRating = (TextView) itemView.findViewById(R.id.np_textViewRating);
            np_tvWatch = (TextView) itemView.findViewById(R.id.np_textViewWatch);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Result result = list.get(getAdapterPosition());
                    mINowPlayingAdapter.showNowPlaying(result.poster_path, result.title, result.overview, result.vote_average, result.release_date, result.backdrop_path, result.vote_count, result.popularity, result.original_language);
                }
            });
        }
    }
}
