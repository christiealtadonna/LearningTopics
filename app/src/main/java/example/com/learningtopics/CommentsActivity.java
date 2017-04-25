package example.com.learningtopics;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import example.com.learningtopics.Adapters.CommentListViewAdapter;
import example.com.learningtopics.Models.Comment;
import example.com.learningtopics.Models.Topic;
import example.com.learningtopics.Utils.SharedPrefUtils;

import static example.com.learningtopics.R.id.username;

public class CommentsActivity extends AppCompatActivity {

    List<Comment> commentList;
    int post_id;
    CommentListViewAdapter commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);


        final ListView listView = (ListView)findViewById(R.id.listview);
        Intent intent = getIntent();
        String comments = intent.getStringExtra("comments");
        ListView commentListView = (ListView) findViewById(R.id.listview);
        commentList = new ArrayList<>();
        commentsAdapter = new CommentListViewAdapter(this, commentList);

        if(!comments.equals("no comments")) {
            //commentList = new ArrayList<>();

            String splitComments[] = comments.split(",");
            String temp[];
            for (int i = 0; i < splitComments.length; i++) {
                temp = splitComments[i].split(":");
                Comment comment = new Comment(Integer.parseInt(temp[0]), temp[1], temp[2], Integer.parseInt(temp[3]));
                post_id = Integer.parseInt(temp[3]);
                Log.d("topid is: ", temp[0]);
                commentList.add(comment);
            }

            commentsAdapter.setCommentList(commentList);
            //CommentListViewAdapter commentsAdapter = new CommentListViewAdapter(this, commentList);
            // commentsAdapter = new ArrayAdapter<Comment>(this, android.R.layout.simple_list_item_1, android.R.id.text1, commentList);
            commentListView.setAdapter(commentsAdapter);
//        final ArrayAdapter adapter = new ArrayAdapter(this,R.layout.comments_layout,commentList);
//        listView.setAdapter(adapter);
        }
        else{
            String postID = intent.getStringExtra("post_id");
            post_id = Integer.parseInt(postID);
        }
        Button addCommentButton = (Button) findViewById(R.id.add_comment_button);

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newComment = (EditText) findViewById(R.id.add_comment);
                String body = newComment.getText().toString();
                String authorNew = SharedPrefUtils.getUsernamePreference(CommentsActivity.this);
                String postId = post_id+"";
                Connect conn = new Connect(CommentsActivity.this);
                conn.execute("add_comment",authorNew, body, postId);

                newComment.setText("");
            }
        });

    }


    public void refreshComments(String comments){
        commentList = new ArrayList<>();

        String splitComments[] = comments.split(",");
        String temp[];
        for (int i = 0; i < splitComments.length; i++) {
            temp = splitComments[i].split(":");
            Comment comment = new Comment(Integer.parseInt(temp[0]), temp[1], temp[2], Integer.parseInt(temp[3]));
            post_id = Integer.parseInt(temp[3]);
            Log.d("topid is: ", temp[0]);
            commentList.add(comment);
            Log.d("comment list i is: ", commentList.get(i)+"");
        }

        commentsAdapter.setCommentList(commentList);

//        Connect conn = new Connect(this);
//        String postId = post_id+"";
//        conn.execute("comments",postId);
    }


}
