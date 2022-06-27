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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.Information;
import org.springblade.vessel.entity.ShipInformationResource;
import org.springblade.vessel.enums.ShipResourceEnum;
import org.springblade.vessel.mapper.InformationMapper;
import org.springblade.vessel.service.IInformationService;
import org.springblade.vessel.service.IShipInformationResourceService;
import org.springblade.vessel.vo.InformationVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 *
 * @author BladeX
 * @since 2022-04-29
 */
@Service
@AllArgsConstructor
public class InformationServiceImpl extends BaseServiceImpl<InformationMapper, Information> implements IInformationService {

	private final IShipInformationResourceService shipInformationResourceService;


	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveInformation(Information information) {
		boolean result = save(information);
		boolean resultR = shipInformationResourceService.save(processInfo(information));
		return result & resultR;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateInformation(Information information) {
		boolean result = updateById(information);
		ShipInformationResource informationResource = shipInformationResourceService.getOne(Wrappers.<ShipInformationResource>lambdaQuery()
			.eq(ShipInformationResource::getShipId, information.getId()).eq(ShipInformationResource::getType, ShipResourceEnum.SHIP.getType()));
		if (Func.isEmpty(informationResource)) {
			informationResource = processInfo(information);
		} else {
			informationResource.setShipNo(information.getShipNo());
			informationResource.setResourceName(information.getShipNo());
		}
		boolean resultR = shipInformationResourceService.saveOrUpdate(informationResource);
		return result & resultR;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> getDirector() {
		QueryWrapper<Information> shipWrapper = new QueryWrapper<>();
		shipWrapper.select("DISTINCT service_director");
		List<Information> list = super.list(shipWrapper);
		int size = list.size();
		List<String> directorList = new ArrayList<String>(size);
		for (Information information : list) {
			directorList.add(information.getServiceDirector());
		}
		return directorList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<Year, ArrayList<String>> getYearAndShip() {
		Map<Year,ArrayList<String>> yearAndShipMap = new LinkedHashMap<>();
		QueryWrapper<Information> wrapper = new QueryWrapper<>();
		List<Information> info1 = super.list(wrapper.select("DISTINCT year").orderByAsc("year"));
		ArrayList<Year> yearList = new ArrayList<>();
		for (Information info : info1) {
			yearList.add(info.getYear());
		}

		for (Year year : yearList) {
			ArrayList<String> shipNoList = new ArrayList<>();
			List<Information> shipList = super.list(Wrappers.<Information>lambdaQuery()
				.select(Information::getShipNo).eq(Information::getYear, year));
			for (Information info : shipList) {
				shipNoList.add(info.getShipNo());
			}
			yearAndShipMap.put(year,shipNoList);
		}
		return yearAndShipMap;
	}

	private ShipInformationResource processInfo(Information information) {
		ShipInformationResource shipInformationResource = new ShipInformationResource();
		shipInformationResource.setShipId(information.getId());
		shipInformationResource.setShipNo(information.getShipNo());
		shipInformationResource.setType(ShipResourceEnum.SHIP.getType());
		shipInformationResource.setParentId(BladeConstant.TOP_PARENT_ID);
		shipInformationResource.setAncestors(Func.toStr(BladeConstant.TOP_PARENT_ID));
		shipInformationResource.setResourceName(information.getShipNo());
		return shipInformationResource;
	}




}
