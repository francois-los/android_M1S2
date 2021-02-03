package worldline.ssm.rd.ux.wltwitter.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TweetDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TweetDetailFragment extends Fragment implements View.OnClickListener{

    private String mText;
    private String mUrl;
    private String mName;
    private String mAlias;

    public static TweetDetailFragment newInstance(String text, String url, String name, String alias) {
        TweetDetailFragment fragment = new TweetDetailFragment();
        Bundle args = new Bundle();
        args.putString(Constants.Tweet.EXTRA_TEXT, text);
        args.putString(Constants.Tweet.EXTRA_IMAGEURL, url);
        args.putString(Constants.Tweet.EXTRA_NAME, name);
        args.putString(Constants.Tweet.EXTRA_ALIAS, alias);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString(Constants.Tweet.EXTRA_TEXT);
            mUrl = getArguments().getString(Constants.Tweet.EXTRA_IMAGEURL);
            mName = getArguments().getString(Constants.Tweet.EXTRA_NAME);
            mAlias = getArguments().getString(Constants.Tweet.EXTRA_ALIAS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tweet_detail, container, false);

        ((TextView) view.findViewById(R.id.tweetListitemName)).setText(mName);
        ((TextView) view.findViewById(R.id.tweetListitemHandle)).setText("@"+mAlias);
        ((TextView) view.findViewById(R.id.tweetListitemTweet)).setText(mText);

        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this.getContext(),"reply to"+mText,Toast.LENGTH_SHORT).show();
    }
}