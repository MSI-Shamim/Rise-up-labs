package com.increments.riseuplabs.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.increments.riseuplabs.databinding.ItemMovieListBinding;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public ItemMovieListBinding mBinding;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        mBinding = ItemMovieListBinding.bind(itemView);
    }
}
