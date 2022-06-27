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
import org.springblade.vessel.Utils.EchartsUtils;
import org.springblade.vessel.entity.ServiceApproved;
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
@RequestMapping("/monthlyClaimQuantity")
@Api(value = "", tags = "接口")
public class MonthlyPolicyQuantityController extends BladeController {

	private final IServiceApprovedService serviceApprovedService;

	/**
	 * 根据船号查询现有保单
	 * @return
	 */
	@GetMapping("/nowClaim")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "现有保单,关闭保单，保单关闭率", notes = "传入船号")
	public R<Object[]> getNowClaim(@RequestParam String shipNo) {
		return R.data(serviceApprovedService.getNowClaim(shipNo));
	}

	/**
	 * 根据日期查询累计保单
	 * @return
	 */
	@GetMapping("/searchTotalClaim")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "累计保单,关闭保单，保单关闭率", notes = "传入日期区间")
	public R<Object[]> getTotalClaim(@RequestParam String startTime, @RequestParam String endTime) {
		return R.data(serviceApprovedService.getTotalClaim(startTime,endTime));
	}

	/**
	 * 图表初始化各个月份的新增保单量
	 * @return
	 */
	@GetMapping("/monthTotalClaim")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "每月新增保单量", notes = "无参数")
	public R<Map<String, Long>> getMonthTotalClaim() {
		Map<String,Long> totalClaim = new LinkedHashMap<>();
		LambdaQueryWrapper<ServiceApproved> wrapper = new LambdaQueryWrapper<>();
		for (int i = 12;i >= 0; i--){
			String abscissa = EchartsUtils.getAbscissa(i);
			String[] monthDate = abscissa.split("-");
			String firstDayOfMonth = getFirstDayOfMonth(Integer.parseInt(monthDate[0]), Integer.parseInt(monthDate[1]));
			String lastDayOfMonth = EchartsUtils.getLastDayOfMonth(Integer.parseInt(monthDate[0]), Integer.parseInt(monthDate[1]));
			wrapper.ge(ServiceApproved::getPolicyDate,firstDayOfMonth).le(ServiceApproved::getPolicyDate,lastDayOfMonth);
			totalClaim.put(abscissa,serviceApprovedService.count(wrapper));
			wrapper.clear();
		}
		return R.data(totalClaim);

	}
	/**
	 * 图表初始化各个月份的关闭保单量
	 * @return
	 */
	@GetMapping("/monthClosedClaim")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "每月关闭保单量", notes = "无参数")
	public R<Map<String, Long>> getMonthClosedClaim() {
		return R.data(serviceApprovedService.getMonthClosedClaim());

	}





}
