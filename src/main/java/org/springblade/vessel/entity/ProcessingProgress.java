/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.vessel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2022-05-08
 */
@Data
@TableName("policy_processing_progress")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProcessingProgress对象", description = "ProcessingProgress对象")
public class ProcessingProgress extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 船号外键
	*/
		@ApiModelProperty(value = "船号")
		private String shipNo;
	/**
	* 船舶名称
	*/
		@ApiModelProperty(value = "船舶名称")
		private String shipName;
	/**
	* 交船日期
	*/
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		@ApiModelProperty(value = "交船日期")
		private Date deliveryDate;
	/**
	* 保单号
	*/
		@JsonSerialize(nullsUsing = NullSerializer.class)
		@ApiModelProperty(value = "保单号")
		private Integer policyNo;

	/**
	 * 内容描述
	 */
	@ApiModelProperty(value = "内容描述")
	private String description;

	/**
	* 保单主题
	*/
		@ApiModelProperty(value = "保单主题")
		private String subject;
	/**
	* 分类
	*/
		@ApiModelProperty(value = "分类")
		private String classification;
	/**
	* 责任方
	*/
		@ApiModelProperty(value = "责任方")
		private String responsibility;

	/**
	* 保单日期
	*/
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		@ApiModelProperty(value = "保单日期")
		private Date policyDate;
	/**
	* 完成日期
	*/
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		@ApiModelProperty(value = "完成日期")
		private Date completionDate;
	/**
	* 更新日期
	*/
		@ApiModelProperty(value = "更新日期")
		private Date updatedDate;
	/**
	* 保单要求
	*/
		@ApiModelProperty(value = "保单要求")
		private String policyRequirements;
	/**
	* 处理进度
	*/
		@ApiModelProperty(value = "处理进度")
		private String processingProgress;
	/**
	* 费用
	*/
		@ApiModelProperty(value = "费用")
		private Double cost;
	/**
	* 船舶描述
	*/
		@ApiModelProperty(value = "船舶描述")
		private String shipDescription;
	/**
	* 船东名称
	*/
		@ApiModelProperty(value = "船东名称")
		private String shipownerName;
	/**
	* 维保部位
	*/
		@ApiModelProperty(value = "维保部位")
		private String maintenanceParts;
	/**
	* 厂家
	*/
		@ApiModelProperty(value = "厂家")
		private String manufactor;
	/**
	* 损坏原因
	*/
		@ApiModelProperty(value = "损坏原因")
		private String damageCause;
	/**
	* 人工及物料需求
	*/
		@ApiModelProperty(value = "人工及物料需求")
		private String labormaterialRequire;
	/**
	* 发生地点日期及情况
	*/
		@ApiModelProperty(value = "发生地点日期及情况")
		private String placedateSituation;
	/**
	* 设备位置
	*/
		@ApiModelProperty(value = "设备位置")
		private String equipmentLocation;

	/**
	* 损坏部件序列号
	*/
		@ApiModelProperty(value = "损坏部件序列号")
		private String serialNo;
	/**
	 * Master
	 */
		@ApiModelProperty(value = "Master")
		private String master;

	 /**
	 * MasterSignature
	 */
		@ApiModelProperty(value = "MasterSignature")
		private String masterSignature;

	/**
	 * 总工程师
	 */
		@ApiModelProperty(value = "总工程师")
		private String chiefEngineer;

	/**
	 * 总工程师签名
	 */
		@ApiModelProperty(value = "chief_engineer_signature")
		private String chiefEngineerSignature;

	/**
	* 保修工程师
	*/
		@ApiModelProperty(value = "保修工程师")
		private String guaranteeEngineer;

	/**
	 * 保修工程师签名
	 */
	@ApiModelProperty(value = "guarantee_engineer_signature")
	private String guaranteeEngineerSignature;

	/**
	 * 售后人员
	 */
	@ApiModelProperty(value = "service_director")
	private String serviceDirector;

	/**
	* 备注
	*/
		@ApiModelProperty(value = "备注")
		private String remarks;
	/**
	* 图片附件
	*/
		@ApiModelProperty(value = "图片附件")
		private String pictureAttachment;
	/**
	* 专业名称
	*/
		@ApiModelProperty(value = "专业名称")
		private String major;

	/**
	 * 删除
	 */
	@ApiModelProperty(value = "物理删除")
	private Integer isDeleted;



}
