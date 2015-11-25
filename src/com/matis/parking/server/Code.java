package com.matis.parking.server;

/**
 * All text are in: http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
 * @author MiguelAngel
 */
public enum Code {
    OK(200, "The request has succeeded"),
    CREATED(201, "The request has been fulfilled and resulted in a new resource being created"),
    NO_CONTENT(204 , "The server has fulfilled the request but does not need to return an entity-body, and might want to return updated metainformation"),
    
    BAD_REQUEST(400,
            "The request could not be understood by the server due to malformed syntax. The client SHOULD NOT repeat the request without modifications.");
    
    
    private int code;
    private String message;

    private Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
