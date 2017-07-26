package com.xgh.recruitcmbs.entity;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 简历投递表
 *
 **/
@SuppressWarnings("serial")
public class ResumeDelivery implements Serializable {

    /**主键id**/
    private long id;

    /**用户id**/
    private long memberId;

    /**职位id**/
    private long positionId;

    /**简历id**/
    private long resumeId;

    /**投递状态：0未处理1.待面试2待确认3已转正4不合适**/
    private int deliveryStatus;

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

    /**是否被查看1已查看0未查看**/
    private int isView;

    /**备用字段7**/
    private int data7;

    /**备用字段8**/
    private Double data8;


    public ResumeDelivery() { super(); }

    public ResumeDelivery(long id) {
        super();
        this.id=id;
    }

    public ResumeDelivery(long id,long memberId,long positionId,long resumeId,int deliveryStatus,Date createDate,Date updateDate,int status,long data1,long data2,String data3,String data4,String data5,int isView,int data7,Double data8){
        super();
        this.id = id;
        this.memberId = memberId;
        this.positionId = positionId;
        this.resumeId = resumeId;
        this.deliveryStatus = deliveryStatus;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.isView = isView;
        this.data7 = data7;
        this.data8 = data8;

    }
    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setMemberId(Long memberId){
        this.memberId = memberId;
    }

    public Long getMemberId(){
        return this.memberId;
    }

    public void setPositionId(Long positionId){
        this.positionId = positionId;
    }

    public Long getPositionId(){
        return this.positionId;
    }

    public void setResumeId(Long resumeId){
        this.resumeId = resumeId;
    }

    public Long getResumeId(){
        return this.resumeId;
    }

    public void setDeliveryStatus(Integer deliveryStatus){
        this.deliveryStatus = deliveryStatus;
    }

    public Integer getDeliveryStatus(){
        return this.deliveryStatus;
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

    public void setIsView(Integer isView){
        this.isView = isView;
    }

    public Integer getIsView(){
        return this.isView;
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

