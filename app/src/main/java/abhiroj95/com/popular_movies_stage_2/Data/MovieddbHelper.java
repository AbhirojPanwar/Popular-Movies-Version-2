package abhiroj95.com.popular_movies_stage_2.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by APnaturals on 7/3/2016.
 */
public class MovieddbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="movies";

    public static final int DB_VERSION=1;

    public MovieddbHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String exec="CREATE TABLE "+ Movie_Contract.MovieEntry.TABLE_NAME
                +" ("+ Movie_Contract.MovieEntry.ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Movie_Contract.MovieEntry.TITLE+
                " TEXT NOT NULL, "
                + Movie_Contract.MovieEntry.RELEASE
                +" TEXT NOT NULL, "+ Movie_Contract.MovieEntry.VOTE
                +" TEXT NOT NULL, "+ Movie_Contract.MovieEntry.PLOT
                +" TEXt NOT NULL, "+ Movie_Contract.MovieEntry.IMAGE
                +" TEXT NOT NULL, "+ Movie_Contract.MovieEntry.VIDEO+" TEXT NOT NULL, "
                + Movie_Contract.MovieEntry.REVIEW+" TEXT NOT NULL, "
                + Movie_Contract.MovieEntry.MOVIE_ID+" TEXT UNIQUE);";
        sqLiteDatabase.execSQL(exec);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
