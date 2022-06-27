package org.springblade.vessel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : zzz
 * @version : 1.0
 * @Email : 1430060359@qq.com
 */
@Data
@TableName("policy_processing_progress")
public class Policy implements Serializable {

	private static final long serialVersionUID = 1L;

	/*** 主键 */
	//@TableId(value = "id",type = IdType.ID_WORKER)
	private Long id;

	/*** 船号 */
	private String shipNo;

	/*** 船名 */
	private String shipName;

	/*** 交船日期 */
	@JsonFormat(timezone="GMT", pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date deliveryDate;

	/*** 保单号 */
	private String policyNo;

	/*** 分类 */
	private String classification;

	/*** 责任方 */
	private String responsibility;

	/*** 内容描述 */
	private String description;

	/*** 保单日期 */
	@JsonFormat(timezone="GMT", pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date policyDate;

	/*** 完成日期 */
	private Date completionDate;

	/*** 更新日期 */
	private Date updatedDate;

	/*** 保单要求 */
	private String policyRequirements;

	/*** 处理进度 */
	private String processingProgress;

	/*** 费用 */
	private Double cost;

	/***  船舶描述*/
	private String shipDescription;

	/***  船东名称*/
	private String shipownerName;

	/***  维保部位*/
	private String maintenanceParts;

	/***  厂家*/
	private String manufactor;

	/***  损坏原因*/
	private String damageCause;

	/***  人工及物料需求*/
	private String labormaterialRequire;

	/***  发生地点日期及情况*/
	private String placedateSituation;

	/***  设备位置*/
	private String equipmentLocation;

	/***  附件*/
	private String enclosure;

	/***  序列号*/
	private String serialNo;

	/***  保修工程师*/
	private String guaranteeEngineer;

	/***  总工程师*/
	private String chiefEngineer;

	/***  备注*/
	private String remarks;

	/***  图片附件*/
	private String pictureAttachment;

	/***  专业名称*/
	private String major;





//	/*** 是否已删除 */
//	@TableLogic
//	private Integer deleted;
}
