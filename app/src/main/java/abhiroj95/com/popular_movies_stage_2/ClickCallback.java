package abhiroj95.com.popular_movies_stage_2;

/**
 * Created by APnaturals on 7/6/2016.
 */

/*
Interface to provide Click call back feature from fragment to Main Activity
 */
public interface ClickCallback {
    void itemClicked(int id);
    void itemFavorite(int movieid);
}
