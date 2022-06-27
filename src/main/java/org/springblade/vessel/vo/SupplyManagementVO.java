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
package org.springblade.vessel.vo;

import org.springblade.vessel.entity.SupplyManagement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 视图实体类
 *
 * @author BladeX
 * @since 2022-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SupplyManagementVO对象", description = "SupplyManagementVO对象")
public class SupplyManagementVO extends SupplyManagement {
	private static final long serialVersionUID = 1L;

}
