package com.increments.riseuplabs.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.increments.riseuplabs.models.User;
import com.increments.riseuplabs.ripositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository mRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
    }

    public void inset(User user) {
        mRepository.insert(user);
    }

    public void update(User user) {
        mRepository.update(user);
    }

    public void delete(User user) {
        mRepository.delete(user);
    }

    public LiveData<List<User>> getUserLiveData() {
        return mRepository.getUserLiveData();
    }
}
