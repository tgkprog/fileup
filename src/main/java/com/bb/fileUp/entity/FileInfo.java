package com.bb.fileUp.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class FileInfo {
    private String fileName;
    private String clientName;
    private Map<String, Object> vars = new HashMap<>();
    private String errorMessage;//null no error
    private int errorCode;//0 all okay
    private int processCode;//0 ok added, 1 updated, 3 ...
}
