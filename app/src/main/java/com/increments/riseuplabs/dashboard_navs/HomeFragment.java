package com.increments.riseuplabs.dashboard_navs;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.increments.riseuplabs.adapters.MovieAdapter;
import com.increments.riseuplabs.daos.Api;
import com.increments.riseuplabs.skeletons.RetrofitClient;
import com.increments.riseuplabs.databinding.FragmentHomeBinding;
import com.increments.riseuplabs.models.Movie;
import com.increments.riseuplabs.models.MoviePack;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    public HomeFragment() {
        // Required empty public constructor
    }

    private FragmentHomeBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    private MovieAdapter mAdapter;
    private List<MoviePack> mMoviePacks;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.shimmer.startShimmer();

        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mAdapter = new MovieAdapter(requireContext());
        mBinding.recyclerView.setAdapter(mAdapter);
        mMoviePacks = new ArrayList<>();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadUserData();
            }
        }, 1000);
    }

    private void loadUserData() {
        Api api = RetrofitClient.getClient().create(Api.class);
        Call<List<MoviePack>> call = api.getMovies();
        call.enqueue(new Callback<List<MoviePack>>() {
            @Override
            public void onResponse(Call<List<MoviePack>> call, Response<List<MoviePack>> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "Error: " + response.code());
                    return;
                }

                mMoviePacks = response.body();
                List<Movie> movies = new ArrayList<>();
                for (MoviePack moviePack : mMoviePacks) {
                    movies.add(moviePack.getShow());
                }
                mAdapter.setMovies(movies);
                mAdapter.notifyDataSetChanged();
                mBinding.shimmer.stopShimmer();
                mBinding.shimmer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<MoviePack>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}