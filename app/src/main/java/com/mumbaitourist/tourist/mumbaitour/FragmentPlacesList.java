package com.mumbaitourist.tourist.mumbaitour;

import android.os.Bundle;
import android.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FragmentPlacesList extends Fragment {
    private static final String LOG_TAG = FragmentPlacesList.class.getSimpleName();
    
    @BindView(R.id.places_list)
    RecyclerView placesListRecyclerView;
    
    private ArrayList<String> placesArrayList;
    private PlacesAdapter placeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_places_list, container, false);
        ButterKnife.bind(this, view);
        placesArrayList = new ArrayList<String>();
        placeAdapter = new PlacesAdapter(placesArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        placesListRecyclerView.setLayoutManager(layoutManager);
        placesListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        placesListRecyclerView.setAdapter(placeAdapter);

        createSingleObservable();

        return view;
    }

    private void createSingleObservable() {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                Log.d(LOG_TAG, "onComplete of places list addition");
                addItemsToArrayList();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(LOG_TAG, "onComplete of places list addition");

            }

            @Override
            public void onError(Throwable t) {
                Log.d(LOG_TAG, "onError ," + t.getLocalizedMessage());
            }

        });
    }

    private void addItemsToArrayList() {
        placesArrayList.add("GATEWAY OF INDIA");
        placesArrayList.add("ELEPHANTA CAVES");
        placesArrayList.add("COLABA CAUSEWAY MARKET");
        placesArrayList.add("JUHU BEACH");
        placesArrayList.add("VICTORIA TERMINUS");
        placesArrayList.add("FILM CITY");
        placesArrayList.add("HAJI ALI");
        placesArrayList.add("MARINE DRIVE");
        placesArrayList.add("SIDDHIVINAYAKA TEMPLE");
        placesArrayList.add("ESSEL WORLD");
        placesArrayList.add("CHOR BAZAAR");
        placesArrayList.add("AKSHA BEACH");
        placesArrayList.add("POWAI LAKE");
        placesArrayList.add("WORLI SEAFACE");
        placeAdapter.notifyDataSetChanged();

    }
}
