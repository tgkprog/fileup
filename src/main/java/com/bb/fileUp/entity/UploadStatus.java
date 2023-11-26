package com.bb.fileUp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true)
@Data
public class UploadStatus {
    private int httpStatus = 0;
    private String message = "";

    private int errorCode;
    private String errorMessage;

    public UploadStatus mainSet(String msg, String errorMessage){
        message = msg;
        this.errorMessage = errorMessage;
        return this;
    }
}
