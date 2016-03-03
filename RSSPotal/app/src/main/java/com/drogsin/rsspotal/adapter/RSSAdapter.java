package com.drogsin.rsspotal.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.drogsin.rsspotal.R;
import com.drogsin.rsspotal.activity.WebViewActivity;
import com.drogsin.rsspotal.application.RSSPortalApplication;
import com.drogsin.rsspotal.model.RSSItem;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


public class RSSAdapter extends BaseAdapterBase {
	private final String TAG = "RSSAdapter";
	private Context context;
	private ArrayList<RSSItem> result;

	public RSSAdapter(Context context, ArrayList<RSSItem> result) {
		super(context);
		this.context = context;
		this.result = result;
	}

	@Override
	public int getCount() {
		return result.size();
	}

	@Override
	public RSSItem getItem(int positon) {
		return result.get(positon);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_cardview, null);
			holder = new ViewHolder();

			holder.titleTv = (TextView) convertView.findViewById(R.id.tv_title);
			holder.imageIv = (ImageView) convertView.findViewById(R.id.image);
			holder.creatorIv = (ImageView) convertView.findViewById(R.id.iv_creator);
			holder.creatorTv = (TextView) convertView.findViewById(R.id.tv_creator);
			holder.pubDateTv = (TextView) convertView.findViewById(R.id.tv_pubdate);
			holder.cardView = (CardView) convertView.findViewById(R.id.cardview);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final RSSItem item = result.get(position);
		if ("".equals(item.getImageUrl()) || item.getImageUrl() == null) {
			holder.imageIv.setVisibility(View.GONE);
		} else {
			holder.imageIv.setVisibility(View.VISIBLE);
			float scale = parent.getContext().getResources().getDisplayMetrics().density;
			int heightDp = (int) (item.getImageHeight() / 2 * scale);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightDp + 200);
			holder.imageIv.setLayoutParams(params);
			ImageLoader.getInstance().displayImage(item.getImageUrl(), holder.imageIv, ((RSSPortalApplication) (context.getApplicationContext())).getDisplayImageOptions());
		}
		holder.titleTv.setText(item.getTitle());
		holder.creatorTv.setText(item.getCreator());
		if (!"".equals(item.getPubDate())) {
			holder.pubDateTv.setText(item.getPubDate().substring(0, item.getPubDate().length() - 3));
		}
		switch (item.getCreator()) {
			// 연예
			case "뉴스엔" :
				holder.creatorIv.setImageResource(R.drawable.ci_newsn);
				break;
			case "OSEN" :
				holder.creatorIv.setImageResource(R.drawable.ci_osen);
				break;
			case "TV리포트" :
				holder.creatorIv.setImageResource(R.drawable.ci_tv_report);
				break;
			case "티브이데일리" :
				holder.creatorIv.setImageResource(R.drawable.ci_tv_daily);
				break;
			case "스타뉴스" :
				holder.creatorIv.setImageResource(R.drawable.ci_starnews);
				break;
			case "조이뉴스24" :
				holder.creatorIv.setImageResource(R.drawable.ci_joynews);
				break;
			case "디스패치" :
				holder.creatorIv.setImageResource(R.drawable.ci_dispatch);
				break;
			case "스타투데이" :
				holder.creatorIv.setImageResource(R.drawable.ci_startoday);
				break;
			case "enews24" :
				holder.creatorIv.setImageResource(R.drawable.ci_enews24);
				break;

			// 스포츠
			case "인터풋볼" :
				holder.creatorIv.setImageResource(R.drawable.ci_interfootball);
				break;
			case "스포탈코리아" :
				holder.creatorIv.setImageResource(R.drawable.ci_sportal);
				break;
			case "골닷컴" :
				holder.creatorIv.setImageResource(R.drawable.ci_goaldotcom);
				break;
			case "MK스포츠" :
				holder.creatorIv.setImageResource(R.drawable.ci_mk);
				break;
			case "연합뉴스" :
				holder.creatorIv.setImageResource(R.drawable.ci_yonhap);
				break;
			case "마이데일리" :
				holder.creatorIv.setImageResource(R.drawable.ci_mydaily);
				break;
			case "스포츠경향" :
				holder.creatorIv.setImageResource(R.drawable.ci_sports_kh);
				break;
			case "일간스포츠" :
				holder.creatorIv.setImageResource(R.drawable.ci_isplus);
				break;
			case "스포츠조선" :
				holder.creatorIv.setImageResource(R.drawable.ci_sports_chosun);
				break;
			case "이데일리" :
				holder.creatorIv.setImageResource(R.drawable.ci_edaily);
				break;
			case "베스트일레븐" :
				holder.creatorIv.setImageResource(R.drawable.ci_besteleven);
				break;
			case "엑스포츠뉴스" :
				holder.creatorIv.setImageResource(R.drawable.ci_xports);
				break;
			case "스포츠동아" :
				holder.creatorIv.setImageResource(R.drawable.ci_sports_donga);
				break;
			case "스포츠한국" :
				holder.creatorIv.setImageResource(R.drawable.ci_sports_korea);
				break;
			case "스포츠서울" :
				holder.creatorIv.setImageResource(R.drawable.ci_sports_seoul);
				break;
			case "스포츠투데이" :
				holder.creatorIv.setImageResource(R.drawable.ci_stoo);
				break;
			case "점프볼" :
				holder.creatorIv.setImageResource(R.drawable.ci_jumpball);
				break;
			case "스포티비뉴스" :
				holder.creatorIv.setImageResource(R.drawable.ci_spotv);
				break;
			case "포모스" :
				holder.creatorIv.setImageResource(R.drawable.ci_fomos);
				break;
			case "풋볼리스트" :
				holder.creatorIv.setImageResource(R.drawable.ci_footballist);
				break;
			case "하키뉴스코리아" :
				holder.creatorIv.setImageResource(R.drawable.ci_hockeynews);
				break;

			// 시사
			case "경향신문" :
				holder.creatorIv.setImageResource(R.drawable.ci_khan);
				break;
			case "뉴스1" :
				holder.creatorIv.setImageResource(R.drawable.ci_news1);
				break;
			case "연합뉴스TV" :
				holder.creatorIv.setImageResource(R.drawable.ci_yonhap_tv);
				break;
			case "노컷뉴스" :
				holder.creatorIv.setImageResource(R.drawable.ci_nocut);
				break;
			case "문화일보" :
				holder.creatorIv.setImageResource(R.drawable.ci_munhwa);
				break;
			case "머니투데이" :
				holder.creatorIv.setImageResource(R.drawable.ci_money_today);
				break;
			case "MBN" :
				holder.creatorIv.setImageResource(R.drawable.ci_mbn);
				break;
			case "오마이뉴스" :
				holder.creatorIv.setImageResource(R.drawable.ci_ohmynews);
				break;
			case "국민일보" :
				holder.creatorIv.setImageResource(R.drawable.ci_kmib);
				break;
			case "한국일보" :
				holder.creatorIv.setImageResource(R.drawable.ci_hankookib);
				break;
			case "뉴시스" :
				holder.creatorIv.setImageResource(R.drawable.ci_newsis);
				break;
			case "아이뉴스24" :
				holder.creatorIv.setImageResource(R.drawable.ci_inews24);
				break;
			case "중앙일보" :
				holder.creatorIv.setImageResource(R.drawable.ci_joins);
				break;
			case "세계일보" :
				holder.creatorIv.setImageResource(R.drawable.ci_segye);
				break;
			case "EBS" :
				holder.creatorIv.setImageResource(R.drawable.ci_ebs);
				break;
			case "뉴스토마토" :
				holder.creatorIv.setImageResource(R.drawable.ci_newstomato);
				break;
			case "KBS" :
				holder.creatorIv.setImageResource(R.drawable.ci_kbs);
				break;
			case "한겨레" :
				holder.creatorIv.setImageResource(R.drawable.ci_hani);
				break;
			case "한겨레21" :
				holder.creatorIv.setImageResource(R.drawable.ci_hani);
				break;
			case "조선일보" :
				holder.creatorIv.setImageResource(R.drawable.ci_chosun);
				break;
			case "YTN" :
				holder.creatorIv.setImageResource(R.drawable.ci_ytn);
				break;
			case "동아일보" :
				holder.creatorIv.setImageResource(R.drawable.ci_donga);
				break;
			case "서울신문" :
				holder.creatorIv.setImageResource(R.drawable.ci_seoulsinmun);
				break;
			case "쿠키뉴스" :
				holder.creatorIv.setImageResource(R.drawable.ci_kukinews);
				break;
			case "MBC" :
				holder.creatorIv.setImageResource(R.drawable.ci_mbc);
				break;
			case "MBC연예" :
				holder.creatorIv.setImageResource(R.drawable.ci_mbc);
				break;
			case "SBS" :
				holder.creatorIv.setImageResource(R.drawable.ci_sbs);
				break;
			case "시사INLive" :
				holder.creatorIv.setImageResource(R.drawable.ci_sisainlive);
				break;
			// 경제
			case "해럴드경제" :
				holder.creatorIv.setImageResource(R.drawable.ci_herald);
				break;
			case "헤럴드경제" :
				holder.creatorIv.setImageResource(R.drawable.ci_herald);
				break;
			case "아시아경제" :
				holder.creatorIv.setImageResource(R.drawable.ci_asiae);
				break;
			case "전자신문" :
				holder.creatorIv.setImageResource(R.drawable.ci_etnews);
				break;
			case "매일경제" :
				holder.creatorIv.setImageResource(R.drawable.ci_mk);
				break;
			case "파이낸셜뉴스" :
				holder.creatorIv.setImageResource(R.drawable.ci_fnnews);
				break;
			case "한국경제" :
				holder.creatorIv.setImageResource(R.drawable.ci_hk);
				break;
			case "서울경제" :
				holder.creatorIv.setImageResource(R.drawable.ci_seouleconomy);
				break;
			case "머니위크" :
				holder.creatorIv.setImageResource(R.drawable.ci_moneyweek);
				break;
			case "데일리안" :
				holder.creatorIv.setImageResource(R.drawable.ci_dailian);
				break;
			case "조선비즈" :
				holder.creatorIv.setImageResource(R.drawable.ci_chosun);
				break;
			// 문화예술
			case "코스모폴리탄" :
				holder.creatorIv.setImageResource(R.drawable.ci_cosmo);
				break;
			case "포토친구" :
				holder.creatorIv.setImageResource(R.drawable.ci_photofriend);
				break;
			case "코메디닷컴" :
				holder.creatorIv.setImageResource(R.drawable.ci_kormedi);
				break;
			case "하이닥" :
				holder.creatorIv.setImageResource(R.drawable.ci_hidoc);
				break;
			case "투어코리아" :
				holder.creatorIv.setImageResource(R.drawable.ci_tournews21);
				break;
			case "월간이밥차" :
				holder.creatorIv.setImageResource(R.drawable.ci_2bab);
				break;
			case "정책브리핑" :
				holder.creatorIv.setImageResource(R.drawable.ci_korea);
				break;
			// IT
			case "디지털타임스" :
				holder.creatorIv.setImageResource(R.drawable.ci_digital_times);
				break;
			case "지디넷코리아" :
				holder.creatorIv.setImageResource(R.drawable.ci_zdnet);
				break;

			default:
				holder.creatorIv.setImageResource(R.mipmap.ic_launcher);
				break;
		}

		holder.cardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent  = new Intent(context, WebViewActivity.class);
				intent.putExtra("linkUrl", item.getLink());
				context.startActivity(intent);
			}
		});
		return convertView;
	}

	public class ViewHolder {
		TextView titleTv;
		ImageView imageIv;
		ImageView creatorIv;
		TextView creatorTv;
		TextView pubDateTv;
		CardView cardView;
	}
}
