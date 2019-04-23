package com.liyv.taotao.service.impl;

import com.liyv.taotao.service.UploadService;
import com.liyv.taotao.utils.IDUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 上传图片
 */
@Service
public class UploadServiceImpl implements UploadService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String uploadPicture(MultipartFile file, String path) {
        //文件名称
        String originalFilename = file.getOriginalFilename();
        //新的文件名称
        String newName = IDUtil.genImageName();
        String dateDir = new DateTime().toString("/yyyy-MM/");
        String imgPath = "";
        int result = -1;
        try {
            newName = newName + originalFilename.substring(originalFilename.lastIndexOf("."));
            String filePath = path + dateDir + newName;
            result = upload(file.getInputStream(), filePath);
        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
        if (result > 0) {
            imgPath = dateDir + newName;
        }else {
            imgPath = "";
        }
        return imgPath;
    }

    public int upload(InputStream inputStream, String filePath) {
        createFile(filePath);
        InputStream is = new BufferedInputStream(inputStream);
        try {
            OutputStream os = new BufferedOutputStream(new FileOutputStream(filePath));
            return copyStream(is, os);
        } catch (Exception e) {
            return -2;
        }
    }

    private File createFile(String filePath) {
        File file;
        try {
            file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                FileUtils.forceMkdir(parentDir);
            }
        } catch (Exception e) {
            logger.error("create file failure", e);
            throw new RuntimeException(e);
        }
        return file;
    }

    private int copyStream(InputStream inputStream, OutputStream outputStream) {
        try {
            int length;
            byte[] buffer = new byte[4 * 1024];
            while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            return 1;
        } catch (Exception e) {
            logger.error("copy stream failure", e);
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                logger.error("close stream failure", e);
            }
        }
    }

}
