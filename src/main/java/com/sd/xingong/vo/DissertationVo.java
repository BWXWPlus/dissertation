package com.sd.xingong.vo;

/**
 * 封装论文信息的实体类....好像没必要封装，，算了
 */
public class DissertationVo {
    private String studentId; //学生学号
    private String title; //论文题目
    private String language; //论文语言
    private String researchDirection;//论文研究方向
    private String thesisKeyword;//论文关键字
    private String thesisType;  //论文类型
    private String file; //论文文件1
    private String file2; //论文文件2
    private String file3; //论文文件3
    private Byte natureTopicSelection; //选题性质 1理论研究 2应用研究 3技术开发 4工程设计
    private Byte sourceTopicSelection; //选题来源 1指定选题 2自拟选题

}
