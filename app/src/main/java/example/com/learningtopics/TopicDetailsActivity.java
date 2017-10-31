package example.com.learningtopics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import example.com.learningtopics.Adapters.PostRecyclerViewAdapter;
import example.com.learningtopics.Models.Comment;
import example.com.learningtopics.Models.Post;
import example.com.learningtopics.Utils.SharedPrefUtils;

public class TopicDetailsActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private PostRecyclerViewAdapter mAdapter;
    private int topic_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_details);

        mRecyclerView = (RecyclerView) findViewById(R.id.posts_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        String posts = intent.getStringExtra("posts");

        mAdapter = new PostRecyclerViewAdapter(this);
        Log.d(" post : ",posts);
        //Enter if there are posts
        if(!posts.equals("none")) {
            Log.d("in ","if");
            List<Post> postList = new ArrayList<>();

            String splitTopics[] = posts.split(",");
            String temp[];
            for (int i = 0; i < splitTopics.length; i++) {
                temp = splitTopics[i].split(":");
                Post post = new Post(Integer.parseInt(temp[0]), temp[1], temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
                topic_id =Integer.parseInt(temp[4]);
                postList.add(post);

            }
            Log.d("topics are: ", posts);
//            ListView mListView = (ListView) findViewById(R.id.topic_listview);
            //mAdapter = new PostRecyclerViewAdapter(postList, this);
            mAdapter.setPostList(postList);
            mRecyclerView.setAdapter(mAdapter);
        }
        else{
            topic_id = Integer.parseInt(intent.getStringExtra("topicID"));
            //PostRecyclerViewAdapter mAdapter = new PostRecyclerViewAdapter(null, this);
            mRecyclerView.setAdapter(mAdapter);
        }
        mAdapter.setTopic(topic_id);

        Button addPost = (Button) findViewById(R.id.addPostButton);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newPost = (EditText) findViewById(R.id.add_post);
                String post = newPost.getText().toString();
                String username= SharedPrefUtils.getUsernamePreference(TopicDetailsActivity.this);
                Connect conn = new Connect(TopicDetailsActivity.this);
                String topicID = topic_id+"";
                Log.d("topic id is: ",topicID);
                conn.execute("add_post", topicID, post, username);

                newPost.setText("");

            }
        });

    }


    public void refreshPage(String posts){
        Log.d("in ","if");
        List<Post> postList = new ArrayList<>();

        String splitTopics[] = posts.split(",");
        String temp[];
        for (int i = 0; i < splitTopics.length; i++) {
            temp = splitTopics[i].split(":");
            Post post = new Post(Integer.parseInt(temp[0]), temp[1], temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
            topic_id =Integer.parseInt(temp[4]);
            postList.add(post);

        }
        //mAdapter = new PostRecyclerViewAdapter(postList, this);
        mAdapter.setPostList(postList);
    }


    public void refreshPageLikes(){

        Connect conn = new Connect(this);
        String topicID = topic_id+"";
        conn.execute("topic_page", topicID);
    }

    public void populatePage(String posts){
        List<Post> postList = new ArrayList<>();

        String splitTopics[] = posts.split(",");
//        Topic topicList[]= new Topic[splitTopics.length];
        String temp[];
        for(int i = 0; i < splitTopics.length; i++){
            temp = splitTopics[i].split(":");
            Post post = new Post(Integer.parseInt(temp[0]), temp[1], temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
            topic_id = Integer.parseInt(temp[4]);
            postList.add(post);
//            topicList[i]= new Topic(Integer.parseInt(temp[0]), temp[1]);
        }
        Log.d("topics are: ", posts);
//        ListView mListView = (ListView) findViewById(R.id.topic_listview);
        PostRecyclerViewAdapter mAdapter = new PostRecyclerViewAdapter(postList, this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
