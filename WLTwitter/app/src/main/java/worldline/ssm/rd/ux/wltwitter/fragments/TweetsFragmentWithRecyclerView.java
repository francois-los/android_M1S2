package worldline.ssm.rd.ux.wltwitter.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

/**
 * A fragment representing a list of Items.
 */
public class TweetsFragmentWithRecyclerView extends Fragment implements TweetChangeListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TweetsFragmentWithRecyclerView() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TweetsFragmentWithRecyclerView newInstance(int columnCount) {
        TweetsFragmentWithRecyclerView fragment = new TweetsFragmentWithRecyclerView();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    private RetrieveTweetsAsyncTask mTweetsAsyncTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        final String login = PreferenceUtils.getLogin();
        if(!TextUtils.isEmpty(login)) {
            mTweetsAsyncTask = new RetrieveTweetsAsyncTask(this);
            mTweetsAsyncTask.execute(login);
        }
    }

    private TweetListener mTweetListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TweetListener){
            mTweetListener = (TweetListener) context;
        }
    }

    private RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweets_with_recycler_view_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            mRecyclerView.setAdapter(new MyTweetRecyclerViewAdapter(new ArrayList<Tweet>(), mTweetListener));
        }
        return view;
    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {
        mRecyclerView.setAdapter(new MyTweetRecyclerViewAdapter(tweets, mTweetListener));
    }
}