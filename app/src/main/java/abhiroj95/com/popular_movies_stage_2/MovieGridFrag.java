package abhiroj95.com.popular_movies_stage_2;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieGridFrag extends Fragment {


    private GridView moviegrid;

    static  interface MovieListener{
        void itemClicked(int id);
    }

    private MovieListener movielistener;
    public List<Movie> movieList;

    public MovieGridFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_movie_grid, container, false);
        moviegrid=(GridView) view.findViewById(R.id.movie_display);
        MovieGridAdapter movieGridAdapter=new MovieGridAdapter(getActivity(),movieList);
        movieGridAdapter.notifyDataSetChanged();
        moviegrid.setAdapter(movieGridAdapter);
        moviegrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
if(movielistener!=null)
{
movielistener.itemClicked(position);
}
            }
        });
        return view;
    }


}
