package id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.adapter.FavAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl118.bikmovies.model.Fav;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavFragment extends Fragment
{
    FavAdapter fAdapter;
    ArrayList<Fav> fList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public FavFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fList = new ArrayList<>();

        adapter = new FavAdapter(fList, getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);

        fList.addAll(Fav.listAll(Fav.class));
        adapter.notifyDataSetChanged();


        return view;
    }

/*    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        fAdapter = new FavAdapter(this.getActivity(), fList);
        recyclerView.setAdapter(fAdapter);

        refreshData(null);
    }

    private void refreshData(String query)
    {
        fList.clear();

        if (query == null || query.isEmpty())
            fList.addAll(Fav.listAll(Fav.class));

        fAdapter.notifyDataSetChanged();
    }

    @Override
    public void doDelete(long id_sugar)
    {

    }*/
}
