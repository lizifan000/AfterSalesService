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

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.vessel.entity.OwnerInformation;
import org.springblade.vessel.mapper.OwnerInformationMapper;
import org.springblade.vessel.service.IOwnerInformationService;
import org.springblade.vessel.vo.OwnerInformationVO;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2022-04-29
 */
@Service
public class OwnerInformationServiceImpl extends BaseServiceImpl<OwnerInformationMapper, OwnerInformation> implements IOwnerInformationService {

	@Override
	public IPage<OwnerInformationVO> selectOwnerInformationPage(IPage<OwnerInformationVO> page, OwnerInformationVO ownerInformation) {
		return page.setRecords(baseMapper.selectOwnerInformationPage(page, ownerInformation));
	}



}
