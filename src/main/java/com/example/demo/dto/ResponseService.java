/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dto;

/**
 *
 * @author nik
 */
public class ResponseService {

    private boolean status;
    private String message;
    private Object data;

    public ResponseService() {
    }

    public ResponseService(String message) {
        this.status = true;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public void setErrorMessage(String info) {
        message = info;
        status = false;
    }
}
