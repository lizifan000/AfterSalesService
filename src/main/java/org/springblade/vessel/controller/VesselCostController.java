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
import org.springblade.vessel.entity.Information;
import org.springblade.vessel.entity.ProcessingProgress;
import org.springblade.vessel.entity.Progress;
import org.springblade.vessel.entity.ServiceApproved;
import org.springblade.vessel.service.IInformationService;
import org.springblade.vessel.service.IProgressService;
import org.springblade.vessel.service.IServiceApprovedService;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/vesselCost")
@Api(value = "", tags = "接口")
public class VesselCostController extends BladeController {

	private final IServiceApprovedService serviceApprovedService;
	private final IProgressService progressService;

	private final IInformationService informationService;


	/**
	 * 船号列表
	 * @return
	 */
	@GetMapping("/searchVesselNo")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "船号列表", notes = "无参数")
	public R<List<String>> getVesselNo() {
		LambdaQueryWrapper<Information> shipWrapper = new LambdaQueryWrapper<>();
		shipWrapper.select(Information::getShipNo);
		List<Information> list = informationService.list(shipWrapper);
		shipWrapper.clear();
		int size = list.size();
		List<String> shipList = new ArrayList<String>(size);
		for (Information information : list) {
			shipList.add(information.getShipNo());
		}
		return R.data(shipList);
	}

	/**
	 * 根据船号查询单船保单成本
	 * @return
	 */
	@GetMapping("/searchCostByVesselNo")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "单船保单成本", notes = "船号")
	public R<Double> getCostByVesselNo(@RequestParam String shipNo) {
		/*QueryWrapper<ServiceApproved> costWrapper = new QueryWrapper<>();
		costWrapper.eq("ship_no",shipNo).select("IFNULL(SUM(cost),0) as cost");
		return R.data(serviceApprovedService.getOne(costWrapper).getCost()/10000);*/
		return null;
	}


	/**
	 * 初始化所有船合计成本
	 * @return
	 */
	@SneakyThrows
	@GetMapping("/searchTotalCost")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "所有船总成本", notes = "无参数")
	public R<Double[]> getTotalCost() {
		List<Information> infoList = informationService.list(Wrappers.<Information>lambdaQuery()
			.ge(Information::getDeliveryDate, EchartsUtils.getFiveYearDate())
			.le(Information::getDeliveryDate, EchartsUtils.getCurrentDate()).select(Information::getShipNo));
		List<String> shipNoList = new ArrayList<>();
		for (Information info : infoList) {
			shipNoList.add(info.getShipNo()) ;
		}

		Double[] costs = new Double[5];
		QueryWrapper<Progress> costWrapper = new QueryWrapper<>();
		costWrapper.select("IFNULL(SUM(cost),0) as cost").in("ship_no",shipNoList);
		double costTotal = progressService.getOne(costWrapper).getCost() / 10000;
		costs[0] = costTotal;
		costWrapper.select("IFNULL(SUM(cost),0) as cost").eq("cost_type","物流费用").in("ship_no",shipNoList);
		double costExpress = progressService.getOne(costWrapper).getCost() / 10000;
		costs[1] = costExpress;

		costWrapper.clear();
		costWrapper.select("IFNULL(SUM(cost),0) as cost").eq("cost_type","采购费用").in("ship_no",shipNoList);
		double costPurchase = progressService.getOne(costWrapper).getCost() / 10000;
		costs[2] = costPurchase;

		costWrapper.clear();
		costWrapper.select("IFNULL(SUM(cost),0) as cost").eq("cost_type","差旅费用").in("ship_no",shipNoList);
		double costTravel = progressService.getOne(costWrapper).getCost() / 10000;
		costs[3] = costTravel;

		costWrapper.clear();
		costWrapper.select("IFNULL(SUM(cost),0) as cost").eq("cost_type","维修费用").in("ship_no",shipNoList);
		double costRepair = progressService.getOne(costWrapper).getCost() / 10000;
		costs[4] = costRepair;

		return R.data(costs);

	}


	/**
	 * 图表初始化各船的保单总成本
	 * @return
	 */
	@GetMapping("/vesselClaimCost")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "各个船的保单成本", notes = "无参数")
	public R<Map<String, Double>> getVesselClaimCost() {

		LambdaQueryWrapper<Information> shipWrapper = new LambdaQueryWrapper<>();
		shipWrapper.select(Information::getShipNo);
		List<Information> list = informationService.list(shipWrapper);
		QueryWrapper<Progress> costWrapper = new QueryWrapper<>();

		Map<String, Double> vesselClaimCost = new LinkedHashMap<>();
		for (Information information : list) {
			String shipNo = information.getShipNo();
			costWrapper.eq("ship_no",shipNo).select("IFNULL(SUM(cost),0) as cost");
			double cost = (progressService.getOne(costWrapper).getCost()) / 10000;
			vesselClaimCost.put(shipNo,cost);
			costWrapper.clear();
		}

		return R.data(vesselClaimCost);

	}

	/**
	 * 查询多个船号的成本
	 */
	@SneakyThrows
	@PostMapping("/searchCostByParam")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "查询船号的成本", notes = "传入多个船号")
	public double[] searchCostByParam(@ApiParam(value = "主键集合", required = true)@RequestParam String shipNos) {
		String[] shipNoArray = StringUtils.split(shipNos, ",");
		double[] costArray = new double[5];

		double costTotal = 0;		//总费用
		double costExpress = 0;		//物流费用
		double costPurchase = 0;	//采购费用
		double costTravel = 0;		//差旅费用
		double costRepair = 0;		//维修费用

		QueryWrapper<Progress> wrapper = new QueryWrapper<>();
		costTotal = (progressService.getOne(wrapper.select("IFNULL(SUM(cost),0) as cost").in("ship_no",shipNoArray)).getCost())/10000;
			wrapper.clear();

		costExpress = (progressService.getOne(wrapper.select("IFNULL(SUM(cost),0) as cost").in("ship_no", shipNoArray).eq("cost_type","物流费用")).getCost())/10000;
			wrapper.clear();

		costPurchase = (progressService.getOne(wrapper.select("IFNULL(SUM(cost),0) as cost").in("ship_no", shipNoArray).eq("cost_type","采购费用")).getCost())/10000;
			wrapper.clear();

		costTravel = (progressService.getOne(wrapper.select("IFNULL(SUM(cost),0) as cost").in("ship_no", shipNoArray).eq("cost_type","差旅费用")).getCost())/10000;
			wrapper.clear();

		costRepair = (progressService.getOne(wrapper.select("IFNULL(SUM(cost),0) as cost").in("ship_no", shipNoArray).eq("cost_type","维修费用")).getCost())/10000;
			wrapper.clear();


		costArray[0] = costTotal;
		costArray[1] = costExpress;
		costArray[2] = costPurchase;
		costArray[3] = costTravel;
		costArray[4] = costRepair;

		return costArray;
	}


}
