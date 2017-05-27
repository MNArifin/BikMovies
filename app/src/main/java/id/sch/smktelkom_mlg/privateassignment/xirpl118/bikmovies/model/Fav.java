package id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by MNArifin on 25/05/2017.
 */

public class Fav extends SugarRecord implements Serializable
{
    public String overview;
    public String title;
    public byte[] backdrop_path = new byte[2048];

    public Fav()
    {

    }

    public Fav(String overview, String title, byte[] backdrop_path)
    {
        this.overview = overview;
        this.title = title;
        this.backdrop_path = backdrop_path;
    }
}
