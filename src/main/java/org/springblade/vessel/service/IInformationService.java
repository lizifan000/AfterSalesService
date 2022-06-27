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

import org.springblade.vessel.entity.Information;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.vessel.vo.InformationVO;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务类
 *
 * @author BladeX
 * @since 2022-04-29
 */
public interface IInformationService extends BaseService<Information> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param information
	 * @return
	 */


	/**
	 * 保存船舶信息
	 *
	 * @param information
	 * @return
	 */
	boolean saveInformation(Information information);

	/**
	 * 更新船舶信息
	 *
	 * @param information
	 * @param information
	 * @return
	 */
	boolean updateInformation(Information information);
	List<String> getDirector();
	Map<Year, ArrayList<String>> getYearAndShip();
}
