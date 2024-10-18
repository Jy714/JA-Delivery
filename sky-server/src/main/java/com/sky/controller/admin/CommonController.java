package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {
    /**
     * 文件上传
     * @param file
     * @return
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传: {}",file);
        try{
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件的后缀 xxxx.png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName  = UUID.randomUUID().toString() + extension;
            //文件请求路径
            file.transferTo(new File("C:\\Users\\EndUser\\Desktop\\Computing\\JA Food\\DishImage\\ " + objectName));
            return Result.success("C:\\Users\\EndUser\\Desktop\\Computing\\JA Food\\DishImage\\ " + objectName);
        }catch (IOException e){
            log.error("文件上传失败: {}", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

}
