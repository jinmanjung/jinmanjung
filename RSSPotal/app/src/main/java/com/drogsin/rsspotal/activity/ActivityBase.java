package com.drogsin.rsspotal.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.drogsin.rsspotal.R;

/**
 * Created by jjm on 2015-10-12.
 */
public class ActivityBase extends AppCompatActivity {
    protected Context mContext;

    private AlertDialog.Builder builder;
    private Dialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        overridePendingTransition(R.animator.puff_in, R.animator.puff_in);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.animator.scale_in, R.animator.scale_in);
    }

    public void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public void showConfirmAlert(String title, String message) {
        builder = new AlertDialog.Builder(this);
        builder
                .setTitle(title)       		// 제목 설정
                .setMessage(message)  	// 메세지 설정
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton(getString(R.string.str_yes), new DialogInterface.OnClickListener(){
                    // 확인 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton){
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.str_no), new DialogInterface.OnClickListener() {
                    // 취소 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new Dialog(mContext, R.style.TransDialog);
            ProgressBar pb = new ProgressBar(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mProgressDialog.addContentView(pb, params);
            mProgressDialog.setCancelable(false);
            mProgressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            mProgressDialog.show();
        } else {
            mProgressDialog.show();
        }
    }

    public void hideProgress() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
        mProgressDialog = null;
    }
}
