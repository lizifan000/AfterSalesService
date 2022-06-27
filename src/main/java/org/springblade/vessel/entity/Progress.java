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

import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2022-05-16
 */
@Data
@TableName("processing_progress")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Progress对象", description = "Progress对象")
public class Progress extends BaseEntity {

	private static final long serialVersionUID = 1L;


	/**
	* 保单id
	*/
		@ApiModelProperty(value = "保单id")
		private Long policyId;
	/**
	* 处理内容
	*/
		@ApiModelProperty(value = "处理内容")
		private String progressContent;

	/**
	* 费用
	*/
		@JsonSerialize(nullsUsing = NullSerializer.class)
		@ApiModelProperty(value = "费用")
		private Double cost;
	/**
	 * 费用类型
	*/
		@ApiModelProperty(value = "费用类型")
		private String costType;

	/**
	 * 船号
	 */
	@ApiModelProperty(value = "船号")
	private String shipNo;

	/**
	* 处理时间
	*/
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		@ApiModelProperty(value = "处理时间")
		private Date createTime;


}
