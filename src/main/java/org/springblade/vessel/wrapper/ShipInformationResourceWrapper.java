package org.springblade.vessel.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.vessel.entity.ShipInformationResource;
import org.springblade.vessel.enums.ShipResourceEnum;
import org.springblade.vessel.vo.ShipInformationResourceVO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 船舶资源管理表包装类,返回视图层所需的字段
 *
 * @author Ricky
 * @since 2022-05-25
 */
public class ShipInformationResourceWrapper extends BaseEntityWrapper<ShipInformationResource, ShipInformationResourceVO>  {

	public static ShipInformationResourceWrapper build() {
		return new ShipInformationResourceWrapper();
 	}

	@Override
	public ShipInformationResourceVO entityVO(ShipInformationResource shipInformationResource) {
		ShipInformationResourceVO shipInformationResourceVO = Objects.requireNonNull(BeanUtil.copy(shipInformationResource, ShipInformationResourceVO.class));

		//User createUser = UserCache.getUser(shipInformationResource.getCreateUser());
		//User updateUser = UserCache.getUser(shipInformationResource.getUpdateUser());
		//shipInformationResourceVO.setCreateUserName(createUser.getName());
		//shipInformationResourceVO.setUpdateUserName(updateUser.getName());
		shipInformationResourceVO.setTypeName(ShipResourceEnum.of(shipInformationResource.getType()).getDesc());
		return shipInformationResourceVO;
	}

	public List<ShipInformationResourceVO> listNodeLazyVO(List<ShipInformationResourceVO> list) {
		List<ShipInformationResourceVO> collect = list.stream().peek(resourceVO -> {
			Objects.requireNonNull(resourceVO).setTypeName(ShipResourceEnum.of(resourceVO.getType()).getDesc());
		}).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}
}
