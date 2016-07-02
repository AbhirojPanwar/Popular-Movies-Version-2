package abhiroj95.com.popular_movies_stage_2;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFrag extends Fragment {


    /*
        @BindView(R.id.movie_poster)
        ImageView poster;

      @BindView(R.id.release_year)
        TextView release_year;



        @BindView(R.id.rating)
        TextView Rating;

        @BindView(R.id.favoriteMov)
       Button favoriteMov;

        @BindView(R.id.plot_synop)
        TextView plotSynop;
        public MovieDetailFrag() {
            // Required empty public constructor
        }
    */
    int position=Movie.position_fordetailfrag;
    RequestQueue requestQueue;

    Movie movie;
    String BASE_URL = "http://api.themoviedb.org/3/movie/";
    String final_URL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
//        ButterKnife.bind(this, view);
        movie = Movie.movieArray[position];
        makeRequest();

        ImageView poster = (ImageView) view.findViewById(R.id.movie_poster);
        String image_URL = "http://image.tmdb.org/t/p/w185" + movie.getImgPath();
        Picasso.with(getActivity()).load(image_URL.trim()).into(poster);

        TextView release_year=(TextView) view.findViewById(R.id.release_year);
        String[] date = movie.getmRelDate().split("-");
        release_year.setText(date[0]);

        TextView Rating=(TextView) view.findViewById(R.id.rating);
        Rating.setText(movie.getVoteAvg() + "/10");

        TextView plotSynop=(TextView) view.findViewById(R.id.plot_synop);
        plotSynop.setText(movie.getPlotsynop());


        return view;
    }

    public void makeRequest() {
        String id = movie.get_id() + "/";
        final_URL = BASE_URL + id + "videos?" + Movie.api_key;
        requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, final_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray ja = response.getJSONArray("results");

                            Video.videarray=new Video[ja.length()];

                            for (int i=0;i<ja.length();i++)
                            {
                                JSONObject job=ja.getJSONObject(i);
                                Video item=new Video();
                                item.key=job.getString("key");
                                item.name=job.getString("name");
                                item.size=job.getString("size");
                                item.type=job.getString("type");
                                Video.videarray[i]=item;
                            }

                            videoFraagment vf=new videoFraagment();
                            vf.vilist= Arrays.asList(Video.videarray);
                            getFragmentManager().beginTransaction().replace(R.id.vid_container,vf).commit();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");

                    }
                }
        );

        final_URL = BASE_URL + id + "reviews?" + Movie.api_key;

        JsonObjectRequest jor2 = new JsonObjectRequest(Request.Method.GET, final_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray ja = response.getJSONArray("results");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
        requestQueue.add(jor2);
    }


}
