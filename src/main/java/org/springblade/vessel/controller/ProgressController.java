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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.datascope.annotation.DataAuth;
import org.springblade.core.datascope.enums.DataScopeEnum;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.Progress;
import org.springblade.vessel.service.IProgressService;
import org.springblade.vessel.vo.ProgressVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2022-05-16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/progress")
@Api(value = "", tags = "接口")
public class ProgressController extends BladeController {

	private final IProgressService progressService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入progress")
	public R<Progress> detail(Progress progress) {
		Progress detail = progressService.getOne(Condition.getQueryWrapper(progress));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入progress")
	public R<IPage<Progress>> list(Progress progress, Query query) {
		IPage<Progress> pages = progressService.page(Condition.getPage(query), Condition.getQueryWrapper(progress));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入progress")
	public R<IPage<ProgressVO>> page(ProgressVO progress, Query query) {
		IPage<ProgressVO> pages = progressService.selectProgressPage(Condition.getPage(query), progress);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入progress")
	public R save(@Valid @RequestBody Progress progress) {
		return R.status(progressService.save(progress));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入progress")
	public R update(@Valid @RequestBody Progress progress) {
		return R.status(progressService.updateById(progress));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入progress")
	public R submit(@Valid @RequestBody Progress progress) {
		return R.status(progressService.saveOrUpdate(progress));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(progressService.deleteLogic(Func.toLongList(ids)));
	}


}
