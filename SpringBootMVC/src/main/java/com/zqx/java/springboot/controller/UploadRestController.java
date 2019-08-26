package com.zqx.java.springboot.controller;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.domain.upload.ThumbImage;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zqx.java.springboot.utils.DateUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * 上传图片不带缩略图
     * @param file MultipartFile文件
     * @param thumbnailIfDefault 0=不产生缩略图，1=产生缩略图并上传 默认0
     */
    @RequestMapping(value = "/uploads/images", method = RequestMethod.POST)
    public String uploadImages(MultipartFile file, @Min(0) @Max(1) @RequestParam(defaultValue = "0") int thumbnailIfDefault) {
        try {
            InputStream inputStream = file.getInputStream();
            int fileSize = file.getBytes().length;
            String fileExtName = FilenameUtils.getExtension(file.getOriginalFilename());
            Set<MetaData> metaDataSet = createMetaData();
            metaDataSet.add(new MetaData("fileName", file.getOriginalFilename()));
            ThumbImage thumbImage = null;
            if (thumbnailIfDefault == 1) {
                thumbImage = new ThumbImage(150, 150);
            }
            FastImageFile image = new FastImageFile(inputStream, fileSize, fileExtName, metaDataSet, thumbImage);
            String groupName = image.getGroupName();
            StorePath path = client.uploadImage(image);
            System.out.println(path.getFullPath());
            return path.getFullPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/uploads/files")
    public String uploadFiles(@NotNull MultipartFile file) {
        try {
            String fileExtName = FilenameUtils.getExtension(file.getOriginalFilename());
            Set<MetaData> metaDataSet = createMetaData();
            metaDataSet.add(new MetaData("fileName", file.getOriginalFilename()));
            FastFile fastFile = new FastFile(file.getInputStream(), file.getBytes().length, fileExtName, metaDataSet);
            StorePath path = client.uploadFile(fastFile);
            System.out.println(path.getFullPath());
            return path.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/downloads/files")
    public String downloadFiles(String filePath) {
        DownloadByteArray array = new DownloadByteArray();
        byte[] content = client.downloadFile("group1", filePath, array);
        try {
            FileOutputStream fos = new FileOutputStream("D://zhang.txt");
            fos.write(content, 0, content.length);
            return "upload success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload error";
    }

    /**
     * 删除数据文件(路径从数据库中取或其他方式)
     * @param filePath StorePath.getFullPath() = group1/M00/00/00/wKjHil1fm_eAL-obAAh53vCw0rs686.png
     */
    @RequestMapping("/delete")
    public String deleteImage(String filePath) {

        /** 此处获取存储位置信息 */

        try {
            client.deleteFile(filePath);
            return "delete success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "delete error";
    }

    private Set<MetaData> createMetaData() {
        Set<MetaData> metaDataSet = new HashSet<>();
        metaDataSet.add(new MetaData("Author", "zqx")); // 当前用户名：从session或redis等中取
        metaDataSet.add(new MetaData("CreateDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtils.FORMAT_yyyy_MM_dd_HH_mm_ss)))); // 当前创建时间
        return metaDataSet;
    }

}
