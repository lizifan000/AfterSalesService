package org.springblade.vessel.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.vessel.entity.ShipDocumentInformation;
import org.springblade.vessel.vo.ShipDocumentInformationVO;
import java.util.Objects;

/**
 * 交船资料表包装类,返回视图层所需的字段
 *
 * @author Ricky
 * @since 2022-05-25
 */
public class ShipDocumentInformationWrapper extends BaseEntityWrapper<ShipDocumentInformation, ShipDocumentInformationVO>  {

	public static ShipDocumentInformationWrapper build() {
		return new ShipDocumentInformationWrapper();
 	}

	@Override
	public ShipDocumentInformationVO entityVO(ShipDocumentInformation shipDocumentInformation) {
		ShipDocumentInformationVO shipDocumentInformationVO = Objects.requireNonNull(BeanUtil.copy(shipDocumentInformation, ShipDocumentInformationVO.class));

		//User createUser = UserCache.getUser(shipDocumentInformation.getCreateUser());
		//User updateUser = UserCache.getUser(shipDocumentInformation.getUpdateUser());
		//shipDocumentInformationVO.setCreateUserName(createUser.getName());
		//shipDocumentInformationVO.setUpdateUserName(updateUser.getName());

		return shipDocumentInformationVO;
	}

}
