package com.njupt.garbage.controller;

import com.njupt.garbage.common.utils.JsonUtils;
import com.njupt.garbage.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class PicController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile) throws IOException {
        Map resultMap = pictureService.pictureUpload(uploadFile);
        String json = JsonUtils.objectToJson(resultMap);
        return json;
    }
}
