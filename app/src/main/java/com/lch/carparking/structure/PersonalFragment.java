package com.lch.carparking.structure;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lch.carparking.R;

import butterknife.ButterKnife;

public class PersonalFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_personal,container , false);
        ButterKnife.bind(this,rootView);
//        pager.setPagingEnabled(false);
        return rootView;
    }
}