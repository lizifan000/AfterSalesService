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
package org.springblade.vessel.service;

import org.springblade.vessel.entity.SupplierInformation;
import org.springblade.vessel.vo.ShipInformationResourceVO;
import org.springblade.vessel.vo.SupplierInformationVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * 角色表 服务类
 *
 * @author BladeX
 * @since 2022-06-13
 */
public interface ISupplierInformationService extends BaseService<SupplierInformation> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param supplierInformation
	 * @return
	 */
	IPage<SupplierInformationVO> selectSupplierInformationPage(IPage<SupplierInformationVO> page, SupplierInformationVO supplierInformation);

	/**
	 * 树形结构
	 *
	 * @return
	 */
	List<SupplierInformationVO> tree();

	/**
	 * 提交
	 *
	 * @param role
	 * @return
	 */
	boolean submit(SupplierInformation role);

	/**
	 * 懒加载供应商表
	 *
	 * @param parentId
	 * @param param
	 * @return
	 */
	List<SupplierInformationVO> lazyList(Long parentId, Map<String, Object> param);

	/**
	 * 查询没有子集的供应商
	 * @return
	 */
	List<SupplierInformation> selectSingleSupplier();
}
