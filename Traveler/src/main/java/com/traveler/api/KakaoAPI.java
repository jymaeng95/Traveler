package com.traveler.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class KakaoAPI {

   private final static String K_CLIENT_ID = "95b818577076aee958016c6878581e88";
   private final static String K_REDIRECT_URI = "http://localhost:8080/oauth";

   public String getAccessToken(String autorize_code) {

      final String RequestUrl = "https://kauth.kakao.com/oauth/token";
      final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
      postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
      postParams.add(new BasicNameValuePair("client_id", K_CLIENT_ID)); // REST API KEY
      postParams.add(new BasicNameValuePair("redirect_uri", K_REDIRECT_URI)); // 리다?��?��?�� URI
      postParams.add(new BasicNameValuePair("code", autorize_code)); // 로그?�� 과정 �? ?��?? code �?

      final HttpClient client = HttpClientBuilder.create().build();
      final HttpPost post = new HttpPost(RequestUrl);
      JsonNode returnNode = null;

      try {

         post.setEntity(new UrlEncodedFormEntity(postParams));
         final HttpResponse response = client.execute(post);
         final int responseCode = response.getStatusLine().getStatusCode();

         log.info("response: " + response);
         log.info("response: " + responseCode);

         // JSON ?��?�� 반환�? 처리

         ObjectMapper mapper = new ObjectMapper();
         returnNode = mapper.readTree(response.getEntity().getContent());

         log.info("returnNode: " + returnNode.get("access_token").toString());

      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } catch (ClientProtocolException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         // clear resources
      }
      return returnNode.get("access_token").toString();
   }

   public JsonNode getKakaoUserInfo(String accessToken) {

      final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
      final HttpClient client = HttpClientBuilder.create().build();
      final HttpPost post = new HttpPost(RequestUrl);

      // add header
      post.addHeader("Authorization", "Bearer " + accessToken);

      log.info("accessToken: " + accessToken);

      JsonNode returnNode = null;

      try {

         final HttpResponse response = client.execute(post);
         final int responseCode = response.getStatusLine().getStatusCode();

         log.info("\nSending 'POST' request to URL : " + RequestUrl);
         log.info("Response Code : " + responseCode);

         // JSON ?��?�� 반환�? 처리
         ObjectMapper mapper = new ObjectMapper();
         returnNode = mapper.readTree(response.getEntity().getContent());

      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } catch (ClientProtocolException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {

         // clear resources
      }
      return returnNode;
   }



   public void kakaoLogout(String accessToken) {
      final String RequestUrl = "https://kapi.kakao.com/v1/user/logout";
      final HttpClient client = HttpClientBuilder.create().build();
      final HttpPost post = new HttpPost(RequestUrl);

      // add header
      post.addHeader("Authorization", "Bearer " + accessToken);

      try {

         final HttpResponse response = client.execute(post);
         final int responseCode = response.getStatusLine().getStatusCode();

         log.info("Response Code : " + responseCode);

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}