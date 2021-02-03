package worldline.ssm.rd.ux.wltwitter.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TweetsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TweetsFragment extends Fragment implements TweetChangeListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public TweetsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TweetsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TweetsFragment newInstance(String param1, String param2) {
        TweetsFragment fragment = new TweetsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private RetrieveTweetsAsyncTask mTweetsAsyncTask;
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        final String login = PreferenceUtils.getLogin();
        if(!TextUtils.isEmpty(login)) {
            mTweetsAsyncTask = new RetrieveTweetsAsyncTask(this);
            mTweetsAsyncTask.execute(login);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_tweets, container, false);
        mListView = (ListView) RootView.findViewById(R.id.list);
        return RootView;

    }



    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {

       final ArrayAdapter<Tweet> adapter = new ArrayAdapter<Tweet>(getActivity(),
                                                            android.R.layout.simple_list_item_1
                                                            , tweets);
        mListView.setAdapter(adapter);

    }
}