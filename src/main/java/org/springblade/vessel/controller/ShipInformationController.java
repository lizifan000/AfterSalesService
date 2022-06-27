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
package org.springblade.vessel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.Information;
import org.springblade.vessel.service.IInformationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Year;
import java.util.Calendar;
import java.util.List;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2022-04-29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shipInformation")
@Api(value = "", tags = "接口")
public class ShipInformationController extends BladeController {

	private final IInformationService informationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入information")
	public R<Information> detail(Information information) {
		Information detail = informationService.getOne(Condition.getQueryWrapper(information));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入information")
	public R<IPage<Information>> list(Information information, Query query) {
		IPage<Information> pages = informationService.page(Condition.getPage(query), Condition.getQueryWrapper(information));
		return R.data(pages);
	}

	/**
	 * 不分页
	 * @return
	 */
	@GetMapping("/allList")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "不分页", notes = "null")
	public R<List<Information>> getAllList() {
		//LambdaQueryWrapper<Information> wrapper = new LambdaQueryWrapper<>();
		return R.data(informationService.list(null));

	}

	/**
	 * 自定义分页
	 */
/*	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入information")
	public R<IPage<InformationVO>> page(InformationVO information, Query query) {
		IPage<InformationVO> pages = informationService.selectInformationPage(Condition.getPage(query), information);
		return R.data(pages);
	}*/

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入information")
	public R save(@Valid @RequestBody Information information) {
		Calendar c = Calendar.getInstance();
		c.setTime(information.getDeliveryDate());
		int year = c.get(Calendar.YEAR);
		information.setYear(Year.of(year));
		return R.status(informationService.saveInformation(information));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入information")
	public R update(@Valid @RequestBody Information information) {
		Calendar c = Calendar.getInstance();
		c.setTime(information.getDeliveryDate());
		int year = c.get(Calendar.YEAR);
		information.setYear(Year.of(year));
		return R.status(informationService.updateById(information));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入information")
	public R submit(@Valid @RequestBody Information information) {
		return R.status(informationService.updateInformation(information));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(informationService.deleteLogic(Func.toLongList(ids)));
	}
	/**
	 * 详情
	 */
	@GetMapping("/getShipOfHullNo")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "详情", notes = "传入ship_no")
	public R<Information> getShipOfHullNo(String shipNo) {
		QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ship_no",shipNo);
		return R.data(informationService.getOne(queryWrapper));
	}




}
