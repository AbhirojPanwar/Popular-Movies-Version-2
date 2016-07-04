package abhiroj95.com.popular_movies_stage_2;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import abhiroj95.com.popular_movies_stage_2.Data.Movie;
import abhiroj95.com.popular_movies_stage_2.Data.Movie_Contract;
import abhiroj95.com.popular_movies_stage_2.Data.MovieddbHelper;


public class MainActivity extends ActionBarActivity implements MovieGridFrag.MovieListener{

    public static String user_pref=null;
    public static String BASE_URL="http://api.themoviedb.org/3/movie/";

    public static String final_URL=null;
    ProgressDialog loader;
    RequestQueue requestQueue;
    boolean HAS_VIEW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loader=new ProgressDialog(this);
        loader.setMessage("Please Wait...");
        loader.show();
        if(savedInstanceState!=null)
        {
            user_pref=savedInstanceState.getString("USR_PREF");
        }

        View view=findViewById(R.id.detail_container);
        if(view!=null) HAS_VIEW=true;

        try{
            Movie.movieArray=(Movie[])getLastNonConfigurationInstance();
        }
        catch(NullPointerException e)
        {
            if(savedInstanceState!=null) finish(); // Finish the activity
        }
        if(user_pref==null)
        {
            user_pref="popular?";
        }
        makeRequest();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("USR_PREF",user_pref);

        super.onSaveInstanceState(outState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return Movie.movieArray;
    }


    public void makeRequest()
    {
        final_URL=BASE_URL+user_pref+Movie.api_key;
        final_URL.trim();
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, final_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            JSONArray ja = response.getJSONArray("results");

                            Movie.movieArray=new Movie[ja.length()];

                            for(int i=0; i < ja.length(); i++){

                                Movie movie=new Movie();
                                JSONObject jsonObject = ja.getJSONObject(i);
                                String posterPath=jsonObject.getString("poster_path");
                                String plotSynop=jsonObject.getString("overview");
                                String relDate=jsonObject.getString("release_date");
                                String _id=jsonObject.getString("id");
                                String title=jsonObject.getString("original_title");
                                String vote_avg=jsonObject.getString("vote_average");

                                movie.set_id(_id);
                                movie.setImgPath(posterPath);
                                movie.setmmTitle(title);
                                movie.setPlotsynop(plotSynop);
                                movie.setmRelDate(relDate);
                                movie.setVoteAvg(vote_avg);

                                Movie.movieArray[i]=movie;
                            }

                            loader.dismiss();

                            MovieGridFrag mgf=new MovieGridFrag();
                            mgf.movieList= Arrays.asList(Movie.movieArray);
                            FragmentTransaction ft=getFragmentManager().beginTransaction();
                            ft.replace(R.id.list_container,mgf);
                            ft.commit();
                            if(HAS_VIEW)
                            defaultforFrag();

                        }catch(JSONException e){e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");

                    }
                }
        );

        requestQueue.add(jor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_popular) {
           user_pref="popular?";
            makeRequest();
            return true;
        }

        if (id == R.id.action_topRated)
        {
            user_pref="top_rated?";
            makeRequest();
            return true;
        }

        if(id==R.id.action_favorite)
        {
            SQLiteOpenHelper dbHep=new MovieddbHelper(this);
            SQLiteDatabase db=dbHep.getReadableDatabase();
            Cursor cursor = db.query(Movie_Contract.MovieEntry.TABLE_NAME, new String[]{Movie_Contract.MovieEntry.ID, Movie_Contract.MovieEntry.IMAGE}, null, null, null, null,null);
        if(cursor.getCount()==0) Toast.makeText(this,"No favorite Movies Yet",Toast.LENGTH_SHORT).show();
            else
        {
            favoritefragment ff=new favoritefragment();
            getFragmentManager().beginTransaction().replace(R.id.list_container,ff).commit();

        }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemClicked(int id) {

        Movie.position_fordetailfrag=id;
        if(HAS_VIEW)
        {
           MovieDetailFrag mdf=new MovieDetailFrag();
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.replace(R.id.detail_container,mdf);
            ft.commit();

        }
        else
        {

            Intent i =new Intent(this,DetailActivity.class);
            startActivity(i);
        }
    }

    public void defaultforFrag()
    {
        Movie.position_fordetailfrag=0;
        MovieDetailFrag mdf=new MovieDetailFrag();
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.detail_container,mdf);
        ft.commit();

    }
}
