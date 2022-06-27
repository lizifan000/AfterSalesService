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
import org.springblade.vessel.entity.ShipDocumentInformation;
import org.springblade.vessel.service.IShipDocumentInformationService;
import org.springblade.vessel.vo.ShipDocumentInformationVO;
import org.springblade.vessel.wrapper.ShipDocumentInformationWrapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 交船资料表 控制器
 *
 * @author Ricky
 * @since 2022-05-25
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shipdocumentinformation")
@Api(value = "交船资料表", tags = "交船资料表接口")
public class ShipDocumentInformationController extends BladeController {

	private final IShipDocumentInformationService shipDocumentInformationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入shipDocumentInformation")
	public R<ShipDocumentInformationVO> detail(ShipDocumentInformation shipDocumentInformation) {
		ShipDocumentInformation detail = shipDocumentInformationService.getOne(Condition.getQueryWrapper(shipDocumentInformation));
		return R.data(ShipDocumentInformationWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 交船资料表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入shipDocumentInformation")
	public R<IPage<ShipDocumentInformationVO>> list(ShipDocumentInformation shipDocumentInformation, Query query) {
		IPage<ShipDocumentInformation> pages = shipDocumentInformationService.page(Condition.getPage(query), Condition.getQueryWrapper(shipDocumentInformation));
		return R.data(ShipDocumentInformationWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 交船资料表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入shipDocumentInformation")
	public R<IPage<ShipDocumentInformationVO>> page(ShipDocumentInformationVO shipDocumentInformation, Query query) {
		IPage<ShipDocumentInformationVO> pages = shipDocumentInformationService.selectShipDocumentInformationPage(Condition.getPage(query), shipDocumentInformation);
		return R.data(pages);
	}

	/**
	 * 新增 交船资料表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入shipDocumentInformation")
	public R save(@Valid @RequestBody ShipDocumentInformationVO shipDocumentInformation) {
		return R.status(shipDocumentInformationService.saveShipDocument(shipDocumentInformation));
	}

	/**
	 * 修改 交船资料表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入shipDocumentInformation")
	public R update(@Valid @RequestBody ShipDocumentInformation shipDocumentInformation) {
		return R.status(shipDocumentInformationService.updateById(shipDocumentInformation));
	}

	/**
	 * 新增或修改 交船资料表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入shipDocumentInformation")
	public R submit(@Valid @RequestBody ShipDocumentInformation shipDocumentInformation) {
		return R.status(shipDocumentInformationService.saveOrUpdate(shipDocumentInformation));
	}


	/**
	 * 删除 交船资料表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(shipDocumentInformationService.deleteLogic(Func.toLongList(ids)));
	}


}
