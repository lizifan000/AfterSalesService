package org.springblade.vessel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.api.R;
import org.springblade.vessel.Utils.EchartsUtils;
import org.springblade.vessel.entity.Progress;
import org.springblade.vessel.service.IInformationService;
import org.springblade.vessel.service.IProgressService;
import org.springblade.vessel.service.IServiceApprovedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static org.springblade.vessel.Utils.EchartsUtils.getFirstDayOfMonth;

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
@RequestMapping("/monthlyVesselCost")
@Api(value = "", tags = "接口")
public class MonthlyVesselCostController extends BladeController {

	private final IServiceApprovedService serviceApprovedService;
	private final IInformationService informationService;
	private final IProgressService progressService;


	/**
	 * 根据日期区间查询所有船保单成本
	 * @return
	 */
	@GetMapping("/searchCostByDate")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "日期区间内所有船保单成本", notes = "传入日期区间")
	public R<Double> getCostByDate(@RequestParam String startTime,@RequestParam String endTime) {
		if(Objects.equals(startTime, "") || Objects.equals(endTime, "")){
			throw new ServiceException("请选择日期！");
		}
		QueryWrapper<Progress> costWrapper = new QueryWrapper<>();
		costWrapper.select("IFNULL(SUM(cost),0) as cost").ge("create_time",startTime).le("create_time",endTime);
		return R.data(progressService.getOne(costWrapper).getCost()/10000);
	}


	/**
	 * 初始化12个月所有船合计成本
	 * @return
	 */
	@GetMapping("/searchTotalCost")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "所有船总成本", notes = "无参数")
	public R<Double> getMonthlyTotalCost() {

		double costTotal = 0.00;
		QueryWrapper<Progress> costWrapper = new QueryWrapper<>();

		for (int i = 12;i >= 0; i--){
			String abscissa = EchartsUtils.getAbscissa(i);
			String[] monthDate = abscissa.split("-");
			String firstDayOfMonth = getFirstDayOfMonth(Integer.parseInt(monthDate[0]), Integer.parseInt(monthDate[1]));
			String lastDayOfMonth = EchartsUtils.getLastDayOfMonth(Integer.parseInt(monthDate[0]), Integer.parseInt(monthDate[1]));
			costWrapper.ge("create_time",firstDayOfMonth).le("create_time",lastDayOfMonth).select("IFNULL(SUM(cost),0) as cost");
			double cost = progressService.getOne(costWrapper).getCost()/10000;
			costTotal+=cost;
			costWrapper.clear();
		}
		return R.data(costTotal);
	}


	/**
	 * 图表初始化各个月份的保单总成本
	 * @return
	 */
	@GetMapping("/monthlyVesselClaimCost")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "各个月份的保单成本", notes = "无参数")
	public R<Map<String, Double>> getMonthlyVesselClaimCost() {
		Map<String,Double> totalCost = new LinkedHashMap<>();
		QueryWrapper<Progress> costWrapper = new QueryWrapper<>();

		for (int i = 12;i >= 0; i--){
			String abscissa = EchartsUtils.getAbscissa(i);
			String[] monthDate = abscissa.split("-");
			String firstDayOfMonth = getFirstDayOfMonth(Integer.parseInt(monthDate[0]), Integer.parseInt(monthDate[1]));
			String lastDayOfMonth = EchartsUtils.getLastDayOfMonth(Integer.parseInt(monthDate[0]), Integer.parseInt(monthDate[1]));
			costWrapper.ge("create_time",firstDayOfMonth).le("create_time",lastDayOfMonth).select("IFNULL(SUM(cost),0) as cost");
			double cost = progressService.getOne(costWrapper).getCost()/10000;
			totalCost.put(abscissa,cost);
			costWrapper.clear();
		}
		return R.data(totalCost);



	}






}
