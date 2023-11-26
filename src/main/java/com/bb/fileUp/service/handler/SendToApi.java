package com.bb.fileUp.service.handler;

import com.bb.fileUp.FileUploadConstants;
import com.bb.fileUp.entity.FileInfo;
import com.bb.fileUp.entity.UploadInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;

@Service(FileUploadConstants.API)
@Slf4j
public class SendToApi implements FileHandler {
    @Override
    public void newFile(UploadInfo uploadInfo, FileInfo f) {
      log.info("SendToApi new " + f);
    }

    @Override
    public void data(UploadInfo uploadInfo, FileInfo f, BufferedInputStream is) throws Exception {
        log.info("SendToApi data " + f);
    }

    @Override
    public void endOfFile(UploadInfo uploadInfo, FileInfo f) {
        log.info("SendToApi endOfFile " + f);
    }

    @Override
    public void data(UploadInfo uploadInfo, FileInfo f, byte[] buffer, int read) {

    }
}
