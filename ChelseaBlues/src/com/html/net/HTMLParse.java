package com.html.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;

import com.html.app.Match_HTML_Act;
import com.html.app.News_HTML_Act;

public class HTMLParse {
	private static final String TAG = HTMLParse.class.getSimpleName();
	private static final String origURL = "http://korea.chelseafc.com";
	private String url;
	private Context context;
	private Handler handler;
	private ProgressDialog progressDialog;
	private Source source;
	public ArrayList<HashMap<String, Object>> data;
	private ArrayList<HashMap<String, String>> saveData;
	public String newsLink = null;
	public String matchLink = null;
	private HashMap<Integer, String> hmLink;
	private HashMap<Integer, String> mHashMapMatchLink;
	private int mNewsIndex;
	private int mMatchIndex;
	private Drawable da;
	
	public HTMLParse(Context context, Handler handler,
			ArrayList<HashMap<String, Object>> data) {
		this.context = context;
		this.handler = handler;
		this.data = data;
		saveData = new ArrayList<HashMap<String, String>>();
		Log.d(TAG, "HTMLParse");
	}

	public void open(String type) {
		// 첼시 경기일정
		if (type.equals("news")) {
			Log.d(TAG, "open() news");
			url = "http://korea.chelseafc.com/news";
		} else if (type.equals("match")){
			Log.d(TAG, "match");
			url = "http://korea.chelseafc.com/facts";
		} else if (type.equals("matchDetail")){
			Log.d(TAG, "matchDetail");
			url = mHashMapMatchLink.get(mMatchIndex);
		} else if (type.equals("newsDetail")){
			Log.d(TAG, "newsDetail");
			url = hmLink.get(mNewsIndex);
		}
		Log.d(TAG, url);
		// 처리하기
		try {
			process(type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void process(final String type) throws IOException {
		// 상태 Progress 띄우기 위해서 사용함!
		final Handler mHandler = new Handler();
		new Thread() {

			public void run() {
				URL nURL;
				try {
					nURL = new URL(url);
					mHandler.post(new Runnable() {

						public void run() {
							progressDialog = ProgressDialog.show(context, "",
									"Data loading...");
							System.out.println("data loading");
						}
					});

					// 모든 데이터 초기화
					//data.clear();

					InputStream html = nURL.openStream();
					
					// 가져오는 HTML의 인코딩형식
					source = new Source(new InputStreamReader(html, "utf-8"));

					// 테이블가져오기
					Element table = null;
					int tr_count = 0;
					Element tr = null;
					
					List<Element> ulTag = null;
					List<Element> tableList = null;
					List<Element> tdList = null;
					List<Element> newsTitle = null;
					List<Element> newsSummary = null;
					List<Element> link = null;
					//String newsLink = null;
					List<Element> thumbnail = null;
					String newsThumbnail = null;
					HashMap<String, Object> hm = null;
					int td_count = 0;
					Element td = null;

					if (type.equals("news")) {
						for(int i = 0; i < source.getAllElements(HTMLElementName.TABLE).size(); i++) {
							table = (Element) source.getAllElements(HTMLElementName.TABLE).get(i);
							tr_count = table.getAllElements(HTMLElementName.TR).size();
							for (int j = 0; j < tr_count; j++) {
								tr = (Element) table.getAllElements(HTMLElementName.TR).get(j);
								td_count = tr.getAllElements(HTMLElementName.TD).size();
								
								if(td_count == 2) {
									hm = new HashMap<String, Object> ();
									for(int k = 0; k < td_count; k++) {
										td = (Element) tr.getAllElements(HTMLElementName.TD).get(k);
										String Url;
										
										if(k == 0) {
											Url = origURL + td.getAllElements(HTMLElementName.A).get(0).getAllElements(HTMLElementName.IMG).get(0).getAttributeValue("src");
											da = LoadImageFromWebOperations(Url);
											hm.put("newsThumb", da);
										} else {
											hm.put("title", td.getAllElements(HTMLElementName.A).get(0).getContent().toString().trim().replaceAll("\t", ""));
											hm.put("news", td.getAllElements(HTMLElementName.DIV).get(0).getContent().toString().trim().replaceAll("\t", ""));
										}
									}
									data.add(hm);
								}
							}
						}
						
						hmLink = new HashMap<Integer, String>();
						
						ulTag = source.getAllElements(HTMLElementName.UL);
						for(int i = 0; i < ulTag.size(); i++){
							if("news-list".equals(ulTag.get(i).getAttributeValue("id"))){
								tableList = ulTag.get(i).getAllElements(HTMLElementName.TABLE);
							}
						}
						
						if(tableList == null){
							Log.d(TAG, "Error : Cannot find UL TAG!");
						}
						
						for(int i = 0; i < tableList.size(); i++){
							tdList = tableList.get(i).getAllElements(HTMLElementName.TD);
							
							for(int j = 0; j < tdList.size(); j++){
								link = tdList.get(0).getAllElements(HTMLElementName.A);
								thumbnail = tdList.get(0).getAllElements(HTMLElementName.IMG);
								newsTitle = tdList.get(1).getAllElements(HTMLElementName.A);
								newsSummary = tdList.get(1).getAllElements(HTMLElementName.DIV);
								newsLink = origURL + link.get(0).getAttributeValue("href");
								newsThumbnail = origURL + thumbnail.get(0).getAttributeValue("src");
								System.out.println("news(" + j + ") newsLink : " + newsLink + "\nnewsThumbnail: " + newsThumbnail);
							}
							hmLink.put(i, newsLink);
							Log.d(TAG, "image loading");
						}
					} else if (type.equals("match")){	// matches
						mHashMapMatchLink = new HashMap<Integer, String>();
						int index = 0;

						for(int i = 0; i < source.getAllElements(HTMLElementName.TABLE).size(); i++) {
	
							table = (Element) source.getAllElements(HTMLElementName.TABLE).get(i);
							tr_count = table.getAllElements(HTMLElementName.TR).size();
	
							for (int j = 1; j < tr_count; j++) {
								tr = (Element) table.getAllElements(HTMLElementName.TR).get(j);
								td_count = tr.getAllElements(HTMLElementName.TD).size();
	
								hm = new HashMap<String, Object> ();
								
								if(td_count == 7 ) {
									for(int k = 0; k < td_count - 1; k++) {
										if("무승부".equals(((Element) tr.getAllElements(HTMLElementName.TD).get(k)).getContent().toString().trim())) {
											hm.put( "test" + (k+1) , "무");
										} else {
											hm.put( "test" + (k+1) , ((Element) tr.getAllElements(HTMLElementName.TD).get(k)).getContent().toString().trim());
										}
									}
									data.add(hm);

									td = (Element) tr.getAllElements(HTMLElementName.TD).get(6);

									if(!td.getAllElements(HTMLElementName.A).isEmpty()) {
										link = td.getAllElements(HTMLElementName.A);
										matchLink = origURL + link.get(0).getAttributeValue("href");
									} else {
										matchLink = origURL;
									}
									mHashMapMatchLink.put(index, matchLink);
									index++;
								}
							}
						}
					} else if (type.equals("newsDetail")){
						List<Element> divTag2 = null, divTag3 = null;
						String title = null, date = null, img = null, line = null, contents = "";
						Element eImage = null;
						Element eContent = null;
						
						HashMap<String, String> hmDetail = new HashMap<String, String>();
						eImage = source.getElementById("highlight_image");
						eContent = source.getElementById("mainContent");
						
						if(eImage != null && eImage.length() != 0) {
							title = eImage.getAllElements(HTMLElementName.IMG).get(0).getAttributeValue("alt");
							img = origURL + eImage.getAllElements(HTMLElementName.IMG).get(0).getAttributeValue("src");
							Log.d(TAG, title);
							Log.d(TAG, img);
						}
						
						if(eContent != null && eContent.length() != 0) {
							divTag2 = eContent.getAllElements(HTMLElementName.DIV);
							divTag3 = eContent.getAllElements(HTMLElementName.P);
							
							for(int j = 0; j < divTag2.size(); j++) {
								if("date".equals(divTag2.get(j).getAttributeValue("class"))) {
									date = divTag2.get(j).getContent().toString();
									Log.d(TAG, date);
								}
							}
							for(int j = 0; j < divTag3.size(); j++) {
								line = "<P>" + divTag3.get(j).getContent().toString().trim() + "</P>";
								
								if(line.contains("<img")) {
									Log.d(TAG, "before : " + line);
									line = line.replace("&nbsp;", "");
									
									if(line.contains("<br />")) {
										line = line.replace("<img", "</P><P><img");
										line = line.replace("<br />", "");
									}
								}
								Log.d(TAG, line);
								contents = contents + line;
							}
						}
						
						hmDetail.put("title", title);
						hmDetail.put("img", img);
						hmDetail.put("date", date);
						hmDetail.put("contents", contents);
						hmDetail.put("index", mNewsIndex+"");
						saveData.add(hmDetail);
						
						((News_HTML_Act)context).startNewsDetailActivity(hmDetail);
					} else if (type.equals("matchDetail")){
						HashMap<String, String> hmDetail = new HashMap<String, String>();

						String summary = "";

						if(origURL.equals(url)) {
							summary = "경기 시작 전";
						} else {
							List<Element> pTag = null;
							pTag = source.getAllElements(HTMLElementName.P);

							for(int i=0; i<pTag.size(); i++) {
								if(pTag.get(i).getAllElements(HTMLElementName.A).isEmpty()) {
									summary = summary + pTag.get(i).getContent().toString();
								}
							}

							summary = summary.replaceAll("<img", "<p><img").replaceAll(" />", " /></p>")
									.replaceAll("&nbsp;", "").replaceAll("<br />", "")
									.replaceAll("<strong","<p><strong").replaceAll("</strong>", "</strong></p>").trim().replaceAll("\t", "");
						}

						hmDetail.put("summary", summary);
						hmDetail.put("index", mMatchIndex+"");
						saveData.add(hmDetail);

						((Match_HTML_Act)context).startMatchDetailActivity(hmDetail);
					}
					hm = null;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
				} finally {
					mHandler.post(new Runnable() {
						public void run() {
							if(progressDialog != null) {
								progressDialog.cancel();
								// 업데이트 완료를 핸들러로 보내줌
								handler.sendEmptyMessage(0);
							}
						}
					});
				}

			}

		}.start();
	}

	public void setmNewsIndex(int mNewsIndex) {
		this.mNewsIndex = mNewsIndex;
	}

	public void setMatchIndex(int matchIndex) {
		this.mMatchIndex = matchIndex;
	}

	public ArrayList<HashMap<String, String>> getSaveData() {
		return saveData;
	}

	private Drawable LoadImageFromWebOperations(String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			return d;
		}catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "Exception e");
			return null;
		}
	}
	
}
