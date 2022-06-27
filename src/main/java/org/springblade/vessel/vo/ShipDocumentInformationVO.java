package org.springblade.vessel.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.vessel.entity.ShipDocumentInformation;

import java.util.List;

/**
 * 交船资料表视图实体类
 *
 * @author Ricky
 * @since 2022-05-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShipDocumentInformationVO extends ShipDocumentInformation {
	private static final long serialVersionUID = 1L;

	private List<FileInfo> fileInfos;
}
