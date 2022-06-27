package org.springblade.vessel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.vessel.entity.ShipInformationResource;
import org.springblade.vessel.vo.ShipInformationResourceVO;

import java.util.List;
import java.util.Map;

/**
 * 船舶资源管理表 Mapper 接口
 *
 * @author Ricky
 * @since 2022-05-25
 */
public interface ShipInformationResourceMapper extends BaseMapper<ShipInformationResource> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param shipInformationResource
	 * @return
	 */
	List<ShipInformationResourceVO> selectShipInformationResourcePage(IPage page, ShipInformationResourceVO shipInformationResource);

	/**
	 * 懒加载列表
	 *
	 * @param parentId
	 * @param param
	 * @return
	 */
	List<ShipInformationResourceVO> lazyList(Long parentId, Map<String, Object> param);

	/**
	 * 懒加载获取树形节点
	 *
	 * @param parentId
	 * @return
	 */
	List<ShipInformationResourceVO> lazyTree(Long parentId);

	/**
	 * 获取树形节点
	 *
	 * @return
	 */
	List<ShipInformationResourceVO> tree();
}
