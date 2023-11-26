package com.bb.fileUp.service.handler;

import com.bb.fileUp.FileUploadConstants;
import com.bb.fileUp.entity.FileInfo;
import com.bb.fileUp.entity.UploadInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;

@Service(FileUploadConstants.S3)
@Slf4j
public class SaveToS3 extends StreamHandlerBase {
    @Override
    public void newFile(UploadInfo uploadInfo, FileInfo f) {
        log.info("S3 new " + f);
    }

    @Override
    public void data(UploadInfo uploadInfo, FileInfo f, BufferedInputStream is) throws Exception {
        log.info("S3 data " + f);
    }

    @Override
    public void data(UploadInfo uploadInfo, FileInfo f, byte[] buffer, int read) {

    }

    @Override
    public void endOfFile(UploadInfo uploadInfo, FileInfo f) {
        log.info("S3 endOfFile " + f);
    }
}
