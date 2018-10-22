package rlh35.cs262.calvin.edu.homework02;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class PlayerLoader extends AsyncTaskLoader<String> {
    private String mFetchString;

    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    public PlayerLoader(@NonNull Context context, String fetchString) {
        super(context);
        mFetchString = fetchString;
    }


    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getPlayerInfo(mFetchString);
    }
}
