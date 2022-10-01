package com.sd.xingong.controller;

import com.sd.xingong.mapper.StudentMapper;
import com.sd.xingong.mapper.TeacherMapper;
import com.sd.xingong.service.StudentService;
import com.sd.xingong.service.TeacherService;
import com.sd.xingong.vo.FileNameVo;
import com.sd.xingong.vo.PwdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
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
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

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

        System.out.println(file+"=" + Id +"=" +grade +"=" +specialities + "=" +stuClass+"=" +type)  ;
        //支持上传的文件格式
        String []filter = {"bmp", "jpg", "jpeg", "gif", "png","doc","pdf","zip","rar","docx","webp"};
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("[.]");
        int flat = 0;
        split[0] = split[0].trim();
        if(split[0].equals("")){
            return "文件命名有误";
        }
        //判断是不是当前支持的文件格式
        for(String str : filter){
         //   System.out.println(str+"===");
            if(str.equals(split[1])){

                flat = 1;
                break;
            }
        }
        if (flat == 0){
            return "文件格式有误";
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
        if(type.equals("100")){
            path = "教师\\" + Id +"\\" + System.currentTimeMillis() + "\\";
            flat = 3;
        }else {
            path = grade + "级\\" + specialities + "专业\\" + stuClass + "班\\" + Id + "\\" + System.currentTimeMillis() + "\\";
            flat = 2;

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
            String str =  path+originalFilename;

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

    @PostMapping("/edit/pwd")
    public String editPassword(@RequestBody PwdVo pwdVo){
        String type = pwdVo.getType();
        String id = pwdVo.getId();
        String newPassword = pwdVo.getNewPassword();
        String  oldPassword = pwdVo.getOldPassword();
        String str = "没有啊,宝";

        System.out.println(oldPassword+"------"+newPassword+"   =====");
        if(type.equals("1")){
            str = teacherService.editPassWord(id,newPassword,oldPassword);
        }
        if(type.equals("2")){
            str = studentService.editPassWord(id,newPassword,oldPassword);
        }
        return str;
    }
}
