package com.chelsea.app.chelseablues.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import android.content.Context;
import android.database.CursorJoiner.Result;
import android.util.Log;

import com.chelsea.app.chelseablues.application.ChelseaApplication;
import com.chelsea.app.chelseablues.model.Record;

public class HTMLUtil {
	private static final String TAG = HTMLUtil.class.getSimpleName();
	public static ArrayList<HashMap<String, Object>> data;
	public static ArrayList<Record> HttpRequest(Context context,
			String stringUrl, String type) {
		
		ArrayList<Record> result = new ArrayList<Record>();
		Record record = null;
				
		Source source = null;
		// get tag list
		List<Element> divTag = null;
		List<Element> ulTag = null;
		List<Element> liTag = null;
		List<Element> aTag = null;
		List<Element> imgTag = null;
		List<Element> tableTag = null;
		List<Element> trTag = null;
		List<Element> tbodyTag = null;
		List<Element> tdTag = null;
		List<Element> strongTag = null;
		List<Element> h3Tag = null;
		List<Element> spanTag = null;
		List<Element> pTag = null;
		
		// get tag
		Element table = null;
		Element div = null;
		Element tr = null;
		Element ul = null;
		Element li = null;
		Element img = null;
		Element a = null;
		Element td = null;
		Element tbody = null;
		Element span = null;
		Element strong = null;
		Element p = null;

		String newsLink = "";
		String newsThumbUrl = "";
		String newsTitle = "";
		String newsSummary = "";
		String newsDate = "";
		String newsImage = "";
		String newsContents = "";

		try {
			URL url = new URL(stringUrl);
			InputStream html = url.openStream();
			// incoding
			source = new Source(new InputStreamReader(html, "utf-8"));
			if (type.equals("mainPage")) {	// get main page data
				/*
				 * get next match data
				 */
				String logoImgUrl = "";
				String oppositTeam = "";
				String matchDate = "";
				String matchStadium = "";
				divTag = source.getAllElements(HTMLElementName.DIV);
				for (int i = 0; i < divTag.size(); i++) {
					div = divTag.get(i);
					String divAtt = div.getAttributeValue("class");
					if (divAtt != null && divAtt.equals("info-box")) {
						divTag = div.getAllElements(HTMLElementName.DIV);
						for (int j = 0; j < divTag.size(); j++) {
							div = divTag.get(j);
							divAtt = div.getAttributeValue("class");
							if (divAtt != null && divAtt.equals("logo")) {
								img = div.getAllElements(HTMLElementName.IMG).get(0);
								logoImgUrl = ChelseaApplication.MAIN_URL + img.getAttributeValue("src").toString().trim().replaceAll("\t", "");
								PrefUtil.set(context, "logoImgUrl", logoImgUrl);
							} else if (divAtt != null && divAtt.equals("info")) {
								oppositTeam = div.getAllElements(HTMLElementName.DIV).get(0).getAllElements(HTMLElementName.A).get(0).getContent().toString().trim().replaceAll("\t", "");
								matchDate = div.getAllElements(HTMLElementName.DIV).get(2).getContent().toString().trim().replaceAll("\t", "");
								matchStadium = div.getAllElements(HTMLElementName.DIV).get(3).getContent().toString().trim().replaceAll("\t", "");
								PrefUtil.set(context, "oppositTeam", oppositTeam);
								PrefUtil.set(context, "matchDate", matchDate);
								PrefUtil.set(context, "matchStadium", matchStadium);
							}
						}
						
					}
				}
				
				/*
				 * get news data
				 */
				ulTag = source.getAllElements(HTMLElementName.UL);
				for (int i = 0; i < ulTag.size(); i++) {
					ul = ulTag.get(i);
					String ulAtt = ul.getAttributeValue("class");
					if (ulAtt != null) {
						if (ulAtt.equals("newsContent")){
							// get liTag
							liTag = ul.getAllElements(HTMLElementName.LI);
							for (int j = 0; j < liTag.size(); j++) {
								// create record
								record = new Record();
								li = liTag.get(j);
								// get aTag
								aTag = li.getAllElements(HTMLElementName.A);
								a = aTag.get(0);
								// get imgTag
								imgTag = li.getAllElements(HTMLElementName.IMG); 	
								img = imgTag.get(0);
								
								// get news link
								newsLink = a.getAttributeValue("href").trim().replaceAll("\t", "");
								// get thumbnail image url
								newsThumbUrl = ChelseaApplication.MAIN_URL + img.getAttributeValue("src").trim().replaceAll("\t", "");
								// get news title
								newsTitle = aTag.get(1).getAllElements(HTMLElementName.DIV).get(0).getContent().toString().trim().replaceAll("\t", "");
								
								// get news summary
								newsSummary = aTag.get(2).getAllElements(HTMLElementName.DIV).get(0).getContent().toString().trim().replaceAll("\t", "");
								Log.d(TAG, "newsSummary = " + newsSummary.toString());
								// put data
								record.put("newsLink", newsLink);
								record.put("newsThumbUrl", newsThumbUrl);
								record.put("newsTitle", newsTitle);
								record.put("newsSummary", newsSummary);
								result.add(record);
							}
						}
					}
				}
			} else if (type.equals("latestTactics")) {
				// get lineup
				pTag = source.getAllElements(HTMLElementName.P);
				for (int i = 0; i < pTag.size(); i++) {
					p = pTag.get(i);
					strongTag = p.getAllElements(HTMLElementName.STRONG);
					
					for (int j = 0; j < strongTag.size(); j++) {
						strong = strongTag.get(j);
						String strongContent = strong.getContent().toString().trim().replaceAll("\t", "");
						if (strongContent.equals("첼시 (4-2-3-1):") || strongContent.contains("전반 스쿼드")) {
							String pContent = p.getContent().toString().trim().replaceAll("\t", "");
							String [] pContentArr = pContent.split("</strong>");
							
							String fomation = pContentArr[0].replace("<strong>", "").replace(":", "");
							PrefUtil.set(context, "fomation", fomation);
							String lineup = pContentArr[1].replace(";", ",").replace(".<br />", "").replace("<strong>", "").replace("벤치잔류", "");
							PrefUtil.set(context, "lineup", lineup);
						}
					}
				}
			} else if (type.equals("teamInfo")) {
				String playerLink = "";
				String playerImage = "";
				String playerName = "";
				
				// get ulTag
				ulTag = source.getAllElements(HTMLElementName.UL);
				
				for (int i = 0; i < ulTag.size(); i++) {
					ul = ulTag.get(i);
					
					String ulAtt = ul.getAttributeValue("class");
					if (ulAtt != null) {
						if (ulAtt.equals("teamPlayerList")) {
							// get liTag
							liTag = ul.getAllElements(HTMLElementName.LI);
							
							for (int j = 0; j < liTag.size(); j++) {
								record = new Record();
								
								li = liTag.get(j);
								//get playerLink
								playerLink = ChelseaApplication.MAIN_URL + li.getAllElements(HTMLElementName.A).get(0).getAttributeValue("href").trim().replaceAll("\t", "");;
								//get playerImage
								playerImage = ChelseaApplication.MAIN_URL + li.getAllElements(HTMLElementName.IMG).get(0).getAttributeValue("src").trim().replaceAll("\t", "");;
								//get playerName
								playerName = li.getAllElements(HTMLElementName.DIV).get(0).getContent().toString().trim().replaceAll("\t", "");;
								
								// put data
								record.put("playerLink", playerLink);
								record.put("playerImage", playerImage);
								record.put("playerName", playerName);
								result.add(record);
							}
						}
					} 
				}
			} else if (type.equals("playerInfo")) {
				// get divTag
				divTag = source.getAllElements(HTMLElementName.DIV);
				for (int i = 0; i < divTag.size(); i++) {
					div = divTag.get(i);
					String divAtt = div.getAttributeValue("id");
					if (divAtt != null) {
						if (divAtt.equals("profile-bio")) {
							// get tableTag
							tableTag = div.getAllElements(HTMLElementName.TABLE);
							for (int j = 0; j < tableTag.size(); j++) {
								record = new Record();
								// get trTag
								table = tableTag.get(j);
								trTag = table.getAllElements(HTMLElementName.TR);
									// get nation
									String nation = trTag.get(0).getAllElements(HTMLElementName.TD).get(0).getContent().toString().trim().replaceAll("\t", "");;
									// get birth date
									String birthDate = trTag.get(1).getAllElements(HTMLElementName.TD).get(0).getContent().toString().trim().replaceAll("\t", "");;
									// get height
									String height = trTag.get(2).getAllElements(HTMLElementName.TD).get(0).getContent().toString().trim().replaceAll("\t", "");;
									// get weight
									String weight = trTag.get(3).getAllElements(HTMLElementName.TD).get(0).getContent().toString().trim().replaceAll("\t", "");;
									// get backnumber
									String backNumber = trTag.get(4).getAllElements(HTMLElementName.TD).get(0).getContent().toString().trim().replaceAll("\t", "");;
									// get contact date
									String contactDate = trTag.get(5).getAllElements(HTMLElementName.TD).get(0).getContent().toString().trim().replaceAll("\t", "");;
									// get before club
									String beforeClub = trTag.get(6).getAllElements(HTMLElementName.TD).get(0).getContent().toString().trim().replaceAll("\t", "");;
									// get position
									String position = trTag.get(7).getAllElements(HTMLElementName.TD).get(0).getContent().toString().trim().replaceAll("\t", "");;
									Log.d(TAG, "position = " + position);
									
									// put data
									record.put("nation", nation);
									record.put("birthDate", birthDate);
									record.put("height", height);
									record.put("weight", weight);
									record.put("backNumber", backNumber);
									record.put("contactDate", contactDate);
									record.put("beforeClub", beforeClub);
									record.put("position", position);
									
									result.add(record);
							}
						}
					}
				}
			} else if (type.equals("leaguetable")) {
				// get divTag
				divTag = source.getAllElements(HTMLElementName.DIV);
				for (int i = 0; i < divTag.size(); i++) {
					div = divTag.get(i);
					String divAtt = div.getAttributeValue("class");
					if (divAtt != null) {
						if (divAtt.equals("box-long")) {
							// get tableTag
							table = div.getAllElements(HTMLElementName.TABLE).get(0);
							// get tbodyTag
							tbody = table.getAllElements(HTMLElementName.TBODY).get(0);
							// get trTag
							trTag = tbody.getAllElements(HTMLElementName.TR);
							for (int j = 0; j < trTag.size(); j++) {
								record = new Record();
								
								tr = trTag.get(j);
								// 순위
								String rank = tr.getAllElements(HTMLElementName.TD).get(0).getContent().toString().trim().replaceAll("\t", "");
								// 팀 이름
								String team = tr.getAllElements(HTMLElementName.TD).get(1).getContent().toString().trim().replaceAll("\t", "");;
								// 경기수
								String played = tr.getAllElements(HTMLElementName.TD).get(2).getContent().toString().trim().replaceAll("\t", "");
								// 홈 승
								String hWin = tr.getAllElements(HTMLElementName.TD).get(3).getContent().toString().trim().replaceAll("\t", "");
								// 홈 무
								String hDraw = tr.getAllElements(HTMLElementName.TD).get(4).getContent().toString().trim().replaceAll("\t", "");
								// 홈 패
								String hLose = tr.getAllElements(HTMLElementName.TD).get(5).getContent().toString().trim().replaceAll("\t", "");
								// 홈 득점
								String hScore = tr.getAllElements(HTMLElementName.TD).get(6).getContent().toString().trim().replaceAll("\t", "");
								// 홈 실점
								String hLosePoint = tr.getAllElements(HTMLElementName.TD).get(7).getContent().toString().trim().replaceAll("\t", "");
								
								// 어웨이 승
								String aWin = tr.getAllElements(HTMLElementName.TD).get(8).getContent().toString().trim().replaceAll("\t", "");
								// 어웨이 무
								String aDraw = tr.getAllElements(HTMLElementName.TD).get(9).getContent().toString().trim().replaceAll("\t", "");
								// 어웨이 패
								String aLose = tr.getAllElements(HTMLElementName.TD).get(10).getContent().toString().trim().replaceAll("\t", "");
								// 어웨이 득점
								String aScore = tr.getAllElements(HTMLElementName.TD).get(11).getContent().toString().trim().replaceAll("\t", "");
								// 어웨이 실점
								String aLosePoint = tr.getAllElements(HTMLElementName.TD).get(12).getContent().toString().trim().replaceAll("\t", "");
								
								// 득실차
								String goalDifference = tr.getAllElements(HTMLElementName.TD).get(13).getContent().toString().trim().replaceAll("\t", "");
								// 승점
								String point = tr.getAllElements(HTMLElementName.TD).get(14).getContent().toString().trim().replaceAll("\t", "");
								
								record.put("rank", rank);
								record.put("team", team);
								record.put("played", played);
								record.put("hWin", hWin);
								record.put("hDraw", hDraw);
								record.put("hLose", hLose);
								record.put("hScore", hScore);
								record.put("hLosePoint", hLosePoint);
								record.put("aWin", aWin);
								record.put("aDraw", aDraw);
								record.put("aLose", aLose);
								record.put("aScore", aScore);
								record.put("aLosePoint", aLosePoint);
								record.put("goalDifference", goalDifference);
								record.put("point", point);
							
								result.add(record);
							}
						}
					}
				}
			} else if (type.equals("matchInfo")) {
				String matchDate = "";
				String kickOff = "";
				String teamNameHome = "";
				String scoreHome = "";
				String scoreAway = "";
				String teamNameAway = "";
				String competition = "";
				String matchReport = "";
				
				tableTag = source.getAllElements(HTMLElementName.TABLE);
				String tableAtt = "";
				for (int i = 0; i < tableTag.size(); i++) {
					table = tableTag.get(i);
					tbodyTag = table.getAllElements(HTMLElementName.TBODY);
					for (int j = 0; j < tbodyTag.size(); j++) {
						tbody = tbodyTag.get(j);
						trTag = tbody.getAllElements(HTMLElementName.TR);
						for (int k = 0; k < trTag.size(); k++) {
							record = new Record();
							tr = trTag.get(k);
							tdTag = tr.getAllElements(HTMLElementName.TD);
							Log.d(TAG, "tdTag" + tdTag.toString());
							// get match data
							matchDate = tdTag.get(0).getContent().toString().trim().replaceAll("\t", "");
							kickOff = tdTag.get(1).getContent().toString().trim().replaceAll("\t", "");
							teamNameHome = tdTag.get(2).getContent().toString().trim().replaceAll("\t", "");
							scoreHome = tdTag.get(3).getContent().toString().trim().replaceAll("\t", "");
							scoreAway = tdTag.get(4).getContent().toString().trim().replaceAll("\t", "");
							teamNameAway = tdTag.get(5).getContent().toString().trim().replaceAll("\t", "");
							competition = tdTag.get(6).getContent().toString().trim().replaceAll("\t", "");
							matchReport = tdTag.get(7).getContent().toString().trim().replaceAll("\t", "");
							
							record.put("matchDate", matchDate);
							record.put("kickOff", kickOff);
							record.put("teamNameHome", teamNameHome);
							record.put("scoreHome", scoreHome);
							record.put("scoreAway", scoreAway);
							record.put("teamNameAway", teamNameAway);
							record.put("competition", competition);
							record.put("matchReport", matchReport);

							result.add(record);
						}
					}
				}
			} else if (type.equals("matchDetail")) {
				// get lineup
				pTag = source.getAllElements(HTMLElementName.P);
				String opponent = PrefUtil.get(context, "opponent", "");
				for (int i = 0; i < pTag.size(); i++) {
					p = pTag.get(i);
					strongTag = p.getAllElements(HTMLElementName.STRONG);
					for (int j = 0; j < strongTag.size(); j++) {
						strong = strongTag.get(j);
						String strongContent = strong.getContent().toString().trim().replaceAll("\t", "");
						// get lineup
						if (strongContent.contains("첼시 (")) {
							String pContent = p.getContent().toString().trim().replaceAll("\t", "");
							Log.d(TAG, "pContent = " + pContent.toString());
							String [] pContentArr = pContent.split("</strong>");
							
							String fomation = pContentArr[0].replace("<strong>", "").replace(":", "");
							PrefUtil.set(context, "fomation", fomation);
							String lineup = pContentArr[1].replace(";", ",").replace(".<br />", "").replace("<strong>", "").replace("벤치잔류", "");
							PrefUtil.set(context, "lineup", lineup);
							Log.d(TAG, "lineup = " + lineup.toString());
						} else if (strongContent.contains(opponent + " (")){
							String pContent = p.getContent().toString().trim().replaceAll("\t", "");
							String [] pContentArr = pContent.split("</strong>");
							
							String fomation = pContentArr[0].replace("<strong>", "").replace(":", "");
							PrefUtil.set(context, "fomation", fomation);
							String lineup = pContentArr[1].replace(";", ",").replace(".<br />", "").replace("<strong>", "").replace("벤치잔류", "");
							PrefUtil.set(context, "lineup", lineup);
							Log.d(TAG, "opponentlineup = " + lineup.toString());
						}
					}
				}
				// get backnumber
				divTag = source.getAllElements(HTMLElementName.DIV);
				for (int i = 0; i < divTag.size(); i++) {
					div = divTag.get(i);
					String divAtt = div.getAttributeValue("class");
					if (divAtt != null) {
						if (divAtt.equals("box-short-left line-up")) {
							h3Tag = div.getAllElements(HTMLElementName.H3);
							String teamLineup = h3Tag.get(0).getContent().toString().trim().replaceAll("\t", "");
							if (teamLineup.equals("첼시 FC 선수 명단")){
								divTag = div.getAllElements(HTMLElementName.DIV).get(1).getAllElements(HTMLElementName.DIV);
								for (int j = 1; j < divTag.size(); j++) {
									// create record
									record = new Record();
									
									spanTag = divTag.get(j).getAllElements(HTMLElementName.SPAN);
									String backNumber = spanTag.get(0).getContent().toString().trim().replaceAll("\t", "");
									Log.d(TAG, "backNumber = " + backNumber.toString());
									
									record.put("backNumber", backNumber);
									result.add(record);
								}
							} else if (teamLineup.contains(opponent)) {
								Log.d(TAG, "opponent" + opponent);
								divTag = div.getAllElements(HTMLElementName.DIV).get(1).getAllElements(HTMLElementName.DIV);
								for (int j = 1; j < 11; j++) {
									spanTag = divTag.get(j).getAllElements(HTMLElementName.SPAN);
									String opponentBackNumber = spanTag.get(0).getContent().toString().trim().replaceAll("\t", "");
									Log.d(TAG, "opponentBackNumber = " + opponentBackNumber.toString());
								}
							}
						}
					}
				}
				
				
			} else if (type.equals("matchSummary")) {
				//get match summary
				pTag = source.getAllElements(HTMLElementName.P);
				for (int i = 0; i < pTag.size(); i++) {
					p = pTag.get(i);
					strongTag = p.getAllElements(HTMLElementName.STRONG);
					for (int j = 0; j < strongTag.size(); j++) {
						strong = strongTag.get(j);
						String strongContent = strong.getContent().toString().trim().replaceAll("\t", "");
						if (strongContent.equals("경기요약")) {
							Log.d(TAG, "matchSummarystrongContent = " + p.getContent().toString());
						}
					}
				}
				
			} else if (type.equals("matchGallery")) {
				// get match image
				divTag  = source.getAllElements(HTMLElementName.DIV);
				for (int i = 0; i < divTag.size(); i++) {
					div = divTag.get(i);
					String divAtt = div.getAttributeValue("id");
					if (divAtt != null) {
						if (divAtt.equals("gallery")) {
							div = div.getAllElements(HTMLElementName.DIV).get(0);
							divTag = div.getAllElements(HTMLElementName.DIV);
							for (int j = 0; j < divTag.size(); j++) {
								div = divTag.get(j);
								divAtt = div.getAttributeValue("class");
								if (divAtt != null) {
									if (divAtt.equals("gallery-controller")) {
										divTag = div.getAllElements(HTMLElementName.DIV);
										for (int k = 0; k < divTag.size(); k++) {
											div = divTag.get(k);
											divAtt = div.getAttributeValue("class");
											if (divAtt != null) {
												if (divAtt.equals("gallery-list-container")) {
													ul = div.getAllElements(HTMLElementName.UL).get(0);
													liTag = ul.getAllElements(HTMLElementName.LI);
													for (int l = 0; l < liTag.size(); l++) {
														record = new Record();
														li = liTag.get(l);
														a = li.getFirstElement(HTMLElementName.A);
														String galleryImageUrl = ChelseaApplication.MAIN_URL + a.getAttributeValue("href").toString().trim().replaceAll("\t", "");
														record.put("galleryImageUrl", galleryImageUrl);
														result.add(record);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			} else if (type.equals("news")) {
				/*
				 * get news data
				 */
				ulTag = source.getAllElements(HTMLElementName.UL);
				for (int i = 0; i < ulTag.size(); i++) {
					ul = ulTag.get(i);
					String ulAtt = ul.getAttributeValue("id");
					if (ulAtt != null) {
						if (ulAtt.equals("news-list")){
							// get liTag
							liTag = ul.getAllElements(HTMLElementName.LI);
							for (int j = 0; j < liTag.size(); j++) {
								record = new Record();
								
								li = liTag.get(j);
								table = li.getAllElements(HTMLElementName.TABLE).get(0);
								tr = table.getAllElements(HTMLElementName.TR).get(0);
								td = tr.getAllElements(HTMLElementName.TD).get(1);
								a = td.getAllElements(HTMLElementName.A).get(0);
								// get thumbnail image url
								newsThumbUrl = ChelseaApplication.MAIN_URL + tr.getAllElements(HTMLElementName.TD).get(0).getAllElements(HTMLElementName.A).get(0).getAllElements(HTMLElementName.IMG).get(0).getAttributeValue("src").trim().replaceAll("\t", "");
								
								// get news date
								newsDate = td.getContent().toString().trim().replaceAll("\t", "").replace("<br />", "");;
								
								// get news link
								newsLink = ChelseaApplication.MAIN_URL + a.getAttributeValue("href").trim().replaceAll("\t", "");
								
								// get news title
								newsTitle = a.getContent().toString().trim().replaceAll("\t", "");
								
								// get news summary
								newsSummary = td.getAllElements(HTMLElementName.DIV).get(0).getContent().toString().trim().replaceAll("\t", "").replace("&nbsp", "");
								Log.d(TAG, "newsSummary : " + newsSummary);
								
								// put data
								record.put("newsLink", newsLink);
								record.put("newsThumbUrl", newsThumbUrl);
								record.put("newsTitle", newsTitle);
								record.put("newsSummary", newsSummary);
								result.add(record);
							}
						}
					}
				}
			} else if (type.equals("getNewsDetailInfo")) {
				record = new Record();
				String line = "";
				
				div = source.getElementById("highlight_image");
				img = div.getAllElements(HTMLElementName.IMG).get(0);
				newsTitle = img.getAttributeValue("alt");
				newsImage = ChelseaApplication.MAIN_URL + img.getAttributeValue("src");
				
				// get main content
				divTag = source.getElementById("mainContent").getAllElements(HTMLElementName.DIV);
				for(int i = 0; i < divTag.size(); i++) {
					div = divTag.get(i);
					if ("date".equals(div.getAttributeValue("class"))) {
						newsDate = div.getContent().toString();
						record.put("newsDate", newsDate);
					}
				}
				
				pTag = source.getElementById("mainContent").getAllElements(HTMLElementName.P);
				for(int i = 0; i < pTag.size(); i++) {
					if (pTag != null && pTag.size() != 0) {
						p = pTag.get(i);
						if (p != null && p.length() != 0) {
//							line = p.getContent().toString().trim().replaceAll("\t", "").replace("&lsquo;", "'").replace("&rsquo;", "'").replace("&nbsp;", "");
							line = "<P>" + p.getContent().toString().trim() + "</P>";
							if(line.contains("<img")) {
								Log.d(TAG, "before : " + line);
								line = line.replace("&nbsp;", "");
								
								if(line.contains("<br />")) {
									line = line.replace("<img", "</P><P><img");
									line = line.replace("<br />", "");
									Log.d(TAG, "after : " + line);
								}
								continue;
							}
							newsContents = newsContents + line;
						}
					}
				}
				record.put("newsTitle", newsTitle);
				record.put("newsImage", newsImage);
				record.put("newsDate", newsDate);
				record.put("newsContents", newsContents);
				
				result.add(record);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
