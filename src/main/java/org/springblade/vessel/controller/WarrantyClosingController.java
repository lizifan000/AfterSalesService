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
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.ProcessingProgress;
import org.springblade.vessel.entity.ServiceApproved;
import org.springblade.vessel.entity.WarrantyClosing;
import org.springblade.vessel.service.IOwnerInformationService;
import org.springblade.vessel.service.IWarrantyClosingService;
import org.springblade.vessel.vo.WarrantyClosingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2022-04-29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/warrantyClosing")
@Api(value = "", tags = "接口")
public class WarrantyClosingController extends BladeController {

	private final IWarrantyClosingService warrantyClosingService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入WarrantyClosing")
	public R<WarrantyClosing> detail(WarrantyClosing warrantyClosing) {
		WarrantyClosing detail = warrantyClosingService.getOne(Condition.getQueryWrapper(warrantyClosing));
		return R.data(detail);
	}


	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入WarrantyClosing")
	public R<IPage<WarrantyClosing>> list(@ApiIgnore @RequestParam Map<String, Object> warrantyClosing, Query query) {
		IPage<WarrantyClosing> pages = warrantyClosingService.page(Condition.getPage(query), Condition.getQueryWrapper(warrantyClosing,WarrantyClosing.class));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入ownerInformation")
	public R<IPage<WarrantyClosingVO>> page(WarrantyClosingVO warrantyClosing, Query query) {
		IPage<WarrantyClosingVO> pages = warrantyClosingService.selectWarrantyClosingPage(Condition.getPage(query), warrantyClosing);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入WarrantyClosingVO")
	public R save(@Valid @RequestBody WarrantyClosing warrantyClosing) {
		return R.status(warrantyClosingService.save(warrantyClosing));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入warrantyClosing")
	public R update(@Valid @RequestBody WarrantyClosing warrantyClosing) {
		return R.status(warrantyClosingService.updateById(warrantyClosing));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入warrantyClosing")
	public R submit(@Valid @RequestBody WarrantyClosing warrantyClosing) {
		return R.status(warrantyClosingService.saveOrUpdate(warrantyClosing));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(warrantyClosingService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 封保
	 */
	@PostMapping("/shipClosing")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "封保船舶", notes = "传入ids")
	public R shipClosing(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {

		String[] idArray = StringUtils.split(ids, ",");
		List<Long> collect = Arrays.stream(idArray).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
		return R.status(warrantyClosingService.changeStatus(collect,0));
	}

}
