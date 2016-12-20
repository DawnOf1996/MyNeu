package com.example.mouse.myneu.bean;

import java.io.Serializable;

/**
 * Created by lyp on 2016/12/10.
 */

public class PositionStageChild implements Serializable {

    private String resName;
    private String resProcess;
    private String examType;
    private int resId; //课程id
    private int resType;

    public PositionStageChild(String resName, String resProcess, String examType, int resId, int resType) {
        this.resName = resName;
        this.resProcess = resProcess;
        this.examType = examType;
        this.resId = resId;
        this.resType = resType;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResProcess() {
        return resProcess;
    }

    public void setResProcess(String resProcess) {
        this.resProcess = resProcess;
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

    @Override
    public String toString() {
        return "PositionStageChild{" +
                "resName='" + resName + '\'' +
                ", resProcess='" + resProcess + '\'' +
                ", examType='" + examType + '\'' +
                ", resId=" + resId +
                ", resType=" + resType +
                '}';
    }
}
