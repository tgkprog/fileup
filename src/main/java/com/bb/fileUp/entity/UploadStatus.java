package com.bb.fileUp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;


@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true)
@Data
public class UploadStatus {
    private int httpStatus = 0;
    private String message = "";

    private int errorCode;
    private String errorMessage;
}
