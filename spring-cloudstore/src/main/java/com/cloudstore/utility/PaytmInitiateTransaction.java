package com.cloudstore.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
//
//import com.paytm.pg.constants.LibraryConstants;
//import com.paytm.pg.constants.MerchantProperties;
import com.paytm.pg.merchant.PaytmChecksum;


@Component
public class PaytmInitiateTransaction {
	
	//STAGING
//	private String MERCHANT_ID = "RUoKbo86921897956224";
//	private String MERCHANT_KEY = "pvwPY0ez8d7fxsGs";
	
	//PRODUCTION
	private String MERCHANT_ID = "SApWAl73540535701479";
	private String MERCHANT_KEY = "37MuHFVCCXnpK1ER";
	
	public String initiateTransaction(String orderId, String amount) throws Exception {
		
		System.out.println("------------Started the Initiate Transaction Process----------------");
		
		JSONObject paytmParams = new JSONObject();
		
		JSONObject body = new JSONObject();
		body.put("requestType", "Payment");
		//TODO This is Test MID
		body.put("mid", MERCHANT_ID);
//		body.put("websiteName", "CloudStore");
//		body.put("websiteName", "WEBSTAGING");
		body.put("websiteName", "DEFAULT");
		//TODO ORDERID should be unique. Use id of the order document in mongodb
		body.put("orderId", orderId);
		//TODO Update the callbackUrl
//		body.put("callbackUrl", "http://localhost:3000/cart/success");
		body.put("callbackUrl", "http://localhost:8080/user/customer/paymentResponse");
		
		JSONObject txnAmount = new JSONObject();
		//TODO Update the amount
		txnAmount.put("value", amount);
		txnAmount.put("currency", "INR");
		
//		//These are optional
		JSONObject userInfo = new JSONObject();
		userInfo.put("custId", "CUST_001");
		userInfo.put("mobile", "9446028121");
		
		body.put("txnAmount", txnAmount);
		body.put("userInfo", userInfo);
		
		String checksum = PaytmChecksum.generateSignature(body.toString(), MERCHANT_KEY);
		
		JSONObject head = new JSONObject();
		head.put("signature", checksum);
		
		paytmParams.put("body", body);
		paytmParams.put("head", head);
		
		System.out.println("Paytm POST BODY: " + paytmParams);
		
		String post_data = paytmParams.toString();
		
		String stageUrl = "https://securegw-stage.paytm.in/theia/api/v1/initiateTransaction?";
		String prodUrl = "https://securegw.paytm.in/theia/api/v1/initiateTransaction?";
		
		URL url = new URL(prodUrl + "mid="+MERCHANT_ID+"&orderId="+orderId);
		
		String responseData = "";
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			
			DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
			requestWriter.writeBytes(post_data);
			requestWriter.close();
//			String responseData = "";
			InputStream is = connection.getInputStream();
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
			if((responseData = responseReader.readLine()) != null) {
				System.out.append("Response: " + responseData);
			}
			responseReader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("--------Finshed the Initiate Transaction Process--------------");
		
		return responseData;
	}
	
	
//	public void initiateTransactionWithSDK(){
//		// For Staging
//		String environment = LibraryConstants.STAGING_ENVIRONMENT;
//		
//		String mid = "RUoKbo86921897956224";
//		String key = "pvwPY0ez8d7fxsGs";
//		String website = "WEBSTAGING";
//		String client_id = "CLIENT_adsfh1223";
//		
//		String callbackUrl = "MERCANT_CALLBACK_URL";
//		MerchantProperties.setCallbackUrl(callbackUrl);
//		
//		MerchantProperties.initialize(environment, mid, key, client_id, website);
//		
//		
//	}
	
}
