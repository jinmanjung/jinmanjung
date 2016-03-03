package com.drogsin.rsspotal.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.WindowManager;
import android.widget.Toast;

public class FragmentBase extends Fragment {
	protected Context mContext;
	private ProgressDialog mProgressDialog;
	private AlertDialog.Builder builder;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Activity activity = this.getActivity();
		if (activity != null) {
			mContext = activity;
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}

	public void showProgress(Context context) {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(context);
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mProgressDialog.setCancelable(false);
			mProgressDialog.setIndeterminate(true);
			mProgressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			mProgressDialog.show();
		} else {
			mProgressDialog.show();
		}
	}

	public void hideProgress() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}

	public void showToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	
	public void showDialog(String title, String message, int icon) {
		if (builder == null) {
			builder = new AlertDialog.Builder(mContext); 
		} else {
			builder.setTitle(title)
			.setMessage(message)
			.setIcon(icon)
			.setNegativeButton("아니오", new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			})
			.setPositiveButton("예", new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			})
			.show();
		}
	}
	
	public void showConfirmAfterAction(String title, String message, final Intent intent) {
		builder = new AlertDialog.Builder(mContext);
		builder
		.setTitle(title)       		// 제목 설정
		.setMessage(message)  	// 메세지 설정
		.setCancelable(false)        		// 뒤로 버튼 클릭시 취소 가능 설정
		.setPositiveButton("확인", new DialogInterface.OnClickListener(){       
		 // 확인 버튼 클릭시 설정
		public void onClick(DialogInterface dialog, int whichButton){
			startActivity(intent);
			dialog.dismiss();
			getActivity().finish();
		}
		})
		.setNegativeButton("취소", new DialogInterface.OnClickListener(){      
		     // 취소 버튼 클릭시 설정
		public void onClick(DialogInterface dialog, int whichButton){
			dialog.cancel();
		}
		});
	
	AlertDialog dialog = builder.create();
	dialog.show();
	}
	
	public void showConfirmAfterAction(String title, String message, final Intent intent, final Activity activity) {
		builder = new AlertDialog.Builder(mContext);
		builder
		.setTitle(title)       		// 제목 설정
		.setMessage(message)  	// 메세지 설정
		.setCancelable(false)        		// 뒤로 버튼 클릭시 취소 가능 설정
		.setPositiveButton("확인", new DialogInterface.OnClickListener(){       
		 // 확인 버튼 클릭시 설정
		public void onClick(DialogInterface dialog, int whichButton){
			activity.startActivity(intent);
			dialog.dismiss();
			activity.finish();
		}
		})
		.setNegativeButton("취소", new DialogInterface.OnClickListener(){      
		     // 취소 버튼 클릭시 설정
		public void onClick(DialogInterface dialog, int whichButton){
			dialog.cancel();
		}
		});
	
	AlertDialog dialog = builder.create();
	dialog.show();
	}
	
	public void showConfirmAfterActionNotFinish(String title, String message, final Intent intent, final Activity activity) {
		if (mContext != null) {
			builder = new AlertDialog.Builder(mContext);
		} else {
			builder = new AlertDialog.Builder(activity);
		}
		builder
		.setTitle(title)       		// 제목 설정
		.setMessage(message)  	// 메세지 설정
		.setCancelable(false)        		// 뒤로 버튼 클릭시 취소 가능 설정
		.setPositiveButton("확인", new DialogInterface.OnClickListener(){
		 // 확인 버튼 클릭시 설정
		public void onClick(DialogInterface dialog, int whichButton){
			activity.startActivity(intent);
			dialog.dismiss();
//			activity.finish();
		}
		})
		.setNegativeButton("취소", new DialogInterface.OnClickListener(){
		     // 취소 버튼 클릭시 설정
		public void onClick(DialogInterface dialog, int whichButton){
			dialog.cancel();
		}
		});
	
	AlertDialog dialog = builder.create();
	dialog.show();
	}
	
	public void showAlert(String message) {
		builder = new AlertDialog.Builder(mContext);
		builder
		.setTitle("알림")
		.setMessage(message)  	// 메세지 설정
		.setCancelable(false)        		// 뒤로 버튼 클릭시 취소 가능 설정
		.setPositiveButton("확인", new DialogInterface.OnClickListener(){
		 // 확인 버튼 클릭시 설정
		public void onClick(DialogInterface dialog, int whichButton){
			dialog.dismiss();
		}
		});
	
	AlertDialog dialog = builder.create();
	dialog.show();
	}
}
