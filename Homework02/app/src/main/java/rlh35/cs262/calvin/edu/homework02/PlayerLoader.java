package rlh35.cs262.calvin.edu.homework02;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class PlayerLoader extends AsyncTaskLoader<String> {
    private String mFetchString;

    /**
     * I don't know what this method does.
     */
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    /**
     * I don't know what this method does.
     * @param context
     * @param fetchString
     */
    public PlayerLoader(@NonNull Context context, String fetchString) {
        super(context);
        mFetchString = fetchString;
    }

    /**
     * I don't know what this method does.
     * @return
     */
    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getPlayerInfo(mFetchString);
    }
}
