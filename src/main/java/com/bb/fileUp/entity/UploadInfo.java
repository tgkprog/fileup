package com.bb.fileUp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true)
@Data
public class UploadInfo {
    double totalBytesProcessed;
    double totalBytesAllowed;
    int fileCount;

    Date startedAt;

    Date completedAt;

    List<FileInfo> fileInfos;

    String clientId;

    String externalCorrelationId;

    String ourId;

    String boxId;
}
