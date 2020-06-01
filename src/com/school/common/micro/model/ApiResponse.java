package com.school.common.micro.model;

/**
 * Created by Monu Kumar on 25/01/2020.
 */
public class ApiResponse {
    private Boolean success;
    private String message;
    private Object data;

    public ApiResponse() {
    }
    
    
    public ApiResponse(Boolean success, String message, Object data) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApiResponse [success=" + success + ", message=" + message + ", data=" + data + "]";
	}
    
	
    
}
