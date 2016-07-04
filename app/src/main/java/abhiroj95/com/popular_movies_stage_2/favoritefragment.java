package abhiroj95.com.popular_movies_stage_2;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import abhiroj95.com.popular_movies_stage_2.Data.Movie_Contract;
import abhiroj95.com.popular_movies_stage_2.Data.MovieddbHelper;
import abhiroj95.com.popular_movies_stage_2.Utility.FavoriteAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class favoritefragment extends Fragment {


    GridView grid;
    public favoritefragment() {
        // Required empty public constructor
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_movie_grid,container,false);
        grid=(GridView) view.findViewById(R.id.movie_display);
        SQLiteOpenHelper mdbh=new MovieddbHelper(getActivity());
        SQLiteDatabase db=mdbh.getReadableDatabase();
        try (Cursor cursor = db.query(Movie_Contract.MovieEntry.TABLE_NAME, new String[]{Movie_Contract.MovieEntry.ID, Movie_Contract.MovieEntry.IMAGE}, null, null, null, null,null)) {
                FavoriteAdapter adapter=new FavoriteAdapter(getActivity(),cursor);
            grid.setAdapter(adapter);

        }catch (Exception e)
        {
            Log.e("MOVIE_DATABASE",String.valueOf(e));
        }
        return view;
    }


}
