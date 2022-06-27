package org.springblade.vessel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.vessel.entity.ShipDocumentInformation;
import org.springblade.vessel.vo.ShipDocumentInformationVO;

/**
 * 交船资料表 服务类
 *
 * @author Ricky
 * @since 2022-05-25
 */
public interface IShipDocumentInformationService extends BaseService<ShipDocumentInformation> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param shipDocumentInformation
	 * @return
	 */
	IPage<ShipDocumentInformationVO> selectShipDocumentInformationPage(IPage<ShipDocumentInformationVO> page, ShipDocumentInformationVO shipDocumentInformation);

	/**
	 * 保存交船资料
	 *
	 * @param shipDocumentInformationVO
	 * @return
	 */
	boolean saveShipDocument(ShipDocumentInformationVO shipDocumentInformationVO);
}
