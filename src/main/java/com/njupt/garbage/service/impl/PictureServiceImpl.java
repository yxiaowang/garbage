package com.njupt.garbage.service.impl;

import com.njupt.garbage.common.utils.IDUtils;
import com.njupt.garbage.service.PictureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    @Value("${PIC_STORAGE}")
    private String PIC_STORAGE;

    @Value("${PIC_BASE_URL}")
    private String PIC_BASE_URL;

    //图片上传
    @Override
    public Map pictureUpload(MultipartFile uploadFile) throws IOException {
        //旧文件名
        String oldName = uploadFile.getOriginalFilename();
        //使用id生成工具类创建新文件名
        String newName = IDUtils.genImageName() + oldName.substring(oldName.lastIndexOf("."));
        //存储路径,物理路径
        String pic_path = PIC_STORAGE;
        //将图片写入图片文件
        File newFile = new File(pic_path + newName);
        uploadFile.transferTo(newFile);
        Map resultMap = new HashMap();
        if (newFile != null) {
            resultMap.put("error", 0);
            resultMap.put("url", PIC_BASE_URL + newName);
            return resultMap;
        } else {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传失败");
            return resultMap;
        }
    }
}
