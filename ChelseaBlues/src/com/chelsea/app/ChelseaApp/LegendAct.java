package com.chelsea.app.ChelseaApp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LegendAct extends Activity {

	ArrayList<MyItem> arItem;
	WebView wv = null;
	AlertDialog.Builder alert;
	String url = null;
	int version;
	private static final String TAG = LegendAct.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.legendlistview);

		version = android.os.Build.VERSION.SDK_INT;
		arItem = new ArrayList<MyItem>();
		MyItem mi;
		
		mi = new MyItem(R.drawable.drogba, "", R.drawable.camcorder);arItem.add(mi);
		mi = new MyItem(R.drawable.zola, "", R.drawable.camcorder);arItem.add(mi);
		mi = new MyItem(R.drawable.osgood, "", R.drawable.camcorder);arItem.add(mi);
		mi = new MyItem(R.drawable.dimatteo, "", R.drawable.camcorder);arItem.add(mi);
		mi = new MyItem(R.drawable.bobby, "", R.drawable.camcorder);arItem.add(mi);
		mi = new MyItem(R.drawable.makelele, "", R.drawable.camcorder);arItem.add(mi);
		mi = new MyItem(R.drawable.hassel, "", R.drawable.camcorder);arItem.add(mi);
		mi = new MyItem(R.drawable.ruud, "", R.drawable.camcorder);arItem.add(mi);
		mi = new MyItem(R.drawable.desailly, "", R.drawable.camcorder);arItem.add(mi);
		
		
		
		MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.teams, arItem);
		
		ListView ChelseaList;
		ChelseaList = (ListView)findViewById(R.id.list);
		ChelseaList.setAdapter(MyAdapter);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(wv != null){
			wv.destroy();
			wv.destroyDrawingCache();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	//리스트 뷰에 출력할 항목
	class MyItem{
		MyItem(int aIcon, String aName, int aImageBtn){
		Icon = aIcon;
		Name = aName;
		ImageBtn = aImageBtn;
		}
		int Icon;
		String Name;
		int ImageBtn;
	}
	
	public class MyListAdapter extends BaseAdapter {

		Context maincon;
		LayoutInflater Inflater;
		ArrayList<MyItem> arSrc;
		int layout;
		
		public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc) {
			// TODO Auto-generated constructor stub
			maincon = context;
			Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			arSrc = aarSrc;
			layout = alayout;
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return arSrc.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return arSrc.get(position).Name;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final int pos = position;
			if(convertView == null){
				convertView = Inflater.inflate(layout, parent, false);
			}
			ImageView img = (ImageView)convertView.findViewById(R.id.img);
			img.setImageResource(arSrc.get(position).Icon);
			img.setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					//create alert dialog 
					AlertDialog.Builder mAlert = new AlertDialog.Builder(LegendAct.this);
					switch(arSrc.get(pos).Icon){
					case R.drawable.drogba:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("이름 : 디디에 드록바 ( Didier Drogba )" +'\n' 
								+ "출생 : 1978년 3월 11일 ( 코트디부아르 )" + '\n'
								+ "신체 : 189cm, 91kg" + '\n'
								+ "포지션 : 포워드, 스트라이커" + '\n'
								+ "등번호 : 11번" + '\n' + '\n'
								+ "04 시즌 마르세유에서 첼시로 이적" + '\n'+ '\n'
								+ "총 341 경기 출전하여 157골을 넣음" + '\n'+ '\n'
								+ "3회의 프리미어리그 우승, 챔피언스리그 1회 우승, 4회의 FA컵 우승, 2회의 칼링컵 우승에 혁혁한 기여 " + '\n'+ '\n'
								+ "07, 09년 2회의 프리미어리그 득점왕 수상, 06년 프리미어리그 도움왕, 10년 타임지 세계에서 가장 영향력 있는 100인 선정"+ '\n'+ '\n'
								+ "탁월한 키로 헤딩 제공권 확보, 절대 밀리지 않는 몸싸움에 절대 감각인 득점 본능까지 가진 완벽한 공격수" + '\n'+ '\n'
								+ "빠른발, 정확한 임팩트, 세밀하고도 민첩한 상황판단, 골키퍼와의 일대일 상황에서 지체없이 찔러넣는 슛, 골키퍼가 잡을수 없는 곳에다 송곳처럼 찔러대는 세련된 스트라이커" + '\n'+ '\n'
								+ "엄청난 무회전 프리킥 능력, 엄청난 힘과 스피드 유연성, 결정력으로 프리미어리그 센터백들이 거의 남아나지 않음, 특히 아스날의 대형 유망주였던 센데로스는 그에게 매번 당하고 결국 하락의 길을 걸음"+ '\n'+ '\n'
								+ "FA 컵의 사나이, FA 결승전의 사나이라는 별칭이 있을정도로 컵대회에 아주 강했으며, 11-12 챔피언스리그에서처럼 팀을 위기에서 구하는 해결사 능력을 보여줌"+ '\n'+ '\n'
								+ "득점뿐만 아니라 도움까지도 잘하는 이타적인 공격수로 이름을 날림"+ '\n'+ '\n'
								+ "그의 절정은 11 -12 시즌 UEFA 챔피언스리그에서 바르셀로나와 바이에른뮌헨등을 이기고 팀에 챔피언스리그 첫 우승을 안기는데 엄청난 기여를 함"+ '\n'+ '\n'
								+ "조국 코트디부아르의 내전을 잠시 중단시키는 엄청난 영향력도 보이며 전쟁을 멈춘 사나이라는 엄청난 칭호가 붙으며 '드록신' 이라는 별명을 가지게 됨"+ '\n'+ '\n'
								+ "토레스, 뎀바바, 루카쿠, 에투 등이 있지만 지금도 첼시가 포스트 드록바를 구하지못해 힘들어 하는것을 보면 그가 첼시 성적과 경기력에 얼마나 엄청난 영향을 미친 선수인지 짐작이 감"+ '\n'+ '\n'
								+ "말로 표현할수 없는 그의 플레이, 25분짜리 스페셜 영상으로 느껴보세요.");
						break;
						
					case R.drawable.zola:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("이름 : 지안프랑코 졸라 ( Gianfranco Zola )" +'\n' 
								+ "출생 : 1966년 7월 5일 ( 이탈리아 )" + '\n'
								+ "신체 : 168cm, 68kg" + '\n'
								+ "포지션 : 쉐도우 스트라이커 , 공격형 미드필더" + '\n'
								+ "등번호 : 25번" + '\n' + '\n'
								+ "96 시즌 첼시 이적" + '\n'+ '\n'
								+ "드리블링, 킬패스, 로빙패스, 크로스, 스루패스에 능했던 전통적인 이탈리안 판타지스타" + '\n'+ '\n'
								+ "믿을수 없는 궤적의 골을 자주 만듬" + '\n'+ '\n'
								+ "역대 최강의 프리키커 중 하나" + '\n'+ '\n'
								+ "사실 득점하는건 PK보단 프리킥이 더 쉬웠어요ㅋㅋ by 졸라"+ '\n'+ '\n'
								+ "로만 인수 후 2004년 8월 8일 첼시에서의 마지막 인터뷰를 끝으로 팀을 떠남"+ '\n'+ '\n'
								+ "7년간 첼시에서 통산 69골을 넣음"+ '\n'+ '\n'
								+ "역대 최고의 첼시 레전드");
						break;
					case R.drawable.osgood:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("이름 : 피터 레슬리 오스굿 ( Peter Osgood )" +'\n' 
								+ "출생 :1947년 2월 20일 (잉글랜드)" + '\n'
								+ "신체 : 185cm" + '\n'
								+ "포지션 : 스트라이커" + '\n'+ '\n'
								+ "스탬포드 브릿지의 왕" + '\n'+ '\n'
								+ "첼시의 유소년으로 클럽에 들어와 17살의 나이로 리그컵에서 2골을 넣으며 화려하게 데뷔" + '\n'+ '\n'
								+ "힘을 들이지 않고도 플레이에서 우아함을 유지하고 감탄할만큼 절묘한 기술을 가지고 있었음, 이후 강인함과 피지컬적인 존개감까지 과시" + '\n'+ '\n'
								+ "장거리, 양발, 머리로도 10시즌동안 감독이 가장 먼저 출전명단에 자동으로 이름을 써넣는 선수가 됨" + '\n'+ '\n'
								+ "1970년 FA컵에서 모든 라운드에서 득점을 기록, 다음 2번의 시즌동안 첼시가 가진 컵대회 결승전에서 모두 골을 기록" + '\n'+ '\n'
								+ "클럽의 매니지먼트와 불화로 팀과 떨어져 계약을 거부당하고 74년 3월 첼시를 떠나 사우스햄튼으로 이적"+ '\n'+ '\n'
								+ "1978-79시즌 스탬포드브릿지로 돌아왔지만 팀은 강등되고 말았음"+ '\n'+ '\n'
								+ "첼시에서 그림자같은 존재였고, 첼시에서 총 150골을 달성"+ '\n'+ '\n'
								+ "첼시의 클럽역사에서 가장 많은 득점을 기록한 4번째 선수"+ '\n'+ '\n'
								+ "심장마비로 인해 겨우 59세의 나이로 2006년 3월 세상을 떠나고 말았다."+ '\n'+ '\n'
								+ "그의 동상은 스탬포드 브릿지 웨스트 스탠드밖에 서있으며 그의 유해를 화장한 재는 셰드 엔드 페널티 스폿아래에 묻혀있다.");
						break;
					case R.drawable.dimatteo:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("이름 : 로베르토 디 마테오 ( Roberto Di Matteo )" +'\n' 
								+ "출생 : 1970년 5월 29일 (스위스)" + '\n'
								+ "신체 : 180cm" + '\n'
								+ "포지션 : 미드필더" + '\n'+ '\n'
								+ "유럽챔피언이란 역사를 써낸 영리한 선수이자 위대한 감독" + '\n'+ '\n'
								+ "1996년 여름 라치오에서 첼시로 이적" + '\n'+ '\n'
								+ "홈 데뷔전에서 미들스보로를 상대로 결승골을 넣으며 승리로 장식" + '\n'+ '\n'
								+ "가장 특별한 의미가 있는 골은 1997년 FA컵 결승전에서 경기시작 단 43초만에 터진 그의 중거리슛" + '\n'+ '\n'
								+ "3번의 다른 결승전에서 모두 득점을 하는 클럽 역사상 두번째 첼시선수로 클럽 역사에 이름이 기록됨" + '\n'+ '\n'
								+ "첼시의 유러피언 컵 위너스 컵에서의 성공에서도 팀의 중심이었음"+ '\n'+ '\n'
								+ "2000년 9월 UEFA컵 경기에서 삼중 다리 골절부상을 입고 말았음"+ '\n'+ '\n'
								+ "18개월동안 사투를 벌인 로비는 겨우 31세의 나이로 부상을 극복하지 못하고 안타깝게도 은퇴를 결심해야만 했다."+ '\n'+ '\n'
								+ "몇개의 클럽을 거쳐, 2011-12시즌 첼시의 수석코치로서 클럽에 복귀"+ '\n'+ '\n'
								+ "시즌도중 감독경질로 인해 디 마테오는 갑작스럽게 첼시의 매니저로서 클럽을 이끌어야하는 상황에 놓였음"+ '\n'+ '\n'
								+ "로만 아브라모비치시대이후로 최악의 성적을 거두고 있던 팀을 FA컵 우승팀으로 만들었고, 유럽 챔피언스리그에서 클럽 역사상 첫 우승을 이끌어내는 기적을 만들었다."+ '\n'+ '\n'
								+ "선수와 감독으로서 모두 클럽에 우승컵을 안겨주고, 역사를 써낸 그는 세상 어디를 가더라도 첼시팬들에게 환영받을것이다.");
						break;
					case R.drawable.makelele:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("이름 : 클로드 마켈렐레 ( Claude Makelele )" +'\n' 
								+ "출생 : 1973년 2월 18일 (콩고민주공화국)" + '\n'
								+ "신체 : 170cm, 70kg" + '\n'
								+ "포지션 : 수비형 미드필더" + '\n'
								+ "마켈렐레 포지션(The Makelele Role)'의 표본" + '\n'+ '\n'
								+ "2003년 레알에서 첼시로 이적" + '\n'+ '\n'
								+ "그의 이름을 따서 명명한 포지션을 갖고있는 유일한 첼시 플레이어" + '\n'+ '\n'
								+ "마케렐레가 첼시로 이적 한 이후 수년간, 첼시는 2번의 리그타이틀과 1번의 FA컵, 2번의 리그 컵우승을 달성했지만, 마켈렐레를 떠나보낸 레알마드리드는 어떠한 것도 우승하지 못했음" + '\n'+ '\n'
								+ "수비하는 것에 있어서 퀄리티를 가지고 있었고, 경기를 읽을줄 알았으며 팀이 공격적으로 나갈시에 그를 후방에 두고 더 많은 공격적인 선수들을 사용할 수 있게 만들었음" + '\n'+ '\n'
								+ "수비라인을 보호함으로서 유명한 25번의 클린시트를 유지할 수 있게 하였음" + '\n'+ '\n'
								+ "첼시가 2004-05시즌의 챔피언이 되는동안 1000분이상 무실점경기를 이어나갔음"+ '\n'+ '\n'
								+ "조세 무리뉴는 클로드 마켈렐레를 그 시즌의 가장 중요한 팀의 구성원이라고 공표"+ '\n'+ '\n'
								+ "수비형 미드필더의 교과서라고 칭함"+ '\n'+ '\n'
								+ "2008년 챔피언스리그 결승전에서 그의 커리어에서 최고의 경기력 중 일부를 보여줌"+ '\n'+ '\n'
								+ "챔피언스리그 결승전은 클로드 마켈렐레의 첼시에 대한 마지막 경기였고, 클럽에 대한 그의 공헌을 인정받아 파리 생재르망으로 자유 이적을 허용해줌"+ '\n'+ '\n'
								+ "마케렐레는 은퇴했지만 그가 제시했던 수비형 미드필더의 새로운 역할들은 축구계에 새로운 패러다임을 제시했음");
						break;
					case R.drawable.ruud:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("이름 : 루드 굴리트 ( Ruud Gullit )" +'\n' 
								+ "출생 : 1962년 12월 1일 ( 네덜란드 )" + '\n'
								+ "신체 : 186cm" + '\n'
								+ "포지션 : 포워드, 수비수(in checlsea) "+ '\n'
								+ "등번호 : 10번" + '\n' + '\n'
								+ "95 AC밀란에서 첼시로 이적" + '\n'+ '\n'
								+ "전성기때보다 낮은 위치에서 볼을 배급  및 경기 조율" + '\n'+ '\n'
								+ "선수생활의 황혼기 시기에 활동" + '\n'+ '\n'
								+ "단단한 피지컬을 기반으로 뛰어난 몸싸움과 제공권을 갖춘 선수"
								+ "선수 겸 감독으로 활동" + '\n'+ '\n'
								+ "팀을 중상위권으로 끌어올림"+ '\n'+ '\n'
								+ "97년 FA 컵 수상"+ '\n'+ '\n'
								+ "3년이라는 짧은 시간을 보냈지만 첼시를 한단계 끌어올리는 역할을 한 선수이자 감독");
						break;
					case R.drawable.hassel:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("이름 : 하셀바잉크 ( Jimmy Floyd Hasselbaink )" +'\n' 
								+ "출생 : 1972년 3월 27일 (수리남)" + '\n'
								+ "신체 : 183cm, 85kg" + '\n'
								+ "포지션 : 포워드 "+ '\n'
								+ "등번호 : 9번" + '\n' + '\n'
								+ "00 시즌 ATM에서  첼시로 이적" + '\n'+ '\n'
								+ "2경기당 1골에 가까운 평균 득점력 기록" + '\n'+ '\n'
								+ "프리미어리그 역사상 가장 간결한 피니셔 중 한명" + '\n'+ '\n'
								+ "격이 다른 스트라이커의 모습을 보여주며 2년연속 득점왕"+ '\n'+ '\n'
								+ "초강력 슈팅, 다이나믹한 플레이로 골" + '\n'+ '\n'
								+ "결정적인 순간마다 팀을 구해내며 첼시의 히어로로 떠오름"+ '\n'+ '\n'
								+ "로만 부임 후 미들즈 브러로 이적"+ '\n'+ '\n'
								+ "4시즌 동안 70골을 기록하고 팀을 떠남");
						break;
					case R.drawable.desailly:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("이름 :  마르셀 드사이 ( Marcel Desailly )" +'\n' 
								+ "출생 : 1968년 9월 7일 (가나)" + '\n'
								+ "신체 : 184cm, 84kg" + '\n'
								+ "포지션 : 수비수 "+ '\n'
								+ "등번호 : 6번" + '\n' + '\n'
								+ "98 시즌 밀란에서  첼시로 이적" + '\n'+ '\n'
								+ "'Le Rock'이라는 닉네임을 얻었고 2000년 첼시에서 FA컵 우승 달성" + '\n'+ '\n'
								+ "222경기에서 출전하여 7골을 득점" + '\n'+ '\n'
								+ "같은 프랑스 출신 르뵈프와 함께 강력한 디펜시브 파트너쉽 형성"+ '\n'+ '\n'
								+ "침착성과 확신, 압박으로부터의 냉정함 등 현재 캡틴인 어린 존 테리의 성장에 커다란 경험을 줌" + '\n'+ '\n');
						break;		
					case R.drawable.bobby:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("이름 :  바비 탬블링 ( Bobby Tambling )" +'\n' 
								+ "출생 : 1941년 9월 18일 (잉글랜드)" + '\n'
								+ "포지션 : 포워드, 윙 "+ '\n'+ '\n'
								+ "1958년 17세의 나이로 첼시에 데뷔" + '\n'+ '\n'
								+ "첼시 2부 강등 후 첫 풀시즌에서 22골을 넣음" + '\n'+ '\n'
								+ "1년 후 젊은 캡틴이 되고 총 37골을 기록 후 팀을 강등에서 구해냄" + '\n'+ '\n'
								+ "FA컵 결승전에서 득점한 첼시 클럽 역사상 최초의 선수가 되었지만 우승은 못함"+ '\n'+ '\n'
								+ "69~70 시즌 여름에 첼시를 떠나 크리스탈 팰리스에 합류" + '\n'+ '\n'
								+ "70년 첼시가 첫 FA컵을 우승하는 자리에 함께 하지 못함" + '\n'+ '\n'
								+ "통산 202골을 넣어 최근까지 첼시 클럽레코드 기록을 갖고 있었지만, 12~13 시즌 램파드가 기록 갱신" + '\n'+ '\n');
						break;
					}
					mAlert.show();
					return false;
				}
			});
			TextView txt = (TextView)convertView.findViewById(R.id.text);
			txt.setText(arSrc.get(pos).Name);
			
			Button btn = (Button)convertView.findViewById(R.id.btn);
			btn.setOnClickListener(new Button.OnClickListener(){

				public void onClick(View v) {
					// TODO Auto-generated method stub
					// video
					Uri uri = null;
					Intent web = null;
					
					switch(arSrc.get(pos).Icon){
					case R.drawable.drogba:
						url = "http://www.youtube.com/watch?v=UBUGL1XxhUA";
						break;
					case R.drawable.zola:
						url = "http://www.youtube.com/watch?v=eiN_XEPIP7I";
						break;
					case R.drawable.osgood:
						url = "http://www.youtube.com/watch?v=22Q9Gsfhojs";
						break;
					case R.drawable.dimatteo:
						url = "http://www.youtube.com/watch?v=WE9R8uv1M2Q";
						break;
					case R.drawable.makelele:
						url = "http://www.youtube.com/watch?v=63WojjZy7QE";
						break;
					case R.drawable.ruud:
						url = "http://www.youtube.com/watch?v=mCkciwltGEs";
						break;
					case R.drawable.hassel:
						url = "http://www.youtube.com/watch?v=iLwgpjh-Pvw";
						break;
					case R.drawable.desailly:
						url = "http://www.youtube.com/watch?v=GB5hwdWIEco";
						break;
					case R.drawable.bobby:
						url = "http://www.youtube.com/watch?v=hd1llslBTHA";
						break;
					}
					uri = Uri.parse(url);
					web = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(web);
				}		
			});
			
			return convertView;
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onKeyDown");
		if(keyCode == event.KEYCODE_BACK){
			Log.d(TAG, "KEYCODE_BACK");
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

			dialogBuilder.setTitle("종료");
			dialogBuilder.setMessage("종료 하시겠습니까?");
			dialogBuilder.setPositiveButton("취소", null);
			dialogBuilder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			dialogBuilder.show(); 
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void webViewInDiag(String url) {
		// TODO Auto-generated method stub
		//create webview
		Log.d(TAG, "webViewInDiag , url = "+url );
		wv = new WebView(this);
		wv.loadUrl(url);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.getSettings().setPluginState(PluginState.ON);
		wv.getSettings().setPluginsEnabled(true);
		wv.setWebViewClient(new WebViewClient());
		wv.setWebChromeClient(new WebChromeClient());
		
		
		//create alert dialog 
		alert = new AlertDialog.Builder(this);
		alert.setView(wv);

		alert.setCancelable(true);
		alert.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				Log.d(TAG, "setOnKeyListener");
				if(keyCode == event.KEYCODE_BACK){
					Log.d(TAG, "KEYCODE_BACK");
				}
				return false;
			}
		});
		alert.setOnCancelListener(new OnCancelListener() {
			
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				Log.d(TAG, "setOnCancelListener");
				wv.destroy();
				wv.destroyDrawingCache();
			}
		});
		alert.show();
	}
}
