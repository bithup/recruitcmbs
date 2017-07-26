package com.xgh.recruitcmbs.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Recharge implements Serializable {

	/**id**/
	private long id;

	/**用户id**/
	private long memberId;

	/**用户类型：1，用户（目前只有企业充值，留着备用）；2，企业**/
	private int memberType;

	/**充值金额**/
	private Double rechargeAmount;

	/**充值状态：0，未到账；1，已到账**/
	private int payStatus;

	/**支付时间**/
	private Date payTime;

	/**支付方式：1，支付宝；2，银行卡**/
	private int payType;

	/**订单编号**/
	private String orderNo;

	/**交易号**/
	private String tradeNo;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**状态：1，正常；-1，删除**/
	private int status;

	/****/
	private String data1;

	/****/
	private String data2;

	/****/
	private String data3;

	/****/
	private long data4;

	/****/
	private int data5;

	/****/
	private Double data6;


	public Recharge() { super(); }

	public Recharge(long id) {
	 super();
	 this.id=id;
	}

	public Recharge(long id,long memberId,int memberType,Double rechargeAmount,int payStatus,Date payTime,int payType,String orderNo,String tradeNo,Date createDate,Date updateDate,int status,String data1,String data2,String data3,long data4,int data5,Double data6){
		super();
		this.id = id;
		this.memberId = memberId;
		this.memberType = memberType;
		this.rechargeAmount = rechargeAmount;
		this.payStatus = payStatus;
		this.payTime = payTime;
		this.payType = payType;
		this.orderNo = orderNo;
		this.tradeNo = tradeNo;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
		this.data6 = data6;

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

	public void setMemberType(Integer memberType){
		this.memberType = memberType;
	}

	public Integer getMemberType(){
		return this.memberType;
	}

	public void setRechargeAmount(Double rechargeAmount){
		this.rechargeAmount = rechargeAmount;
	}

	public Double getRechargeAmount(){
		return this.rechargeAmount;
	}

	public void setPayStatus(Integer payStatus){
		this.payStatus = payStatus;
	}

	public Integer getPayStatus(){
		return this.payStatus;
	}

	public void setPayTime(Date payTime){
		this.payTime = payTime;
	}

	public Date getPayTime(){
		return this.payTime;
	}

	public void setPayType(Integer payType){
		this.payType = payType;
	}

	public Integer getPayType(){
		return this.payType;
	}

	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public String getOrderNo(){
		return this.orderNo;
	}

	public void setTradeNo(String tradeNo){
		this.tradeNo = tradeNo;
	}

	public String getTradeNo(){
		return this.tradeNo;
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

	public void setData4(Long data4){
		this.data4 = data4;
	}

	public Long getData4(){
		return this.data4;
	}

	public void setData5(Integer data5){
		this.data5 = data5;
	}

	public Integer getData5(){
		return this.data5;
	}

	public void setData6(Double data6){
		this.data6 = data6;
	}

	public Double getData6(){
		return this.data6;
	}

}
