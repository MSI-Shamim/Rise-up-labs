package com.increments.riseuplabs.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.increments.riseuplabs.models.User;

public class SignedUserViewModel extends ViewModel {
    MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public void setUser(User user){
        userLiveData.setValue(user);
    }

    public User getUser(){
        return userLiveData.getValue();
    }
}
