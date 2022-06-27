package org.springblade.vessel.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.io.Serializable;

/**
 * 保单Excel类
 *
 * @author Ricky
 * @version 2.0.0
 * @since 2022-05-24
 */
@Data
@ColumnWidth(20)
@HeadRowHeight(20)
@ContentRowHeight(20)
public class PolicyExcel implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExcelProperty("公司ID")
	private String companyId;

	@ExcelProperty("公司CODE")
	private String companyCode;

	@ExcelProperty("公司名称")
	private String companyName;

	@ExcelProperty("区域ID")
	private String areaId;

	@ExcelProperty("区域名称")
	private String areaName;

	@ExcelProperty("城市公司ID")
	private String cityId;

	@ExcelProperty("城市公司CODE")
	private String cityCode;

	@ExcelProperty("城市公司名称")
	private String cityName;

	@ExcelProperty("项目ID")
	private String projectId;

	@ExcelProperty("项目CODE")
	private String projectCode;

	@ExcelProperty("项目名称")
	private String projectName;

	@ExcelProperty("项目分期ID")
	private String periodId;

	@ExcelProperty("项目分期CODE")
	private String periodCode;

	@ExcelProperty("项目分期名称")
	private String periodName;

	@ExcelProperty("标段ID")
	private String partitionId;

	@ExcelProperty("标段CODE")
	private String partitionCode;

	@ExcelProperty("标段名称")
	private String partitionName;

	@ExcelProperty("楼栋ID")
	private String buildingId;

	@ExcelProperty("楼栋CODE")
	private String buildingCode;

	@ExcelProperty("楼栋名称")
	private String buildingName;

	@ExcelProperty("单元")
	private String unit;

	@ExcelProperty("楼层")
	private String floor;

	@ExcelProperty("室号")
	private String roomNo;

	@ExcelProperty("房间ID")
	private String roomId;

	@ExcelProperty("房间CODE")
	private String roomCode;

	@ExcelProperty("房间完整信息")
	private String roomInfo;

}
