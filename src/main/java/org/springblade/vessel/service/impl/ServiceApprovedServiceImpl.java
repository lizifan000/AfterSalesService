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
package org.springblade.vessel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.FileUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.Utils.EchartsUtils;
import org.springblade.vessel.entity.DocumentInformation;
import org.springblade.vessel.entity.Information;
import org.springblade.vessel.entity.ProcessingProgress;
import org.springblade.vessel.entity.ServiceApproved;
import org.springblade.vessel.mapper.ServiceApprovedMapper;
import org.springblade.vessel.service.*;
import org.springblade.vessel.vo.ServiceApprovedVO;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static org.springblade.vessel.Utils.EchartsUtils.getFirstDayOfMonth;

/**
 * 服务实现类
 *
 * @author BladeX
 * @since 2022-05-15
 */
@Slf4j
@Service
@AllArgsConstructor
public class ServiceApprovedServiceImpl extends BaseServiceImpl<ServiceApprovedMapper, ServiceApproved> implements IServiceApprovedService {

	private final IProcessingProgressService processingProgressService;
	private final IDocumentInformationService documentInformationService;
	private final IInformationService informationService;

	@Override
	public IPage<ServiceApprovedVO> selectServiceApprovedPage(IPage<ServiceApprovedVO> page, ServiceApprovedVO serviceApproved) {
		return page.setRecords(baseMapper.selectServiceApprovedPage(page, serviceApproved));
	}

	@Override
	@SneakyThrows
	public Map<String, Object> getPolicyData(Long policyId) {
		ProcessingProgress processingProgress = processingProgressService.getById(policyId);
		List<DocumentInformation> documentInformationList = documentInformationService.list(Wrappers.<DocumentInformation>lambdaQuery().eq(DocumentInformation::getPolicyId, policyId));

		log.info("processingProgress is ===================={}", JsonUtil.toJson(processingProgress));
		log.info("documentInformationList is ===================={}", JsonUtil.toJson(documentInformationList));

		Map<String, Object> dataMap = new HashMap<>(25);
		dataMap.put("shipName", processingProgress.getShipName());
		dataMap.put("shipNo", processingProgress.getShipNo());
		dataMap.put("deliveryDate", processingProgress.getDeliveryDate());
		dataMap.put("shipownerName", processingProgress.getShipownerName());
		dataMap.put("shipDescription", processingProgress.getShipDescription());
		dataMap.put("subject", processingProgress.getSubject());
		dataMap.put("policyDate", processingProgress.getPolicyDate());
		dataMap.put("manufactor", processingProgress.getManufactor());
		dataMap.put("major", processingProgress.getMajor());
		dataMap.put("policyNo", processingProgress.getPolicyNo());
		dataMap.put("serialNo", processingProgress.getSerialNo());
		dataMap.put("description", processingProgress.getDescription());
		dataMap.put("damageCause", processingProgress.getDamageCause());
		dataMap.put("labormaterialRequire", processingProgress.getLabormaterialRequire());
		dataMap.put("placedateSituation", processingProgress.getPlacedateSituation());
		dataMap.put("equipmentLocation", processingProgress.getEquipmentLocation());
		dataMap.put("master", processingProgress.getMaster());
		dataMap.put("chiefEngineer", processingProgress.getChiefEngineer());
		dataMap.put("guaranteeEngineer", processingProgress.getGuaranteeEngineer());

		if (Func.isNotEmpty(documentInformationList)) {
			List<String> fileName = documentInformationList.stream().map(d -> d.getDocumentName()).collect(Collectors.toList());
			dataMap.put("fileNames", Func.join(fileName));
		}

		if (Func.isNotEmpty(processingProgress.getMasterSignature())) {
			ByteArrayOutputStream byteArrayOut;
			URL url = new URL(processingProgress.getMasterSignature());
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			if (httpURLConnection.getResponseCode() == 200) {
				BufferedImage bufferImg = ImageIO.read(url.openStream());
				byteArrayOut = new ByteArrayOutputStream();
				// 图片后缀格式
				String sfx = FileUtil.getFileExtension(processingProgress.getMasterSignature());
				ImageIO.write(bufferImg, sfx, byteArrayOut);
				bufferImg.flush();
				dataMap.put("masterSignature", byteArrayOut.toByteArray());
				if (Func.isNotEmpty(byteArrayOut)) {
					byteArrayOut.close();
				}
			}
		}
		if (Func.isNotEmpty(processingProgress.getChiefEngineerSignature())) {
			ByteArrayOutputStream byteArrayOut;
			URL url = new URL(processingProgress.getChiefEngineerSignature());
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			if (httpURLConnection.getResponseCode() == 200) {
				BufferedImage bufferImg = ImageIO.read(url.openStream());
				byteArrayOut = new ByteArrayOutputStream();
				// 图片后缀格式
				String sfx = FileUtil.getFileExtension(processingProgress.getChiefEngineerSignature());
				ImageIO.write(bufferImg, sfx, byteArrayOut);
				bufferImg.flush();
				dataMap.put("chiefEngineerSignature", byteArrayOut.toByteArray());
				if (Func.isNotEmpty(byteArrayOut)) {
					byteArrayOut.close();
				}
			}
		}
		if (Func.isNotEmpty(processingProgress.getGuaranteeEngineerSignature())) {
			ByteArrayOutputStream byteArrayOut;
			URL url = new URL(processingProgress.getGuaranteeEngineerSignature());
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			if (httpURLConnection.getResponseCode() == 200) {
				BufferedImage bufferImg = ImageIO.read(url.openStream());
				byteArrayOut = new ByteArrayOutputStream();
				// 图片后缀格式
				String sfx = FileUtil.getFileExtension(processingProgress.getGuaranteeEngineerSignature());
				ImageIO.write(bufferImg, sfx, byteArrayOut);
				bufferImg.flush();
				dataMap.put("guaranteeEngineerSignature", byteArrayOut.toByteArray());
				if (Func.isNotEmpty(byteArrayOut)) {
					byteArrayOut.close();
				}
			}
		}
		dataMap.put("processingProgress", processingProgress);


		return dataMap;
	}

	@Override
	public Map<String, Long> getMonthClosedClaim() {
		Map<String,Long> totalClaim = new LinkedHashMap<>();
		LambdaQueryWrapper<ServiceApproved> wrapper = new LambdaQueryWrapper<>();
		for (int i = 12;i >= 0; i--){
			String abscissa = EchartsUtils.getAbscissa(i);
			String[] monthDate = abscissa.split("-");
			String firstDayOfMonth = getFirstDayOfMonth(Integer.parseInt(monthDate[0]), Integer.parseInt(monthDate[1]));
			String lastDayOfMonth = EchartsUtils.getLastDayOfMonth(Integer.parseInt(monthDate[0]), Integer.parseInt(monthDate[1]));
			wrapper.ge(ServiceApproved::getCompletionDate,firstDayOfMonth).le(ServiceApproved::getCompletionDate,lastDayOfMonth).in(ServiceApproved::getStatus,1,0);
			totalClaim.put(abscissa,super.count(wrapper));
			wrapper.clear();
		}
		return totalClaim;
	}

	@Override
	public Object[] getNowClaim(String shipNo) {
		if(Objects.equals(shipNo, "")){
			throw new ServiceException("请输入船号！");
		}
		Object[] objs = new Object[3];
		LambdaQueryWrapper<ServiceApproved> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(ServiceApproved::getShipNo,shipNo);
		long count1 = super.count(wrapper);
		wrapper.clear();
		wrapper.eq(ServiceApproved::getShipNo,shipNo).in(ServiceApproved::getStatus,0,1);
		long count2 = super.count(wrapper);
		wrapper.clear();
		objs[0] = count1;
		objs[1] = count2;
		if(count1 == 0){
			objs[2] = 0;
		}
		else{
			double count3 = (double)count2/(double)count1;
			objs[2] = count3;
		}

		return objs;
	}

	@Override
	public Object[] getTotalClaim(String startTime, String endTime) {
		if(Objects.equals(startTime, "") || Objects.equals(endTime, "")){
			throw new ServiceException("请选择日期！");
		}
		Object[] objs = new Object[3];
		LambdaQueryWrapper<ServiceApproved> wrapper = new LambdaQueryWrapper<>();
		wrapper.ge(ServiceApproved::getPolicyDate,startTime).le(ServiceApproved::getPolicyDate,endTime);
		long count1 = super.count(wrapper);
		wrapper.clear();
		wrapper.ge(ServiceApproved::getCompletionDate,startTime).le(ServiceApproved::getCompletionDate,endTime).in(ServiceApproved::getStatus,0,1);
		long count2 = super.count(wrapper);
		wrapper.clear();
		objs[0] = count1;
		objs[1] = count2;
		if(count1 == 0){
			objs[2] = 0;
		}
		else{
			double count3 = (double)count2/(double)count1;
			objs[2] = count3;
		}
		return objs;

	}

	@Override
	public Map<String, Double> getMonthClosingRate() {
		Map<String,Double> monthlyClosingRate = new LinkedHashMap<String,Double>();
		LambdaQueryWrapper<ServiceApproved> closedWrapper = new LambdaQueryWrapper<>();
		LambdaQueryWrapper<ServiceApproved> totalWrapper = new LambdaQueryWrapper<>();
		for (int i = 12;i >= 0; i--){
			String abscissa = EchartsUtils.getAbscissa(i);
			String[] monthDate = abscissa.split("-");
			String lastDayOfMonth = EchartsUtils.getLastDayOfMonth(Integer.parseInt(monthDate[0]), Integer.parseInt(monthDate[1]));
			closedWrapper.le(ServiceApproved::getCompletionDate,lastDayOfMonth).in(ServiceApproved::getStatus,0,1);
			totalWrapper.le(ServiceApproved::getPolicyDate,lastDayOfMonth);
			long closedNum = super.count(closedWrapper);
			long totalNum = super.count(totalWrapper);
			if(!(totalNum == 0)){
				double closingRate = (double)closedNum/totalNum;
				monthlyClosingRate.put(abscissa,closingRate);

			}
			else{
				monthlyClosingRate.put(abscissa,0.00);
			}
			closedWrapper.clear();
			totalWrapper.clear();
		}
		return monthlyClosingRate;
	}

	@Override
	public Object[] getTotalClosingRateNoParam() {
		Object[] objs = new Object[3];
		LambdaQueryWrapper<Information> infoWrapper = new LambdaQueryWrapper<>();
		LambdaQueryWrapper<ServiceApproved> claimWrapper = new LambdaQueryWrapper<>();
		long count1 = informationService.count(infoWrapper);
		long count2 = super.count(claimWrapper);
		objs[0] = count1;
		objs[1] = count2;
		if(count1==0){
			objs[2] = 0;
		}
		else {
			double count3 = (double)count2/(double)count1;
			objs[2] = count3;
		}
		return objs;
	}

	@Override
	public Object[] getTotalClosingRate(String startTime, String endTime) {
		if(Func.isEmpty(startTime) || Func.isEmpty(endTime)){
			throw new ServiceException("请选择交船日期！");
		}

		Object[] objs = new Object[3];
		LambdaQueryWrapper<Information> infoWrapper = new LambdaQueryWrapper<>();
		LambdaQueryWrapper<ServiceApproved> claimWrapper = new LambdaQueryWrapper<>();

		infoWrapper.ge(Information::getDeliveryDate,startTime).le(Information::getDeliveryDate,endTime);
		long count1 = informationService.count(infoWrapper);
		objs[0] = count1;

		claimWrapper.ge(ServiceApproved::getPolicyDate,startTime).le(ServiceApproved::getPolicyDate,endTime);
		long count2 = super.count(claimWrapper);
		objs[1] = count2;
		if(count1==0){
			objs[2] = 0;
		}
		else {
			double count3 = (double)count2/(double)count1;
			objs[2] = count3;
		}
		return objs;
	}

	@SneakyThrows
	@Override
	public Map<String, Long> getDirectorTotalClaim() {

		Map<String,Long> directorTotalClaim = new LinkedHashMap<>();
		LambdaQueryWrapper<ServiceApproved> claimWrapper = new LambdaQueryWrapper<>();
		QueryWrapper<Information> infoWrapper = new QueryWrapper<>();
		infoWrapper.select("DISTINCT service_director");
		List<Information> list = informationService.list(infoWrapper);

		for (Information information : list) {
			String serviceDirector = information.getServiceDirector();
			claimWrapper.eq(ServiceApproved::getServiceDirector,serviceDirector).ge(ServiceApproved::getPolicyDate,EchartsUtils.getFiveYearDate()).le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate());
			long totalClaim = super.count(claimWrapper);
			directorTotalClaim.put(serviceDirector,totalClaim);
			claimWrapper.clear();
		}
		return directorTotalClaim;
	}

	@SneakyThrows
	@Override
	public Map<String, Long> getDirectorUnclosedClaim() {
		Map<String,Long> directorTotalClaim = new LinkedHashMap<>();
		LambdaQueryWrapper<ServiceApproved> claimWrapper = new LambdaQueryWrapper<>();
		QueryWrapper<Information> infoWrapper = new QueryWrapper<>();
		infoWrapper.select("DISTINCT service_director");
		List<Information> list = informationService.list(infoWrapper);
		for (Information information : list) {
			String serviceDirector = information.getServiceDirector();
			claimWrapper.eq(ServiceApproved::getServiceDirector,serviceDirector).eq(ServiceApproved::getStatus,2).ge(ServiceApproved::getPolicyDate,EchartsUtils.getFiveYearDate()).le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate());
			long totalClaim = super.count(claimWrapper);
			directorTotalClaim.put(serviceDirector,totalClaim);
			claimWrapper.clear();
		}
		return directorTotalClaim;
	}

	@SneakyThrows
	@Override
	public Map<String, Long> getVesselTotalClaim() {
		Map<String,Long> directorTotalClaim = new LinkedHashMap<>();
		QueryWrapper<Information> shipWrapper = new QueryWrapper<>();
		LambdaQueryWrapper<ServiceApproved> claimWrapper = new LambdaQueryWrapper<>();
		shipWrapper.select("DISTINCT ship_no");
		List<Information> list = informationService.list(shipWrapper);
		for (Information information : list) {
			String shipNo = information.getShipNo();
			claimWrapper.eq(ServiceApproved::getShipNo,shipNo).ge(ServiceApproved::getPolicyDate,EchartsUtils.getFiveYearDate()).le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate());
			long totalClaim = super.count(claimWrapper);
			directorTotalClaim.put(shipNo,totalClaim);
			claimWrapper.clear();
		}
		return directorTotalClaim;
	}

	@SneakyThrows
	@Override
	public Map<String, Long> getVesselUnclosedClaim() {
		Map<String,Long> directorTotalClaim = new LinkedHashMap<>();
		QueryWrapper<Information> shipWrapper = new QueryWrapper<>();
		LambdaQueryWrapper<ServiceApproved> claimWrapper = new LambdaQueryWrapper<>();
		shipWrapper.select("DISTINCT ship_no");
		shipWrapper.clear();
		List<Information> list = informationService.list(shipWrapper);
		for (Information information : list) {
			String shipNo = information.getShipNo();
			claimWrapper.eq(ServiceApproved::getShipNo,shipNo).eq(ServiceApproved::getStatus,2).ge(ServiceApproved::getPolicyDate,EchartsUtils.getFiveYearDate()).le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate());
			long totalClaim = super.count(claimWrapper);
			directorTotalClaim.put(shipNo,totalClaim);
			claimWrapper.clear();
		}
		return directorTotalClaim;
	}
}
