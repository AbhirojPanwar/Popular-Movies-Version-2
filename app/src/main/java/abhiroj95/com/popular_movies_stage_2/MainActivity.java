package abhiroj95.com.popular_movies_stage_2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity implements MovieGridFrag.MovieListener{

    public static String user_pref=null;
    public static String BASE_URL="http://api.themoviedb.org/3/movie/";
    public static final String api_key="";
    public static String final_URL=null;
    public static Movie[] movieArray;
    ProgressDialog loader;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if(user_pref==null)
        {
            user_pref="popular?";
        }
        final_URL=BASE_URL+user_pref+api_key;
        final_URL.trim();
        makeRequest();
        setContentView(R.layout.activity_main);

    }


    public void makeRequest()
    {
        loader=new ProgressDialog(this);
        loader.setMessage("Please Wait...");
        loader.show();
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, final_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            JSONArray ja = response.getJSONArray("results");

                            movieArray=new Movie[ja.length()];

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

                                movieArray[i]=movie;
                            }

                            loader.dismiss();

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemClicked(int id) {

    }
}
