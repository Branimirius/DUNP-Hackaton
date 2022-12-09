package com.hackathon.server.service.util;

public interface UtilService {
    String saveFileToSystem(byte[] data, String localFilePath, String awsFilePath);
    byte[] downloadFileFromSystem(String fileIdentifier, String localFilePath, String awsFilePath);

}
