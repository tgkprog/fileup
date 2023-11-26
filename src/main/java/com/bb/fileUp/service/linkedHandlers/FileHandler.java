package com.bb.fileUp.service.linkedHandlers;

import com.bb.fileUp.entity.FileInfo;

import java.io.BufferedInputStream;
public interface FileHandler {

    public void newFile(FileInfo f);

    public void data(FileInfo f, BufferedInputStream is) throws Exception;

    public void endOfFile(FileInfo f);

    public void data(FileInfo f, byte[]buffer, int read);
}
