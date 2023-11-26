package com.bb.fileUp.service.handler;

import com.bb.fileUp.entity.FileInfo;
import com.bb.fileUp.entity.UploadInfo;

import java.io.BufferedInputStream;
public interface FileHandler {

    public void newFile(UploadInfo uploadInfo, FileInfo f);

    public void data(UploadInfo uploadInfo, FileInfo f, BufferedInputStream is) throws Exception;

    public void data(UploadInfo uploadInfo, FileInfo f, byte[]buffer, int read);

    public void endOfFile(UploadInfo uploadInfo, FileInfo f);


}
