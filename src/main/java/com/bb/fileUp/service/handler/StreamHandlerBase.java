package com.bb.fileUp.service.handler;

import com.bb.fileUp.entity.FileInfo;
import com.bb.fileUp.entity.UploadInfo;

import java.io.BufferedInputStream;

import static com.bb.fileUp.FileUploadConstants.BUFFER_LEN;

//TODO inter
public abstract class StreamHandlerBase implements FileHandler {

    @Override
    public void newFile(UploadInfo uploadInfo, FileInfo f){

    }
    @Override
    public void data(UploadInfo uploadInfo, FileInfo f, BufferedInputStream is) throws Exception {
        byte[] buf = new byte[BUFFER_LEN];
        int tries = 0;
        int read = 0;
        while (tries < 3) {
            while ((read = is.read(buf, 0, BUFFER_LEN)) != -1) {
                data(, f, buf, read);
            }
            tries++;
        }

    }


}
