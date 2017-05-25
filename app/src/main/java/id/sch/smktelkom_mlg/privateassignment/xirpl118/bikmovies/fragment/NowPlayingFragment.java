package id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.adapter.NowPlayingAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.model.Result;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.model.ResultResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment implements NowPlayingAdapter.INowPlayingAdapter
{
    ArrayList<Result> np_List = new ArrayList<>();
    NowPlayingAdapter np_mAdapter;

    public NowPlayingFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        np_mAdapter = new NowPlayingAdapter(this.getActivity(), np_List);
        recyclerView.setAdapter(np_mAdapter);

        downloadDataNowPlaying();
    }

    /*public void onAttach(Context context)
    {
        super.onAttach(context);
        downloadDataNowPlaying();
    }*/

    private void downloadDataNowPlaying()
    {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=5ac213ef7204a3d0939b6c240e8540c3&language=en-US&page=1";

        GsonGetRequest<ResultResponse> myRequest = new GsonGetRequest<ResultResponse>(url, ResultResponse.class, null, new Response.Listener<ResultResponse>()
        {
            @Override
            public void onResponse(ResultResponse response)
            {
                Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                np_List.addAll(response.results);
                np_mAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e("FLOW", "onErrorResponse: ", error);
            }
        });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }

    @Override
    public void showNowPlaying(String poster_path, String title, String overview, String vote_average, String release_date, String backdrop_path, String vote_count, String popularity, String original_language)
    {

    }
}
