package kr.co.interfootball.totopang.util;

import java.io.InputStream;
import java.lang.reflect.Field;

import kr.co.interfootball.totopang.R;
import kr.co.interfootball.totopang.application.TotopangApplication;
import kr.co.interfootball.totopang.application.TutoInfo;
import kr.co.interfootball.totopang.widget.GifDecoderView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.text.BoringLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyAdView;
import com.fsn.cauly.CaulyAdViewListener;
import com.fsn.cauly.CaulyInterstitialAd;
import com.fsn.cauly.CaulyInterstitialAdListener;
import com.tpmn.adsdk.publisher.AdError;
import com.tpmn.adsdk.publisher.AdView.OnAdClickedListener;
import com.tpmn.adsdk.publisher.AdView.OnAdClosedListener;
import com.tpmn.adsdk.publisher.AdView.OnAdFailedListener;
import com.tpmn.adsdk.publisher.AdView.OnAdLoadedListener;
import com.tpmn.adsdk.publisher.BannerAdView;
import com.tpmn.adsdk.publisher.InterstitialAd;

public class UiUtils {
	private static final String TAG = "UiUtils";
	private static final String CAULY_TAG = "CAULY";
	private static final String TPMN_TAG = "TPMN";
	private static int ACTIONBAR_HOME_WIDTH = 110;
	/**
	 * 배당률 화살표 표시
	 * @param context
	 * @return
	 */
	public static void setDivRateArrow(ImageView imageView, final float latestRate, final float latestBeforeRate) {
		if (latestRate == 1.00) {
			imageView.setVisibility(View.GONE);
			return;
		}
		if (latestRate > latestBeforeRate) {
			imageView.setImageResource(R.drawable.red_arrow);
			imageView.setVisibility(View.VISIBLE);
		} else if (latestRate < latestBeforeRate) {
			imageView.setImageResource(R.drawable.blue_arrow);
			imageView.setVisibility(View.VISIBLE);
		} else {
			imageView.setVisibility(View.GONE);
		}
	}
	
	/**
	 * 프로토승부식 100점 이상 소수점 표시
	 * @param context
	 * @return
	 */
	public static void setPrimeScore(Context context, String score, TextView intScoreTv, TextView primeScoreTv, boolean homeWin) {
		if (score.length() >= 5 && score.contains(".")) {
			String intScore = score.substring(0, 3);
			String primeScore = score.substring(3, score.length());
			
			intScoreTv.setText(intScore);
			primeScoreTv.setVisibility(View.VISIBLE);
			primeScoreTv.setText(primeScore);
			// 색 처리
			if (homeWin) {
				intScoreTv.setTextColor(context.getResources().getColor(R.color.text_red_color));
				primeScoreTv.setTextColor(context.getResources().getColor(R.color.text_red_color));
			} else {
				intScoreTv.setTextColor(Color.BLACK);
				primeScoreTv.setTextColor(Color.BLACK);
			}
		} else {
			primeScoreTv.setVisibility(View.GONE);
			intScoreTv.setText(score);
			if (homeWin) {
				intScoreTv.setTextColor(context.getResources().getColor(R.color.text_red_color));
			} else {
				intScoreTv.setTextColor(Color.BLACK);
			}
		}
	}
	/**
	 * fragment 제거
	 * @param mFragmentTransaction
	 * @param mFragment
	 */
	public static void removeFragment(FragmentTransaction mFragmentTransaction, Fragment mFragment) {
		mFragmentTransaction.remove(mFragment);
		mFragmentTransaction.commit();
	}
	/**
	 * 각 기기 해상도 별로 custom actionbar width 지정 
	 * @param context
	 * @param actionBarLayout
	 */
	public static void resizeCustomActionLayoutWidth(Context context, LinearLayout actionBarLayout) {
		int resizeWidth = Utils.getDeviceMaxWidth(context) - ACTIONBAR_HOME_WIDTH;
		actionBarLayout.setLayoutParams(new LayoutParams(resizeWidth, LayoutParams.MATCH_PARENT));
	}
	
	/**
	 * cauly 하나만 set
	 * @param context
	 * @param caulyAdView
	 */
	public static void setAdView(Context context, final CaulyAdView caulyAdView) {
		// CaulyAdInfo 상세 설정 방법은 하단 표 참조
		// 설정하지 않은 항목들은 기본값으로 설정됨
		CaulyAdInfo adInfo = new CaulyAdInfoBuilder(context.getString(R.string.cauly_app_code)).effect("RightSlide").
		 build();
		caulyAdView.setAdInfo(adInfo);
		caulyAdView.setAdViewListener(new CaulyAdViewListener() {
			
			@Override
			public void onShowLandingScreen(CaulyAdView adView) {
				// 광고 배너를 클릭하여 랜딩 페이지가 열린 경우 호출됨.
				Log.d(CAULY_TAG, "banner AD landing screen opened.");
			}
			
			@Override
			public void onReceiveAd(CaulyAdView adView, boolean isChargeableAd) {
				// 광고 수신 성공 & 노출된 경우 호출됨.
				// 수신된 광고가 무료 광고인 경우 isChargeableAd 값이 false 임.
				if (isChargeableAd == false) {
					Log.d(CAULY_TAG, "free banner AD received.");
				}
				else {
					Log.d(CAULY_TAG, "normal banner AD received.");
				}
			}
			
			@Override
			public void onFailedToReceiveAd(CaulyAdView adView, int errorCode, String errorMsg) {
				// 배너 광고 수신 실패할 경우 호출됨.
				Log.e(CAULY_TAG, "failed to receive banner AD.");
				adView.reload();
			}
			
			@Override
			public void onCloseLandingScreen(CaulyAdView adView) {
				// 광고 배너를 클릭하여 열린 랜딩 페이지가 닫힌 경우 호출됨.
				Log.d(CAULY_TAG, "banner AD landing screen closed.");
			}
		});
	}
	
	/**
	 * 두개이상의 광고 set
	 * @param context
	 * @param caulyAdView
	 * @param tpmnAdView 
	 */
	public static void setAdViews(final Context context, final CaulyAdView caulyAdView, final BannerAdView tpmnAdView) {
		switch (Utils.getRandomMath(2, 1)) {
		case 1:	// tpmn
			tpmnAdView.setPublisherId("INFB");
			tpmnAdView.setInventoryId("257");
			tpmnAdView.setRefreshInterval(30);
			tpmnAdView.setOnAdLoadedListener(new OnAdLoadedListener() {
				
				@Override
				public void OnAdLoaded() {
					if (TotopangApplication.IS_DEBUG_MODE) {
						Log.i(TPMN_TAG, "banner AD received.");
					}
					if (!tpmnAdView.isShown()) {
						tpmnAdView.setVisibility(View.VISIBLE);
					}
				}
			});
			tpmnAdView.setOnAdFailedListener(new OnAdFailedListener() {
				
				@Override
				public void OnAdFailed(AdError error, String errorMsg) {
					if (TotopangApplication.IS_DEBUG_MODE) {
						Log.i(TPMN_TAG, "failed to receive banner AD.");
					}
					if (tpmnAdView.isShown()) {
						tpmnAdView.setVisibility(View.GONE);
					}
//					caulyAdView.reload();
					// CaulyAdInfo 상세 설정 방법은 하단 표 참조
					// 설정하지 않은 항목들은 기본값으로 설정됨
					CaulyAdInfo adInfo = new CaulyAdInfoBuilder(context.getString(R.string.cauly_app_code)).effect("RightSlide").build();
					caulyAdView.setAdInfo(adInfo);
					caulyAdView.setAdViewListener(new CaulyAdViewListener() {
						
						@Override
						public void onShowLandingScreen(CaulyAdView adView) {
							// 광고 배너를 클릭하여 랜딩 페이지가 열린 경우 호출됨.
							if (TotopangApplication.IS_DEBUG_MODE) {
								Log.i(CAULY_TAG, "banner AD landing screen opened.");
							}
						}
						
						@Override
						public void onReceiveAd(CaulyAdView adView, boolean isChargeableAd) {
							// 광고 수신 성공 & 노출된 경우 호출됨.
							// 수신된 광고가 무료 광고인 경우 isChargeableAd 값이 false 임.
							if (isChargeableAd == false) {
								if (TotopangApplication.IS_DEBUG_MODE) {
									Log.i(CAULY_TAG, "free banner AD received.");
								}
							}
							else {
								if (TotopangApplication.IS_DEBUG_MODE) {
									Log.i(CAULY_TAG, "normal banner AD received.");
								}
							}
							if (!adView.isShown()) {
								adView.setVisibility(View.VISIBLE);
							}
						}
						
						@Override
						public void onFailedToReceiveAd(CaulyAdView adView, int errorCode, String errorMsg) {
							// 배너 광고 수신 실패할 경우 호출됨.
							if (TotopangApplication.IS_DEBUG_MODE) {
								Log.e(CAULY_TAG, "failed to receive banner AD.");
							}
							if (adView.isShown()) {
								adView.setVisibility(View.GONE);
							}
//							adView.reload();
							tpmnAdView.loadAd();
						}
						
						@Override
						public void onCloseLandingScreen(CaulyAdView adView) {
							// 광고 배너를 클릭하여 열린 랜딩 페이지가 닫힌 경우 호출됨.
							if (TotopangApplication.IS_DEBUG_MODE) {
								Log.i(CAULY_TAG, "banner AD landing screen closed.");
							}
						}
					});
				}
			});
			break;
		case 2:	// cauly
			CaulyAdInfo adInfo = new CaulyAdInfoBuilder(context.getString(R.string.cauly_app_code)).effect("RightSlide").build();
			caulyAdView.setAdInfo(adInfo);
			caulyAdView.setAdViewListener(new CaulyAdViewListener() {
				
				@Override
				public void onShowLandingScreen(CaulyAdView adView) {
					// 광고 배너를 클릭하여 랜딩 페이지가 열린 경우 호출됨.
					if (TotopangApplication.IS_DEBUG_MODE) {
						Log.i(CAULY_TAG, "banner AD landing screen opened.");
					}
				}
				
				@Override
				public void onReceiveAd(CaulyAdView adView, boolean isChargeableAd) {
					// 광고 수신 성공 & 노출된 경우 호출됨.
					// 수신된 광고가 무료 광고인 경우 isChargeableAd 값이 false 임.
					if (isChargeableAd == false) {
						if (TotopangApplication.IS_DEBUG_MODE) {
							Log.i(CAULY_TAG, "free banner AD received.");
						}
					}
					else {
						if (TotopangApplication.IS_DEBUG_MODE) {
							Log.i(CAULY_TAG, "normal banner AD received.");
						}
					}
					if (!adView.isShown()) {
						caulyAdView.setVisibility(View.VISIBLE);
					}
				}
				
				@Override
				public void onFailedToReceiveAd(CaulyAdView adView, int errorCode, String errorMsg) {
					// 배너 광고 수신 실패할 경우 호출됨.
					if (TotopangApplication.IS_DEBUG_MODE) {
						Log.e(CAULY_TAG, "failed to receive banner AD.");
					}
					if (adView.isShown()) {
						adView.setVisibility(View.GONE);
					}
					tpmnAdView.setPublisherId("INFB");
					tpmnAdView.setInventoryId("257");
					tpmnAdView.setRefreshInterval(30);
					tpmnAdView.setOnAdLoadedListener(new OnAdLoadedListener() {
						
						@Override
						public void OnAdLoaded() {
							if (TotopangApplication.IS_DEBUG_MODE) {
								Log.i(TPMN_TAG, "banner AD received.");
							}
							if (!tpmnAdView.isShown()) {
								tpmnAdView.setVisibility(View.VISIBLE);
							}
						}
					});
					tpmnAdView.setOnAdFailedListener(new OnAdFailedListener() {
						
						@Override
						public void OnAdFailed(AdError error, String errorMsg) {
							if (TotopangApplication.IS_DEBUG_MODE) {
								Log.i(TPMN_TAG, "failed to receive banner AD.");
							}
							if (tpmnAdView.isShown()) {
								tpmnAdView.setVisibility(View.GONE);
							}
							caulyAdView.reload();
						}
					});
				}
				
				@Override
				public void onCloseLandingScreen(CaulyAdView adView) {
					// 광고 배너를 클릭하여 열린 랜딩 페이지가 닫힌 경우 호출됨.
					if (TotopangApplication.IS_DEBUG_MODE) {
						Log.i(CAULY_TAG, "banner AD landing screen closed.");
					}
				}
			});
			break;
		}
	}
	
	
	/**
	 * 카울리 배너광고 리소스 해제
	 * @param caulyAdView
	 * @param adamAdView
	 */
	public static void clearAdViewResource(CaulyAdView caulyAdView) {
		if (caulyAdView != null) {
			caulyAdView.destroy();
			caulyAdView = null;
		}
	}
	
	public static void clearAdViewsResource(CaulyAdView caulyAdView, BannerAdView tpmnAdView) {
		if (caulyAdView != null) {
			caulyAdView.destroy();
			caulyAdView = null;
		}
		if (tpmnAdView != null) {
			tpmnAdView.destroy();
			tpmnAdView = null;
		}
	}
	
	public static void onRequestInterstitial(final Activity activity) {
		switch (Utils.getRandomMath(2, 1)) {
		case 1: 
			InterstitialAd mAdInter = new InterstitialAd(activity);
			mAdInter.setInventoryId("258");
			mAdInter.setPublisherId("INFB");
			mAdInter.setOnAdLoadedListener(new OnAdLoadedListener() {
				
				@Override
				public void OnAdLoaded() {
					if (TotopangApplication.IS_DEBUG_MODE) {
						Log.i(TPMN_TAG, "전면 광고가 로딩되었습니다.");	
					}
				}
			});
			mAdInter.setOnAdFailedListener(new OnAdFailedListener() {
				
				@Override
				public void OnAdFailed(AdError error, String errorMessage) {
					if (TotopangApplication.IS_DEBUG_MODE) {
						Log.e(TPMN_TAG, "failed to receive interstitial AD.");
						Log.e(TPMN_TAG, "errorMsg : " + errorMessage);
					}
//					activity.finish();
					// CaulyAdInfo 생성
					// 배너 광고와 동일하게 광고 요청을 설정할 수 있다.
					CaulyAdInfo interstitialAdInfo = new CaulyAdInfoBuilder(activity.getString(R.string.cauly_app_code)).build();
					// 전면 광고 생성
					CaulyInterstitialAd caulyInterstitialAd = new CaulyInterstitialAd();
					caulyInterstitialAd.setAdInfo(interstitialAdInfo);
					// 전면광고 노출 후 back 버튼을 막기를 원할 경우 disableBackKey();을 추가한다
					// 단, requestInterstitialAd 위에서 추가되어야 합니다.
					// interstitialAd.disableBackKey(); 
					
					// 광고 요청. 광고 노출은 CaulyInterstitialAdListener의 onReceiveInterstitialAd에서 처리한다.
					caulyInterstitialAd.requestInterstitialAd(activity);	
					caulyInterstitialAd.setInterstialAdListener(new CaulyInterstitialAdListener() {
						
						@Override
						public void onReceiveInterstitialAd(CaulyInterstitialAd ad, boolean isChargeableAd) {
							// 광고 수신 성공한 경우 호출됨.
							// 수신된 광고가 무료 광고인 경우 isChargeableAd 값이 false 임.
							if (isChargeableAd == false) {
								if (TotopangApplication.IS_DEBUG_MODE) {
									Log.d(CAULY_TAG, "free interstitial AD received.");
								}
							}
							else {
								if (TotopangApplication.IS_DEBUG_MODE) {
									Log.d(CAULY_TAG, "normal interstitial AD received.");
								}
							}
							// 노출 활성화 상태이면, 광고 노출
//							if (showInterstitial)
//								ad.show();
//							else
//								ad.cancel();
							ad.show();			
						}
						
						@Override
						public void onLeaveInterstitialAd(CaulyInterstitialAd ad) {
							ad.cancel();
							activity.finish();
						}
						@Override
						public void onFailedToReceiveInterstitialAd(CaulyInterstitialAd ad, int errorCode, String errorMsg) {
							// 전면 광고 수신 실패할 경우 호출됨.
							if (TotopangApplication.IS_DEBUG_MODE) {
								Log.e(CAULY_TAG, "failed to receive interstitial AD.");
								Log.e(CAULY_TAG, "errorMsg : " + errorMsg);
							}
							activity.finish();

						}
						@Override
						public void onClosedInterstitialAd(CaulyInterstitialAd ad) {
							// 전면 광고가 닫힌 경우 호출됨.
							activity.finish();
						}
					});
				}
			});
			mAdInter.setOnAdClickedListener(new OnAdClickedListener() {
				
				@Override
				public void OnAdClicked() {
					activity.finish();
				}
			});
			mAdInter.setOnAdClosedListener(new OnAdClosedListener() {
				
				@Override
				public void OnAdClosed() {
					activity.finish();
				}
			});
			mAdInter.loadAd();
			break;
		case 2:
			// CaulyAdInfo 생성
			// 배너 광고와 동일하게 광고 요청을 설정할 수 있다.
			CaulyAdInfo interstitialAdInfo = new CaulyAdInfoBuilder(activity.getString(R.string.cauly_app_code)).build();
			// 전면 광고 생성
			CaulyInterstitialAd caulyInterstitialAd = new CaulyInterstitialAd();
			caulyInterstitialAd.setAdInfo(interstitialAdInfo);
			// 전면광고 노출 후 back 버튼을 막기를 원할 경우 disableBackKey();을 추가한다
			// 단, requestInterstitialAd 위에서 추가되어야 합니다.
			// interstitialAd.disableBackKey(); 
			
			// 광고 요청. 광고 노출은 CaulyInterstitialAdListener의 onReceiveInterstitialAd에서 처리한다.
			caulyInterstitialAd.requestInterstitialAd(activity);	
			caulyInterstitialAd.setInterstialAdListener(new CaulyInterstitialAdListener() {
				
				@Override
				public void onReceiveInterstitialAd(CaulyInterstitialAd ad, boolean isChargeableAd) {
					// 광고 수신 성공한 경우 호출됨.
					// 수신된 광고가 무료 광고인 경우 isChargeableAd 값이 false 임.
					if (isChargeableAd == false) {
						if (TotopangApplication.IS_DEBUG_MODE) {
							Log.d(CAULY_TAG, "free interstitial AD received.");
						}
					}
					else {
						if (TotopangApplication.IS_DEBUG_MODE) {
							Log.d(CAULY_TAG, "normal interstitial AD received.");
						}
					}
					// 노출 활성화 상태이면, 광고 노출
//					if (showInterstitial)
//						ad.show();
//					else
//						ad.cancel();
					ad.show();			
				}
				
				@Override
				public void onLeaveInterstitialAd(CaulyInterstitialAd ad) {
					ad.cancel();
					activity.finish();
				}
				
				@Override
				public void onFailedToReceiveInterstitialAd(CaulyInterstitialAd ad, int errorCode, String errorMsg) {
					// 전면 광고 수신 실패할 경우 호출됨.
					if (TotopangApplication.IS_DEBUG_MODE) {
						Log.e(CAULY_TAG, "failed to receive interstitial AD.");
						Log.e(CAULY_TAG, "errorMsg : " + errorMsg);
					}
//					activity.finish();
					InterstitialAd mAdInter = new InterstitialAd(activity);
					mAdInter.setInventoryId("258");
					mAdInter.setPublisherId("INFB");
					mAdInter.setOnAdLoadedListener(new OnAdLoadedListener() {
						
						@Override
						public void OnAdLoaded() {
							if (TotopangApplication.IS_DEBUG_MODE) {
								Log.i(TPMN_TAG, "전면 광고가 로딩되었습니다.");	
							}
						}
					});
					mAdInter.setOnAdFailedListener(new OnAdFailedListener() {
						
						@Override
						public void OnAdFailed(AdError error, String errorMessage) {
							if (TotopangApplication.IS_DEBUG_MODE) {
								Log.e(TPMN_TAG, "failed to receive interstitial AD.");
								Log.e(TPMN_TAG, "errorMsg : " + errorMessage);
							}
							activity.finish();
						}
					});
					mAdInter.setOnAdClickedListener(new OnAdClickedListener() {
						
						@Override
						public void OnAdClicked() {
							activity.finish();
						}
					});
					mAdInter.setOnAdClosedListener(new OnAdClosedListener() {
						
						@Override
						public void OnAdClosed() {
							activity.finish();
						}
					});
					mAdInter.loadAd();
				}
				
				@Override
				public void onClosedInterstitialAd(CaulyInterstitialAd ad) {
					// 전면 광고가 닫힌 경우 호출됨.
					activity.finish();
				}
			});
			break;
		}
	}
	
	/**
	 * 전면광고 리소스 해제
	 * @param caulyInterstitialAd
	 * @param adamInterstitialAd
	 */
	public static void clearAdViewInterstResource(CaulyInterstitialAd caulyInterstitialAd) {
		if (caulyInterstitialAd != null) {
			caulyInterstitialAd = null;
		}
	}

	public static void setHomeAsUpBackgroundResource(Context context, View actionBarView) {
		actionBarView.setBackgroundResource(R.drawable.bar_top);
		if (actionBarView != null && actionBarView instanceof ViewGroup) {
			ViewGroup vgAb = (ViewGroup) actionBarView;
			if (vgAb.getChildCount() > 0) {
				View drawerBtn = vgAb.getChildAt(0);
				if (drawerBtn != null && drawerBtn instanceof ImageButton) {
					LayoutParams param = (LayoutParams) drawerBtn.getLayoutParams();
					switch (Utils.getDeviceMaxWidth(context)) {
//					case 480:		// s2
//						ACTIONBAR_HOME_WIDTH = 35;
//						param.width = ACTIONBAR_HOME_WIDTH;
//	                    param.height = 60;
//						break;
//					case 960:		// optimus view2
//						ACTIONBAR_HOME_WIDTH = 80;
//						param.width = ACTIONBAR_HOME_WIDTH;
//	                    param.height = 60;
//						break;
//					case 1080:		// s4, s5, secret note
//						ACTIONBAR_HOME_WIDTH = 100;
//						param.width = ACTIONBAR_HOME_WIDTH;
//	                    param.height = 60;
//						break;
//						
//					case 1440:		// note4, s6
//						ACTIONBAR_HOME_WIDTH = 140;
//						param.width = ACTIONBAR_HOME_WIDTH;
//	                    param.height = 60;
//						break;
//						
//					case 1532:		// note4 edge
//						ACTIONBAR_HOME_WIDTH = 140;
//						param.width = ACTIONBAR_HOME_WIDTH;
//	                    param.height = 60;
//						break;
						
					default:
						ACTIONBAR_HOME_WIDTH = 80;
						param.width = ACTIONBAR_HOME_WIDTH;
	                    param.height = 60;
						break;
					}
                    drawerBtn.setLayoutParams(param);
				}
			}
		}
	}
	
	public static void setActivityAnimation(Activity activity) {
		activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
	}
	
	/**
	 * 회전 애니메이션 set
	 * @param context
	 * @param animation
	 */
	public static Animation getRotateAnimation(Context context, final View view1, final View view2) {
		Animation animation = AnimationUtils.loadAnimation(context, R.animator.rotate);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				view1.setVisibility(View.GONE);
				view2.setEnabled(true);
			}
		});
		return animation;
	}
	
	/**
	 * 확대/축소 애니메이션
	 * @param context
	 * @return
	 */
	public static Animation getScaleAnimaion(Context context) {
		Animation animation = AnimationUtils.loadAnimation(context, R.animator.scale);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
			}
		});
		return animation;
	}
	
	/**
	 * alpha 애니메이션
	 * @param context
	 * @return
	 */
	public static Animation getAlphaAnimaion(Context context) {
		Animation animation = AnimationUtils.loadAnimation(context, R.animator.alpha);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
			}
		});
		return animation;
	}
	
	/**
	 * 로딩 프로그레스바 애니메이션
	 * @param context
	 * @param view1
	 * @param view2
	 * @return
	 */
	public static Animation getRoadingAnimaion(final Context context, final View view1, final View view2) {
		Animation animation = AnimationUtils.loadAnimation(context, R.animator.loading_anim);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// rotate
				// 새로운 마진 설정
				setMargin(view1, (int) (view1.getHeight() - (view1.getHeight() * 0.1)));
				// 정렬
				setGravity(view1, Gravity.CENTER_HORIZONTAL);
				view1.setAnimation(getRoadingRotateAnimation(context, view1, view2));
			}
		});
		return animation;
	}
	
	/**
	 * 회전 애니메이션 set
	 * @param context
	 * @param animation
	 */
	public static Animation getRoadingRotateAnimation(Context context, final View view1, final View view2) {
		Animation animation = AnimationUtils.loadAnimation(context, R.animator.rotate);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				setMargin(view1, 0);
				setGravity(view1, Gravity.CENTER_HORIZONTAL);
				view1.setVisibility(View.GONE);
				view2.setEnabled(true);
			}
		});
		return animation;
	}
	
	/**
	 * 확대/축소 사라짐 애니메이션
	 * @param context
	 * @return
	 */
	public static Animation getScaleOutAnimaion(Context context, final View view) {
		Animation animation = AnimationUtils.loadAnimation(context, R.animator.scale_out);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
//				view.setVisibility(View.GONE);
			}
		});
		return animation;
	}
	
	/**
	 * 추천픽 애니메이션
	 * @param context
	 * @return
	 */
	public static Animation getPickResultAnimaion(final Context context, final View view) {
		Animation animation = AnimationUtils.loadAnimation(context, R.animator.pick_result_anim);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				view.setVisibility(View.GONE);
			}
		});
		return animation;
	}
	
	/**
	 * fulltorefresh 튜토리얼 보기 set
	 * @param context
	 * @param view
	 */
	public static void setRefreshTutoShow(Context context, View view1, View view2, View view3, View view4, View view5, View view6, View view7) {
		TutoInfo.setRefreshTutoShow(context, true);
		if (view1 != null) {
			view1.setVisibility(View.GONE);
			view1 = null;
		}
		if (view2 != null) {
			view2.setVisibility(View.GONE);
			view2 = null;
		}
		if (view3 != null) {
			view3.setVisibility(View.GONE);
			view3 = null;
		}
		if (view4 != null) {
			view4.setVisibility(View.GONE);
			view4 = null;
		}
		if (view5 != null) {
			view5.setVisibility(View.GONE);
			view5 = null;
		}
		if (view6 != null) {
			view6.setVisibility(View.GONE);
			view6 = null;
		}
		if (view7 != null) {
			view7.setVisibility(View.GONE);
			view7 = null;
		}
	}
	/**
	 * webview 리소스 해제
	 * @param webview
	 */
	public static void clearWebView(WebView webview) {
		webview.removeAllViews();
		webview.clearCache(true);
		webview.destroy();
	}
	
	public static void setLeagueName(Context context, TextView leagueNameTv, String leagueId) {
		String leagueName = "";
		switch (leagueId) {
		case "1":
			leagueName = context.getString(R.string.epl);
			break;
		case "11":
			leagueName = context.getString(R.string.premeraliga);
			break;
		case "13":
			leagueName = context.getString(R.string.bundesliga);
			break;
		case "12":
			leagueName = context.getString(R.string.seria_a);
			break;
		case "10":
			leagueName = context.getString(R.string.league1);
			break;
			
		case "9":
			leagueName = context.getString(R.string.eredivisie);
			break;
		case "16":
			leagueName = context.getString(R.string.k_league_clasic);
			break;
		case "15":
			leagueName = context.getString(R.string.j_league);
			break;
		case "14":
			leagueName = context.getString(R.string.premiership);
			break;
		case "73":
			leagueName = context.getString(R.string.segundaliga);
			break;
			
		case "75":
			leagueName = context.getString(R.string.bundesliga2);
			break;
		case "76":
			leagueName = context.getString(R.string.seria_b);
			break;
		case "78":
			leagueName = context.getString(R.string.k_league_chalenge);
			break;
		case "77":
			leagueName = context.getString(R.string.j_league2);
			break;
		case "67":
			leagueName = context.getString(R.string.premeiraliga);
			break;
			
		case "82":
			leagueName = context.getString(R.string.rus_d1);
			break;
		case "72":
			leagueName = context.getString(R.string.tur_d1);
			break;
		case "83":
			leagueName = context.getString(R.string.usa_mls);
			break;
		case "74":
			leagueName = context.getString(R.string.league2);
			break;
		case "96":
			leagueName = context.getString(R.string.ksa_d1);
			break;
		case "87":
			leagueName = context.getString(R.string.ksa_d2);
			break;
		case "86":
			leagueName = context.getString(R.string.cha_sl);
			break;
		}
		leagueNameTv.setText(leagueName);
	}
	
	/**
	 * 로딩 gif 애니메이션 보기
	 * @param stream
	 * @param refreshEfectIv
	 * @param refreshIb
	 */
	public static void setLoadingGifAnimation(InputStream stream, final GifDecoderView refreshEfectIv, final ImageButton refreshIb) {
		refreshEfectIv.playGifOnce(stream);
		Handler mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				refreshEfectIv.setVisibility(View.GONE);
				refreshIb.setEnabled(true);
			}
		}, 3000);
	}
	
	/**
	 * 마진 설정
	 * @param view
	 * @param margin
	 */
	public static void setMargin(View view, int margin) {
		ViewGroup.MarginLayoutParams param = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
		param.setMargins(0, margin, 0, 0);
		view.setLayoutParams(new FrameLayout.LayoutParams(param));
	}
	
	/**
	 * 정렬 설정
	 * @param view
	 * @param gravity
	 */
	public static void setGravity(View view, int gravity) {
		FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
		lp.gravity = Gravity.CENTER_HORIZONTAL;
		view.setLayoutParams(lp);
	}
	
	/**
	 * 스피너 높이 설정
	 * @param spinner
	 * @param height
	 */
	public static void setSpinnerHeight(Context context, Spinner spinner, int height) {
		try {
			Field popup = Spinner.class.getDeclaredField("mPopup");
			popup.setAccessible(true);
			
			ListPopupWindow popupWindow = (ListPopupWindow) popup.get(spinner);
			Log.e(TAG, "Utils.getDeviceMaxHeight(context) : " +Utils.getDeviceMaxHeight(context));
			if (800 >= Utils.getDeviceMaxHeight(context)) {
				height = 400;
			} else if (2000 <= Utils.getDeviceMaxHeight(context)) {
				height = 800;
			}
			popupWindow.setHeight(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * actionbar style set
	 * @param context
	 * @param actionbar
	 */
	public static void setActionbarStyle(Context context, ActionBar actionbar) {
		actionbar.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bar_top));
		actionbar.setStackedBackgroundDrawable(context.getResources().getDrawable(R.drawable.tab_bg));
	}
	
	/**
	 * 이미지 라운딩 처리
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getCircularBitmapWithWhiteBorder(Bitmap bitmap, int strokeWidth, int bitmapWidth, int bitmapHeight) {
		if (bitmap == null || bitmap.isRecycled()) {
	        return null;
	    }
		
	    final int width = bitmapWidth;
	    final int height = bitmapHeight;
	    Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
	    
	    BitmapShader shader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
	    Paint paint = new Paint();
	    paint.setAntiAlias(true);
	    paint.setShader(shader);
	    Canvas canvas = new Canvas(canvasBitmap);
	    float radius = width > height ? ((float) height) / 2f : ((float) width) / 2f;
	    canvas.drawCircle(width / 2, height / 2, radius, paint);
	    paint.setShader(null);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(0XFFE9E9E9);
	    paint.setStrokeWidth(strokeWidth);
	    canvas.drawCircle(width / 2, height / 2, radius + strokeWidth, paint);
	    
	    return canvasBitmap;
	}
	
	/**
	 *
	 * 이미지를 주어진 너비와 높이에 맞게 리사이즈 하는 코드.
	 * 원본 이미지를 크롭 하는게 아니라 리사이즈 하는 것이어서, 
	 * 주어진 너비:높이 의 비율이 원본 bitmap 의 비율과 다르다면 변환 후의 너비:높이의 비율도 주어진 비율과는 다를 수 있다.
	 * 
	 * 가로가 넓거나 세로가 긴 이미지를 정사각형이나 원형의 view 에 맞추려 할 때, 
	 * 이 메쏘드를 호출한 후 반환된 bitmap 을 crop 하면 찌그러지지 않는 이미지를 얻을 수 있다.
	 *
	 * @param Bitmap bitmap 원본 비트맵
	 * @param int width 뷰의 가로 길이
	 * @param int height 뷰의 세로 길이
	 *
	 * @return Bitmap 리사이즈 된 bitmap
	 */
	public static Bitmap resizeBitmap(Bitmap bitmap, int width, int height) {
	    if (bitmap.getWidth() != width || bitmap.getHeight() != height){
	        float ratio = 1.0f;
	 
	        if (width > height) {
	            ratio = (float)width / (float)bitmap.getWidth();
	        } else {
	            ratio = (float)height / (float)bitmap.getHeight();
	        }
	         
	        bitmap = Bitmap.createScaledBitmap(bitmap, 
	                (int)(((float)bitmap.getWidth()) * ratio), // Width 
	                (int)(((float)bitmap.getHeight()) * ratio), // Height
	                false);
	    }
	     
	    return bitmap;
	}
	
	public static int measureWidth(int measureSpec)
	{
	        int result = 0;
	        int specMode = MeasureSpec.getMode(measureSpec);
	        int specSize = MeasureSpec.getSize(measureSpec);

	        if (specMode == MeasureSpec.EXACTLY) {
	            // We were told how big to be
	            result = specSize;
	        }

		return result;
	}
	
	public static int measureHeight(int measureSpecHeight, int measureSpecWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        }
        return result;
    }
}
