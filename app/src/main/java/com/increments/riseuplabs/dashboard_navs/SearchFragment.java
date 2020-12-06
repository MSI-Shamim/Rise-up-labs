package com.increments.riseuplabs.dashboard_navs;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.increments.riseuplabs.R;
import com.increments.riseuplabs.adapters.MovieAdapter;
import com.increments.riseuplabs.daos.Api;
import com.increments.riseuplabs.skeletons.RetrofitClient;
import com.increments.riseuplabs.databinding.FragmentSearchBinding;
import com.increments.riseuplabs.models.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {
    private static final String TAG = "SearchFragment";

    public SearchFragment() {
        // Required empty public constructor
    }

    private FragmentSearchBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSearchBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    private MovieAdapter mAdapter;
    private List<Movie> mMovies;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.shimmerLayout.startShimmer();

        mBinding.searchResultList.setHasFixedSize(true);
        mBinding.searchResultList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mAdapter = new MovieAdapter(requireActivity());
        mBinding.searchResultList.setAdapter(mAdapter);
        mMovies = new ArrayList<>();

        mBinding.searchMovie.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String url = requireActivity().getString(R.string.single_query, newText);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadUserData(url);
            }
        }, 300);
        return false;
    }

    private void loadUserData(String url) {
        Api api = RetrofitClient.getClient().create(Api.class);
        Call<Movie> call = api.getMovie(url);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "Error: " + response.code());
                    return;
                }

                Movie movie = response.body();
                mMovies.clear();
                mMovies.add(movie);
                mAdapter.setMovies(mMovies);
                mAdapter.notifyDataSetChanged();
                mBinding.shimmerLayout.stopShimmer();
                mBinding.shimmerLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}