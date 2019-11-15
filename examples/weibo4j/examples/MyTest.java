package weibo4j.examples;

import weibo4j.Comments;
import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Comment;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

import java.util.List;

public class MyTest {

    private static String accessToken = "2.007NF7NG2ao8gC39afd3fb4fNgZwhD";
    private static String commentsText = "不如跟我来学编程啊！http://t.cn/AirY4ViD";

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            StatusWapper statusWapper = getPublicTimeling();
            for (Status status : statusWapper.getStatuses()) {
                Thread.sleep(10000);
                createComments(commentsText, status.getMid());
            }
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

}
