package com.school.common.micro.gateway;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.common.micro.model.ApiResponse;
import com.school.common.micro.model.MicroEntityModel;

public interface MicroServicesGateway {
	
	RestTemplate restTemplate = new RestTemplate();
	String AUTHORIZATION = "Authorization";
	String SERVER_ERROR = "The server encountered an internal error...!!!";
	String SUCCESS_MSG = "Response Successfully...!!!";
	
	
	default String getSessionToken(MicroServicesParams params) {
		/*certify();

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		if(params.getHeaders() != null && params.getHeaders().getHeader() != null && !params.getHeaders().getHeader().isEmpty()) {
			for(String key : params.getHeaders().getHeader().keySet()) {
				header.add(key, params.getHeaders().getHeader().get(key));
			}
		}

		MicroServicesBodyParams bodyParams = params.getBodyParams();
		//HttpEntity<String> request = new HttpEntity<String>("{\"user_name\" : \"KTAUNK\", \"password\":\"ocr123\"}", header);
		HttpEntity<MicroServicesBodyParams> request = new HttpEntity<MicroServicesBodyParams>(bodyParams, header);*/
		String sessionToken = null;
		try {
			MicroServicesParams cloneParams = (MicroServicesParams) params.clone();
			cloneParams.setUrl(cloneParams.getAuthUrl());
			ResponseEntity<String> responseEntity = postForObject(cloneParams);
			
			if(responseEntity != null) {
				HttpHeaders headers = responseEntity.getHeaders();
				if(headers.get(AUTHORIZATION) != null && !headers.get(AUTHORIZATION).isEmpty())
					sessionToken = headers.get(AUTHORIZATION).get(0);
				if(params.getHeaders() == null)
					params.setHeaders(new com.school.common.micro.gateway.HttpHeaders());
				params.getHeaders().setSessionToken(sessionToken);
				System.out.println(sessionToken);
				
				System.out.println(responseEntity);
			}
			//System.out.println(responseEntityStr.getBody());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return sessionToken;
	}
	
	@SuppressWarnings("unchecked")
	default ResponseEntity<String> postForObject(MicroServicesParams params) {
		certify();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		if(params.getHeaders() != null && params.getHeaders().getHeader() != null && !params.getHeaders().getHeader().isEmpty()) {
			for(String key : params.getHeaders().getHeader().keySet()) {
				header.add(key, params.getHeaders().getHeader().get(key));
			}
		}
		if(params.getHeaders() != null && params.getHeaders().getSessionToken() != null && !params.getHeaders().getSessionToken().isEmpty())
			header.add(AUTHORIZATION, params.getHeaders().getSessionToken());
		
		MicroEntityModel bodyParams = params.getMicroEntity();
		String bodyParamsStr = writeJsonString(bodyParams);
		HttpEntity<String> request = new HttpEntity<String>(bodyParamsStr, header);
		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.postForEntity(params.getUrl(), request, String.class);
		} catch (RestClientException e) {
			System.out.println(e.getMessage());
			responseEntity = setErrorMessage(e.getMessage());
		}
		return responseEntity;
	}
	
	@SuppressWarnings("unchecked")
	default ResponseEntity<ApiResponse> postForEntity(MicroServicesParams params) {
		certify();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		if(params.getHeaders() != null && params.getHeaders().getHeader() != null && !params.getHeaders().getHeader().isEmpty()) {
			for(String key : params.getHeaders().getHeader().keySet()) {
				header.add(key, params.getHeaders().getHeader().get(key));
			}
		}
		if(params.getHeaders() != null && params.getHeaders().getSessionToken() != null && !params.getHeaders().getSessionToken().isEmpty())
			header.add(AUTHORIZATION, params.getHeaders().getSessionToken());
		
		MicroEntityModel bodyParams = params.getMicroEntity();
		HttpEntity<MicroEntityModel> request = new HttpEntity<MicroEntityModel>(bodyParams, header);
		ResponseEntity<ApiResponse> responseEntity = null;
		try {
			responseEntity = restTemplate.postForEntity(params.getUrl(), request, ApiResponse.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responseEntity = setErrorMessage(e.getMessage());
		}
		return responseEntity;
	}
	

	@SuppressWarnings("unchecked")
	default ResponseEntity<ApiResponse> putForObject(MicroServicesParams params) {
		certify();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		if(params.getHeaders() != null && params.getHeaders().getHeader() != null && !params.getHeaders().getHeader().isEmpty()) {
			for(String key : params.getHeaders().getHeader().keySet()) {
				header.add(key, params.getHeaders().getHeader().get(key));
			}
		}
		if(params.getHeaders() != null && params.getHeaders().getSessionToken() != null && !params.getHeaders().getSessionToken().isEmpty())
			header.add(AUTHORIZATION, params.getHeaders().getSessionToken());
		
		MicroEntityModel bodyParams = params.getMicroEntity();
		String bodyParamsStr = writeJsonString(bodyParams);
		HttpEntity<String> request = new HttpEntity<String>(bodyParamsStr, header);
		ResponseEntity<ApiResponse> responseEntity = null;
		try {
			restTemplate.put(params.getUrl(), bodyParams, request);
			ApiResponse apiResponse = new ApiResponse(true, SUCCESS_MSG, null);
			responseEntity = new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (RestClientException e) {
			System.out.println(e.getMessage());
			responseEntity = setErrorMessage(e.getMessage());
		}
		return responseEntity;
	}
	
	@SuppressWarnings("unchecked")
	default ResponseEntity<ApiResponse> putForEntity(MicroServicesParams params) {
		certify();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		if(params.getHeaders() != null && params.getHeaders().getHeader() != null && !params.getHeaders().getHeader().isEmpty()) {
			for(String key : params.getHeaders().getHeader().keySet()) {
				header.add(key, params.getHeaders().getHeader().get(key));
			}
		}
		if(params.getHeaders() != null && params.getHeaders().getSessionToken() != null && !params.getHeaders().getSessionToken().isEmpty())
			header.add(AUTHORIZATION, params.getHeaders().getSessionToken());
		
		MicroEntityModel bodyParams = params.getMicroEntity();
		HttpEntity<MicroEntityModel> request = new HttpEntity<MicroEntityModel>(bodyParams, header);
		ResponseEntity<ApiResponse> responseEntity = null;
		try {
			restTemplate.put(params.getUrl(), request);
			ApiResponse apiResponse = new ApiResponse(true, SUCCESS_MSG, null);
			responseEntity = new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responseEntity = setErrorMessage(e.getMessage());
		}
		return responseEntity;
	}
	
	
	@SuppressWarnings("unchecked")
	default ResponseEntity<ApiResponse> getForEntity(MicroServicesParams params) {
		certify();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		if(params.getHeaders() != null && params.getHeaders().getHeader() != null && !params.getHeaders().getHeader().isEmpty()) {
			for(String key : params.getHeaders().getHeader().keySet()) {
				header.add(key, params.getHeaders().getHeader().get(key));
			}
		}
		if(params.getHeaders() != null && params.getHeaders().getSessionToken() != null && !params.getHeaders().getSessionToken().isEmpty())
			header.add(AUTHORIZATION, params.getHeaders().getSessionToken());
		
		MicroEntityModel bodyParams = params.getMicroEntity();
		HttpEntity<MicroEntityModel> request = new HttpEntity<MicroEntityModel>(bodyParams, header);
		ResponseEntity<ApiResponse> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(params.getUrl(), HttpMethod.GET, request, ApiResponse.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responseEntity = setErrorMessage(e.getMessage());
		}
		System.out.println(responseEntity);
		return responseEntity;
	}
	

	
	@SuppressWarnings("unchecked")
	default ResponseEntity<ApiResponse> getForObject(MicroServicesParams params) {
		certify();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		if(params.getHeaders() != null && params.getHeaders().getHeader() != null && !params.getHeaders().getHeader().isEmpty()) {
			for(String key : params.getHeaders().getHeader().keySet()) {
				header.add(key, params.getHeaders().getHeader().get(key));
			}
		}
		if(params.getHeaders() != null && params.getHeaders().getSessionToken() != null && !params.getHeaders().getSessionToken().isEmpty())
			header.add(AUTHORIZATION, params.getHeaders().getSessionToken());
		
		MicroEntityModel bodyParams = params.getMicroEntity();
		String bodyParamsStr = writeJsonString(bodyParams);
		HttpEntity<String> request = new HttpEntity<String>(bodyParamsStr, header);
		ResponseEntity<ApiResponse> responseEntity = null;
		try {
			ResponseEntity<String> responseEntityStr = restTemplate.exchange(params.getUrl(), HttpMethod.GET, request, String.class);
			
			JSONParser parser = new JSONParser(); 
			JSONObject json = (JSONObject) parser.parse(responseEntityStr.getBody());
			String dataStr = (json.get("data") != null) ? json.get("data").toString() : "";
			ApiResponse apiResponse = new ApiResponse((Boolean)json.get("success"), (String)json.get("message"), dataStr);
			responseEntity = new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responseEntity = setErrorMessage(e.getMessage());
		}
		return responseEntity;
	}
	
	
	public static void main(String[] args) {
		certify();
		MicroServicesBodyParams bodyParams = new MicroServicesBodyParams();
		bodyParams.setUsername("KTAUNK");
		bodyParams.setPassword("ocr123");
		bodyParams.setUser_name("KTAUNK");

		HttpHeaders header = new HttpHeaders();
		header.add("Application-Id", "0c8eef04-7762-449a-a5c1-90078ecb1856");
		//HttpEntity<String> request = new HttpEntity<String>("{\"user_name\" : \"KTAUNK\", \"password\":\"ocr123\"}", header);
		HttpEntity<MicroServicesBodyParams> request = new HttpEntity<MicroServicesBodyParams>(bodyParams, header);
		ResponseEntity<String> responseEntityStr = restTemplate.postForEntity( "https://18.195.197.121:8080//api/access/onguard/openaccess/authentication?version=1.0", 
				request, 
				String.class);
		HttpHeaders headers = responseEntityStr.getHeaders();
		System.out.println(headers.get("Server"));
		System.out.println(responseEntityStr.getBody());

	}

	
	@SuppressWarnings("unused")
	public static void certify() {
		TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}
			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
		}
		};

		try {
			SSLContext ctx = SSLContext.getInstance("SSL");
			ctx.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());

			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	default ResponseEntity setErrorMessage(String errorMsg) {
		ResponseEntity responseEntity = null;
		if(errorMsg != null && !errorMsg.isEmpty()) {
			ApiResponse apiResponse = null;
			if(errorMsg.endsWith("null")) {
				String errorCode = errorMsg.split(" ")[0];
				if("409".equals(errorCode)) {
					apiResponse = new ApiResponse(false, "Data already exist in system...!!!", null);
					responseEntity = new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CONFLICT);
				} else if("401".equals(errorCode)) {
					apiResponse = new ApiResponse(false, "Unauthorized Error...!!!", null);
					responseEntity = new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.UNAUTHORIZED);
				} else if("504".equals(errorCode)) {
					apiResponse = new ApiResponse(false, "Gateway Timeout Error...!!!", null);
					responseEntity = new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.GATEWAY_TIMEOUT);
				} else if("400".equals(errorCode)) {
					apiResponse = new ApiResponse(false, "Bad Request Error...!!!", null);
					responseEntity = new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
				} else if("503".equals(errorCode)) {
					apiResponse = new ApiResponse(false, "Service Unavailable Error...!!!", null);
					responseEntity = new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.SERVICE_UNAVAILABLE);
				} else if("405".equals(errorCode)) {
					apiResponse = new ApiResponse(false, "Method Not Allowed Error...!!!", null);
					responseEntity = new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.METHOD_NOT_ALLOWED);
				} 
			}
		}
		return responseEntity;
	}
	
	public default String writeJsonString(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonOutput = "";
		try {
			//mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (Exception e) {
			return jsonOutput;
		}
		return jsonOutput;
	}

	/*public default LenelResponse readJsonString(String json) {
		LOG.info("VMACS::---readJsonString(): method of class LenelACSGateway started::");
		ObjectMapper mapper = new ObjectMapper();
		LenelResponse lenelResponse = null;
		try {
			mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
			lenelResponse = mapper.readValue(json, LenelResponse.class);
		} catch (Exception e) {
			LOG.error("Exception in class LenelACSGateway of readJsonString():", e);
			return new LenelResponse();
		}
		
		return lenelResponse;
	}*/
}
