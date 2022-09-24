package com.sd.xingong.controller;

import com.sd.xingong.mapper.StudentMapper;
import com.sd.xingong.mapper.TeacherMapper;
import com.sd.xingong.vo.FileNameVo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     *
     * @param file 上传的文件
     * @param Id 学生或老师的Id（学生用学号，老师用Id）
     * @param grade 学生的年级
     * @param specialities 学生的部门
     * @param stuClass 学生的班级
     * @param type //对应上传文件存储的数据库位置
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestBody MultipartFile file,@RequestParam("Id") String Id, @RequestParam("grade") String grade, @RequestParam("specialities") String specialities, @RequestParam("stuClass") String stuClass, @RequestParam("type") String type){
        //支持上传的文件格式
        String []filter = {"bmp", "jpg", "jepg", "gif", "png","doc","pdf","zip","rar","docx"};
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("[.]");
        int flat = 0;
        //判断是不是当前支持的文件格式
        for(String str : filter){
            if(str.equals(split[1])){
                flat = 1;
                break;
            }
        }
        if (flat == 0){
            return "文件上传失败";
        }
        //获取当前项目的路径
        String property = System.getProperty("user.dir");

        //拼接生成文件存放的位置
        property = property + "\\src\\main\\resources\\static\\";

    /*  以文件上传时间命名的格式
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);

        System.out.println(format);
        */

        String path = "";
        if(grade != "" && specialities != "" && stuClass !=""){
            path = grade + "级\\" + specialities + "专业\\" + stuClass + "班\\";
            flat = 2;
        }else {
            path = "教师\\";
            flat = 3;
        }
        property  = property + path;
        String filePath = property + originalFilename;

        //上传文件
        File newFile = new File(filePath);
        //如果目录不存在则生成新目录
        if(!newFile.isDirectory()){
            newFile.mkdirs();
        }
        try {
            file.transferTo(newFile);
            String str = "\\src\\main\\resources\\static\\"+ path+originalFilename;

            if(flat == 2){
                //将文件名称写入学生数据库
                System.out.println(str);
                studentMapper.uploadFiles(Id,str,type);
            }else if(flat == 3){
                //将文件名称写入教师数据库
                teacherMapper.uploadFiles(Id,str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
