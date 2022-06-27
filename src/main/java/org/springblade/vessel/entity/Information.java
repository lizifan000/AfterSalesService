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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Year;
import java.util.Date;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2022-04-29
 */
@Data
@TableName("ship_information")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Information对象", description = "Information对象")
public class Information extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 船号
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
	 * 售后服务负责人
	 */
	@ApiModelProperty(value = "售后服务负责人")
	private String serviceDirector;


	/**
	 * 交船日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@ApiModelProperty(value = "交船日期")
	private Date deliveryDate;

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
	 * 船舶描述
	 */
	@ApiModelProperty(value = "船舶描述")
	private String shipDescription;


	private Integer isDeleted;


}
