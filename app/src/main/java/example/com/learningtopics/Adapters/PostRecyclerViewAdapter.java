package example.com.learningtopics.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import example.com.learningtopics.Connect;
import example.com.learningtopics.Models.Comment;
import example.com.learningtopics.Models.Post;
import example.com.learningtopics.R;


/**
 * Created by christiealtadonna on 4/7/17.
 */

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder> {
        private List<Post> postList;
        private Context context; //for when call Connect
        //  private final TopicListFragment.OnTopicDetailsListener mListener;


        public PostRecyclerViewAdapter(List<Post> items, Context context) {//, TopicListFragment.OnTopicDetailsListener listener) {
            Log.d("in constructor of", "adapter ");
            postList = items;
            this.context = context;
//        mListener = listener;
        }

        public PostRecyclerViewAdapter(Context context){
            this.context = context;
        }


        public void setPostList(List<Post> items) {
            postList = items;
            notifyDataSetChanged();
        }

        public Post getItem(int i) {
            return postList.get(i);
        }

        public long getItemId(int i) {
            return i;
        }


        @Override
        public PostRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.post_item, parent, false);
            return new PostRecyclerViewAdapter.ViewHolder(view);
        }


        public void onBindViewHolder(final PostRecyclerViewAdapter.ViewHolder holder, int position) {
            if (postList == null) {
                return;
            }
            holder.mPost = postList.get(position);
            holder.author.setText(holder.mPost.getUsername());
            holder.body.setText(holder.mPost.getBody());
            holder.likes.setText((holder.mPost.getLikes()+""));


            holder.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.mPost.setLikes(holder.mPost.getLikes()+1);

                    String likes = holder.mPost.getLikes()+"";
                    String postID = holder.mPost.getPostId()+"";
                    Connect conn = new Connect(context);
                    conn.execute("like", postID,likes);
                }
            });
            holder.author.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = holder.mPost.getUsername();
                    Connect conn = new Connect(context);
                    conn.execute("profile", username);
                }
            });
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Connect conn = new Connect(context);
                    String post_id = holder.mPost.getPostId()+"";
                    conn.execute("comments",post_id);
                }
            });
        }



        @Override
        public int getItemCount() {
            if (postList == null) {
                return 0;
            }
            return postList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;

            public Post mPost;
            public TextView author;
            public TextView body;
            public TextView likes;
            public Button likeButton;


            public ViewHolder(View view) {
                super(view);
                mView = view;
                author = (TextView) view.findViewById(R.id.author);
                body = (TextView) view.findViewById(R.id.body);
                likes = (TextView) view.findViewById(R.id.likes);
                likeButton = (Button) view.findViewById(R.id.likeButton);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + author.getText() + "'" +  body.getText() + " '" +likes.getText() +" '";
            }
        }
    }



