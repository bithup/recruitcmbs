package com.xgh.recruitcmbs.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Resume implements Serializable {

	/**主键id**/
	private long id;

	/**用户id**/
	private long memberId;

	/**简历完整度**/
	private String integrity;

	/**是否为默认简历：0不是；1是**/
	private int isDefault;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**状态：-1删除；0取消；1正常**/
	private int status;

	/**是否公开：0公开：1不公开**/
	private int isScreen;

	/**备用字段1**/
	private int data1;

	/**备用字段2**/
	private int data2;

	/**备用字段3**/
	private String data3;

	/**备用字段4**/
	private String data4;

	/**备用字段5**/
	private long data5;

	/**备用字段6**/
	private long data6;

	/**备用字段7**/
	private long data7;

	/**备用字段8**/
	private Date data8;

	/**备用字段9**/
	private String data9;

	/**备用字段10**/
	private int data10;


	public Resume() { super(); }

	public Resume(long id) {
	 super();
	 this.id=id;
	}

	public Resume(long id,long memberId,String integrity,int isDefault,Date createDate,Date updateDate,int status,int isScreen,int data1,int data2,String data3,String data4,long data5,long data6,long data7,Date data8,String data9,int data10){
		super();
		this.id = id;
		this.memberId = memberId;
		this.integrity = integrity;
		this.isDefault = isDefault;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.isScreen = isScreen;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
		this.data6 = data6;
		this.data7 = data7;
		this.data8 = data8;
		this.data9 = data9;
		this.data10 = data10;

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

	public void setIntegrity(String integrity){
		this.integrity = integrity;
	}

	public String getIntegrity(){
		return this.integrity;
	}

	public void setIsDefault(Integer isDefault){
		this.isDefault = isDefault;
	}

	public Integer getIsDefault(){
		return this.isDefault;
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

	public void setIsScreen(Integer isScreen){
		this.isScreen = isScreen;
	}

	public Integer getIsScreen(){
		return this.isScreen;
	}

	public void setData1(Integer data1){
		this.data1 = data1;
	}

	public Integer getData1(){
		return this.data1;
	}

	public void setData2(Integer data2){
		this.data2 = data2;
	}

	public Integer getData2(){
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

	public void setData5(Long data5){
		this.data5 = data5;
	}

	public Long getData5(){
		return this.data5;
	}

	public void setData6(Long data6){
		this.data6 = data6;
	}

	public Long getData6(){
		return this.data6;
	}

	public void setData7(Long data7){
		this.data7 = data7;
	}

	public Long getData7(){
		return this.data7;
	}

	public void setData8(Date data8){
		this.data8 = data8;
	}

	public Date getData8(){
		return this.data8;
	}

	public void setData9(String data9){
		this.data9 = data9;
	}

	public String getData9(){
		return this.data9;
	}

	public void setData10(Integer data10){
		this.data10 = data10;
	}

	public Integer getData10(){
		return this.data10;
	}

}
