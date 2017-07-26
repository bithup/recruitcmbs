package com.xgh.recruitcmbs.entity;
import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 *
 **/
@SuppressWarnings("serial")
public class GreatDemand implements Serializable {

    /****/
    private long id;

    /**公司id**/
    private long companyId;

    /**热门职位数量**/
    private int hotCount;

    /**工作种类id**/
    private long jobKindsId;

    /**创建日期**/
    private Date createDate;

    /**更新日期**/
    private Date updateDate;

    /**1整除0删除**/
    private int status;

    /**备用字段1**/
    private String data1;

    /**备用字段2**/
    private String data2;

    /**备用字段3**/
    private String data3;

    /**备用字段4**/
    private String data4;


    public GreatDemand() { super(); }

    public GreatDemand(long id) {
        super();
        this.id=id;
    }

    public GreatDemand(long id,long companyId,int hotCount,long jobKindsId,Date createDate,Date updateDate,int status,String data1,String data2,String data3,String data4){
        super();
        this.id = id;
        this.companyId = companyId;
        this.hotCount = hotCount;
        this.jobKindsId = jobKindsId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;

    }
    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setCompanyId(Long companyId){
        this.companyId = companyId;
    }

    public Long getCompanyId(){
        return this.companyId;
    }

    public void setHotCount(Integer hotCount){
        this.hotCount = hotCount;
    }

    public Integer getHotCount(){
        return this.hotCount;
    }

    public void setJobKindsId(Long jobKindsId){
        this.jobKindsId = jobKindsId;
    }

    public Long getJobKindsId(){
        return this.jobKindsId;
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

    public void setData1(String data1){
        this.data1 = data1;
    }

    public String getData1(){
        return this.data1;
    }

    public void setData2(String data2){
        this.data2 = data2;
    }

    public String getData2(){
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

}
