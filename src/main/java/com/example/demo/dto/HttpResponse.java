package com.example.demo.dto;

public class HttpResponse <T>{

    public enum HttpResponseMessage{
        SUCCESS("success"), FAIL("fail") , NO_RESOURCE("There is no resource with passed id"), MULTIPLE_VALUE("Object with this value already exists");

        String message;

        HttpResponseMessage(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
        }
    }

    private String message;
    private T data;

    public HttpResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
