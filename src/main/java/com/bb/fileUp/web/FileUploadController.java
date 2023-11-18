package com.bb.fileUp.web;

import com.bb.fileUp.entity.UploadStatus;
import com.bb.fileUp.service.StreamHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import static com.bb.fileUp.FileUploadConstants.ERROR_NOT_A_MULTIPART_REQUEST;

@RestController
@RequestMapping(value = "/file")
@Slf4j
public class FileUploadController {
    @Autowired
    StreamHelper streamHelper;

    @RequestMapping(value = "up", method = RequestMethod.POST)
    public ResponseEntity<UploadStatus> up1(HttpServletRequest request, HttpServletResponse response) {
        UploadStatus res = new UploadStatus();
        String codeLocation = "Initializing upload";
        AtomicLong len2 = new AtomicLong();
        try {
            boolean isMultipart = JakartaServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                codeLocation = "saving stream";
                // Create a new file upload handler
                JakartaServletFileUpload fileUpload = new JakartaServletFileUpload();

                fileUpload.getItemIterator(request).forEachRemaining(item -> {
                    final String name = item.getFieldName();
                    final BufferedInputStream is = new BufferedInputStream(item.getInputStream());
                    if (item.isFormField()) {
                        String val = streamHelper.asString(is);
                        System.out.println("Field " + name + " value " + val + ".");
                    } else {
                        System.out.println("File field " + name + " with file name " + item.getName() + " detected.");
                        final String fileName = item.getName();
                        final String fileMimeType = item.getContentType();
                        log.info("File name " + fileName + ", mime "+ fileMimeType);
                        File foutDir = new File("up2Files");
                        File tmp = new File(fileName);
                        int till = tmp.getName().lastIndexOf('.');
                        if (till < 1) till = tmp.getName().length();
                        final String prename = tmp.getName().substring(0, till);
                        String postName = null;
                        if (till < tmp.getName().length()) {
                            postName = "." + tmp.getName().substring(till + 1);
                        } else {
                            postName = ".";
                        }
                        while (postName.length() < 4) {
                            postName += "x";
                        }
                        File fOut = File.createTempFile(prename, postName, foutDir);
                        final OutputStream fos = new BufferedOutputStream(new FileOutputStream(fOut));

                        int bufLen = 8192;
                        byte[] buf = new byte[bufLen];
                        int tries = 0;
                        int read = 0;
                        while (tries < 1) {
                            while ((read = is.read(buf, 0, bufLen)) != -1) {
                                fos.write(buf, 0, read);
                                len2.addAndGet(read);
                            }
                            try {
                                Thread.sleep(1300);
                            } catch (Exception e) {
                                //ignore
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
                    }
                });
                res.setMessage("Read " + len2 + " bytes at " + new Date());
            } else {
                res.mainSet("No files to process", "Not a multipart message").setErrorCode(ERROR_NOT_A_MULTIPART_REQUEST);
            }

        } catch (Throwable e) {
            res.mainSet("Error", "Error at " + codeLocation + ", " + e);
        }

        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "time", method = RequestMethod.GET)
    public ResponseEntity<UploadStatus> time() {
        UploadStatus res = new UploadStatus().setMessage("Time (v002) :" + new Date());
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "f1", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String form1(HttpServletRequest request, HttpServletResponse response) {
        ///response.setContentType("text/html");
        StringBuilder sb = new StringBuilder();
        String file ="/html/i.html";
        try {
            InputStream in2 = getClass().getResourceAsStream(file);
            if(in2 == null){
                file ="/home/t/code/github/pvt/fileUp/FileUp/src/main/resources/html/i.html";
                in2 = new FileInputStream(file) ;
            }
            if(in2 == null) {
                return "Not found file at " + new Date();
            }
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                    in2));
            String st = null;
            while (true)
            {
                if ((st = br.readLine()) == null) break;
                sb.append(st).append("\r\n");
            }
            sb.append("\n<br>Time (v002) :" + new Date());
            return sb.toString();
        } catch (Exception e) {
           log.warn("Error reading "+ file + " from server ", e);
        }
        return "Could not read file "+ file + " at "+ new Date();
    }
}
