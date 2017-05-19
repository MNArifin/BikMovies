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
 * Created by MNArifin on 19/05/2017.
 */

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.ViewHolder>
{
    public static final String IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500";
    ArrayList<Result> list = new ArrayList<>();
    ITopRatedAdapter mITopRatedAdapter;
    Context context;

    public TopRatedAdapter(Context context, ArrayList<Result> list, Fragment fragment)
    {
        this.list = list;
        this.context = context;
        mITopRatedAdapter = (ITopRatedAdapter) fragment;
    }

    @Override
    public TopRatedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_top_rated, parent, false);
        TopRatedAdapter.ViewHolder vh = new TopRatedAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Result result = list.get(position);
        holder.tr_tvTitle.setText(result.title);
        holder.tr_tvOverview.setText(result.overview);
        holder.tr_tvRating.setText((result.vote_average).toString());
        holder.tr_tvWatch.setText(result.vote_count);
        Glide.with(context).load(IMAGE_URL_BASE_PATH + result.poster_path).into(holder.tr_ivPoster);
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

    public interface ITopRatedAdapter
    {
        void showNowPlaying(String poster_path, String title, String overview, String vote_average, String release_date, String backdrop_path, String vote_count, String popularity, String original_language);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView tr_ivPoster;
        TextView tr_tvTitle;
        TextView tr_tvOverview;
        TextView tr_tvRating;
        TextView tr_tvWatch;

        public ViewHolder(View itemView)
        {
            super(itemView);
            tr_ivPoster = (ImageView) itemView.findViewById(R.id.tr_imageViewPoster);
            tr_tvTitle = (TextView) itemView.findViewById(R.id.tr_textViewTitle);
            tr_tvOverview = (TextView) itemView.findViewById(R.id.tr_textViewOverview);
            tr_tvRating = (TextView) itemView.findViewById(R.id.tr_textViewRating);
            tr_tvWatch = (TextView) itemView.findViewById(R.id.tr_textViewWatch);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Result result = list.get(getAdapterPosition());
                    mITopRatedAdapter.showNowPlaying(result.poster_path, result.title, result.overview, result.vote_average, result.release_date, result.backdrop_path, result.vote_count, result.popularity, result.original_language);
                }
            });
        }
    }
}
