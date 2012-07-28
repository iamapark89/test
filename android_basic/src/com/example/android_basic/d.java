package com.RGSoft;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.RGSoft.Util.util;

public class userForm extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO Auto-generated method stub
		setContentView(R.layout.user_form);

		getXMLDataList();
		btnTurnOn();

		top top = new top(this);
		top.init();

	}

	util cmsutil = new util(); //
	Activity act = this;	   // ��Ŭ�������� �������� ����� ��ƿ��Ƽ�� ��Ƽ��Ƽ, ����Ʈ ���� �����մϴ�.
	Activity actNext;          //
	Intent intent;             //

	public void getXMLDataList() {
		Intent intent = this.getIntent();//���� ��Ƽ��Ƽ�� ���� �ִ� ����Ʈ�� ȣ���մϴ�.
		String mode = intent.getStringExtra("mode");//����Ʈ�� ���� ���޹��� "mode"��� �������� �����ɴϴ�.
		if ("edit".equals(mode)) {//mode�� ���� edit�̸� �������� ���μ����� �ۼ��մϴ�.

		} else {//"mode"�� ���� ���޵��� �ʾ��� ���, ȸ��������(�����)���� ó���ؾ��� ���μ����� �ۼ��մϴ�.
			cmsutil.getEditText(act, R.id.userFormTEL).setText(//�޴���ȭ �Է¶��� �ܸ����� �޴���ȭ��ȣ�� ������ �ڵ����� �̷µǰ� �մϴ�.
					cmsutil.getMyPhoneNumber(act));
			/**
			 * �޴���ȭ�� ���� 10�ڸ� �̻��̳�,�������� �޴���ȭ���� �����Դٰ� ����, �Էµ� �޴���ȭ��ȣ�� �ڿ��ڰ� ���Ƿ� ������ �� ������ setEnabled()�� 
			 * false�� �����մϴ�. �̺κ��� ȸ������ ���å�� ���� �޶����ϴ�. �̷���, �ܸ��� �������θ� ȸ���� ������ �� �ְ� �Ǿ� �Ǹ��������� ������
			 * ��ȭ�� ������ ȸ������ ��� �� �ְ� ������, �� ��ŭ ȸ�������� ������ ���ѵ�  ���ۿ� �����ϴ�.
			 */
			if (cmsutil.getMyPhoneNumber(act).length() >= 10) {
				cmsutil.getEditText(act, R.id.userFormTEL).setEnabled(false);
			}
			cmsutil.getEditText(act, R.id.userFormPWD).setText("");//��ȣ �Է¶� �ʱ�ȭ
		}
	}

	public void btnTurnOn() 
	{
		((ImageButton) this.findViewById(R.id.userFormOKBtn))//��û��ư�� ���� Ŭ�� �̺�Ʈ �޼ҵ� �ۼ�
				.setOnClickListener(new Button.OnClickListener() 
				{
					public void onClick(View v) 
					{
					
						tryToAdd();//��û�մϴ� ��ư�� Ŭ���� tryToAdd()�޼ҵ带 �����Ͽ� ȸ�������� �õ��ϴ� ���μ����� �۵��մϴ�.

					}
				});

		findViewById(R.id.userFormCancelBtn).setOnClickListener(//��� ��ư�� ���� �̺�Ʈ �ۼ�
				new Button.OnClickListener() {
					public void onClick(View v) {
						((RGLab) act.getApplication()).startLoading(act);
						act.onBackPressed();//�� ��ư�� Ŭ���ϸ�, ���� ȭ������ �̿��ϵ��� �ϰ� ȭ�� ��ȯ�� �Ǳ� ������ �ε�ȭ���� ��Ÿ������ �մϴ�.
						((RGLab) act.getApplication()).endLoading();
					}
				});

		cmsutil.getEditText(act, R.id.userFormID).setFilters(//���̵� �Է¶����� ������ ���ڸ� �Է��� �� �ֵ��� cmsutil.filterAlphaNum �̶�� InputFilter�� ���� 
				new InputFilter[] { cmsutil.filterAlphaNum });

	}

	HashMap<string, string=""><span style="font-size: 9pt; "><span style="font-size: 9pt; "> hm;//ȸ�������� ���� �������� ����� ����� �޾� ������ �� �ִ� HashMap��ü�� �����մϴ�.

	/**
	 * ȸ�������� ����ȭ�� �޼ҵ带 �ۼ�
	 */
	public void tryToAdd() {

		if (checkFormValid()) {//checkFormValid() �޼��带 �����Ͽ� �Է¾ȿ� �ùٸ� ������ �ԷµǾ����� üũ�ϰ�, �Է¶��� ���ǿ� �´� �������Է�
								//�Ǿ�����쿡�� �������� ����� �õ��մϴ�.
			String theUrl = "http://localhost:8080/androidServer/user_proc.jsp";
			Log.i(this.getLocalClassName(), theUrl);
			ArrayList</span></span><namevaluepair><span style="font-size: 9pt; "><span style="font-size: 9pt; "> httpParams = new ArrayList</span></span><namevaluepair><span style="font-size: 9pt; "><span style="font-size: 9pt; ">();
			httpParams.add(new BasicNameValuePair("mode", "add"));//add��� �Ķ���͸� �ѱ����� jsp���� ȸ������ ������ ����
			httpParams.add(new BasicNameValuePair("tel", ((EditText) act
					.findViewById(R.id.userFormTEL)).getText().toString()));
			httpParams.add(new BasicNameValuePair("pwd", cmsutil
					.getEditTextVal(act, R.id.userFormPWD)));
			httpParams.add(new BasicNameValuePair("id", cmsutil.getEditTextVal(
					act, R.id.userFormID)));
			httpParams.add(new BasicNameValuePair("alias", cmsutil
					.getEditTextVal(act, R.id.userFormALIAS)));
			httpParams.add(new BasicNameValuePair("name", cmsutil
					.getEditTextVal(act, R.id.userFormNAME)));
			httpParams.add(new BasicNameValuePair("email", cmsutil
					.getEditTextVal(act, R.id.userFormEMAIL)));
			httpParams.add(new BasicNameValuePair("zipcode", cmsutil
					.getEditTextVal(act, R.id.userFormZIPCODE)));
			httpParams.add(new BasicNameValuePair("address", cmsutil
					.getEditTextVal(act, R.id.userFormADDRESS)));
			cmsHTTP cmsHttp = new cmsHTTP();
			// cmsHttp.encoding = encoding;
			cmsHttp.act = this;
			String tmpData = cmsHttp.sendPost(theUrl, httpParams);//�Էµ� ���� POST������� �������� ȸ������ ó�� ��û�մϴ�.
			if (tmpData == null)//�������� ��ſ� ������ ��� ȸ������ ���μ����� �ߴܽ�ŵ�ϴ�.
				return;

			hm = cmsutil.xml2HashMap(tmpData, cmsHttp.encoding);//���������� ���� ����� HashMap���� �ص��մϴ�.
			Log.v(this.getLocalClassName(), tmpData);//���������� ���� ����� �ļ�ó���� �ϴ� addResult()�޼ҵ带 �����մϴ�.
			addResult();
		}
	}

	public boolean checkFormValid() {
		boolean state = false;//�Է¿�� �˻��� ����� ����� ������ �����մϴ�.
		if (cmsutil.getEditTextVal(act, R.id.userFormTEL).length() < 10) {
			Toast.makeText(act, "�޴���ȭ�� �ùٸ��� �Է��ϼ���.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.userFormTEL).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormID).length() < 6) {
			Toast.makeText(act, "���̵� 6���̻� �ùٸ��� �Է��ϼ���.", Toast.LENGTH_LONG)
					.show();
			cmsutil.getEditText(act, R.id.userFormID).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormPWD).length() < 6) {
			Toast.makeText(act, "��ȣ�� 6���̻� �ùٸ��� �Է��ϼ���.", Toast.LENGTH_LONG)
					.show();
			cmsutil.getEditText(act, R.id.userFormPWD).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormALIAS).length() < 6) {
			Toast.makeText(act, "������ 6���̻� �ùٸ��� �Է��ϼ���.", Toast.LENGTH_LONG)
					.show();
			cmsutil.getEditText(act, R.id.userFormALIAS).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormNAME).length() < 2) {
			Toast.makeText(act, "�̸��� �ùٸ��� �Է��ϼ���.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.userFormNAME).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormADDRESS).length() < 6) {
			Toast.makeText(act, "�ּҸ� �ùٸ��� �Է��ϼ���.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.userFormADDRESS).requestFocus();
		} else {
			state = true;
		}
		return state;
	}

	public void addResult() {
		//�������� ��Ű���� rowid������ �����ϱ�� �߽��ϴ�. HashMap���� rowid���� ������ ���������� ��ȯ�մϴ�.
		int rowid = cmsutil.str2int(hm.get("rowid[0]"));
		String msg = hm.get("msg[0]");//ȸ������ �õ� ����� ���� �ȳ����� �����ɴϴ�.

		// rowid : 1(����), -1(����/��õ�), -2(�޴��ȣ�ߺ�), -3(���̵��ߺ�)
		switch (rowid) {
		case 1:
			Toast.makeText(act, msg, Toast.LENGTH_LONG).show();//ȸ������ �õ� ����� ���� �ȳ���
			cmsutil.setAuthHM(act, hm);//ȸ�����Կ� �����ϸ�, ��ϵ� ȸ�������� ����� �����ֱ�� �߽��ϴ�. �̰��� �޾� cmsutil.setAuthHM()�޼ҵ�� ȸ�������� ����ϰ�, �α��εȰ����� ó���մϴ�.
			((RGLab) act.getApplication()).startLoading(act);//ȭ����ȯ�� ����Ǵ� ���� �ε�ȭ���� ��Ÿ�������մϴ�.
			cmsutil.goActivity(act, act.getPackageName() + ".userInfo");//ȸ������ ȭ�鿡 ���� ��Ƽ��Ƽ�� userInfo �̵�
			((RGLab) act.getApplication()).endLoading();//ȭ����ȭ ����
			return;
		case -2://-2�� ���� �޴���ȭ��ȣ �ߺ��� ���� ���μ����� ȣ�� �̴� ȸ������ ���ϵ� �޴���ȭ�� ��쿡 �ش��մϴ�.
			conflictTel(msg);//�޴���ȭ�� �̹� ���Ե� ���, �̹� ȸ���� ���ԵǾ��ٴ� ���� �ǹ��ϹǷ� ��ȣã�� ȭ������ �̵��� �� �ֵ��� �ȳ��մϴ�.
			return;
		case -3://�̹� ������� ���̵��� ��쿡 ���� ���μ����� �ۼ��մϴ�. �� ���� �ܼ��� �ȳ����� ����ϰ� ���̵� �Է¶��� Ŀ���� �̵��ϰ� �Ͽ� �ٸ� ���̵�� ȸ�������� ��õ��� �� �ְ� �մϴ�.
			Toast.makeText(act, msg, Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.userFormID).requestFocus();
			return;
		default://�������� ���±�Ÿ ������ ���� ó���̰� �ȳ����� ����ϴ� ������ �������մϴ�.
			Toast.makeText(act, msg, Toast.LENGTH_LONG).show();
			return;
		}
	}

	public void conflictTel(String msg) {
		String msgAdd = "\n��ȣ�� ã���ðڽ���?";//���������� �޾ƿ� �ȳ����� ������ �߰� �ȳ����� ����
		AlertDialog.Builder builder = new AlertDialog.Builder(act);//AlertDialog.Builder��ü�� ȣ���մϴ�.
		builder.setTitle("�ȳ�").setMessage(msg + msgAdd).setCancelable(false)
				.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
//						act.finish();
//						dialog.dismiss();
						((RGLab) act.getApplication()).startLoading(act);//�ȳ����� �Բ� ��ȭ���ڸ� ����ϵ�, Ȯ�� ��ư�� �ݱ� ��ư���
						cmsutil.goActivity(act, act.getPackageName()	//�� �߰��մϴ�. Ȯ�ι�ư�� Ŭ���ϸ� userFindPWD��Ƽ��Ƽ ȣ��
								+ ".userFindpPWD");						//�Ͽ� ��ȣã�� ȭ������ ��ȯ�ϵ��� �ϰ�, �ݱ� ��ư��Ŭ���ϸ� �Ƴ� ��ȭ���ڰ� �������� �մϴ�.
						((RGLab) act.getApplication()).endLoading();
					}
				}).setNegativeButton("�ݱ�",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
						}).create().show();
	}
	
	// optionMenu//////////////////////////
	@Override
	/**
	 * onPrepareOptionMenu()�޼���� �Ŵ��� ����� ������ �����ϴ� �޼ҵ� �̹Ƿ� ���ѿ� ����
	 * �޴� ��� ���θ� �����ϴ� �������� Ȱ���ϱ⿡ ������
	 */
	public boolean onPrepareOptionsMenu(Menu menu) {
		new optionMenu(this).initMenu(menu);//optionMenu.javaŬ�������� ������ initMenu()�޼ҵ带 ȣ���Ͽ� �α��� ���θ� Ȯ���ϰ� �̿� ����
											//����� �� �ִ� �޴��� �����ϰ� �մϴ�.
		return super.onPrepareOptionsMenu(menu);
	}
	

	// optionMenu//////////////////////////
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean tmp = new optionMenu(this).initSelected(item);
		if (tmp)
			return tmp;
		else
			return super.onOptionsItemSelected(item);
	}
	// optionMenu//////////////////////////

}