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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.*;
import org.springblade.vessel.enums.ShipResourceEnum;
import org.springblade.vessel.service.IDocumentInformationService;
import org.springblade.vessel.service.IInformationService;
import org.springblade.vessel.service.IProcessingProgressService;
import org.springblade.vessel.service.IServiceApprovedService;
import org.springblade.vessel.vo.ProcessingProgressVO;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2022-05-08
 */
@RestController
@AllArgsConstructor
@RequestMapping("/processingProgress")
@Api(value = "", tags = "接口")
public class ProcessingProgressController extends BladeController {

	private final IProcessingProgressService processingProgressService;
	private final IInformationService informationService;
	private final IServiceApprovedService serviceApprovedService;
	private final IDocumentInformationService documentInformationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入processingProgress")
	public R<ProcessingProgress> detail(ProcessingProgress processingProgress) {
		ProcessingProgress detail = processingProgressService.getOne(Condition.getQueryWrapper(processingProgress));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入processingProgress")
	public R<IPage<ProcessingProgress>> list(ProcessingProgress processingProgress, Query query) {
		IPage<ProcessingProgress> pages = processingProgressService.page(Condition.getPage(query), Condition.getQueryWrapper(processingProgress));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入processingProgress")
	public R<IPage<ProcessingProgressVO>> page(ProcessingProgressVO processingProgress, Query query) {
		IPage<ProcessingProgressVO> pages = processingProgressService.selectProcessingProgressPage(Condition.getPage(query), processingProgress);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入processingProgress")
	public R save(@Valid @RequestBody ProcessingProgress processingProgress) {
		return R.status(processingProgressService.save(processingProgress));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入processingProgress")
	public R update(@Valid @RequestBody ProcessingProgress processingProgress) {
		return R.status(processingProgressService.updateById(processingProgress));
	}




	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入processingProgress")
	public R submit(@Valid @RequestBody ProcessingProgress processingProgress) {
		if(Func.isNotEmpty(processingProgress.getShipNo())){
			String shipNo = processingProgress.getShipNo();
			processingProgress.setServiceDirector(informationService.getOne(Wrappers.<Information>lambdaQuery()
				.eq(Information::getShipNo, shipNo)).getServiceDirector());
			try{
				boolean b = processingProgressService.saveOrUpdate(processingProgress);
				return R.status(b);
			}
			catch(DuplicateKeyException ex){
				throw new RuntimeException("Duplicate Claim No!");
			}
		}
		else{
			return R.status(processingProgressService.saveOrUpdate(processingProgress));
		}

	}



	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(processingProgressService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 审核
	 *//*
	@PostMapping("/examine")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "审批", notes = "传入id")
	public R preview(@ApiParam(value = "主键集合", required = true)@RequestParam String ids) {

		String[] idArray = StringUtils.split(ids, ",");
		List<Long> collect = Arrays.stream(idArray).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
		UpdateWrapper<Information> wrapper1 = new UpdateWrapper<>();
		QueryWrapper<Information> wrapper2 = new QueryWrapper<>();

		for (String s : idArray) {
			ProcessingProgress processingProgress = processingProgressService.getById(s);
			wrapper2.eq("ship_no",processingProgress.getShipNo());
			Information information = informationService.getOne(wrapper2);
			wrapper1.eq("ship_no",processingProgress.getShipNo());
			wrapper1.set("policy_number",information.getPolicyNumber()+1);
			informationService.update(wrapper1);
		}
		return R.status(processingProgressService.changeStatus(collect,0));
	}*/

	/**
	 * 审核
	 */
	@PostMapping("/examine")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "审批", notes = "传入id")
	public R preview(@ApiParam(value = "主键集合", required = true)@RequestParam String ids) {
		Date date=new Date();
		String[] idArray = StringUtils.split(ids, ",");
		List<Long> collect = Arrays.stream(idArray).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
		ServiceApproved serviceApproved = new ServiceApproved();
		List<ProcessingProgress> processingProgresses = processingProgressService.listByIds(collect);

		for(Iterator<ProcessingProgress> it=processingProgresses.iterator();it.hasNext();){
			ProcessingProgress processingProgress = it.next();
			processingProgress.setCreateTime(date);
			//BeanUtils.copyProperties(processingProgress, serviceApproved);
			serviceApproved = Objects.requireNonNull(BeanUtil.copy(processingProgress, ServiceApproved.class));
			serviceApprovedService.save(serviceApproved);
			it.remove();
		}

		return R.status(processingProgressService.changeStatus(collect,0));
	}

	/**
	 * 查询最新保单号
	 * @return
	 */
	@GetMapping("/getLatestPolicyNo")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "详情", notes = "传入船号")
	public R<ProcessingProgress> getPolicyNo(String shipNo) {
		QueryWrapper<ProcessingProgress> wrapper = new QueryWrapper<>();
		wrapper.eq("ship_no",shipNo).select("max(policy_no) as policy_no");
		ProcessingProgress one=processingProgressService.getOne(wrapper);
		if(one == null){
			ProcessingProgress processingProgress = new ProcessingProgress();
			processingProgress.setPolicyNo(1);
			return R.data(processingProgress);
		}
		else{
			one.setPolicyNo(one.getPolicyNo()+1);
			return R.data(one);
		}

	}
	/**
	 * 发送保单
	 */
	@PostMapping("/sendPolicy")
	@ApiOperationSupport(order = 10)
	@ApiOperation(value = "发送保单", notes = "传入id")
	public R sendPolicy(@RequestParam String id) {
		return R.status(processingProgressService.changeStatus(Func.toLongList(id),2));
	}

	/**
	 * 删除保单附件
	 */
	@PostMapping("/policy/remove")
	@ApiOperationSupport(order = 11)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R removeDoc(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {

		String[] idArray = StringUtils.split(ids, ",");
		List<Long> arr = new ArrayList<>();

		for (String s : idArray) {
			QueryWrapper<DocumentInformation> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("policy_id",s);
			List<DocumentInformation> list = documentInformationService.list(queryWrapper);
			for(int i = 0; i < list.size(); i++){
				arr.add(list.get(i).getId());
			}
		}
		return R.status(documentInformationService.removeByIds(arr));



		/*QueryWrapper<DocumentInformation> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("policy_id",ids);
		List<DocumentInformation> list = documentInformationService.list(queryWrapper);
		Long[] arr = new Long[list.size()];
		for(int i = 0; i < list.size(); i++){
			arr[i] = list.get(i).getId();
		}
		return R.status(documentInformationService.removeByIds(Arrays.asList(arr)));*/
	}

}
