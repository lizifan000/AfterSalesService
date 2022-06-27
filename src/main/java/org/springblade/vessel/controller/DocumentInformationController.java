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
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.DocumentInformation;
import org.springblade.vessel.service.IDocumentInformationService;
import org.springblade.vessel.vo.DocumentInformationVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2022-05-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/documentInformation")
@Api(value = "", tags = "接口")
public class DocumentInformationController extends BladeController {

	private final IDocumentInformationService documentInformationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入documentInformation")
	public R<DocumentInformation> detail(DocumentInformation documentInformation) {
		DocumentInformation detail = documentInformationService.getOne(Condition.getQueryWrapper(documentInformation));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入documentInformation")
	public R<IPage<DocumentInformation>> list(DocumentInformation documentInformation, Query query) {
		IPage<DocumentInformation> pages = documentInformationService.page(Condition.getPage(query), Condition.getQueryWrapper(documentInformation));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入documentInformation")
	public R<IPage<DocumentInformationVO>> page(DocumentInformationVO documentInformation, Query query) {
		IPage<DocumentInformationVO> pages = documentInformationService.selectDocumentInformationPage(Condition.getPage(query), documentInformation);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入documentInformation")
	public R save(@Valid @RequestBody DocumentInformation documentInformation) {
		return R.status(documentInformationService.save(documentInformation));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入documentInformation")
	public R update(@Valid @RequestBody DocumentInformation documentInformation) {
		return R.status(documentInformationService.updateById(documentInformation));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入documentInformation")
	public R submit(@Valid @RequestBody DocumentInformation documentInformation) {
		return R.status(documentInformationService.saveOrUpdate(documentInformation));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(documentInformationService.deleteLogic(Func.toLongList(ids)));
	}


}
