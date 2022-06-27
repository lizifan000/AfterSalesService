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

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.core.tool.api.R;
import org.springblade.vessel.entity.ServiceApproved;
import org.springblade.vessel.vo.ServiceApprovedVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2022-05-15
 */
public interface IServiceApprovedService extends BaseService<ServiceApproved> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param serviceApproved
	 * @return
	 */
	IPage<ServiceApprovedVO> selectServiceApprovedPage(IPage<ServiceApprovedVO> page, ServiceApprovedVO serviceApproved);


	//ServiceApproved selectServiceApproved(Long id);

	/**
	 * 导出保单
	 * @param policyId 保单Id
	 * @return
	 */
	Map<String,Object> getPolicyData(Long policyId);
	Map<String,Long> getMonthClosedClaim();
	Object[] getNowClaim(String shipNo);
	Object[] getTotalClaim(String startTime,String endTime);
	Map<String,Double> getMonthClosingRate();
	Object[] getTotalClosingRateNoParam();
	Object[] getTotalClosingRate(String startTime,String endTime);
	Map<String, Long> getDirectorTotalClaim();
	Map<String, Long> getDirectorUnclosedClaim();
	Map<String, Long> getVesselTotalClaim();
	Map<String, Long> getVesselUnclosedClaim();
}
