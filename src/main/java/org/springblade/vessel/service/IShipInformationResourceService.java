package org.springblade.vessel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.vessel.entity.ShipInformationResource;
import org.springblade.vessel.vo.ShipInformationResourceVO;

import java.util.List;
import java.util.Map;

/**
 * 船舶资源管理表 服务类
 *
 * @author Ricky
 * @since 2022-05-25
 */
public interface IShipInformationResourceService extends BaseService<ShipInformationResource> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param shipInformationResource
	 * @return
	 */
	IPage<ShipInformationResourceVO> selectShipInformationResourcePage(IPage<ShipInformationResourceVO> page, ShipInformationResourceVO shipInformationResource);

	/**
	 * 提交
	 *
	 * @param shipInformationResource
	 * @return
	 */
	boolean submit(ShipInformationResource shipInformationResource);

	/**
	 * 懒加载船舶资源管理表
	 *
	 * @param parentId
	 * @param param
	 * @return
	 */
	List<ShipInformationResourceVO> lazyList(Long parentId, Map<String, Object> param);

	/**
	 * 懒加载树形结构
	 *
	 * @param parentId
	 * @return
	 */
	List<ShipInformationResourceVO> lazyTree(Long parentId);

	/**
	 * 树形结构
	 *
	 * @return
	 */
	List<ShipInformationResourceVO> tree();

	/**
	 * 删除目录
	 *
	 * @param ids
	 * @return
	 */
	boolean removeCatalog(String ids);
}
