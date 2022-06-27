package org.springblade.vessel.mapper;

import org.springblade.vessel.entity.ShipDocumentInformation;
import org.springblade.vessel.vo.ShipDocumentInformationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 交船资料表 Mapper 接口
 *
 * @author Ricky
 * @since 2022-05-25
 */
public interface ShipDocumentInformationMapper extends BaseMapper<ShipDocumentInformation> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param shipDocumentInformation
	 * @return
	 */
	List<ShipDocumentInformationVO> selectShipDocumentInformationPage(IPage page, ShipDocumentInformationVO shipDocumentInformation);

}
