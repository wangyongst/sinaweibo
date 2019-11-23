package weibo4j.examples;

import weibo4j.Account;
import weibo4j.Comments;
import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.*;

import java.util.List;

public class MyTest {

    private static String accessToken = "2.007NF7NG2ao8gC39afd3fb4fNgZwhD";
    private static String commentsText = "不如来跟我学编程啊！https://s.taobao.com/search?q=%E5%85%A8%E6%B0%91%E5%AD%A6%E7%BC%96%E7%A8%8B%E4%B9%8BJava%E7%AF%87&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20191121&ie=utf8 https://search.jd.com/Search?keyword=%E5%85%A8%E6%B0%91%E5%AD%A6%E7%BC%96%E7%A8%8B%E4%B9%8BJava%E7%AF%87&enc=utf-8&pvid=7e9a43ab8b2b4d24ab26a2fbfc9a4971";

    public static void main(String[] args) {
        while (true) {
            try {
                Thread.sleep(240000);
                StatusWapper statusWapper = getFriendsTimeline();
                for (Status status : statusWapper.getStatuses()) {
                    Thread.sleep(120000);
                    createComments(commentsText, status.getId());
                    Thread.sleep(120000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static RateLimitStatus getAcccountRateLimitStatus() {
        String access_token = accessToken;
        Account am = new Account(access_token);
        try {
            RateLimitStatus json = am.getAccountRateLimitStatus();
            Log.logInfo(json.toString());
            return json;
        } catch (WeiboException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createComments(String comments, String mid) {
        String access_token = accessToken;
        Comments cm = new Comments(access_token);
        try {
            Comment comment = cm.createComment(comments, mid);
            Log.logInfo(comment.toString());
        } catch (WeiboException e) {
            e.printStackTrace();
        }
    }

    public static StatusWapper getPublicTimeling() {
        Timeline tm = new Timeline(accessToken);
        try {
            StatusWapper status = tm.getPublicTimeline();
            Log.logInfo(status.toString());
            return status;
        } catch (WeiboException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static StatusWapper getFriendsTimeline() {
        Timeline tm = new Timeline(accessToken);
        try {
            StatusWapper status = tm.getFriendsTimeline();
            Log.logInfo(status.toString());
            return status;
        } catch (WeiboException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static StatusWapper getHomeTimeline() {
        Timeline tm = new Timeline(accessToken);
        try {
            StatusWapper status = tm.getHomeTimeline();
            Log.logInfo(status.toString());
            return status;
        } catch (WeiboException e) {
            e.printStackTrace();
            return null;
        }
    }

}
