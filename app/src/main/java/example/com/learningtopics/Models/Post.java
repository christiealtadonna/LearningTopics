package example.com.learningtopics.Models;

/**
 * Created by christiealtadonna on 4/22/17.
 */

public class Post {
    private int post_id;
    private String username;
    private String body;
    private int likes;
    private int topic_id;

    public Post(int post_id, String username, String body, int likes, int topic_id){
        this.post_id=post_id;
        this.username=username;
        this.body=body;
        this.likes=likes;
        this.topic_id=topic_id;
    }

    public int getPostId(){
        return post_id;
    }
    public String getUsername(){
        return username;
    }
    public String getBody(){
        return body;
    }
    public int getLikes(){
        return likes;
    }
    public int getTopicId(){
        return topic_id;
    }

    public void setBody(String body){
        this.body=body;
    }
    public void setLikes(int likes){
        this.likes=likes;
    }

}
