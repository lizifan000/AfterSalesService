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
package org.springblade.vessel.service.impl;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.SupplierInformation;
import org.springblade.vessel.vo.ShipInformationResourceVO;
import org.springblade.vessel.vo.SupplierInformationVO;
import org.springblade.vessel.mapper.SupplierInformationMapper;
import org.springblade.vessel.service.ISupplierInformationService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * 角色表 服务实现类
 *
 * @author BladeX
 * @since 2022-06-13
 */
@Service
public class SupplierInformationServiceImpl extends BaseServiceImpl<SupplierInformationMapper, SupplierInformation> implements ISupplierInformationService {
	private static final String PARENT_ID = "parentId";
	@Override
	public IPage<SupplierInformationVO> selectSupplierInformationPage(IPage<SupplierInformationVO> page, SupplierInformationVO supplierInformation) {
		return page.setRecords(baseMapper.selectSupplierInformationPage(page, supplierInformation));
	}

	@Override
	public List<SupplierInformationVO> tree() {
		return ForestNodeMerger.merge(baseMapper.tree());
	}

	@Override
	public boolean submit(SupplierInformation supplierInformation) {
		if (Func.isEmpty(supplierInformation.getParentId())) {
			supplierInformation.setParentId(BladeConstant.TOP_PARENT_ID);
		}
		if (supplierInformation.getParentId() > 0) {
			if (Func.toLong(supplierInformation.getParentId()) == Func.toLong(supplierInformation.getId())) {
				throw new ServiceException("父节点不可选择自身!");
			}
		}
		supplierInformation.setIsDeleted(BladeConstant.DB_NOT_DELETED);
		return saveOrUpdate(supplierInformation);
	}

	@Override
	public List<SupplierInformationVO> lazyList(Long parentId, Map<String, Object> param) {
		// 判断点击搜索带有查询条件的情况
		if (Func.isEmpty(param.get(PARENT_ID)) && param.size() > 1 && Func.toLong(parentId) == 0L) {
			parentId = null;
		}
		return baseMapper.lazyList( parentId, param);
	}

	@Override
	public List<SupplierInformation> selectSingleSupplier() {
		return baseMapper.selectSingleSupplier();
	}


}
