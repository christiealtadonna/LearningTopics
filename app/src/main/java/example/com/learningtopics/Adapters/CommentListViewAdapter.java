package example.com.learningtopics.Adapters;

import android.content.Context;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.learningtopics.Connect;
import example.com.learningtopics.Models.Comment;
import example.com.learningtopics.R;

/**
 * Created by christiealtadonna on 4/24/17.
 */

public class CommentListViewAdapter extends BaseAdapter {
    List<Comment> mComments;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String author;
    private String post_id;


    public CommentListViewAdapter(Context context, List<Comment> comments){//, LayoutInflater layoutInflater ){
        mComments = comments;
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public CommentListViewAdapter(Context context){//, LayoutInflater layoutInflater ){
        mContext = context;
        mComments = new ArrayList<>();
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void setCommentList(List<Comment> comments){
        mComments = comments;
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return mComments.size();
    }

    @Override
    public Object getItem(int i){
        return mComments.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        final View rowView = mLayoutInflater.inflate(R.layout.comment_item, viewGroup, false);
        TextView comment_text = (TextView) rowView.findViewById(R.id.comment_body);
        TextView comment_author = (TextView) rowView.findViewById(R.id.commentAuthor);
        comment_author.setText(mComments.get(i).getUsername());
        comment_text.setText(mComments.get(i).getBody());
       // Button addCommentButton = (Button) rowView.findViewById(R.id.add_comment_button);
      //  final int index = i;
//        addCommentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText newComment = (EditText) rowView.findViewById(R.id.add_comment);
//                String body = newComment.getText().toString();
//                String authorNew = mComments.get(index).getUsername();
//                String post_id = mComments.get(index).getPostId()+"";
//                Connect conn = new Connect(mContext);
//                conn.execute("add_comment",authorNew, body, post_id);
//            }
//        });



        return rowView;
    }


}