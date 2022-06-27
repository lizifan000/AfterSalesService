package org.springblade.vessel.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.vessel.entity.ShipInformationResource;
import org.springblade.vessel.mapper.ShipInformationResourceMapper;
import org.springblade.vessel.service.IShipInformationResourceService;
import org.springblade.vessel.vo.ShipInformationResourceVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 船舶资源管理表 服务实现类
 *
 * @author Ricky
 * @since 2022-05-25
 */
@Service
public class ShipInformationResourceServiceImpl extends BaseServiceImpl<ShipInformationResourceMapper, ShipInformationResource> implements IShipInformationResourceService {

	private static final String PARENT_ID = "parentId";

	@Override
	public IPage<ShipInformationResourceVO> selectShipInformationResourcePage(IPage<ShipInformationResourceVO> page, ShipInformationResourceVO shipInformationResource) {
		return page.setRecords(baseMapper.selectShipInformationResourcePage(page, shipInformationResource));
	}

	@Override
	public boolean submit(ShipInformationResource shipInformationResource) {
		if (Func.isEmpty(shipInformationResource.getParentId())) {
			shipInformationResource.setParentId(BladeConstant.TOP_PARENT_ID);
			shipInformationResource.setAncestors(String.valueOf(BladeConstant.TOP_PARENT_ID));
		}
		if (shipInformationResource.getParentId() > 0) {
			ShipInformationResource parent = getById(shipInformationResource.getParentId());
			if (Func.toLong(shipInformationResource.getParentId()) == Func.toLong(shipInformationResource.getId())) {
				throw new ServiceException("父节点不可选择自身!");
			}
			String ancestors = parent.getAncestors() + StringPool.COMMA + shipInformationResource.getParentId();
			shipInformationResource.setAncestors(ancestors);
			shipInformationResource.setShipId(parent.getShipId());
			shipInformationResource.setShipNo(parent.getShipNo());

		}
		shipInformationResource.setIsDeleted(BladeConstant.DB_NOT_DELETED);
		return saveOrUpdate(shipInformationResource);
	}

	@Override
	public List<ShipInformationResourceVO> lazyList(Long parentId, Map<String, Object> param) {
		// 判断点击搜索带有查询条件的情况
		if (Func.isEmpty(param.get(PARENT_ID)) && param.size() > 1 && Func.toLong(parentId) == 0L) {
			parentId = null;
		}
		return baseMapper.lazyList( parentId, param);
	}

	@Override
	public List<ShipInformationResourceVO> lazyTree(Long parentId) {
		return ForestNodeMerger.merge(baseMapper.lazyTree(parentId));
	}

	@Override
	public List<ShipInformationResourceVO> tree() {
		return ForestNodeMerger.merge(baseMapper.tree());
	}

	@Override
	public boolean removeCatalog(String ids) {
		Integer cnt = Math.toIntExact(baseMapper.selectCount(Wrappers.<ShipInformationResource>query().lambda().in(ShipInformationResource::getParentId, Func.toLongList(ids))));
		if (cnt > 0) {
			throw new ServiceException("请先删除子目录!");
		}
		return removeByIds(Func.toLongList(ids));
	}

}
