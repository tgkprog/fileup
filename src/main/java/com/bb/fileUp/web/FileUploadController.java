package com.bb.fileUp.web;

import com.bb.fileUp.entity.UploadStatus;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/file")
@Slf4j
public class FileUploadController {
    @RequestMapping(value = "up", method = RequestMethod.POST)
    public  ResponseEntity<UploadStatus> up1() {
        UploadStatus res= new UploadStatus();

        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "time", method = RequestMethod.GET)
    public  ResponseEntity<UploadStatus> time() {
        UploadStatus res= new UploadStatus().setMessage("Time (v002) :" + new Date());
        return ResponseEntity.ok(res);
    }
}
