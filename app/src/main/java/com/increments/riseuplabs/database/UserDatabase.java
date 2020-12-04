package com.increments.riseuplabs.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.increments.riseuplabs.daos.UserDao;
import com.increments.riseuplabs.models.User;

import static com.increments.riseuplabs.utils.Constants.DATABASE_NAME;
import static com.increments.riseuplabs.utils.Constants.NAME;
import static com.increments.riseuplabs.utils.Constants.PASSWORD;
import static com.increments.riseuplabs.utils.Constants.PHONE;
import static com.increments.riseuplabs.utils.Constants.USER_NAME;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance;

    public abstract UserDao mUserDao();

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(instance).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private UserDao mUserDao;

        private PopulateDbAsync(UserDatabase userDatabase){
            mUserDao = userDatabase.mUserDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mUserDao.insert(new User(NAME, USER_NAME, PASSWORD, PHONE));
            return null;
        }
    }
}
