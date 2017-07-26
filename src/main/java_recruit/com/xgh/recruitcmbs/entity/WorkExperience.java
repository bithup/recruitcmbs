package com.xgh.recruitcmbs.entity;
import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 *
 **/
@SuppressWarnings("serial")
public class WorkExperience implements Serializable {

	/**主键id**/
	private long id;

	/**简历id**/
	private long resumeId;

	/**公司名称**/
	private String companyName;

	/**开始时间**/
	private Date startDate;

	/**结束时间**/
	private Date endDate;

	/**行业id**/
	private long industryId;

	/**行业名称**/
	private String industryName;

	/**职位类别id**/
	private long jobKindsId;

	/**职位类别名称**/
	private String jobKindsName;

	/**职位名称**/
	private String jobName;

	/**职位月薪**/
	private int monthlyPay;

	/**职责介绍**/
	private String jobIntro;

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

	/****/
	private String data5;

	/**备用字段6**/
	private int data6;

	/**备用字段7**/
	private int data7;

	/**备用字段8**/
	private Double data8;


	public WorkExperience() { super(); }

	public WorkExperience(long id) {
		super();
		this.id=id;
	}

	public WorkExperience(long id,long resumeId,String companyName,Date startDate,Date endDate,long industryId,String industryName,long jobKindsId,String jobKindsName,String jobName,int monthlyPay,String jobIntro,Date createDate,Date updateDate,int status,long data1,long data2,String data3,String data4,String data5,int data6,int data7,Double data8){
		super();
		this.id = id;
		this.resumeId = resumeId;
		this.companyName = companyName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.industryId = industryId;
		this.industryName = industryName;
		this.jobKindsId = jobKindsId;
		this.jobKindsName = jobKindsName;
		this.jobName = jobName;
		this.monthlyPay = monthlyPay;
		this.jobIntro = jobIntro;
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

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyName(){
		return this.companyName;
	}

	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}

	public Date getStartDate(){
		return this.startDate;
	}

	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}

	public Date getEndDate(){
		return this.endDate;
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

	public void setJobKindsId(Long jobKindsId){
		this.jobKindsId = jobKindsId;
	}

	public Long getJobKindsId(){
		return this.jobKindsId;
	}

	public void setJobKindsName(String jobKindsName){
		this.jobKindsName = jobKindsName;
	}

	public String getJobKindsName(){
		return this.jobKindsName;
	}

	public void setJobName(String jobName){
		this.jobName = jobName;
	}

	public String getJobName(){
		return this.jobName;
	}

	public void setMonthlyPay(Integer monthlyPay){
		this.monthlyPay = monthlyPay;
	}

	public Integer getMonthlyPay(){
		return this.monthlyPay;
	}

	public void setJobIntro(String jobIntro){
		this.jobIntro = jobIntro;
	}

	public String getJobIntro(){
		return this.jobIntro;
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
