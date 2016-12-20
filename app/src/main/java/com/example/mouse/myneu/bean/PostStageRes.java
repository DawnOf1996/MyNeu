package com.example.mouse.myneu.bean;

import java.io.Serializable;

/**
 * Created by MOUSE on 2016/12/16.
 */

public class PostStageRes implements Serializable {
    private  String parentStageName;//学习阶段
    private  String stageId;//  阶段id（无用）
    private  String stageName;// 阶段名称
    private  String resProcess;//  （无用）
    private  String resName;// 课程名称
    private  String examType;//  （无用）
    private  int resId;//  课程id
    private  int resType;//   课程类型（无用）
    private boolean stageProcess;//  （无用）

    public String getParentStageName() {
        return parentStageName;
    }

    public void setParentStageName(String parentStageName) {
        this.parentStageName = parentStageName;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getResProcess() {
        return resProcess;
    }

    public void setResProcess(String resProcess) {
        this.resProcess = resProcess;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getResType() {
        return resType;
    }

    public void setResType(int resType) {
        this.resType = resType;
    }

    public boolean isStageProcess() {
        return stageProcess;
    }

    public void setStageProcess(boolean stageProcess) {
        this.stageProcess = stageProcess;
    }

    @Override
    public String toString() {
        return "PostStageRes{" +
                "parentStageName='" + parentStageName + '\'' +
                ", stageId='" + stageId + '\'' +
                ", stageName='" + stageName + '\'' +
                ", resProcess='" + resProcess + '\'' +
                ", resName='" + resName + '\'' +
                ", examType='" + examType + '\'' +
                ", resId=" + resId +
                ", resType=" + resType +
                ", stageProcess=" + stageProcess +
                '}';
    }
}
