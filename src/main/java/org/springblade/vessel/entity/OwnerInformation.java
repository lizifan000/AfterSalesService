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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2022-04-29
 */
@Data
@TableName("ship_owner_information")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OwnerInformation对象", description = "OwnerInformation对象")
public class OwnerInformation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 船东名称
	*/
		@ApiModelProperty(value = "船东名称")
		private String shipownerName;
	/**
	* 船东联系人
	*/
		@ApiModelProperty(value = "船东联系人")
		private String shipownerContact;
	/**
	* 船东联系方式
	*/
		@ApiModelProperty(value = "船东联系方式")
		private String shipownerContactInformation;
	/**
	* 机务负责人
	*/
		@ApiModelProperty(value = "机务负责人")
		private String maintenanceDirector;
	/**
	* 机务联系方式
	*/
		@ApiModelProperty(value = "机务联系方式")
		private String maintenanceContactInformation;
	/**
	* 船东地址
	*/
		@ApiModelProperty(value = "船东地址")
		private String shipownerAddress;


		private Integer isDeleted;


}
