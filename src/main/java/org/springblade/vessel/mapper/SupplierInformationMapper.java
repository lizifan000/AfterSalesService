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
package org.springblade.vessel.mapper;

import org.springblade.vessel.entity.SupplierInformation;
import org.springblade.vessel.vo.ShipInformationResourceVO;
import org.springblade.vessel.vo.SupplierInformationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import java.util.Map;

/**
 * 角色表 Mapper 接口
 *
 * @author BladeX
 * @since 2022-06-13
 */
public interface SupplierInformationMapper extends BaseMapper<SupplierInformation> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param supplierInformation
	 * @return
	 */
	List<SupplierInformationVO> selectSupplierInformationPage(IPage page, SupplierInformationVO supplierInformation);

	/**
	 * 获取树形节点
	 *
	 * @return
	 */
	List<SupplierInformationVO> tree();

	/**
	 * 懒加载列表
	 *
	 * @param parentId
	 * @param param
	 * @return
	 */
	List<SupplierInformationVO> lazyList(Long parentId, Map<String, Object> param);


	/**
	 * 查询没有子级的供应商
	 *
	 * @return
	 */
	List<SupplierInformation> selectSingleSupplier();
}
