package com.zzr.web.controller;

import com.zzr.util.common.JsonResult;
import com.zzr.web.util.HttpUtils;
import com.zzr.web.util.PathFormat;
import com.zzr.web.util.SystemConstant;
import com.zzr.web.util.SystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sjgtw-zzr on 2018/3/8.
 */
@Slf4j
@Controller
@RequestMapping(value = "/")
public class FileController {

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String fileName) throws IOException {
        File file=new File("C:/Users/sjgtw-zzr/Desktop/zk/zzr.xlsx");
        String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public JsonResult bannerImgUpload(MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()){
            return new JsonResult(false,"文件不能为空");
        }
        String bannerPath = PathFormat.parseSiteId(SystemConstant.HEAD_IMG_PATH);
        String realPath = HttpUtils.getRealPath(request, bannerPath);

        SystemUtils.isNotExistCreate(realPath);

        String timeFileName = SystemUtils.timeFileName(file.getOriginalFilename());
        try {
            file.transferTo(new File(realPath + "/" + timeFileName));
        } catch (IOException e) {
            log.error("Banner图片添加失败，错误：{}",e.getMessage());
            return new JsonResult(false,"图片上传失败");
        }
        Map map = new HashMap();
        map.put("filePath",bannerPath + "/" + timeFileName);
        map.put("contentPath", HttpUtils.getBasePath(request));
        return new JsonResult(true,"上传成功",map);
    }

}
