package org.springblade.vessel.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.vessel.Utils.EchartsUtils;
import org.springblade.vessel.entity.Information;
import org.springblade.vessel.entity.ServiceApproved;
import org.springblade.vessel.service.IInformationService;
import org.springblade.vessel.service.IServiceApprovedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/directorClaimQuantity")
@Api(value = "", tags = "接口")
public class DirectorClaimQuantityController extends BladeController {

	private final IServiceApprovedService serviceApprovedService;
	private final IInformationService informationService;

	/**
	 * 售后服务人员名单
	 * @return
	 */
	@GetMapping("/searchDirector")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "售后服务人员名单", notes = "无参数")
	public R<List<String>> getDirector() {
		return R.data(informationService.getDirector());
	}

	/**
	 * 初始化保单总量
	 * @return
	 */
	@SneakyThrows
	@GetMapping("/searchTotalClaim")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "保单总量", notes = "无参数")
	public R<Long> getTotalClaim() {
		return R.data(serviceApprovedService.count(Wrappers.<ServiceApproved>lambdaQuery().ge(ServiceApproved::getPolicyDate, EchartsUtils.getFiveYearDate()).le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate())));
	}

	/**
	 * 初始化未关闭保单量
	 * @return
	 */
	@SneakyThrows
	@GetMapping("/searchUnclosedClaim")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "未关闭保单量", notes = "无参数")
	public R<Long> getUnclosedClaim() {

		return R.data(serviceApprovedService.count(Wrappers.<ServiceApproved>lambdaQuery().ge(ServiceApproved::getPolicyDate, EchartsUtils.getFiveYearDate()).le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate()).eq(ServiceApproved::getStatus,2)));
	}

	/**
	 * 初始化保单关闭率
	 * @return
	 */
	@SneakyThrows
	@GetMapping("/searchClosingRate")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "保单关闭率", notes = "无参数")
	public R<Double> getClosingRate() {

		long closedNum = serviceApprovedService.count(Wrappers.<ServiceApproved>lambdaQuery().ge(ServiceApproved::getPolicyDate, EchartsUtils.getFiveYearDate()).le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate()).in(ServiceApproved::getStatus,0,1));
		long totalNum = serviceApprovedService.count(Wrappers.<ServiceApproved>lambdaQuery().ge(ServiceApproved::getPolicyDate, EchartsUtils.getFiveYearDate()).le(ServiceApproved::getPolicyDate,EchartsUtils.getCurrentDate()));
		double closingRate =  (double)closedNum/totalNum;
		return R.data(closingRate);
	}

	/**
	 * 图表初始化各个售后人员的保单总量
	 * @return
	 */
	@GetMapping("/directorTotalClaim")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "每个售后人员保单总量", notes = "无参数")
	public R<Map<String, Long>> getDirectorTotalClaim() {
		return R.data(serviceApprovedService.getDirectorTotalClaim());
	}

	/**
	 * 图表初始化各个售后人员的未关闭保单量
	 * @return
	 */
	@GetMapping("/directorUnclosedClaim")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "每个售后人员未关闭保单量", notes = "无参数")
	public R<Map<String, Long>> getDirectorUnclosedClaim() {
		return R.data(serviceApprovedService.getDirectorUnclosedClaim());
	}





}
