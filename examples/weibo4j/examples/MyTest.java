package weibo4j.examples;

import weibo4j.Account;
import weibo4j.Comments;
import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.*;

import java.util.List;

public class MyTest {

    private static String accessToken = "2.007NF7NG2ao8gC39afd3fb4fNgZwhD";
    private static String commentsText = "不如来跟我学编程啊！http://t.cn/AirY4ViD";

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            RateLimitStatus rateLimitStatus = getAcccountRateLimitStatus();
            StatusWapper statusWapper = getFriendsTimeline();
            for (Status status : statusWapper.getStatuses()) {
                createComments(commentsText, status.getId());
                Thread.sleep(240000);
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
