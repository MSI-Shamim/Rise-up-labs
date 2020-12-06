package com.increments.riseuplabs.adapters;

import android.content.Context;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.increments.riseuplabs.R;
import com.increments.riseuplabs.models.Movie;
import com.increments.riseuplabs.viewholders.MovieViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.increments.riseuplabs.utils.Constants.NOT_SPECIFIED;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private static final String TAG = "MovieAdapter";
    private final Context mContext;
    private List<Movie> mMovies;

    public MovieAdapter(Context context) {
        mContext = context;
        mMovies = new ArrayList<>();
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movie_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        Log.d(TAG, "Movie " + position + " : " + movie.getImage());
        Glide.with(mContext)
                .applyDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
                .load(Uri.decode(movie.getImage()))
                .placeholder(R.drawable.riseuplabs)
                .into(holder.mBinding.poster);

        if (!movie.getNetwork().equals("_")) {
            holder.mBinding.webChannel.setText(Html.fromHtml(mContext.getString(R.string.str_network, movie.getNetwork())));
        } else if (!movie.getWebChannel().equals("_")) {
            holder.mBinding.webChannel.setText(Html.fromHtml(mContext.getString(R.string.str_web_channel, movie.getWebChannel())));
        } else {
            holder.mBinding.webChannel.setText(Html.fromHtml(mContext.getString(R.string.str_network_web_channel, NOT_SPECIFIED)));
        }

        if (!movie.getSchedule().equals("_")) {
            holder.mBinding.schedule.setText(Html.fromHtml(mContext.getString(R.string.str_schedule, movie.getSchedule())));
        } else {
            holder.mBinding.schedule.setText(Html.fromHtml(mContext.getString(R.string.str_schedule, "Not specified")));
        }

        holder.mBinding.status.setText(Html.fromHtml(mContext.getString(R.string.str_status, movie.getStatus())));

        holder.mBinding.type.setText(Html.fromHtml(mContext.getString(R.string.str_show_type, movie.getType())));

        if (!movie.getGenres().equals("_")) {
            holder.mBinding.genres.setText(Html.fromHtml(mContext.getString(R.string.str_genres, movie.getGenres())));
        } else {
            holder.mBinding.genres.setText(Html.fromHtml(mContext.getString(R.string.str_genres, NOT_SPECIFIED)));
        }

        if (!movie.getOfficialSite().equals("_")) {
            holder.mBinding.officialSite.setText(Html.fromHtml(mContext.getString(R.string.str_official_site, movie.getOfficialSite())));
        } else {
            holder.mBinding.officialSite.setText(Html.fromHtml(mContext.getString(R.string.str_official_site, NOT_SPECIFIED)));
        }


        holder.mBinding.rating.setText(Html.fromHtml(mContext.getString(R.string.str_rating, String.format("%.1f", movie.getRating()))));
    }

    @Override
    public int getItemCount() {
        return Math.max(0, mMovies.size());
    }
}
