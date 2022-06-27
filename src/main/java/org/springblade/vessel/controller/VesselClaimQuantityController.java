package org.springblade.vessel.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.vessel.Utils.EchartsUtils;
import org.springblade.vessel.entity.DocumentInformation;
import org.springblade.vessel.entity.Information;
import org.springblade.vessel.entity.ProcessingProgress;
import org.springblade.vessel.entity.ServiceApproved;
import org.springblade.vessel.service.IInformationService;
import org.springblade.vessel.service.IServiceApprovedService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2022-05-15
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/vesselClaimQuantity")
@Api(value = "", tags = "接口")
public class VesselClaimQuantityController extends BladeController {

	private final IServiceApprovedService serviceApprovedService;
	private final IInformationService informationService;


	/**
	 * 根据船舶年份查询船号列表
	 * @return
	 */
	@PostMapping("/searchVesselNoByYear")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "船号列表", notes = "年份数组")
	public R<ArrayList<String>> getVesselNoByYear(@ApiParam(value = "主键集合", required = true)@RequestParam String years) {
		String[] yearArray = years.split(",");

		ArrayList<String> shipNoList = new ArrayList<String>();
		LambdaQueryWrapper<Information> infoWrapper = new LambdaQueryWrapper<>();
		for (String year : yearArray) {
			infoWrapper.eq(Information::getYear,year).select(Information::getShipNo);
			List<Information> infoList = informationService.list(infoWrapper);
			infoWrapper.clear();
			for (Information information : infoList) {
				shipNoList.add(information.getShipNo());
			}
		}
		return R.data(shipNoList);
	}
	/**
	 * 年份列表
	 * @return
	 */
	@GetMapping("/searchYear")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "年份列表", notes = "无参数")
	public R<List<Year>> getYear() {
		LambdaQueryWrapper<Information> shipWrapper = new LambdaQueryWrapper<>();
		shipWrapper.select(Information::getYear).orderByAsc(Information::getYear);
		List<Information> list = informationService.list(shipWrapper);
		shipWrapper.clear();
		int size = list.size();
		List<Year> yearList = new ArrayList<>(size);
		for (Information information : list) {
			yearList.add(information.getYear());
		}
		return R.data(yearList);
	}

	/**
	 * 初始化保单总量
	 * @return
	 */
	@SneakyThrows
	@GetMapping("/searchTotalClaim")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "保单总量", notes = "无参数")
	public R<Long> getTotalClaim() {
		return R.data(serviceApprovedService.count(Wrappers.<ServiceApproved>lambdaQuery().ge(ServiceApproved::getPolicyDate, EchartsUtils.getFiveYearDate())
			.le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate())));
	}

	/**
	 * 初始化未关闭保单量
	 * @return
	 */
	@SneakyThrows
	@GetMapping("/searchUnclosedClaim")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "未关闭保单量", notes = "无参数")
	public R<Long> getUnclosedClaim() {
		return R.data(serviceApprovedService.count(Wrappers.<ServiceApproved>lambdaQuery().ge(ServiceApproved::getPolicyDate, EchartsUtils.getFiveYearDate())
			.le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate()).eq(ServiceApproved::getStatus,2)));
	}

	/**
	 * 初始化保单关闭率
	 * @return
	 */
	@SneakyThrows
	@GetMapping("/searchClosingRate")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "保单关闭率", notes = "无参数")
	public R<Double> getClosingRate() {

		long closedNum = serviceApprovedService.count(Wrappers.<ServiceApproved>lambdaQuery().ge(ServiceApproved::getPolicyDate, EchartsUtils.getFiveYearDate())
			.le(ServiceApproved::getPolicyDate, EchartsUtils.getCurrentDate()).in(ServiceApproved::getStatus, 0, 1));
		long totalNum = serviceApprovedService.count(Wrappers.<ServiceApproved>lambdaQuery().ge(ServiceApproved::getPolicyDate, EchartsUtils.getFiveYearDate())
			.le(ServiceApproved::getPolicyDate, EchartsUtils.getCurrentDate()));
		double closingRate =  (double)closedNum/totalNum;
		return R.data(closingRate);
	}



	/**
	 * 图表初始化各船的保单总量
	 * @return
	 */
	@GetMapping("/vesselTotalClaim")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "各个船的保单总量", notes = "无参数")
	public R<Map<String, Long>> getVesselTotalClaim() {
		return R.data(serviceApprovedService.getVesselTotalClaim());
	}

	/**
	 * 图表初始化各船的未关闭保单量
	 * @return
	 */
	@GetMapping("/vesselUnclosedClaim")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "各个船的未关闭保单量", notes = "无参数")
	public R<Map<String, Long>> getVesselUnclosedClaim() {
		return R.data(serviceApprovedService.getVesselUnclosedClaim());
	}


	/**
	 * 获取年份和船号
	 * @return
	 */
	@GetMapping("/searchYearAndShip")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "年份及船号列表", notes = "无参数")
	public R<Map<Year, ArrayList<String>>> getYearAndShip() {
		return R.data(informationService.getYearAndShip());
	}

	/**
	 * 查询船号的保单量
	 */
	@SneakyThrows
	@PostMapping("/searchClaimByParam")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "查询船号的保单量", notes = "传入多个船号")
	public Object[] searchClaimByParam(@ApiParam(value = "主键集合", required = true)@RequestParam String shipNos) {
		String[] shipNoArray = StringUtils.split(shipNos, ",");
		Object[] obj = new Object[3];
		int claimNum = 0;
		int claimUnClosedNum = 0;
		double claimClosingRate = 0.00;

		int claimClosedNum = 0;
		for (String shipNo : shipNoArray) {
			long count1 = serviceApprovedService.count(Wrappers.<ServiceApproved>lambdaQuery().eq(ServiceApproved::getShipNo, shipNo)
				.ge(ServiceApproved::getPolicyDate, EchartsUtils.getFiveYearDate()).le(ServiceApproved::getPolicyDate, EchartsUtils.getCurrentDate()));
			claimNum+=count1;

			long count2 = serviceApprovedService.count(Wrappers.<ServiceApproved>lambdaQuery().eq(ServiceApproved::getShipNo, shipNo)
			.ge(ServiceApproved::getPolicyDate,EchartsUtils.getFiveYearDate()).le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate()).in(ServiceApproved::getStatus,0,1));
			claimClosedNum+=count2;

			long count3 = serviceApprovedService.count(Wrappers.<ServiceApproved>lambdaQuery().eq(ServiceApproved::getShipNo, shipNo)
			.ge(ServiceApproved::getPolicyDate,EchartsUtils.getFiveYearDate()).le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate()).eq(ServiceApproved::getStatus,2));
			claimUnClosedNum+=count3;



		}


		obj[0] = claimNum;
		obj[1] = claimUnClosedNum;
		if(claimNum ==0 || claimClosedNum == 0){
			obj[2] = 0;
		}else{
			claimClosingRate = claimClosedNum/(double)claimNum;
			obj[2] = claimClosingRate;
		}

		return obj;
	}

}
