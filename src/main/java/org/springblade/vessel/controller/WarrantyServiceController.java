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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.vessel.entity.WarrantyService;
import org.springblade.vessel.vo.WarrantyServiceVO;
import org.springblade.vessel.service.IWarrantyServiceService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2022-06-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/warrantyservice")
@Api(value = "", tags = "接口")
public class WarrantyServiceController extends BladeController {

	private final IWarrantyServiceService warrantyServiceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入warrantyService")
	public R<WarrantyService> detail(WarrantyService warrantyService) {
		WarrantyService detail = warrantyServiceService.getOne(Condition.getQueryWrapper(warrantyService));
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入warrantyService")
	public R<IPage<WarrantyService>> list(WarrantyService warrantyService, Query query) {
		IPage<WarrantyService> pages = warrantyServiceService.page(Condition.getPage(query), Condition.getQueryWrapper(warrantyService));
		return R.data(pages);
	}

	/**
	 * 自定义分页 
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入warrantyService")
	public R<IPage<WarrantyServiceVO>> page(WarrantyServiceVO warrantyService, Query query) {
		IPage<WarrantyServiceVO> pages = warrantyServiceService.selectWarrantyServicePage(Condition.getPage(query), warrantyService);
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入warrantyService")
	public R save(@Valid @RequestBody WarrantyService warrantyService) {
		return R.status(warrantyServiceService.save(warrantyService));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入warrantyService")
	public R update(@Valid @RequestBody WarrantyService warrantyService) {
		return R.status(warrantyServiceService.updateById(warrantyService));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入warrantyService")
	public R submit(@Valid @RequestBody WarrantyService warrantyService) {
		return R.status(warrantyServiceService.saveOrUpdate(warrantyService));
	}

	
	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(warrantyServiceService.deleteLogic(Func.toLongList(ids)));
	}

	
}
