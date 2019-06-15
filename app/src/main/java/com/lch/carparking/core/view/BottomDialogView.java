package com.lch.carparking.core.view;

public interface BottomDialogView {
    void update_left_time_textview(String time);
    void update_left_charge_textview(String amount);
    void update_left_distant_textview(String distant);


    void update_middle_time_textview(String time);
    void update_middle_charge_textview(String amount);
    void update_middle_distant_textview(String distant);

    void update_right_time_textview(String time);
    void update_right_charge_textview(String amount);
    void update_right_distant_textview(String distant);

}
