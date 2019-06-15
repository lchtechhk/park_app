package com.lch.carparking.core.presenter;

import com.lch.carparking.core.model.BottomDialogModel;
import com.lch.carparking.core.view.BottomDialogView;

public class BottomDialogPresenter {
    private BottomDialogView bottomDialogView;

    public BottomDialogPresenter(BottomDialogView bottomDialogView) {
        this.bottomDialogView = bottomDialogView;
    }

    public void passData(BottomDialogModel bottomDialogModel){
        this.bottomDialogView.update_left_time_textview(bottomDialogModel.getLeft_time());
        this.bottomDialogView.update_left_charge_textview(bottomDialogModel.getLeft_charge());
        this.bottomDialogView.update_left_distant_textview(bottomDialogModel.getLeft_distant());

        this.bottomDialogView.update_middle_time_textview(bottomDialogModel.getMiddle_time());
        this.bottomDialogView.update_middle_charge_textview(bottomDialogModel.getMiddle_charge());
        this.bottomDialogView.update_middle_distant_textview(bottomDialogModel.getMiddle_distant());

        this.bottomDialogView.update_right_time_textview(bottomDialogModel.getRight_time());
        this.bottomDialogView.update_right_charge_textview(bottomDialogModel.getRight_charge());
        this.bottomDialogView.update_right_distant_textview(bottomDialogModel.getRight_distant());

    }

}
