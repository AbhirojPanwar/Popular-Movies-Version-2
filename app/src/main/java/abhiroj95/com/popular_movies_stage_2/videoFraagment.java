package abhiroj95.com.popular_movies_stage_2;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class videoFraagment extends Fragment {


    List<Video> vilist;
    VideoAdapter vAdap;
    ListView majorList;
    public videoFraagment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_video_fraagment, container, false);
        majorList=(ListView)view.findViewById(R.id.trailer_list);
         vAdap=new VideoAdapter(getActivity(),vilist);
        vAdap.notifyDataSetChanged();
        majorList.setAdapter(vAdap);
        majorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        return view;
    }


}
