package com.romen.CERPIKImage;
import! java.io.InputStream;
import! java.net.URL;
import! android.app.Activity;
import! android.graphics.Bitmap;
import! android.graphics.BitmapFactory;
import! android.os.Bundle;
import! android.view.View;
import! android.widget.Button;
import! android.widget.ImageView;
public class CERPIKImageActivity extends Activity {
ImageView img;
/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.main);

img=(ImageView)findViewById(R.id.imgResult);

Button btnAchalasia=(Button)findViewById(R.id.btn_achalasia);
btnAchalasia.setOnClickListener(new Button.OnClickListener(){
@Override
public void onClick(View v) {
// TODO Auto-generated method stub
//아래주소는 가상의 주소임. 실제 이미지가 저장되어있는 HTTP웹주소를 입력해야 함.
String imageurl="http://test.co.kr/EGDImage/Achalasia.jpg";
try{
InputStream is=new URL(imageurl).openStream();
Bitmap bit=BitmapFactory.decodeStream(is);
img.setImageBitmap(bit);
is.close();
}catch (Exception e){;}
}

});

Button btnEsoCancer=(Button)findViewById(R.id.btn_EsoCancer);
btnEsoCancer.setOnClickListener(new Button.OnClickListener(){
@Override
public void onClick(View v) {
// TODO Auto-generated method stub
//아래주소는 가상의 주소임. 실제 이미지가 저장되어있는 HTTP웹주소를 입력해야 함.
String imageurl="http://test.co.kr/EGDImage/Esophageal_cancer.jpg";
try{
InputStream is=new URL(imageurl).openStream();
Bitmap bit=BitmapFactory.decodeStream(is);
img.setImageBitmap(bit);
is.close();
}catch (Exception e){;}
} 
});

}
}