package com.xgh.recruitcmbs.entity;
import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 *
 **/
@SuppressWarnings("serial")
public class Interview implements Serializable {

    /**主键id**/
    private long id;

    /**公司id**/
    private long companyId;

    /**用户id**/
    private long memberId;

    /**邀请标题**/
    private String title;

    /**邀请内容**/
    private String content;

    /**面试时间**/
    private Date interviewTime;

    /**面试地址**/
    private String address;

    /**联系人**/
    private String contacts;

    /**联系电话**/
    private String telephone;

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

    /**用户操作状态-1删除1正常**/
    private int personOperationStatus;

    /**备用字段7**/
    private int data7;

    /**用字段8**/
    private Double data8;


    public Interview() { super(); }

    public Interview(long id) {
        super();
        this.id=id;
    }

    public Interview(long id,long companyId,long memberId,String title,String content,Date interviewTime,String address,String contacts,String telephone,Date createDate,Date updateDate,int status,long data1,long data2,String data3,String data4,String data5,int personOperationStatus,int data7,Double data8){
        super();
        this.id = id;
        this.companyId = companyId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.interviewTime = interviewTime;
        this.address = address;
        this.contacts = contacts;
        this.telephone = telephone;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.personOperationStatus = personOperationStatus;
        this.data7 = data7;
        this.data8 = data8;

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

    public void setMemberId(Long memberId){
        this.memberId = memberId;
    }

    public Long getMemberId(){
        return this.memberId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }

    public void setInterviewTime(Date interviewTime){
        this.interviewTime = interviewTime;
    }

    public Date getInterviewTime(){
        return this.interviewTime;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setContacts(String contacts){
        this.contacts = contacts;
    }

    public String getContacts(){
        return this.contacts;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public String getTelephone(){
        return this.telephone;
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

    public void setPersonOperationStatus(Integer personOperationStatus){
        this.personOperationStatus = personOperationStatus;
    }

    public Integer getPersonOperationStatus(){
        return this.personOperationStatus;
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
