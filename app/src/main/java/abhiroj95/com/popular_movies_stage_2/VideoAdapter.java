package abhiroj95.com.popular_movies_stage_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import abhiroj95.com.popular_movies_stage_2.R;
import abhiroj95.com.popular_movies_stage_2.Video;

/**
 * Created by APnaturals on 7/2/2016.
 */
public class VideoAdapter extends ArrayAdapter<Video> {


    public VideoAdapter(Context context, List<Video> vilist) {
        super(context,0,vilist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.video_row_layout,parent,false);
        }
        ImageView play=(ImageView) convertView.findViewById(R.id.play_icon);
        Picasso.with(getContext()).load(R.drawable.play).resize(50,50).into(play);
        TextView trailer=(TextView) convertView.findViewById(R.id.trailer);
        trailer.setText(Video.videarray[position].name);
        trailer.setTextSize(18);


        return convertView;
    }
}
