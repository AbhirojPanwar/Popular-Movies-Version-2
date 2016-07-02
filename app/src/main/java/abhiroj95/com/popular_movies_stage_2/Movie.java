package abhiroj95.com.popular_movies_stage_2;

/**
 * Created by APnaturals on 7/1/2016.
 */
public class Movie {
    String mTitle;
    String mRelDate;
    String vote_avg;
    String plotsynop;
    String imgPath;
    String videoPath;
    String reviewPath;
    String _id;

    public  void set_id(String id)
    {
        _id=id;
    }

    public String get_id()
    {
        return _id;
    }

    public String getReviewPath() {
        return reviewPath;
    }

    public void setReviewPath(String reviewPath) {
        this.reviewPath = reviewPath;
    }

    public void setmmTitle(String title) {
        mTitle = title;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getmmTitle() {
        return mTitle;
    }

    public String getmRelDate() {
        return mRelDate;
    }

    public void setmRelDate(String reldate) {
        mRelDate = reldate;
    }

    public String getVoteAvg() {
        return vote_avg;
    }

    public void setVoteAvg(String voteavg) {
        vote_avg = voteavg;
    }

    public String getPlotsynop() {
        return plotsynop;
    }

    public void setPlotsynop(String psn) {
        plotsynop = psn;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgpath) {
        imgPath = imgpath;
    }
}
