package com.sd.xingong.controller;

import com.sd.xingong.vo.FileNameVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
public class FileController {

    @PostMapping("/upload")
    public String upload(@RequestBody MultipartFile file,@RequestParam("grade") String grade, @RequestParam("specialities") String specialities, @RequestParam("stuClass") String stuClass){
        System.out.println(grade);
        if(grade == ""){
            String []filter = {"bmp", "jpg", "jepg", "gif", "png"};
        }else {
            String []filter = {"doc","pdf","zip","rar","docx"};
        }

        //获取文件名
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("[.]");
        int flat = 0;
        String[] filter={}; //TODO  明天删掉，暂时不报错
        for(String str : filter){
            if(str.equals(split[1])){
                flat = 1;
            }
        }
        if (flat == 0){
            return "文件上传失败";
        }
        //获取当前项目的路径
        String property = System.getProperty("user.dir");
        property = property + "\\src\\main\\resources\\static\\";
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);

        System.out.println(format);
        property  = property + format + "\\";
        String filePath = property + originalFilename;

        File newFile = new File(filePath);
        if(!newFile.isDirectory()){
            newFile.mkdirs();
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
