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

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.FileUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.vessel.entity.ProcessingProgress;
import org.springblade.vessel.entity.ServiceApproved;
import org.springblade.vessel.service.IServiceApprovedService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * 控制器
 *
 * @author BladeX
 * @since 2022-05-15
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/serviceapproved")
@Api(value = "", tags = "接口")
public class ServiceApprovedController extends BladeController {

	private final IServiceApprovedService serviceApprovedService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入serviceApproved")
	public R<ServiceApproved> detail(ServiceApproved serviceApproved) {
		ServiceApproved detail = serviceApprovedService.getOne(Condition.getQueryWrapper(serviceApproved));
		return R.data(detail);
		//ServiceApproved detail = serviceApprovedService.selectServiceApproved(Condition.getQueryWrapper(serviceApproved));
		/*ServiceApproved detail = serviceApprovedService.selectServiceApproved(serviceApproved.getId());
		return R.data(detail);*/
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入serviceApproved")
	public R<IPage<ServiceApproved>> list(ServiceApproved serviceApproved, Query query) {
		IPage<ServiceApproved> pages = serviceApprovedService.page(Condition.getPage(query), Condition.getQueryWrapper(serviceApproved));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	/*@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入serviceApproved")
	public R<IPage<ServiceApprovedVO>> page(ServiceApprovedVO serviceApproved, Query query) {
		IPage<ServiceApprovedVO> pages = serviceApprovedService.selectServiceApprovedPage(Condition.getPage(query), serviceApproved);
		return R.data(pages);
	}*/

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入serviceApproved")
	public R save(@Valid @RequestBody ServiceApproved serviceApproved) {
		return R.status(serviceApprovedService.save(serviceApproved));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入serviceApproved")
	public R update(@Valid @RequestBody ServiceApproved serviceApproved) {
		return R.status(serviceApprovedService.updateById(serviceApproved));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入serviceApproved")
	public R submit(@Valid @RequestBody ServiceApproved serviceApproved) {

		if(serviceApproved.getCompletionDate() ==null){
			LocalDate localDate = LocalDate.now();
			Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
			Date date = Date.from(instant);
			if (!((serviceApproved.getStatus()) == 2)) {
				serviceApproved.setCompletionDate(date);
			}
		}

		return R.status(serviceApprovedService.saveOrUpdate(serviceApproved));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(serviceApprovedService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 导出保单
	 */
	@GetMapping("export-policy")
	@ApiOperationSupport(order = 10)
	@ApiOperation(value = "导出保单")
	@SneakyThrows
	public void exportPolicy(HttpServletResponse response, @ApiIgnore Long policyId) {
		String fileName = "保单数据";
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding(StringPool.UTF_8);
		fileName = URLEncoder.encode(fileName, StringPool.UTF_8);
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

		Resource resource = new ClassPathResource("/templates/new_temple.xlsx");
		//读取Excel
		ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(resource.getFile()).build();
		WriteSheet writeSheet = EasyExcel.writerSheet().build();
		Map<String, Object> dataMap = serviceApprovedService.getPolicyData(policyId);


		ProcessingProgress processingProgress = (ProcessingProgress) dataMap.get("processingProgress");

		JSONArray jsonArray = new JSONArray();
		if (Func.isNotEmpty(processingProgress.getPictureAttachment())) {
			for (String attach : Func.toStrList(processingProgress.getPictureAttachment())) {
				ByteArrayOutputStream byteArrayOut;
				URL url = null;
				url = new URL(attach);
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				if (httpURLConnection.getResponseCode() == 200) {
					BufferedImage bufferImg = ImageIO.read(url.openStream());
					byteArrayOut = new ByteArrayOutputStream();
					// 图片后缀格式
					String sfx = FileUtil.getFileExtension(attach);
					ImageIO.write(bufferImg, sfx, byteArrayOut);
					bufferImg.flush();
					JSONObject obj = new JSONObject();
					obj.put("picture", byteArrayOut.toByteArray());
					jsonArray.add(obj);
					if (Func.isNotEmpty(byteArrayOut)) {
						byteArrayOut.close();
					}
				}
			}
		}
		log.info("dataMap is ==============={}", JsonUtil.toJson(dataMap));
		excelWriter.fill(dataMap, writeSheet);
		// 列表数据填充
		FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
		excelWriter.fill(new FillWrapper("table", jsonArray), fillConfig, writeSheet);
		excelWriter.finish();
	}
}
