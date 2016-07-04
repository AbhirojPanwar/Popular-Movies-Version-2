package abhiroj95.com.popular_movies_stage_2.Data;

import android.provider.BaseColumns;

/**
 * Created by APnaturals on 7/3/2016.
 */
public class Movie_Contract {


    public static  final class MovieEntry implements BaseColumns {

public static final String TABLE_NAME="favorite";
        public static String URI="content://abhiroj95.com.popular_movies_stage_2";


        public static String ID="_id";
        public static String TITLE="title";
        public static String RELEASE="relaeasedate";
        public static String VOTE="vote";
        public static String PLOT="plot_synopsis";
        public static String IMAGE="imagepath";
        public static String VIDEO="videopath";
        public static String REVIEW="reviewpath";
        public static String MOVIE_ID="mid";



    }
}
