package com.lch.carparking.element;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lch.carparking.MainActivity;
import com.lch.carparking.MainActivity_ViewBinding;
import com.lch.carparking.R;
import com.lch.carparking.core.model.CarParkingModel;
import com.lch.carparking.core.presenter.CarParkPresenter;
import com.lch.carparking.dao.CarparkDao;
import com.lch.carparking.network.ResponseModel;

/**
 * Created by james on 15/3/2017.
 */

public class ClassicDialog {

    private static MaterialDialog materialDialog;

    public static void showIndeterminate(Context context, String title, String content){
        materialDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
//                .canceledOnTouchOutside(false)
//                .cancelable(false)
                .show();
    }

    public static void showBasicDialog(Context context, String title, String content){
        materialDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(R.string.yes)
                .show();
    }

    public static void show_holding(final Context context, CarParkingModel carParkingModel, CarparkDao carparkDao, String request_json, CarParkPresenter carParkPresenter){
        materialDialog = new MaterialDialog.Builder(context)
                .title(carParkingModel.getCode())
                .content("要保留？")
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ResponseModel responseModel = carparkDao.request_holding(request_json);
                        if(!responseModel.isMessage_status())Toast.makeText(context,responseModel.getMessage(),Toast.LENGTH_SHORT).show();
                        carParkPresenter.updateGridView(responseModel.getPark_data());
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
    public static void showQ_R_arrvial(final Context context, CarParkingModel carParkingModel, CarparkDao carparkDao, String request_json, CarParkPresenter carParkPresenter){
        materialDialog = new MaterialDialog.Builder(context)
                .title(carParkingModel.getCode())
                .content("是否已到達")
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent i = new Intent(context,QRActivity.class);
                        i.putExtra("pre_arrival",request_json);
                        context.startActivity(i);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
    public static void show_payment(final Context context, CarParkingModel carParkingModel, CarparkDao carparkDao, String request_json, CarParkPresenter carParkPresenter){
        materialDialog = new MaterialDialog.Builder(context)
                .title(carParkingModel.getCode())
                .content("支付：$"+carParkingModel.getAmount())
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ResponseModel responseModel = carparkDao.request_payment(request_json);
                        if(!responseModel.isMessage_status())Toast.makeText(context,responseModel.getMessage(),Toast.LENGTH_SHORT).show();
                        carParkPresenter.updateGridView(responseModel.getPark_data());
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static void show_arrvial(final Context context, CarParkingModel carParkingModel, CarparkDao carparkDao, String request_json, CarParkPresenter carParkPresenter){
        materialDialog = new MaterialDialog.Builder(context)
                .title(carParkingModel.getCode())
                .content("是否已到達")
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ResponseModel responseModel = carparkDao.request_arrival(request_json);
                        if(!responseModel.isMessage_status())Toast.makeText(context,responseModel.getMessage(),Toast.LENGTH_SHORT).show();
                        carParkPresenter.updateGridView(responseModel.getPark_data());
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static void place_list(final Context context){
        new MaterialDialog.Builder(context)
                .title("尋找最近的出口")
                .items(R.array.place_array)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            Toast.makeText(context,which,Toast.LENGTH_SHORT).show();
                        /**
                         * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected radio button to actually be selected.
                         **/
                        return true;
                    }
                })
                .positiveText("OK")
                .show();
    }

    public static void show_level(final Context context, CarParkingModel carParkingModel, CarparkDao carparkDao, String request_json, CarParkPresenter carParkPresenter){
        materialDialog = new MaterialDialog.Builder(context)
                .title(carParkingModel.getCode())
                .content("是否已離開")
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent i = new Intent(context,QRActivity.class);
                        i.putExtra("pre_leave",request_json);
                        context.startActivity(i);
//                        ResponseModel responseModel = carparkDao.request_leave(request_json);
//                        if(!responseModel.isMessage_status())Toast.makeText(context,responseModel.getMessage(),Toast.LENGTH_SHORT).show();
//                        carParkPresenter.updateGridView(responseModel.getPark_data());
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static void show_used_park(final Context context){
        materialDialog = new MaterialDialog.Builder(context)
                .title("沒有空位")
                .content("請找其他車位")
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.yes)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
    public static void showLeave(final Context context,String content){
        materialDialog = new MaterialDialog.Builder(context)
                .content(content)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.action_settings)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent=new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                        System.exit(0);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static void back_map(final Context context){
        materialDialog = new MaterialDialog.Builder(context)
                .content("返回地圖？")
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent i = new Intent(context, MainActivity.class);
                        context.startActivity(i);
                        System.exit(0);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static void dismiss(){
        Log.v("asd","Dialog Dismiss :" + materialDialog.isCancelled());
        materialDialog.dismiss();

    }
}
