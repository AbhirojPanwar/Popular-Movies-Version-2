package abhiroj95.com.popular_movies_stage_2.Utility;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import abhiroj95.com.popular_movies_stage_2.Data.Movie_Contract;
import abhiroj95.com.popular_movies_stage_2.R;

/**
 * Created by APnaturals on 7/4/2016.
 */
public class FavoriteAdapter extends CursorAdapter {
    LayoutInflater inflater;
    public FavoriteAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

    View view=inflater.inflate(R.layout.disp_image,viewGroup,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView img=(ImageView) view.findViewById(R.id.movie_display);
        String url="http://image.tmdb.org/t/p/w185"+cursor.getString(cursor.getColumnIndex(Movie_Contract.MovieEntry.IMAGE));
        Picasso.with(context).load(url).into(img);
    }
}
