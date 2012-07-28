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
	Activity act = this;	   // 이클래스에서 공용으로 사용할 유틸리티와 액티비티, 인텐트 등을 선언합니다.
	Activity actNext;          //
	Intent intent;             //

	public void getXMLDataList() {
		Intent intent = this.getIntent();//현재 액티비티가 속해 있는 인텐트를 호출합니다.
		String mode = intent.getStringExtra("mode");//인텐트를 통해 전달받은 "mode"라는 변수값을 가져옵니다.
		if ("edit".equals(mode)) {//mode의 값이 edit이면 수정폼의 프로세스를 작성합니다.

		} else {//"mode"의 값이 전달되지 않았을 경우, 회원가입폼(등록폼)으로 처리해야할 프로세스를 작성합니다.
			cmsutil.getEditText(act, R.id.userFormTEL).setText(//휴대전화 입력란에 단말기의 휴대전화번호를 가져와 자동으로 이력되게 합니다.
					cmsutil.getMyPhoneNumber(act));
			/**
			 * 휴대전화의 값이 10자리 이상이녀,정상적인 휴대전화값을 가져왔다고 보고, 입력된 휴대전화번호를 자용자가 임의로 수정할 수 없도록 setEnabled()를 
			 * false로 설정합니다. 이부분은 회원정보 운영정책에 따라 달라집니다. 이렇게, 단말기 인증으로만 회원에 가입하 수 있게 되어 실명인증보다 보안이
			 * 강화된 형태의 회원제를 운영할 수 있게 되지만, 그 만큼 회원가입의 자유는 제한될  수밖에 없습니다.
			 */
			if (cmsutil.getMyPhoneNumber(act).length() >= 10) {
				cmsutil.getEditText(act, R.id.userFormTEL).setEnabled(false);
			}
			cmsutil.getEditText(act, R.id.userFormPWD).setText("");//암호 입력란 초기화
		}
	}

	public void btnTurnOn() 
	{
		((ImageButton) this.findViewById(R.id.userFormOKBtn))//신청버튼에 대한 클릭 이벤트 메소드 작성
				.setOnClickListener(new Button.OnClickListener() 
				{
					public void onClick(View v) 
					{
					
						tryToAdd();//신청합니다 버튼을 클릭시 tryToAdd()메소드를 실행하여 회원가입을 시도하는 프로세스를 작동합니다.

					}
				});

		findViewById(R.id.userFormCancelBtn).setOnClickListener(//취소 버튼에 대한 이벤트 작성
				new Button.OnClickListener() {
					public void onClick(View v) {
						((RGLab) act.getApplication()).startLoading(act);
						act.onBackPressed();//이 버튼을 클릭하면, 이전 화면으로 이오하도록 하고 화면 전환이 되기 전까지 로딩화면이 나타나도록 합니다.
						((RGLab) act.getApplication()).endLoading();
					}
				});

		cmsutil.getEditText(act, R.id.userFormID).setFilters(//아이디 입력란에는 영문과 숫자만 입력할 수 있도록 cmsutil.filterAlphaNum 이라는 InputFilter를 적용 
				new InputFilter[] { cmsutil.filterAlphaNum });

	}

	HashMap<string, string=""><span style="font-size: 9pt; "><span style="font-size: 9pt; "> hm;//회원가입을 위해 웹서버와 통신한 결과를 받아 저장할 수 있는 HashMap객체를 선언합니다.

	/**
	 * 회원가입을 ㅅ디화는 메소드를 작성
	 */
	public void tryToAdd() {

		if (checkFormValid()) {//checkFormValid() 메서드를 실행하여 입력안에 올바른 정보가 입력되었는지 체크하고, 입력란의 조건에 맞는 정보가입력
								//되었을경우에만 웹서버와 통신을 시도합니다.
			String theUrl = "http://localhost:8080/androidServer/user_proc.jsp";
			Log.i(this.getLocalClassName(), theUrl);
			ArrayList</span></span><namevaluepair><span style="font-size: 9pt; "><span style="font-size: 9pt; "> httpParams = new ArrayList</span></span><namevaluepair><span style="font-size: 9pt; "><span style="font-size: 9pt; ">();
			httpParams.add(new BasicNameValuePair("mode", "add"));//add라는 파라미터를 넘김으로 jsp에서 회원가입 로직이 실행
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
			String tmpData = cmsHttp.sendPost(theUrl, httpParams);//입력된 값을 POST방식으로 웹서버에 회원가입 처리 요청합니다.
			if (tmpData == null)//웹서버와 통신에 실패할 경우 회원가입 프로세스를 중단시킵니다.
				return;

			hm = cmsutil.xml2HashMap(tmpData, cmsHttp.encoding);//웹서버에서 받은 결과를 HashMap으로 해독합니다.
			Log.v(this.getLocalClassName(), tmpData);//웹서버에서 받은 결과에 후속처리를 하는 addResult()메소드를 실행합니다.
			addResult();
		}
	}

	public boolean checkFormValid() {
		boolean state = false;//입력요건 검사의 결과로 사용할 변수를 선언합니다.
		if (cmsutil.getEditTextVal(act, R.id.userFormTEL).length() < 10) {
			Toast.makeText(act, "휴대전화를 올바르게 입력하세요.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.userFormTEL).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormID).length() < 6) {
			Toast.makeText(act, "아이디를 6자이상 올바르게 입력하세요.", Toast.LENGTH_LONG)
					.show();
			cmsutil.getEditText(act, R.id.userFormID).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormPWD).length() < 6) {
			Toast.makeText(act, "암호를 6자이상 올바르게 입력하세요.", Toast.LENGTH_LONG)
					.show();
			cmsutil.getEditText(act, R.id.userFormPWD).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormALIAS).length() < 6) {
			Toast.makeText(act, "별명을 6자이상 올바르게 입력하세요.", Toast.LENGTH_LONG)
					.show();
			cmsutil.getEditText(act, R.id.userFormALIAS).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormNAME).length() < 2) {
			Toast.makeText(act, "이름을 올바르게 입력하세요.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.userFormNAME).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormADDRESS).length() < 6) {
			Toast.makeText(act, "주소를 올바르게 입력하세요.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.userFormADDRESS).requestFocus();
		} else {
			state = true;
		}
		return state;
	}

	public void addResult() {
		//웹서버의 통신결과는 rowid값으로 구분하기로 했습니다. HashMap에서 rowid값을 가져와 정수형으로 변환합니다.
		int rowid = cmsutil.str2int(hm.get("rowid[0]"));
		String msg = hm.get("msg[0]");//회원가입 시도 결과에 대한 안내문을 가져옵니다.

		// rowid : 1(성공), -1(오류/재시도), -2(휴대번호중복), -3(아이디중복)
		switch (rowid) {
		case 1:
			Toast.makeText(act, msg, Toast.LENGTH_LONG).show();//회원가입 시도 결과에 대한 안내문
			cmsutil.setAuthHM(act, hm);//회원가입에 성공하면, 등록된 회원정보를 결과로 보내주기로 했습니다. 이값을 받아 cmsutil.setAuthHM()메소드로 회원정보를 기억하고, 로그인된것으로 처리합니다.
			((RGLab) act.getApplication()).startLoading(act);//화면전환이 진행되는 동안 로딩화면이 나타나도록합니다.
			cmsutil.goActivity(act, act.getPackageName() + ".userInfo");//회원정보 화면에 대한 엑티비티인 userInfo 이동
			((RGLab) act.getApplication()).endLoading();//화면전화 종료
			return;
		case -2://-2일 경우는 휴대전화번호 중복에 대한 프로세스를 호출 이는 회원으로 동록된 휴대전화일 경우에 해당합니다.
			conflictTel(msg);//휴대전화가 이미 가입된 경우, 이미 회원에 가입되었다는 것을 의미하므로 암호찾기 화면으로 이동할 수 있도록 안내합니다.
			return;
		case -3://이미 사용주인 아이디일 경우에 대한 프로세스를 작성합니다. 이 경우는 단순히 안내문만 출력하고 아이디 입력란에 커서를 이동하게 하여 다른 아이디로 회원가입을 재시도할 수 있게 합니다.
			Toast.makeText(act, msg, Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.userFormID).requestFocus();
			return;
		default://나머지의 경우는기타 오류에 대한 처리이고 안내문만 출력하는 것으로 마무리합니다.
			Toast.makeText(act, msg, Toast.LENGTH_LONG).show();
			return;
		}
	}

	public void conflictTel(String msg) {
		String msgAdd = "\n암호를 찾으시겠습니?";//웹서버에서 받아온 안내문에 덧붙일 추가 안내문을 정의
		AlertDialog.Builder builder = new AlertDialog.Builder(act);//AlertDialog.Builder객체를 호출합니다.
		builder.setTitle("안내").setMessage(msg + msgAdd).setCancelable(false)
				.setPositiveButton("확인", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
//						act.finish();
//						dialog.dismiss();
						((RGLab) act.getApplication()).startLoading(act);//안내문과 함께 대화상자를 출력하되, 확인 버튼과 닫기 버튼기능
						cmsutil.goActivity(act, act.getPackageName()	//을 추가합니다. 확인버튼을 클릭하면 userFindPWD액티비티 호출
								+ ".userFindpPWD");						//하여 암호찾기 화면으로 전환하도록 하고, 닫기 버튼을클릭하면 아내 대화상자가 닫히도록 합니다.
						((RGLab) act.getApplication()).endLoading();
					}
				}).setNegativeButton("닫기",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
						}).create().show();
	}
	
	// optionMenu//////////////////////////
	@Override
	/**
	 * onPrepareOptionMenu()메서드는 매뉴를 출력할 때마다 실행하는 메소드 이므로 권한에 따라
	 * 메뉴 출력 여부를 정의하는 수단으로 활용하기에 적합하
	 */
	public boolean onPrepareOptionsMenu(Menu menu) {
		new optionMenu(this).initMenu(menu);//optionMenu.java클래스에서 정의한 initMenu()메소드를 호출하여 로그인 여부를 확인하고 이에 따라
											//출력할 수 있는 메뉴를 결정하게 합니다.
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