package com.liyv.taotao.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    public String uploadPicture(MultipartFile file,String filePath);
}
