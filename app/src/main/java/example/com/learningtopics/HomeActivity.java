package example.com.learningtopics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import example.com.learningtopics.Adapters.TopicRecyclerViewAdapter;
import example.com.learningtopics.Models.Topic;
import example.com.learningtopics.Utils.SharedPrefUtils;

public class HomeActivity extends AppCompatActivity{ //implements TopicListFragment.OnTopicDetailsListener {

    private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    TopicRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        String topics = intent.getStringExtra("topics");

        List<Topic> topicList = new ArrayList<>();

        String splitTopics[] = topics.split(",");
//        Topic topicList[]= new Topic[splitTopics.length];
        String temp[];
        for(int i = 0; i < splitTopics.length; i++){
            temp = splitTopics[i].split(":");
            Topic topic = new Topic(Integer.parseInt(temp[0]), temp[1]);
            Log.d("topid is: ",temp[0]);
            topicList.add(topic);
//            topicList[i]= new Topic(Integer.parseInt(temp[0]), temp[1]);
        }
        Log.d("topics are: ", topics);
//        ListView mListView = (ListView) findViewById(R.id.topic_listview);
        mAdapter = new TopicRecyclerViewAdapter(topicList, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(true);




        Button addTopicButton = (Button) findViewById(R.id.addTopicButton);

        addTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newTopic = (EditText) findViewById(R.id.add_topic);
                String topic = newTopic.getText().toString();

                Connect conn = new Connect(HomeActivity.this);
                conn.execute("add_topic",topic);

                newTopic.setText("");
            }
        });

    }


    public void refreshTopics(String topics){
        List<Topic> topicList = new ArrayList<>();

        String splitTopics[] = topics.split(",");
//        Topic topicList[]= new Topic[splitTopics.length];
        String temp[];
        for(int i = 0; i < splitTopics.length; i++){
            temp = splitTopics[i].split(":");
            Topic topic = new Topic(Integer.parseInt(temp[0]), temp[1]);
            Log.d("topid is: ",temp[0]);
            topicList.add(topic);
//            topicList[i]= new Topic(Integer.parseInt(temp[0]), temp[1]);
        }
        //mAdapter = new TopicRecyclerViewAdapter(topicList, this);
        mAdapter.setTopicList(topicList);

//
//        Connect con = new Connect(HomeActivity.this);
//        //must use instance of class that connects to database
//        String user_name = SharedPrefUtils.getUsernamePreference(this);
//        String password = SharedPrefUtils.getPasswordPreference(this);
//        con.execute("login", user_name, password);
    }

    public void onShowTopicDetails(Topic topic){

    }


}
