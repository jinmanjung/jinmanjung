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

	//����Ʈ �信 ����� �׸�
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
						mAlert.setMessage("�̸� : ��� ��Ϲ� ( Didier Drogba )" +'\n' 
								+ "��� : 1978�� 3�� 11�� ( ��Ʈ��ξƸ� )" + '\n'
								+ "��ü : 189cm, 91kg" + '\n'
								+ "������ : ������, ��Ʈ����Ŀ" + '\n'
								+ "���ȣ : 11��" + '\n' + '\n'
								+ "04 ���� ������������ ÿ�÷� ����" + '\n'+ '\n'
								+ "�� 341 ��� �����Ͽ� 157���� ����" + '\n'+ '\n'
								+ "3ȸ�� �����̾�� ���, è�Ǿ𽺸��� 1ȸ ���, 4ȸ�� FA�� ���, 2ȸ�� Į���� ��¿� ������ �⿩ " + '\n'+ '\n'
								+ "07, 09�� 2ȸ�� �����̾�� ������ ����, 06�� �����̾�� �����, 10�� Ÿ���� ���迡�� ���� ����� �ִ� 100�� ����"+ '\n'+ '\n'
								+ "Ź���� Ű�� ��� ������ Ȯ��, ���� �и��� �ʴ� ���ο� ���� ������ ���� ���ɱ��� ���� �Ϻ��� ���ݼ�" + '\n'+ '\n'
								+ "������, ��Ȯ�� ����Ʈ, �����ϰ� ��ø�� ��Ȳ�Ǵ�, ��Ű�ۿ��� �ϴ��� ��Ȳ���� ��ü���� �񷯳ִ� ��, ��Ű�۰� ������ ���� ������ �۰�ó�� �񷯴�� ���õ� ��Ʈ����Ŀ" + '\n'+ '\n'
								+ "��û�� ��ȸ�� ����ű �ɷ�, ��û�� ���� ���ǵ� ������, ���������� �����̾�� ���͹���� ���� ���Ƴ��� ����, Ư�� �ƽ����� ���� �����ֿ��� �����ν��� �׿��� �Ź� ���ϰ� �ᱹ �϶��� ���� ����"+ '\n'+ '\n'
								+ "FA ���� �糪��, FA ������� �糪�̶�� ��Ī�� ���������� �Ŵ�ȸ�� ���� ��������, 11-12 è�Ǿ𽺸��׿���ó�� ���� ���⿡�� ���ϴ� �ذ�� �ɷ��� ������"+ '\n'+ '\n'
								+ "�����Ӹ� �ƴ϶� ��������� ���ϴ� ��Ÿ���� ���ݼ��� �̸��� ����"+ '\n'+ '\n'
								+ "���� ������ 11 -12 ���� UEFA è�Ǿ𽺸��׿��� �ٸ����γ��� ���̿���������� �̱�� ���� è�Ǿ𽺸��� ù ����� �ȱ�µ� ��û�� �⿩�� ��"+ '\n'+ '\n'
								+ "���� ��Ʈ��ξƸ��� ������ ��� �ߴܽ�Ű�� ��û�� ����µ� ���̸� ������ ���� �糪�̶�� ��û�� Īȣ�� ������ '��Ͻ�' �̶�� ������ ������ ��"+ '\n'+ '\n'
								+ "�䷹��, ���ٹ�, ��ī��, ���� ���� ������ ���ݵ� ÿ�ð� ����Ʈ ��Ϲٸ� ���������� ����� �ϴ°��� ���� �װ� ÿ�� ������ ���¿� �󸶳� ��û�� ������ ��ģ �������� ������ ��"+ '\n'+ '\n'
								+ "���� ǥ���Ҽ� ���� ���� �÷���, 25��¥�� ����� �������� ����������.");
						break;
						
					case R.drawable.zola:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("�̸� : ���������� ���� ( Gianfranco Zola )" +'\n' 
								+ "��� : 1966�� 7�� 5�� ( ��Ż���� )" + '\n'
								+ "��ü : 168cm, 68kg" + '\n'
								+ "������ : ������ ��Ʈ����Ŀ , ������ �̵��ʴ�" + '\n'
								+ "���ȣ : 25��" + '\n' + '\n'
								+ "96 ���� ÿ�� ����" + '\n'+ '\n'
								+ "�帮��, ų�н�, �κ��н�, ũ�ν�, �����н��� ���ߴ� �������� ��Ż���� ��Ÿ����Ÿ" + '\n'+ '\n'
								+ "������ ���� ������ ���� ���� ����" + '\n'+ '\n'
								+ "���� �ְ��� ����ŰĿ �� �ϳ�" + '\n'+ '\n'
								+ "��� �����ϴ°� PK���� ����ű�� �� ������䤻�� by ����"+ '\n'+ '\n'
								+ "�θ� �μ� �� 2004�� 8�� 8�� ÿ�ÿ����� ������ ���ͺ並 ������ ���� ����"+ '\n'+ '\n'
								+ "7�Ⱓ ÿ�ÿ��� ��� 69���� ����"+ '\n'+ '\n'
								+ "���� �ְ��� ÿ�� ������");
						break;
					case R.drawable.osgood:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("�̸� : ���� ������ ������ ( Peter Osgood )" +'\n' 
								+ "��� :1947�� 2�� 20�� (�ױ۷���)" + '\n'
								+ "��ü : 185cm" + '\n'
								+ "������ : ��Ʈ����Ŀ" + '\n'+ '\n'
								+ "�������� �긴���� ��" + '\n'+ '\n'
								+ "ÿ���� ���ҳ����� Ŭ���� ���� 17���� ���̷� �����ſ��� 2���� ������ ȭ���ϰ� ����" + '\n'+ '\n'
								+ "���� ������ �ʰ� �÷��̿��� ������� �����ϰ� ��ź�Ҹ�ŭ ������ ����� ������ �־���, ���� �����԰� ���������� ���������� ����" + '\n'+ '\n'
								+ "��Ÿ�, ���, �Ӹ��ε� 10���𵿾� ������ ���� ���� ������ܿ� �ڵ����� �̸��� ��ִ� ������ ��" + '\n'+ '\n'
								+ "1970�� FA�ſ��� ��� ���忡�� ������ ���, ���� 2���� ���𵿾� ÿ�ð� ���� �Ŵ�ȸ ��������� ��� ���� ���" + '\n'+ '\n'
								+ "Ŭ���� �Ŵ�����Ʈ�� ��ȭ�� ���� ������ ����� �źδ��ϰ� 74�� 3�� ÿ�ø� ���� ��콺��ư���� ����"+ '\n'+ '\n'
								+ "1978-79���� ��������긴���� ���ƿ����� ���� ����ǰ� ������"+ '\n'+ '\n'
								+ "ÿ�ÿ��� �׸��ڰ��� ���翴��, ÿ�ÿ��� �� 150���� �޼�"+ '\n'+ '\n'
								+ "ÿ���� Ŭ�����翡�� ���� ���� ������ ����� 4��° ����"+ '\n'+ '\n'
								+ "���帶��� ���� �ܿ� 59���� ���̷� 2006�� 3�� ������ ������ ���Ҵ�."+ '\n'+ '\n'
								+ "���� ������ �������� �긴�� ����Ʈ ���ĵ�ۿ� �������� ���� ���ظ� ȭ���� ��� �ε� ���� ���Ƽ �����Ʒ��� �����ִ�.");
						break;
					case R.drawable.dimatteo:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("�̸� : �κ����� �� ���׿� ( Roberto Di Matteo )" +'\n' 
								+ "��� : 1970�� 5�� 29�� (������)" + '\n'
								+ "��ü : 180cm" + '\n'
								+ "������ : �̵��ʴ�" + '\n'+ '\n'
								+ "����è�Ǿ��̶� ���縦 �᳽ ������ �������� ������ ����" + '\n'+ '\n'
								+ "1996�� ���� ��ġ������ ÿ�÷� ����" + '\n'+ '\n'
								+ "Ȩ ���������� �̵齺���θ� ���� ��°��� ������ �¸��� ���" + '\n'+ '\n'
								+ "���� Ư���� �ǹ̰� �ִ� ���� 1997�� FA�� ��������� ������ �� 43�ʸ��� ���� ���� �߰Ÿ���" + '\n'+ '\n'
								+ "3���� �ٸ� ��������� ��� ������ �ϴ� Ŭ�� ����� �ι�° ÿ�ü����� Ŭ�� ���翡 �̸��� ��ϵ�" + '\n'+ '\n'
								+ "ÿ���� �����Ǿ� �� ���ʽ� �ſ����� ���������� ���� �߽��̾���"+ '\n'+ '\n'
								+ "2000�� 9�� UEFA�� ��⿡�� ���� �ٸ� �����λ��� �԰� ������"+ '\n'+ '\n'
								+ "18�������� ������ ���� �κ�� �ܿ� 31���� ���̷� �λ��� �غ����� ���ϰ� ��Ÿ���Ե� ���� ����ؾ߸� �ߴ�."+ '\n'+ '\n'
								+ "��� Ŭ���� ����, 2011-12���� ÿ���� ������ġ�μ� Ŭ���� ����"+ '\n'+ '\n'
								+ "������ ���������� ���� �� ���׿��� ���۽����� ÿ���� �Ŵ����μ� Ŭ���� �̲�����ϴ� ��Ȳ�� ������"+ '\n'+ '\n'
								+ "�θ� �ƺ����ġ�ô����ķ� �־��� ������ �ŵΰ� �ִ� ���� FA�� ��������� �������, ���� è�Ǿ𽺸��׿��� Ŭ�� ����� ù ����� �̲���� ������ �������."+ '\n'+ '\n'
								+ "������ �������μ� ��� Ŭ���� ������� �Ȱ��ְ�, ���縦 �᳽ �״� ���� ��� ������ ÿ���ҵ鿡�� ȯ���������̴�.");
						break;
					case R.drawable.makelele:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("�̸� : Ŭ�ε� ���̷��� ( Claude Makelele )" +'\n' 
								+ "��� : 1973�� 2�� 18�� (�����ְ�ȭ��)" + '\n'
								+ "��ü : 170cm, 70kg" + '\n'
								+ "������ : ������ �̵��ʴ�" + '\n'
								+ "���̷��� ������(The Makelele Role)'�� ǥ��" + '\n'+ '\n'
								+ "2003�� ���˿��� ÿ�÷� ����" + '\n'+ '\n'
								+ "���� �̸��� ���� ����� �������� �����ִ� ������ ÿ�� �÷��̾�" + '\n'+ '\n'
								+ "���ɷ����� ÿ�÷� ���� �� ���� ���Ⱓ, ÿ�ô� 2���� ����Ÿ��Ʋ�� 1���� FA��, 2���� ���� �ſ���� �޼�������, ���̷����� �������� ���˸��帮��� ��� �͵� ������� ������" + '\n'+ '\n'
								+ "�����ϴ� �Ϳ� �־ ����Ƽ�� ������ �־���, ��⸦ ������ �˾����� ���� ���������� �����ÿ� �׸� �Ĺ濡 �ΰ� �� ���� �������� �������� ����� �� �ְ� �������" + '\n'+ '\n'
								+ "��������� ��ȣ�����μ� ������ 25���� Ŭ����Ʈ�� ������ �� �ְ� �Ͽ���" + '\n'+ '\n'
								+ "ÿ�ð� 2004-05������ è�Ǿ��� �Ǵµ��� 1000���̻� ��������⸦ �̾����"+ '\n'+ '\n'
								+ "���� �������� Ŭ�ε� ���̷����� �� ������ ���� �߿��� ���� �������̶�� ��ǥ"+ '\n'+ '\n'
								+ "������ �̵��ʴ��� ��������� Ī��"+ '\n'+ '\n'
								+ "2008�� è�Ǿ𽺸��� ��������� ���� Ŀ����� �ְ��� ���� �� �Ϻθ� ������"+ '\n'+ '\n'
								+ "è�Ǿ𽺸��� ������� Ŭ�ε� ���̷����� ÿ�ÿ� ���� ������ ��⿴��, Ŭ���� ���� ���� ������ �����޾� �ĸ� ���縣������ ���� ������ �������"+ '\n'+ '\n'
								+ "���ɷ����� ���������� �װ� �����ߴ� ������ �̵��ʴ��� ���ο� ���ҵ��� �౸�迡 ���ο� �з������� ��������");
						break;
					case R.drawable.ruud:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("�̸� : ��� ����Ʈ ( Ruud Gullit )" +'\n' 
								+ "��� : 1962�� 12�� 1�� ( �״����� )" + '\n'
								+ "��ü : 186cm" + '\n'
								+ "������ : ������, �����(in checlsea) "+ '\n'
								+ "���ȣ : 10��" + '\n' + '\n'
								+ "95 AC�ж����� ÿ�÷� ����" + '\n'+ '\n'
								+ "�����⶧���� ���� ��ġ���� ���� ���  �� ��� ����" + '\n'+ '\n'
								+ "������Ȱ�� Ȳȥ�� �ñ⿡ Ȱ��" + '\n'+ '\n'
								+ "�ܴ��� �������� ������� �پ ���ο�� �������� ���� ����"
								+ "���� �� �������� Ȱ��" + '\n'+ '\n'
								+ "���� �߻��������� ����ø�"+ '\n'+ '\n'
								+ "97�� FA �� ����"+ '\n'+ '\n'
								+ "3���̶�� ª�� �ð��� �������� ÿ�ø� �Ѵܰ� ����ø��� ������ �� �������� ����");
						break;
					case R.drawable.hassel:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("�̸� : �ϼ�����ũ ( Jimmy Floyd Hasselbaink )" +'\n' 
								+ "��� : 1972�� 3�� 27�� (������)" + '\n'
								+ "��ü : 183cm, 85kg" + '\n'
								+ "������ : ������ "+ '\n'
								+ "���ȣ : 9��" + '\n' + '\n'
								+ "00 ���� ATM����  ÿ�÷� ����" + '\n'+ '\n'
								+ "2���� 1�� ����� ��� ������ ���" + '\n'+ '\n'
								+ "�����̾�� ����� ���� ������ �Ǵϼ� �� �Ѹ�" + '\n'+ '\n'
								+ "���� �ٸ� ��Ʈ����Ŀ�� ����� �����ָ� 2�⿬�� ������"+ '\n'+ '\n'
								+ "�ʰ��� ����, ���̳����� �÷��̷� ��" + '\n'+ '\n'
								+ "�������� �������� ���� ���س��� ÿ���� ����η� ������"+ '\n'+ '\n'
								+ "�θ� ���� �� �̵��� �귯�� ����"+ '\n'+ '\n'
								+ "4���� ���� 70���� ����ϰ� ���� ����");
						break;
					case R.drawable.desailly:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("�̸� :  ������ ����� ( Marcel Desailly )" +'\n' 
								+ "��� : 1968�� 9�� 7�� (����)" + '\n'
								+ "��ü : 184cm, 84kg" + '\n'
								+ "������ : ����� "+ '\n'
								+ "���ȣ : 6��" + '\n' + '\n'
								+ "98 ���� �ж�����  ÿ�÷� ����" + '\n'+ '\n'
								+ "'Le Rock'�̶�� �г����� ����� 2000�� ÿ�ÿ��� FA�� ��� �޼�" + '\n'+ '\n'
								+ "222��⿡�� �����Ͽ� 7���� ����" + '\n'+ '\n'
								+ "���� ������ ��� �������� �Բ� ������ ����ú� ��Ʈ�ʽ� ����"+ '\n'+ '\n'
								+ "ħ������ Ȯ��, �й����κ����� ������ �� ���� ĸƾ�� � �� �׸��� ���忡 Ŀ�ٶ� ������ ��" + '\n'+ '\n');
						break;		
					case R.drawable.bobby:
						mAlert.setTitle("***");
						mAlert.setIcon(arSrc.get(position).Icon);
						mAlert.setMessage("�̸� :  �ٺ� �ƺ� ( Bobby Tambling )" +'\n' 
								+ "��� : 1941�� 9�� 18�� (�ױ۷���)" + '\n'
								+ "������ : ������, �� "+ '\n'+ '\n'
								+ "1958�� 17���� ���̷� ÿ�ÿ� ����" + '\n'+ '\n'
								+ "ÿ�� 2�� ���� �� ù Ǯ���𿡼� 22���� ����" + '\n'+ '\n'
								+ "1�� �� ���� ĸƾ�� �ǰ� �� 37���� ��� �� ���� ����� ���س�" + '\n'+ '\n'
								+ "FA�� ��������� ������ ÿ�� Ŭ�� ����� ������ ������ �Ǿ����� ����� ����"+ '\n'+ '\n'
								+ "69~70 ���� ������ ÿ�ø� ���� ũ����Ż �Ӹ����� �շ�" + '\n'+ '\n'
								+ "70�� ÿ�ð� ù FA���� ����ϴ� �ڸ��� �Բ� ���� ����" + '\n'+ '\n'
								+ "��� 202���� �־� �ֱٱ��� ÿ�� Ŭ�����ڵ� ����� ���� �־�����, 12~13 ���� ���ĵ尡 ��� ����" + '\n'+ '\n');
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

			dialogBuilder.setTitle("����");
			dialogBuilder.setMessage("���� �Ͻðڽ��ϱ�?");
			dialogBuilder.setPositiveButton("���", null);
			dialogBuilder.setNegativeButton("Ȯ��", new DialogInterface.OnClickListener() {
				
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
