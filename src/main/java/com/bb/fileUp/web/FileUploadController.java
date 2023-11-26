package com.bb.fileUp.web;

import com.bb.fileUp.FileUploadConstants;
import com.bb.fileUp.conf.SpringConfig;
import com.bb.fileUp.entity.ClientConf;
import com.bb.fileUp.entity.FileInfo;
import com.bb.fileUp.entity.UploadInfo;
import com.bb.fileUp.entity.UploadStatus;
import com.bb.fileUp.service.StreamHelper;
import com.bb.fileUp.service.handler.FileHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload2.core.FileItemInput;
import org.apache.commons.fileupload2.core.FileItemInputIterator;
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

    @Autowired
    SpringConfig springConfig;
    @Autowired
    ClientConf clientConf;

    @RequestMapping(value = "up", method = RequestMethod.POST)
    public ResponseEntity<UploadStatus> up1(HttpServletRequest request, HttpServletResponse response) {
        UploadStatus res = new UploadStatus();
        String codeLocation = "Initializing upload";
        AtomicLong len2 = new AtomicLong();
        int streamCnt = 0;
        UploadInfo uploadInfo = new UploadInfo();
        try {
            FileInfo fileInfo = new FileInfo();
            boolean isMultipart = JakartaServletFileUpload.isMultipartContent(request);
            if (isMultipart) {

                codeLocation = "saving stream";
                // Create a new file upload handler
                JakartaServletFileUpload fileUpload = new JakartaServletFileUpload();
                String clientId = request.getHeader("clientId");
                FileItemInputIterator items = fileUpload.getItemIterator(request);
                FileItemInput item = null;
                for (item = items.next(); items.hasNext(); item = items.next()) {
                    final String name = item.getFieldName();
                    final BufferedInputStream is = new BufferedInputStream(item.getInputStream());
                    if (item.isFormField()) {
                        String val = streamHelper.asString(is);
                        System.out.println("Field " + name + " value " + val + ".");
                        if (clientId == null && name.equals("clientId")) {
                            clientId = val;
                        }
                        fileInfo.getVars().put(name, val);
                    } else {
                        ClientConf.Client  client = clientConf.getClients().get(clientId);//TODO null checks
                        FileHandler  handler =  springConfig.getFileHandlers().get(client.getHandler());
                        if(handler == null){
                            log.warn("handler not found for type " + client + ", defaulting to local file ");
                            handler =   springConfig.getFileHandlers().get(client.getHandler(FileUploadConstants.LOCAL;));
                        }

                        log.info("client ", client +  ", handler type " +  client.getHandler()  + ", handler " + handler);
                        handler.newFile(uploadInfo, fileInfo);
                        fileInfo.setFileName(name);
                        try {
                            handler.data(uploadInfo, fileInfo, is);
                        } catch (Exception eIs) {
                            log.warn("Error handler.data " + eIs.getMessage(), eIs);
                        }
                        handler.endOfFile(uploadInfo, fileInfo);
                        streamCnt++;
                    }
                    try {
                        is.close();
                    } catch (Exception eIs) {
                        log.warn("Error closing stream "+ eIs.getMessage(), eIs);
                    }
                }
                //TODO msg correct
                res.setMessage("Read " + streamCnt + " files, TODO bytes at " + new Date());
            } else {
                res.mainSet("No files to process", "Not a multipart message").setErrorCode(ERROR_NOT_A_MULTIPART_REQUEST);
            }

        } catch (Throwable e) {
            res.mainSet("Error", "Error at " + codeLocation + ", " + e);
            log.warn("Err " + e.getMessage() + "| " + e, e);
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
        String file = "/html/i.html";
        try {
            InputStream in2 = getClass().getResourceAsStream(file);
            if (in2 == null) {
                file = "/home/t/code/github/pvt/fileUp/FileUp/src/main/resources/html/i.html";
                in2 = new FileInputStream(file);
            }
            if (in2 == null) {
                return "Not found file at " + new Date();
            }
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            in2));
            String st = null;
            while (true) {
                if ((st = br.readLine()) == null) break;
                sb.append(st).append("\r\n");
            }
            sb.append("\n<br>Time (v002) :" + new Date());
            return sb.toString();
        } catch (Exception e) {
            log.warn("Error reading " + file + " from server ", e);
        }
        return "Could not read file " + file + " at " + new Date();
    }
}
