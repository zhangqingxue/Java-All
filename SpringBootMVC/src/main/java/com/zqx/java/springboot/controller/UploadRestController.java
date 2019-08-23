package com.zqx.java.springboot.controller;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zqx.java.springboot.utils.DateUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 上传下载专用
 */
@RestController
@Validated
@RequestMapping("/api/v1/common")
public class UploadRestController {

    @Autowired
    private FastFileStorageClient client; // FastDFS集成接口

    @RequestMapping("/upload/headphoto")
    public void uploadHeadPhoto(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            int fileSize = file.getBytes().length;
            String fileExtName = FilenameUtils.getExtension(file.getOriginalFilename());
            Set<MetaData> metaDataSet = createMetaData();
            metaDataSet.add(new MetaData("fileName", file.getOriginalFilename()));
            FastImageFile image = new FastImageFile(inputStream, fileSize, fileExtName, metaDataSet, null);
            String groupName = image.getGroupName();
            StorePath path = client.uploadImage(image);
            System.out.println(path.getFullPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据文件(路径从数据库中取或其他方式)
     * @param filePath StorePath.getFullPath() = group1/M00/00/00/wKjHil1fm_eAL-obAAh53vCw0rs686.png
     */
    @RequestMapping("/delete")
    public void deleteImage(String filePath){
        client.deleteFile(filePath);
    }

    private Set<MetaData> createMetaData() {
        Set<MetaData> metaDataSet = new HashSet<>();
        metaDataSet.add(new MetaData("Author", "zqx")); // 当前用户名：从session或redis等中取
        metaDataSet.add(new MetaData("CreateDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtils.FORMAT_yyyy_MM_dd_HH_mm_ss)))); // 当前创建时间
        return metaDataSet;
    }

}
