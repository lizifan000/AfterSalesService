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
import org.springblade.vessel.entity.ProcessingProgress;
import org.springblade.vessel.mapper.ProcessingProgressMapper;
import org.springblade.vessel.service.IProcessingProgressService;
import org.springblade.vessel.vo.ProcessingProgressVO;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2022-05-08
 */
@Service
public class ProcessingProgressServiceImpl extends BaseServiceImpl<ProcessingProgressMapper, ProcessingProgress> implements IProcessingProgressService {

	@Override
	public IPage<ProcessingProgressVO> selectProcessingProgressPage(IPage<ProcessingProgressVO> page, ProcessingProgressVO processingProgress) {

		return page.setRecords(baseMapper.selectProcessingProgressPage(page, processingProgress));
	}

}
