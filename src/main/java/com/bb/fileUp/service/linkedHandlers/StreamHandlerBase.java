package com.bb.fileUp.service.linkedHandlers;

import com.bb.fileUp.entity.FileInfo;

import java.io.BufferedInputStream;

import static com.bb.fileUp.FileUploadConstants.BUFFER_LEN;

//TODO inter
public abstract class StreamHandlerBase implements FileHandler {

    @Override
    public void newFile(FileInfo f){

    }
    @Override
    public void data(FileInfo f, BufferedInputStream is) throws Exception {
        byte[] buf = new byte[BUFFER_LEN];
        int tries = 0;
        int read = 0;
        while (tries < 1) {
            while ((read = is.read(buf, 0, BUFFER_LEN)) != -1) {
                data(f, buf, read);
            }
            tries++;
        }

    }


}
