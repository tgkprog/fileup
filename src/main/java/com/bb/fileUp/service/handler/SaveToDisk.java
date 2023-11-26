package com.bb.fileUp.service.handler;

import com.bb.fileUp.FileUploadConstants;
import com.bb.fileUp.entity.FileInfo;
import com.bb.fileUp.entity.UploadInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;

@Service(FileUploadConstants.LOCAL)
@Slf4j
public class SaveToDisk extends StreamHandlerBase {
    @Override
    public void newFile(UploadInfo uploadInfo, FileInfo f) {
        log.info("Local new " + f);
    }

    @Override
    public void data(UploadInfo uploadInfo, FileInfo f, BufferedInputStream is) throws Exception {
        /*
        log.info("Local data " + f);
        System.out.println("File field " + name + " with file name " + item.getName() + " detected.");
        final String fileName = item.getName();
        final String fileMimeType = item.getContentType();
        log.info("File name " + fileName + ", mime "+ fileMimeType);
        File foutDir = new File("up2Files");
        File tmp = new File(fileName);
        int till = tmp.getName().lastIndexOf('.');
        if (till < 1) till = tmp.getName().length();
        String preName = tmp.getName().substring(0, till);
        String postName = null;
        if (till < tmp.getName().length()) {
            postName = "." + tmp.getName().substring(till + 1);
        } else {
            postName = ".x";
        }
        while (preName.length() < 3) {
            preName += "x";
        }
        if(preName.charAt(0) == '.')preName = 'f' + preName;
        File fOut = File.createTempFile(preName, postName, foutDir);
        final OutputStream fos = new BufferedOutputStream(new FileOutputStream(fOut));

        int bufLen = 8192;
        byte[] buf = new byte[bufLen];
        int tries = 0;
        int read = 0;
        //TODO handlers
        while (tries < 1) {
            while ((read = is.read(buf, 0, bufLen)) != -1) {
                fos.write(buf, 0, read);
                len2.addAndGet(read);
            }

            tries++;
        }
        try {
            fos.close();
            is.close();
        } catch (Exception e3) {
            log.warn("Error closing stream for file " + fileName + ", tries " + tries + ", " + e3, e3);
            throw e3;
        }
        */
    }

    @Override
    public void endOfFile(UploadInfo uploadInfo, FileInfo f) {
        log.info("Local endOfFile " + f);
    }

    @Override
    public void data(UploadInfo uploadInfo, FileInfo f, byte[] buffer, int read) {

    }
}
