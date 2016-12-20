package com.example.mouse.myneu.bean;

/**
 * Created by mouse on 2016/12/14.
 */

import java.io.Serializable;

public class Position implements Serializable {


    private int postId;  //岗位id
    private String postName;// 岗位名称（Android软件工程师）
    private int directId;//  分类id（不用）
    private String postDesc;// 岗位描述 (不用)
    private String postUrl;//   岗位图片
    private int courseNums;//  岗位下课程数量
    private int courseHours;//  课程时长（单位分钟）
    private int videoNums;//   岗位下微课数量
    private int testNums;//   微课程总共包含的测试题数量
    private String studyDays;//   学习时间（不用）
    private int personNums;//   当正在学习人数
    private boolean isStudy;//     （0和1，用户登录后，如果学习过部分显示1；没有则为0）（不用）

    public int getVideoNums() {
        return videoNums;
    }

    public void setVideoNums(int videoNums) {
        this.videoNums = videoNums;
    }

    public boolean isStudy() {
        return isStudy;
    }

    public void setStudy(boolean study) {
        isStudy = study;
    }

    public int isStudyByInt() {
        if (this.isStudy) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setStudy(int study) {
        if (study == 0) {
            this.isStudy = false;
        } else {
            this.isStudy = true;
        }
    }

    public int getCourseNums() {
        return courseNums;
    }

    public void setCourseNums(int courseNums) {
        this.courseNums = courseNums;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public int getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(int courseHours) {
        this.courseHours = courseHours;
    }

    public int getDirectId() {
        return directId;
    }

    public void setDirectId(int directId) {
        this.directId = directId;
    }

    public int getPersonNums() {
        return personNums;
    }

    public void setPersonNums(int personNums) {
        this.personNums = personNums;
    }

    public String getStudyDays() {
        return studyDays;
    }

    public void setStudyDays(String studyDays) {
        this.studyDays = studyDays;
    }

    public int getTestNums() {
        return testNums;
    }

    public void setTestNums(int testNums) {
        this.testNums = testNums;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
