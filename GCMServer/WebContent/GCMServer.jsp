<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import = "com.google.android.gcm.server.*, java.util.*" %>
    
    <%
    Sender sender = new Sender("AIzaSyBOQLlgtfUpWSRsUTMzgtknLoMQaPIKcLw");  //���� �ڵ忡�� �߱޹��� ���� Ű
    Message msg = new Message.Builder()
                                                .addData("1", "hi")  //������ �߰�
                                                .addData("2", "�ȴ�")  //������ �߰�
                                                .build();

    //Ǫ�� ����. �Ķ���ʹ� Ǫ�� ����, ���� �ܸ��� id, �������� �� �𸣰��� 
    Result result = sender.send(msg, "APA91bF6n6o_ldS32aZHbYPmK4HARo_m6l78nbPft6yLBeDJdI__7kCkS_UMbNAJGD87Tay5d2NRLNQtQSvhamU47C1aRURVDJ5RNlIPbiT4VeA_g-TyVR9eoB-PBLoqZnlaw-ZQ0QrI", 5);

    //��� ó��
    if(result.getMessageId() != null) {
       out.println("����!!");
    }
    else {
       String error = result.getErrorCodeName();   //���� ���� �ޱ�
       out.println(result.getMessageId()+"\n");
       out.println(result.getCanonicalRegistrationId()+"\n");
       out.println(error);
    }
    	
    %>
