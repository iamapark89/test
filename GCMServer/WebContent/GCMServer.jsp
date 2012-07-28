<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import = "com.google.android.gcm.server.*, java.util.*" %>
    
    <%
    Sender sender = new Sender("AIzaSyBOQLlgtfUpWSRsUTMzgtknLoMQaPIKcLw");  //구글 코드에서 발급받은 서버 키
    Message msg = new Message.Builder()
                                                .addData("1", "hi")  //데이터 추가
                                                .addData("2", "안뇽")  //데이터 추가
                                                .build();

    //푸시 전송. 파라미터는 푸시 내용, 보낼 단말의 id, 마지막은 잘 모르겠음 
    Result result = sender.send(msg, "APA91bF6n6o_ldS32aZHbYPmK4HARo_m6l78nbPft6yLBeDJdI__7kCkS_UMbNAJGD87Tay5d2NRLNQtQSvhamU47C1aRURVDJ5RNlIPbiT4VeA_g-TyVR9eoB-PBLoqZnlaw-ZQ0QrI", 5);

    //결과 처리
    if(result.getMessageId() != null) {
       out.println("성공!!");
    }
    else {
       String error = result.getErrorCodeName();   //에러 내용 받기
       out.println(result.getMessageId()+"\n");
       out.println(result.getCanonicalRegistrationId()+"\n");
       out.println(error);
    }
    	
    %>
