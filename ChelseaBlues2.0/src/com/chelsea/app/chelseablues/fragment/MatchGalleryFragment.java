package com.chelsea.app.chelseablues.fragment;

import java.util.ArrayList;
import java.util.Random;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.model.Record;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class MatchGalleryFragment extends FragmentBase {
	private final String TAG = "MatchGalleryFragment";
	private View mView;
	private ImageView galleryIv1;
	private ImageView galleryIv2;
	private ImageView galleryIv3;
	private ImageView galleryIv4;
	private ImageView galleryIv5;
	private Gallery gallery;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		new MatchGalleryTask().execute("");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_match_gallery, container, false);
		mView = view;
		galleryIv1 = (ImageView) mView.findViewById(R.id.iv_gallery1);
		galleryIv2 = (ImageView) mView.findViewById(R.id.iv_gallery2);
		galleryIv3 = (ImageView) mView.findViewById(R.id.iv_gallery3);
		galleryIv4 = (ImageView) mView.findViewById(R.id.iv_gallery4);
		galleryIv5 = (ImageView) mView.findViewById(R.id.iv_gallery5);
		galleryIv1.setImageResource(R.drawable.chelseabg);
		return mView;
	}

	/**
	 * MatchGalleryTask
	 */
	private class MatchGalleryTask extends AsyncTask<String, Void, ArrayList<Record>> {

		@Override
		protected ArrayList<Record> doInBackground(String... params) {
			// TODO Auto-generated method stub
			String mMatchUrl = params[0];;
			return mChelseaApplication.getMatchGallery(mContext, mMatchUrl);
		}

		@Override
		protected void onPostExecute(ArrayList<Record> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			viewGallery(result);
			hideProgress();
		}
	}
	
	private void viewGallery(final ArrayList<Record> result) {
		// TODO Auto-generated method stub
//		gallery.setAdapter(new GalleryAdapter(mContext, result));
		for (int i = 0; i < result.size(); i++) {
			Random random = new Random();
			int rand = random.nextInt(result.size());
			UrlImageViewHelper.setUrlDrawable(galleryIv1, result.get(rand).get("galleryImageUrl"));
			rand = random.nextInt(result.size());
			UrlImageViewHelper.setUrlDrawable(galleryIv2, result.get(rand).get("galleryImageUrl"));
			rand = random.nextInt(result.size());
			UrlImageViewHelper.setUrlDrawable(galleryIv3, result.get(rand).get("galleryImageUrl"));
			rand = random.nextInt(result.size());
			UrlImageViewHelper.setUrlDrawable(galleryIv4, result.get(rand).get("galleryImageUrl"));
			rand = random.nextInt(result.size());
			UrlImageViewHelper.setUrlDrawable(galleryIv5, result.get(rand).get("galleryImageUrl"));
		}
	}

	// 외부에서 asynctask call
	public void callAsyncTask(String link) {
		showProgress("", "Data loading...");
		new MatchGalleryTask().execute(link);
	}
}
