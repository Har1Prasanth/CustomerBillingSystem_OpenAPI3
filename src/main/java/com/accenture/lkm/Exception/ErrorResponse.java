package com.accenture.lkm.Exception;

import lombok.Data;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@Data
public class ErrorResponse {

    private String message;
    private int status;
    private long timeStamp;
    // private List<String> details;

    public ErrorResponse(String message, int status, long timeStamp) {
        this.message = message;
        this.status = status;
        this.timeStamp = timeStamp;
        // this.details = details;
    }

    public ErrorResponse() {
    }
}