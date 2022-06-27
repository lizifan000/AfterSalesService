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
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色表实体类
 *
 * @author BladeX
 * @since 2022-06-13
 */
@Data
@TableName("ship_supplier_information")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SupplierInformation对象", description = "角色表")
public class SupplierInformation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 父主键
	*/
		@ApiModelProperty(value = "父主键")
		private Long parentId;
	/**
	* 角色名
	*/
		@ApiModelProperty(value = "角色名")
		private String supplierName;
	/**
	* 角色别名
	*/
		@ApiModelProperty(value = "角色别名")
		private String supplierType;
	/**
	* 联系人
	*/
		@ApiModelProperty(value = "联系人")
		private String contact;
	/**
	* 联系方式
	*/
		@ApiModelProperty(value = "联系方式")
		private String contactInformation;


}
