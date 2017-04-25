package example.com.learningtopics.Models;

/**
 * Created by christiealtadonna on 4/7/17.
 */

public class Topic {

    private int topic_id;
    private String topic;


    public Topic(int topic_id, String topic){
        this.topic_id = topic_id;
        this.topic = topic;
    }

    public Topic(){

    }

    public int getTopicId(){
        return topic_id;
    }

    public String getTopic(){
        return topic;
    }
}
