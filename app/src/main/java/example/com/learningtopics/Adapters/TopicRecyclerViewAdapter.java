package example.com.learningtopics.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import example.com.learningtopics.Connect;
import example.com.learningtopics.Models.Topic;
import example.com.learningtopics.R;


/**
 * Created by christiealtadonna on 4/7/17.
 */

public class TopicRecyclerViewAdapter extends RecyclerView.Adapter<TopicRecyclerViewAdapter.ViewHolder> {
    private List<Topic> topicList;
    private Context context; //for when call Connect
  //  private final TopicListFragment.OnTopicDetailsListener mListener;

    public TopicRecyclerViewAdapter(List<Topic> items, Context context){//, TopicListFragment.OnTopicDetailsListener listener) {
        Log.d("in constructor of", "adapter ");
        topicList = items;
        this.context = context;
//        mListener = listener;
    }

    public void setTopicList(List<Topic> items) {
        topicList = items;
        notifyDataSetChanged();
    }

    public Topic getItem(int i) { return topicList.get(i); }

    public long getItemId(int i) { return i;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topic_item, parent, false);
        return new ViewHolder(view);
    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        final View rowView = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.topic_item, viewGroup, false);
//        TextView topic_name = (TextView) rowView.findViewById(R.id.topicName);
//        topic_name.setText(topicList.get(i).getTopic());
//        return rowView;
//    }



    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (topicList == null) {
            return;
        }
        holder.mTopic = topicList.get(position);
        holder.topicName.setText(holder.mTopic.getTopic());

        final String topicID = holder.mTopic.getTopicId()+"";

        holder.topicName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect conn = new Connect(context);
                conn.execute("topic_page",topicID);
            }
        });
//        holder.buzzDate.setText(holder.buzz.getDate());
//        holder.buzzTitle.setText(holder.buzz.getTitle());
//        holder.voteCount.setText(Integer.toString(holder.buzz.getVoteCount()));
//        //PHASE 3
//        holder.user_id.setText(holder.buzz.getUserId());

//        holder.upVoteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("BUZZ", "Up Vote");
//                mListener.onUpvoteBuzz(holder.buzz);
//                //holder.buzz.setVoteCount(holder.buzz.getVoteCount() + 1);
//                //BuzzRecyclerViewAdapter.this.notifyDataSetChanged();
//            }
//        });
//
//        holder.downVoteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("BUZZ", "Down Vote");
//                mListener.onDownvoteBuzz(holder.buzz);
//                // holder.buzz.setVoteCount(holder.buzz.getVoteCount() - 1);
//                // BuzzRecyclerViewAdapter.this.notifyDataSetChanged();
//            }
//        });
        //PHASE 3 -when click author name
//        holder.user_id.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("BUZZ", "Selected Author");
//                mListener.onUserIdBuzz(holder.buzz.getUserId());
//            }
//        });

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onShowTopicDetails(holder.mTopic);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (topicList == null) {
            return 0;
        }
        return topicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public Topic mTopic;
        public TextView topicName;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            topicName = (TextView) view.findViewById(R.id.topicName);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + topicName.getText() + "'";
        }
    }
}
//    List<Topic> topicList;
//    private Context mContext;
//    private LayoutInflater mLayoutInflator;
//
//    public TopicListViewAdapter(Context context, List<Topic> topicList) {
//        this.topicList = topicList;
//        mContext = context;
//        mLayoutInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//
//    public int getCount() {
//        return topicList.size();
//    }
//
//    public Topic getItem(int i) {
//        return topicList.get(i);
//    }
//
//    public long getItemId(int i) {
//        return i;
//    }
//
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        final View rowView = mLayoutInflator.inflate(R.layout.topic_item, viewGroup, false);
//        TextView tv = (TextView) rowView.findViewById(R.id.topicName);
//        tv.setText(topicList.get(i).getTopic());
//
//        return rowView;
//    }








