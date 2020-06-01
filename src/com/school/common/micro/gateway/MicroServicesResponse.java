package com.school.common.micro.gateway;

/**
 * Created by Monu Kumar on 25/01/2020.
 */
public class MicroServicesResponse {
    private Boolean success;
    private String message;
    private Object data;
    private String status;
    private Integer statusCode;

    public MicroServicesResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    

    public MicroServicesResponse(Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the statusCode
	 */
	public Integer getStatusCode() {
		return statusCode;
	}


	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
    
    
}
