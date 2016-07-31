package com.example.androidjoker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokerActivityFragment extends Fragment {

    public JokerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joker, container, false);
        TextView jokeTextView = (TextView)rootView.findViewById(R.id.joke_text_view);
        String joke = getActivity().getIntent().getStringExtra("joke");
        jokeTextView.setText(joke);
        return rootView;
    }
}
