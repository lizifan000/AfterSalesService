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
 * 实体类
 *
 * @author BladeX
 * @since 2022-05-19
 */
@Data
@TableName("policy_document_information")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DocumentInformation对象", description = "DocumentInformation对象")
public class DocumentInformation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 保单id
	 */
	@ApiModelProperty(value = "保单id")
	private Long policyId;
	/**
	 * 附件地址
	 */
	@ApiModelProperty(value = "附件地址")
	private String link;
	/**
	 * 文件原名
	 */
	@ApiModelProperty(value = "文件原名")
	private String documentName;

	/**
	 * 物理删除
	 */

	private Integer isDeleted;


}
