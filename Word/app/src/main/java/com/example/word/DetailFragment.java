package com.example.word;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.ContentValues.TAG;

public class DetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_detail,container,false);

        TextView word_Word=(TextView)view.findViewById(R.id.word_Word);
        word_Word.setText(getArguments().getString("Word"));

        TextView wordExplain=(TextView)view.findViewById(R.id.word_Explain);
        wordExplain.setText( getArguments().getString("Explain"));

        TextView wordSample=(TextView)view.findViewById(R.id.word_Sample);
        wordSample.setText(getArguments().getString("Sample"));

        return view;
    }








}
