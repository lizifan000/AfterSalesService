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
import java.time.Year;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springblade.core.mp.base.BaseEntity;
import java.time.LocalDate;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2022-06-07
 */
@Data
@TableName("ship_information")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Information对象", description = "Information对象")
public class WarrantyClosing extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 系统船号，唯一键
	*/
		@ApiModelProperty(value = "船号")
		private String shipNo;
	/**
	* 船舶名称
	*/
		@ApiModelProperty(value = "船舶名称")
		private String shipName;
	/**
	* 船舶类型
	*/
		@ApiModelProperty(value = "船舶类型")
		private String shipType;
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
	* 船级社
	*/
		@ApiModelProperty(value = "船级社")
		private String classificationSociety;
	/**
	* 完工资料负责人
	*/
		@ApiModelProperty(value = "完工资料负责人")
		private String dataDirector;
	/**
	* 品保部售后室人员
	*/
		@ApiModelProperty(value = "品保部售后室人员")
		private String serviceDirector;
	/**
	* 交船日期
	*/
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		@ApiModelProperty(value = "交船日期")
		private LocalDate deliveryDate;
	/**
	* 交船年份
	*/
		@ApiModelProperty(value = "交船年份")
		private Year year;
	/**
	* 船舶联系方式
	*/
		@ApiModelProperty(value = "船舶联系方式")
		private String contact;
	/**
	* 保修截至日期
	*/
		@ApiModelProperty(value = "保修截至日期")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		private Date warrantyExpirationDate;
	/**
	* 开始谈判日期
	*/
		@ApiModelProperty(value = "开始谈判日期")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		private Date startTalksDate;
	/**
	* 船东报价日期
	*/
		@ApiModelProperty(value = "船东报价日期")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		private Date ownerQuoteDate;
	/**
	* 协议签订日期
	*/
		@ApiModelProperty(value = "协议签订日期")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		private Date agreementSigningDate;
	/**
	* 封闭费用（美金）
	*/
		@ApiModelProperty(value = "封闭费用（美金）")
		private Double closingCost;


}
