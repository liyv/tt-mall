package com.liyv.taotao.web.backend;

import com.liyv.taotao.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("upload")
public class UploadController {

    @Value("${IMAGE_BASE_URL}")
    String IMAGE_BASE_URL;

    @Autowired
    UploadService service;

    @PostMapping("/picture")
    @ResponseBody
    public Map uploadPicture(MultipartFile uploadFile, HttpServletRequest request) {
        ServletContext servletContext = request.getSession().getServletContext();
        String systemPath = servletContext.getRealPath("/");
        String webPath = "resources/uploadImg";
        String result = service.uploadPicture(uploadFile, systemPath + webPath);
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(result)) {
            map.put("error", 1);
            map.put("message", "文件上传失败");
        } else {
            map.put("error", 0);
            String url = IMAGE_BASE_URL + webPath + result;
            System.out.println(url);
            map.put("url", url);
        }
        return map;
    }
}
