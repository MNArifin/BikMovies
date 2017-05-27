package id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.model.Fav;

/**
 * Created by MNArifin on 25/05/2017.
 */

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder>
{
    public static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w500";

    ArrayList<Fav> fList = new ArrayList<>();
    IFavAdapter mIFavAdapter;
    Context context;
    Bitmap bitmap = null;

    public FavAdapter(ArrayList<Fav> fav, Context context)
    {
        this.fList = fav;
        this.context = context;
    }

    public static Bitmap getImage(byte[] image)
    {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fav, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(FavAdapter.ViewHolder holder, final int position)
    {
        final Fav fav = fList.get(position);
        holder.tvTitle.setText(fav.title);
        holder.tvOverview.setText(fav.overview);
        Bitmap bitmap = getImage(fav.backdrop_path);
        holder.ivBackdrop.setImageBitmap(bitmap);
        holder.bDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final Fav fav1 = fList.get(position);
                fList.remove(position);
                fav1.delete();
                FavAdapter.this.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return fList.size();
    }

    public interface IFavAdapter
    {
//        void doDelete(long id_sugar);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvTitle;
        public TextView tvOverview;
        public Button bDelete;
        public ImageView ivBackdrop;

        public ViewHolder(View itemView)
        {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvOverview = (TextView) itemView.findViewById(R.id.textViewDesc);
            bDelete = (Button) itemView.findViewById(R.id.buttonDelete);
            ivBackdrop = (ImageView) itemView.findViewById(R.id.imageViewBackdrop);

            /*bDelete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                    Fav fav = fList.get(getAdapterPosition());
                    long id_sugar = fav.getId();
                    mIFavAdapter.doDelete(id_sugar);
                }
            });*/
        }
    }
}
