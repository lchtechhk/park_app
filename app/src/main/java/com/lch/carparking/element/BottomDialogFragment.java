package com.lch.carparking.element;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lch.carparking.R;
import com.lch.carparking.core.model.BottomDialogModel;
import com.lch.carparking.core.presenter.BottomDialogPresenter;
import com.lch.carparking.core.view.BottomDialogView;
import com.lch.carparking.structure.CarPaking.CarParkActivity;
import com.lch.carparking.utils.ObjectUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class BottomDialogFragment extends BottomSheetDialogFragment implements BottomDialogView, View.OnClickListener {
    @BindView(R.id.left_layout)
    LinearLayout left_layout;
    @BindView(R.id.left_time)
    TextView left_time;
    @BindView(R.id.left_charge)
    TextView left_charge;
    @BindView(R.id.left_distance)
    TextView left_distance;

    @BindView(R.id.middle_layout)
    LinearLayout middle_layout;
    @BindView(R.id.middle_time)
    TextView middle_time;
    @BindView(R.id.middle_charge)
    TextView middle_charge;
    @BindView(R.id.middle_distance)
    TextView middle_distance;

    @BindView(R.id.right_layout)
    LinearLayout right_layout;
    @BindView(R.id.right_time)
    TextView right_time;
    @BindView(R.id.right_charge)
    TextView right_charge;
    @BindView(R.id.right_distance)
    TextView right_distance;



    public BottomDialogFragment(BottomDialogModel bottomDialogModel) {
        this.bottomDialogModel = bottomDialogModel;
    }

    //
    private BottomDialogModel bottomDialogModel;
    private BottomDialogPresenter bottomDialogPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.bottom_suggetion_direction, container, false);
        ButterKnife.bind(this, view);
        bottomDialogPresenter = new BottomDialogPresenter(this);
        bottomDialogPresenter.passData(bottomDialogModel);
        left_layout.setOnClickListener(this);
        middle_layout.setOnClickListener(this);
        right_layout.setOnClickListener(this);
        return view;
    }
    @Override public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowParams);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//
//        }
        Intent i = new Intent(getActivity(), CarParkActivity.class);
        startActivity(i);

    }

    @Override
    public void update_left_time_textview(String time) {
        if(ObjectUtils.isNotNullEmpty(time))left_time.setText(time);
    }

    @Override
    public void update_left_charge_textview(String amount) {
        if(ObjectUtils.isNotNullEmpty(amount))left_charge.setText(amount);
    }

    @Override
    public void update_left_distant_textview(String distant) {
        if(ObjectUtils.isNotNullEmpty(distant))left_distance.setText(distant);
    }

    @Override
    public void update_middle_time_textview(String time) {
        if(ObjectUtils.isNotNullEmpty(time))middle_time.setText(time);
    }

    @Override
    public void update_middle_charge_textview(String amount) {
        if(ObjectUtils.isNotNullEmpty(amount))middle_charge.setText(amount);
    }

    @Override
    public void update_middle_distant_textview(String distant) {
        if(ObjectUtils.isNotNullEmpty(distant))middle_distance.setText(distant);
    }

    @Override
    public void update_right_time_textview(String time) {
        if(ObjectUtils.isNotNullEmpty(time))right_time.setText(time);
    }

    @Override
    public void update_right_charge_textview(String amount) {
        if(ObjectUtils.isNotNullEmpty(amount))right_charge.setText(amount);
    }

    @Override
    public void update_right_distant_textview(String distant) {
        if(ObjectUtils.isNotNullEmpty(distant))right_distance.setText(distant);
    }

}
