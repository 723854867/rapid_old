package org.rapid.utilt.net.http;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.crypto.DesUtil;
import org.rapid.util.net.http.HttpProxy;
import org.rapid.util.net.http.SyncHttpAdapter;
import org.rapid.util.net.http.handler.SyncStrRespHandler;

public class HttpProxyTest {

	public static void main(String[] args) throws Exception {
		HttpProxy proxy = new HttpProxy();
		
		SyncHttpAdapter adapter = new SyncHttpAdapter();
		proxy.setSyncHttp(adapter);
		proxy.init();
		
		
		OfferSubmit submit = new OfferSubmit();
		submit.setUsername("cxdlzjcx");
		submit.setPassword("111111");
		submit.setLicenseNo("鄂A34JR9");
		submit.setVin("");
		submit.setSyr("郑菊妹");
		submit.setInsureProvince("四川");
		submit.setCompanyID("2038");
		submit.setProductCode("PAZYCX");
		String body = SerializeUtil.XmlUtil.beanToXml(submit, "utf-8");
		body = body.replace(" standalone=\"yes\"", "");
		System.out.println(body);
		body = DesUtil.EncryptDES(body, "68730531");
		System.out.println(body);
		HttpPost post = new HttpPost("http://123.56.130.87:8383/OpenAPI/CheXian.asmx/GetVehicleInByRenewl");
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("xmlData", body));
		post.setEntity(new UrlEncodedFormEntity(list, Charset.forName("UTF-8")));
		String result = proxy.syncRequest(post, SyncStrRespHandler.INSTANCE);
		System.out.println(result);
		byte[] buffer = Base64.decodeBase64("PFJFVFVSTj48TUVTU0FHRT48VkFMVUU+6K+35rGC5oql5paH6Kej5p6Q5aSx6LSl77yBPC9WQUxVRT48VElNRT4yMDE3LTA0LTI2IDE4OjM0OjEzPC9USU1FPjwvTUVTU0FHRT48L1JFVFVSTj4=");
		System.out.println(new String(buffer));
	}
	
	public static void availableInsurance() throws Exception { 
		HttpProxy proxy = new HttpProxy();
		
		SyncHttpAdapter adapter = new SyncHttpAdapter();
		proxy.setSyncHttp(adapter);
		proxy.init();
		
		
		AvailableInsuranceSubmit submit = new AvailableInsuranceSubmit();
		submit.setUsername("cxdlzjcx");
		submit.setPassword("111111");
		submit.setProvince("四川");
		String body = SerializeUtil.JsonUtil.GSON.toJson(submit);
		TimeUnit.SECONDS.sleep(1);
		System.out.println(body);
		body = DesUtil.EncryptDES(body, "68730531");
		System.out.println(body);
		body = new String(Base64.encodeBase64(body.getBytes()));
		System.out.println(body);
		HttpPost post = new HttpPost("http://123.56.130.87:8383/OpenAPI/CheXian.asmx/OtherISREADY");
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("jo", body));
		post.setEntity(new UrlEncodedFormEntity(list, Charset.forName("UTF-8")));
		String result = proxy.syncRequest(post, SyncStrRespHandler.INSTANCE);
		System.out.println(result);
		byte[] buffer = Base64.decodeBase64("ew0KICAiU3RhdGUiOiAxLA0KICAiTWVzc2FnZSI6ICLmn6Xor6LmiJDlip8iLA0KICAiT3Blbkxpc3QiOiBbDQogICAgew0KICAgICAgIkluc0NweUlEIjogIjQ3MzMiLA0KICAgICAgIkluc0NweU5hbWUiOiAi5bmz5a6J6L2m6ZmpIiwNCiAgICAgICJQcm9kdWN0Q29kZSI6ICJQQVpZQ1giLA0KICAgICAgIklzT3BlbiI6IHRydWUNCiAgICB9DQogIF0NCn0=");
		System.out.println(new String(buffer));
	}
}
