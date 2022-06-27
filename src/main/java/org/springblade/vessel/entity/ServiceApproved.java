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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2022-05-15
 */
@Data
//@TableName(value = "policy_service_approved",resultMap = "serviceApprovedResultMap")
@TableName(value = "policy_service_approved")

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ServiceApproved对象", description = "ServiceApproved对象")
public class ServiceApproved extends BaseEntity {

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
	* 交船日期
	*/
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		@ApiModelProperty(value = "交船日期")
		private Date deliveryDate;
	/**
	* 保单号
	*/
		@ApiModelProperty(value = "保单号")
		private Integer policyNo;

	/**
	* 内容描述
	*/
		@ApiModelProperty(value = "内容描述")
		private String description;
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
	*/  @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		@ApiModelProperty(value = "更新日期")
		private Date updatedDate;
	/**
	* 保单要求
	*/
		@ApiModelProperty(value = "保单要求")
		private String policyRequirements;
	/**
	* 备件提供地点及运单号
	*/
		@ApiModelProperty(value = "备件提供地点及运单号")
		private String waybillNumberOfSpareparts;
	/**
	* 责任方
	*/
		@ApiModelProperty(value = "责任方")
		private String manufactor;

	/**
	* 图片附件
	*/
		@ApiModelProperty(value = "图片附件")
		private String pictureAttachment;
	/**
	* 分类
	*/
		@ApiModelProperty(value = "分类")
		private String major;
	/**
	* 附件
	*/
		@ApiModelProperty(value = "附件")
		private String enclosure;

		/**
	* 附件
	*/
		@ApiModelProperty(value = "售后人员")
		private String serviceDirector;

//	/**
//	* 处理进度
//	*/
//		@TableField(exist = false)
//		private List<Progress> progressList;





	public ServiceApproved() {

	}
}
