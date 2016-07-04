package abhiroj95.com.popular_movies_stage_2;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import abhiroj95.com.popular_movies_stage_2.Utility.ExpendableHeightListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class videoFraagment extends Fragment {


    List<Video> vilist;
    VideoAdapter vAdap;
    ExpendableHeightListView majorList;
    public videoFraagment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_video_fraagment, container, false);
        majorList=(ExpendableHeightListView)view.findViewById(R.id.trailer_list);
         vAdap=new VideoAdapter(getActivity(),vilist);
        vAdap.notifyDataSetChanged();
        majorList.setAdapter(vAdap);
        majorList.setExpanded(true);


        majorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+Video.videarray[position].key)));

            }
        });
        return view;
    }




}
