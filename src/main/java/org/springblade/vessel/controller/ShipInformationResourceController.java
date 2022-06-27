package org.springblade.vessel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.ShipInformationResource;
import org.springblade.vessel.enums.ShipResourceEnum;
import org.springblade.vessel.service.IShipInformationResourceService;
import org.springblade.vessel.vo.ShipInformationResourceVO;
import org.springblade.vessel.wrapper.ShipInformationResourceWrapper;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 船舶资源管理表 控制器
 *
 * @author Ricky
 * @since 2022-05-25
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shipinformationresource")
@Api(value = "船舶资源管理表", tags = "船舶资源管理表接口")
public class ShipInformationResourceController extends BladeController {

	private final IShipInformationResourceService shipInformationResourceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入shipInformationResource")
	public R<ShipInformationResourceVO> detail(ShipInformationResource shipInformationResource) {
		ShipInformationResource detail = shipInformationResourceService.getOne(Condition.getQueryWrapper(shipInformationResource));
		return R.data(ShipInformationResourceWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 船舶资源管理表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入shipInformationResource")
	public R<IPage<ShipInformationResourceVO>> list(ShipInformationResource shipInformationResource, Query query) {
		IPage<ShipInformationResource> pages = shipInformationResourceService.page(Condition.getPage(query), Condition.getQueryWrapper(shipInformationResource));
		return R.data(ShipInformationResourceWrapper.build().pageVO(pages));
	}

	/**
	 * 懒加载列表
	 */
	@GetMapping("/lazy-list")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "resourceName", value = "资源名称", paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "type", value = "资源类型", paramType = "query", dataType = "string")
	})
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "懒加载列表", notes = "传入shipInformationResource")
	public R<List<ShipInformationResourceVO>> lazyList(@ApiIgnore @RequestParam Map<String, Object> information, Long parentId) {
		List<ShipInformationResourceVO> list = shipInformationResourceService.lazyList(parentId, information);
		return R.data(ShipInformationResourceWrapper.build().listNodeLazyVO(list));
	}

	/**
	 * 懒加载获取船舶资源管理树
	 */
	@GetMapping("/lazy-tree")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "懒加载树形结构", notes = "树形结构")
	public R<List<ShipInformationResourceVO>> lazyTree(Long parentId) {
		List<ShipInformationResourceVO> tree = shipInformationResourceService.lazyTree(parentId);
		return R.data(tree);
	}

	/**
	 * 自定义分页 船舶资源管理表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入shipInformationResource")
	public R<IPage<ShipInformationResourceVO>> page(ShipInformationResourceVO shipInformationResource, Query query) {
		IPage<ShipInformationResourceVO> pages = shipInformationResourceService.selectShipInformationResourcePage(Condition.getPage(query), shipInformationResource);
		return R.data(pages);
	}

	/**
	 * 新增 船舶资源管理表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入shipInformationResource")
	public R save(@Valid @RequestBody ShipInformationResource shipInformationResource) {
		return R.status(shipInformationResourceService.save(shipInformationResource));
	}

	/**
	 * 修改 船舶资源管理表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入shipInformationResource")
	public R update(@Valid @RequestBody ShipInformationResource shipInformationResource) {
		return R.status(shipInformationResourceService.updateById(shipInformationResource));
	}

	/**
	 * 新增或修改 船舶资源管理表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入shipInformationResource")
	public R submit(@Valid @RequestBody ShipInformationResource shipInformationResource) {
		if (shipInformationResourceService.submit(shipInformationResource)) {
			// 返回懒加载树更新节点所需字段
			Kv kv = Kv.create().set("id", String.valueOf(shipInformationResource.getId()))
				.set("typeName", ShipResourceEnum.of(shipInformationResource.getType()).getDesc());
			return R.data(kv);
		}
		return R.fail("操作失败");
	}

	/**
	 * 删除 船舶资源管理表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		//return R.status(shipInformationResourceService.deleteLogic(Func.toLongList(ids)));
		return R.status(shipInformationResourceService.removeCatalog(ids));
	}

	/**
	 * 获取船舶资源树形结构
	 *
	 * @return
	 */
	@GetMapping("/tree")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "树形结构", notes = "树形结构")
	public R<List<ShipInformationResourceVO>> tree() {
		List<ShipInformationResourceVO> tree = shipInformationResourceService.tree();
		return R.data(tree);
	}
}
