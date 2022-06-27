package org.springblade.vessel.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.vessel.entity.ShipDocumentInformation;
import org.springblade.vessel.entity.ShipInformationResource;
import org.springblade.vessel.mapper.ShipDocumentInformationMapper;
import org.springblade.vessel.service.IShipDocumentInformationService;
import org.springblade.vessel.service.IShipInformationResourceService;
import org.springblade.vessel.vo.FileInfo;
import org.springblade.vessel.vo.ShipDocumentInformationVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 交船资料表 服务实现类
 *
 * @author Ricky
 * @since 2022-05-25
 */
@Service
@AllArgsConstructor
public class ShipDocumentInformationServiceImpl extends BaseServiceImpl<ShipDocumentInformationMapper, ShipDocumentInformation> implements IShipDocumentInformationService {

	private final IShipInformationResourceService shipInformationResourceService;

	@Override
	public IPage<ShipDocumentInformationVO> selectShipDocumentInformationPage(IPage<ShipDocumentInformationVO> page, ShipDocumentInformationVO shipDocumentInformation) {
		return page.setRecords(baseMapper.selectShipDocumentInformationPage(page, shipDocumentInformation));
	}

	@Override
	public boolean saveShipDocument(ShipDocumentInformationVO information) {
		List<FileInfo> fileInfos = information.getFileInfos();
		List<ShipDocumentInformation> shipDocumentInformationList = new ArrayList<>();
		ShipInformationResource informationResource = shipInformationResourceService.getById(information.getResourceId());

		fileInfos.stream().forEach(fileInfo -> {
			ShipDocumentInformation shipDocumentInformation = new ShipDocumentInformation();
			shipDocumentInformation.setResourceId(information.getResourceId());
			shipDocumentInformation.setShipId(informationResource.getShipId());
			shipDocumentInformation.setShipNo(informationResource.getShipNo());
			shipDocumentInformation.setFileName(fileInfo.getLabel());
			shipDocumentInformation.setFileUrl(fileInfo.getValue());

			shipDocumentInformationList.add(shipDocumentInformation);

		});

		return saveBatch(shipDocumentInformationList);
	}
}
