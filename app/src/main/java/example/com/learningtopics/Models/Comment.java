package example.com.learningtopics.Models;

/**
 * Created by christiealtadonna on 4/23/17.
 */

public class Comment {
    private int comment_id;
    private String username;
    private String body;
    private int post_id;

    public Comment(int comment_id, String username, String body, int post_id){
        this.comment_id = comment_id;
        this.username = username;
        this.body = body;
        this.post_id = post_id;
    }

    public int getCommentId(){
        return this.comment_id;
    }

    public String getUsername(){
        return this.username;
    }
    public String getBody(){
        return this.body;
    }
    public int getPostId(){
        return this.post_id;
    }

}
