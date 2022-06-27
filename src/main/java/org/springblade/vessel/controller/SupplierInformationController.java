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
import io.swagger.annotations.*;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import oracle.jdbc.proxy.annotation.Pre;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.ProcessingProgress;
import org.springblade.vessel.wrapper.SupplierInformationWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.vessel.entity.SupplierInformation;
import org.springblade.vessel.vo.SupplierInformationVO;
import org.springblade.vessel.service.ISupplierInformationService;
import org.springblade.core.boot.ctrl.BladeController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 角色表 控制器
 *
 * @author BladeX
 * @since 2022-06-13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/supplierinformation")
@Api(value = "角色表", tags = "角色表接口")

public class SupplierInformationController extends BladeController {

	private final ISupplierInformationService supplierInformationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入supplierInformation")
	public R<SupplierInformation> detail(SupplierInformation supplierInformation) {
		SupplierInformation detail = supplierInformationService.getOne(Condition.getQueryWrapper(supplierInformation));
		return R.data(detail);
	}

	/**
	 * 界面初始化供应商表
	 * @return
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	public R<List<SupplierInformationVO>> list(@ApiIgnore @RequestParam Map<String, Object> supplierInformation, BladeUser bladeUser) {
		QueryWrapper<SupplierInformation> queryWrapper = Condition.getQueryWrapper(supplierInformation, SupplierInformation.class);
		List<SupplierInformation> list = supplierInformationService.list(queryWrapper);
		return R.data(SupplierInformationWrapper.build().listNodeVO(list));
	}

	/**
	 * 供应商表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	public R<List<SupplierInformation>> page() {
		return R.data(supplierInformationService.selectSingleSupplier());
	}

	/**
	 * 新增 角色表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入supplierInformation")
	public R save(@Valid @RequestBody SupplierInformation supplierInformation) {
		return R.status(supplierInformationService.save(supplierInformation));
	}

	/**
	 * 修改 角色表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入supplierInformation")
	public R update(@Valid @RequestBody SupplierInformation supplierInformation) {
		return R.status(supplierInformationService.updateById(supplierInformation));
	}

	/**
	 * 新增或修改 供应商表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入supplierInformation")
	public R submit(@Valid @RequestBody SupplierInformation supplierInformation) {

		return R.status(supplierInformationService.submit(supplierInformation));
	}


	/**
	 * 删除 供应商表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(supplierInformationService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 获取供应商树形结构
	 *
	 * @return
	 */
	@GetMapping("/tree")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "树形结构", notes = "树形结构")
	public R<List<SupplierInformationVO>> tree() {
		List<SupplierInformationVO> tree = supplierInformationService.tree();
		return R.data(tree);
	}

	/**
	 * 懒加载列表
	 */
	@GetMapping("/lazy-list")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierName", value = "资源名称", paramType = "query", dataType = "string"),
	})
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "懒加载列表", notes = "传入supplierInformation")
	public R<List<SupplierInformationVO>> lazyList(@ApiIgnore @RequestParam Map<String, Object> information, Long parentId) {
		List<SupplierInformationVO> list = supplierInformationService.lazyList(parentId, information);
		return R.data(SupplierInformationWrapper.build().listNodeLazyVO(list));
	}

}
