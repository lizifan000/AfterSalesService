package org.springblade.vessel.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.ServiceApproved;
import org.springblade.vessel.service.IServiceApprovedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

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
@RequestMapping("/monthlyClaimClosingRate")
@Api(value = "", tags = "接口")
public class MonthlyPolicyClosingRateController extends BladeController {

	private final IServiceApprovedService serviceApprovedService;



	/**
	 * 根据船号查询某条船保单关闭率
	 * @return
	 */
	@GetMapping("/searchClosingRate")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "单船保单关闭率", notes = "传入船号")
	public R<Double> getClosingRate(@RequestParam String shipNo) {
		LambdaQueryWrapper<ServiceApproved> closedWrapper = new LambdaQueryWrapper<>();
		LambdaQueryWrapper<ServiceApproved> totalWrapper = new LambdaQueryWrapper<>();
		closedWrapper.eq(ServiceApproved::getShipNo,shipNo).in(ServiceApproved::getStatus,0,1);
		totalWrapper.eq(ServiceApproved::getShipNo,shipNo);
		long closedNum = serviceApprovedService.count(closedWrapper);
		long totalNum = serviceApprovedService.count(totalWrapper);
		double closingRate =  (double)closedNum/totalNum;
		closedWrapper.clear();
		totalWrapper.clear();
		return R.data(closingRate);
	}

	/**
	 * 初始化默认船舶数量，保单数，平均保单数
	 * 无参数
	 * @return
	 */
	@GetMapping("/searchTotalClosingRateNoParam")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "船舶数量，保单数，平均保单数", notes = "无参数")
	public Object[] getTotalClosingRateNoParam() {
		return (serviceApprovedService.getTotalClosingRateNoParam());
	}

	/**
	 * 按交船时间船舶数量，保单数，平均保单数
	 * 传入日期区间
	 * @return
	 */
	@GetMapping("/searchTotalClosingRate")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "船舶数量，保单数，平均保单数", notes = "传入日期区间")
	public Object[] getTotalClosingRate(@RequestParam String startTime, @RequestParam String endTime) {

		return (serviceApprovedService.getTotalClosingRate(startTime,endTime));
	}

	/**
	 * 图表初始化各个月份的保单关闭率
	 * @return
	 */
	@GetMapping("/monthClosingRate")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "每月保单关闭率", notes = "无参数")
	public R<Map<String, Double>> getMonthClosingRate() {
		return R.data(serviceApprovedService.getMonthClosingRate());

	}






}
