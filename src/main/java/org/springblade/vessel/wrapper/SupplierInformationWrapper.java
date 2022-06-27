package org.springblade.vessel.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.vessel.entity.SupplierInformation;
import org.springblade.vessel.vo.SupplierInformationVO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 船舶资源管理表包装类,返回视图层所需的字段
 *
 * @author Ricky
 * @since 2022-05-25
 */
public class SupplierInformationWrapper extends BaseEntityWrapper<SupplierInformation, SupplierInformationVO>  {

	public static SupplierInformationWrapper build() {
		return new SupplierInformationWrapper();
 	}
	@Override
	public SupplierInformationVO entityVO(SupplierInformation supplierInformation) {
		return Objects.requireNonNull(BeanUtil.copy(supplierInformation, SupplierInformationVO.class));
	}

	public List<SupplierInformationVO> listNodeLazyVO(List<SupplierInformationVO> list) {
		List<SupplierInformationVO> collect = list.stream().peek(Objects::requireNonNull).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

	public List<SupplierInformationVO> listNodeVO(List<SupplierInformation> list) {
		List<SupplierInformationVO> collect = list.stream().map(this::entityVO).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

}
