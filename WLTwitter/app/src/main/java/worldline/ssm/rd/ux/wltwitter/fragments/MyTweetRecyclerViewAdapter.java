package worldline.ssm.rd.ux.wltwitter.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

import java.util.List;


public class MyTweetRecyclerViewAdapter extends RecyclerView.Adapter<MyTweetRecyclerViewAdapter.ViewHolder> {

    private final List<Tweet> mValues;
    private final TweetListener mListener;

    public MyTweetRecyclerViewAdapter(List<Tweet> items, TweetListener Listener) {
        mValues = items;
        mListener = Listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweet_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.name.setText(mValues.get(position).user.name);
        holder.alias.setText(mValues.get(position).user.screenName);
        holder.text.setText(mValues.get(position).text);
        if(!holder.mItem.user.profileImageUrl.isEmpty()){
            Picasso.get().load(holder.mItem.user.profileImageUrl).fit().into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public final TextView alias;
        public final TextView text;
        public ImageView image;
        public Tweet mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.tweetListitemNameTextView);
            alias = (TextView) view.findViewById(R.id.tweetListitemAliasTextView);
            text = (TextView) view.findViewById(R.id.tweetListitemTweetTextView);
            image = (ImageView) view.findViewById(R.id.tweetListimageView);

            view.setOnClickListener((v)->{
                if(mListener != null) mListener.onViewTweet(mItem);
            }
            );
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }
    }
}