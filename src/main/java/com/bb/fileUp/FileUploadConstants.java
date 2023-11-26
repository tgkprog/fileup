package com.bb.fileUp;


public class FileUploadConstants {

    public static final int ERROR_NOT_A_MULTIPART_REQUEST = 1051;

    public static enum HandlerTypes{
        BOX, LOCAL, S3 ;


    }

    public static final String BOX = "BOX";//HandlerTypes.BOX.name();
    public static final String LOCAL = "LOCAL";//HandlerTypes.LOCAL.name();
    public static final String S3 = "S3";//HandlerTypes.S3.name();
    public static final String API = "API";

    public static final int BUFFER_LEN = 8192;

    public void main (String []a){
        System.out.println(v.FileUploadConstants.HandlerTypes.S3.name());
        ;
    }
}
