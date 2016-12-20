package com.example.mouse.myneu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lyp on 2016/12/10.
 */

public class PositionStage implements Serializable {

    private String parentStageName;
    private String stageName;
    private String stageId;
    private boolean stageProcess;
    private List<PositionStageChild> childStageResList;

    public PositionStage(String parentStageName) {
        this.parentStageName = parentStageName;
    }

    public PositionStage(String parentStageName, String stageName, String stageId, boolean stageProcess, List<PositionStageChild> childStageResList) {
        this.parentStageName = parentStageName;
        this.stageName = stageName;
        this.stageId = stageId;
        this.stageProcess = stageProcess;
        this.childStageResList = childStageResList;
    }

    public PositionStage(String parentStageName, String stageName, String stageId, boolean stageProcess) {
        this.parentStageName = parentStageName;
        this.stageName = stageName;
        this.stageId = stageId;
        this.stageProcess = stageProcess;
    }

    public String getParentStageName() {
        return parentStageName;
    }

    public void setParentStageName(String parentStageName) {
        this.parentStageName = parentStageName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public boolean isStageProcess() {
        return stageProcess;
    }

    public void setStageProcess(boolean stageProcess) {
        this.stageProcess = stageProcess;
    }

    public List<PositionStageChild> getChildStageResList() {
        return childStageResList;
    }

    public void setChildStageResList(List<PositionStageChild> childStageResList) {
        this.childStageResList = childStageResList;
    }
}
