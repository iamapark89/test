package com.example.android_basic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class EchoEditText extends EditText{

	public EchoEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public EchoEditText(Context context, AttributeSet attrs){
		super(context, attrs);
	}
	
	public EchoEditText(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event){
		append("" + (char)event.getUnicodeChar());
		
		return super.onKeyUp(keyCode, event);
	}

}
