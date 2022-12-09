package com.hackathon.server.service.util;

import com.hackathon.server.util.LocalFileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UtilServiceImpl implements UtilService {

    private final LocalFileManager localFileManager;

    @Override
    @Transactional
    public  String saveFileToSystem(byte[] data, String localPath, String awsPath) {
//        if (Variables.springEnv.equals("dev") || Variables.springEnv.equals("docker-local")) {
            return localFileManager.saveFileToSystem(data, localPath);
//        }
//        else {
//            return awsBucketManager.saveFileToSystem(data, awsPath);
//        }
    }

    @Override
    public byte[] downloadFileFromSystem(String fileIdentifier, String localFilePath, String awsFilePath) {
//        if (Variables.springEnv.equals("dev") || Variables.springEnv.equals("docker-local")) {
            return localFileManager.downloadFile(fileIdentifier, localFilePath);
//        } else {
//            return awsBucketManager.downloadFile(fileIdentifier, awsFilePath);
//        }
    }

}
