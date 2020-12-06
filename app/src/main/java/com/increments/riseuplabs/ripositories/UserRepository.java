package com.increments.riseuplabs.ripositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.increments.riseuplabs.daos.UserDao;
import com.increments.riseuplabs.skeletons.UserDatabase;
import com.increments.riseuplabs.models.User;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<User>> mUserLiveData;


    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getInstance(application);
        mUserDao = database.mUserDao();
        mUserLiveData = mUserDao.getUsers();
    }

    public void insert(User user) {
        new InsertUser(mUserDao).execute(user);
    }

    public void update(User user) {
        new UpdateUser(mUserDao).execute(user);
    }

    public void delete(User user) {
        new DeleteUser(mUserDao).execute(user);
    }

    public LiveData<List<User>> getUserLiveData() {
        return mUserLiveData;
    }

    private static class InsertUser extends AsyncTask<User, Void, Void> {
        private UserDao mUserDao;

        private InsertUser(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUser extends AsyncTask<User, Void, Void> {
        private UserDao mUserDao;

        private UpdateUser(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUser extends AsyncTask<User, Void, Void> {
        private UserDao mUserDao;

        private DeleteUser(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.delete(users[0]);
            return null;
        }
    }

}
