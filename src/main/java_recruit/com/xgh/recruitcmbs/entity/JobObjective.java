package com.xgh.recruitcmbs.entity;
import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 *
 **/
@SuppressWarnings("serial")
public class JobObjective implements Serializable {

    /**主键id**/
    private long id;

    /**简历id**/
    private long resumeId;

    /**工作性质：1全职；2兼职**/
    private int jobType;

    /**期望城市id**/
    private long zoneId;

    /**期望城市**/
    private String zoneName;

    /**期望职位**/
    private String jobName;

    /**期望行业id**/
    private long industryId;

    /**期望行业**/
    private String industryName;

    /**期望月薪起点**/
    private String salaryMin;

    /**期望薪资终点**/
    private String salaryMax;

    /**在职状态（1.离岗-随时到岗；2在职-月内到岗；3在职-考虑机会；4在职-暂不考虑；5应届生）**/
    private int serviceStatus;

    /**补充说明**/
    private String remark;

    /**创建时间**/
    private Date createDate;

    /**修改时间**/
    private Date updateDate;

    /**状态：-1删除；0取消；1正常**/
    private int status;

    /**备用字段1**/
    private long data1;

    /**备用字段2**/
    private long data2;

    /**备用字段3**/
    private String data3;

    /**备用字段4**/
    private String data4;

    /**备用字段5**/
    private String data5;

    /**备用字段6**/
    private int data6;

    /**备用字段7**/
    private int data7;

    /**备用字段8**/
    private Double data8;


    public JobObjective() { super(); }

    public JobObjective(long id) {
        super();
        this.id=id;
    }

    public JobObjective(long id,long resumeId,int jobType,long zoneId,String zoneName,String jobName,long industryId,String industryName,String salaryMin,String salaryMax,int serviceStatus,String remark,Date createDate,Date updateDate,int status,long data1,long data2,String data3,String data4,String data5,int data6,int data7,Double data8){
        super();
        this.id = id;
        this.resumeId = resumeId;
        this.jobType = jobType;
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.jobName = jobName;
        this.industryId = industryId;
        this.industryName = industryName;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.serviceStatus = serviceStatus;
        this.remark = remark;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.data6 = data6;
        this.data7 = data7;
        this.data8 = data8;

    }
    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setResumeId(Long resumeId){
        this.resumeId = resumeId;
    }

    public Long getResumeId(){
        return this.resumeId;
    }

    public void setJobType(Integer jobType){
        this.jobType = jobType;
    }

    public Integer getJobType(){
        return this.jobType;
    }

    public void setZoneId(Long zoneId){
        this.zoneId = zoneId;
    }

    public Long getZoneId(){
        return this.zoneId;
    }

    public void setZoneName(String zoneName){
        this.zoneName = zoneName;
    }

    public String getZoneName(){
        return this.zoneName;
    }

    public void setJobName(String jobName){
        this.jobName = jobName;
    }

    public String getJobName(){
        return this.jobName;
    }

    public void setIndustryId(Long industryId){
        this.industryId = industryId;
    }

    public Long getIndustryId(){
        return this.industryId;
    }

    public void setIndustryName(String industryName){
        this.industryName = industryName;
    }

    public String getIndustryName(){
        return this.industryName;
    }

    public void setSalaryMin(String salaryMin){
        this.salaryMin = salaryMin;
    }

    public String getSalaryMin(){
        return this.salaryMin;
    }

    public void setSalaryMax(String salaryMax){
        this.salaryMax = salaryMax;
    }

    public String getSalaryMax(){
        return this.salaryMax;
    }

    public void setServiceStatus(Integer serviceStatus){
        this.serviceStatus = serviceStatus;
    }

    public Integer getServiceStatus(){
        return this.serviceStatus;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
    }

    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public Date getCreateDate(){
        return this.createDate;
    }

    public void setUpdateDate(Date updateDate){
        this.updateDate = updateDate;
    }

    public Date getUpdateDate(){
        return this.updateDate;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }

    public void setData1(Long data1){
        this.data1 = data1;
    }

    public Long getData1(){
        return this.data1;
    }

    public void setData2(Long data2){
        this.data2 = data2;
    }

    public Long getData2(){
        return this.data2;
    }

    public void setData3(String data3){
        this.data3 = data3;
    }

    public String getData3(){
        return this.data3;
    }

    public void setData4(String data4){
        this.data4 = data4;
    }

    public String getData4(){
        return this.data4;
    }

    public void setData5(String data5){
        this.data5 = data5;
    }

    public String getData5(){
        return this.data5;
    }

    public void setData6(Integer data6){
        this.data6 = data6;
    }

    public Integer getData6(){
        return this.data6;
    }

    public void setData7(Integer data7){
        this.data7 = data7;
    }

    public Integer getData7(){
        return this.data7;
    }

    public void setData8(Double data8){
        this.data8 = data8;
    }

    public Double getData8(){
        return this.data8;
    }

}

