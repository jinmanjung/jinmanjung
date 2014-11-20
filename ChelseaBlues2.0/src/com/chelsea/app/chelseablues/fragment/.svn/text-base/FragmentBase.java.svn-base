package com.chelsea.app.chelseablues.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.chelsea.app.chelseablues.application.ChelseaApplication;

public class FragmentBase extends Fragment {
	protected Context mContext;
	protected ChelseaApplication mChelseaApplication;
	private ProgressDialog mProgressDialog = null;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		Activity activity = this.getActivity();
		if (activity != null) {
			mChelseaApplication = (ChelseaApplication) activity.getApplication();
			mContext = activity;
		}
	}
	
	public void showProgress(String title, String message) {
		if (mProgressDialog == null) {
			mProgressDialog = ProgressDialog.show(mContext, title, message);
		}
		else {
			mProgressDialog.show();
		}
	}
	
	public void hideProgress() {
		if (mProgressDialog != null)
			mProgressDialog.dismiss();
	}
	
	public void showToast(String message) {
		Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
	}
}
