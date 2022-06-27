package org.springblade.vessel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;

/**
 * 船舶资源管理表实体类
 *
 * @author Ricky
 * @since 2022-05-25
 */
@Data
@TableName("ship_information_resource")
@EqualsAndHashCode(callSuper = true)
public class ShipInformationResource extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 船舶ID
	*/
	private Long shipId;
	/**
	* 船舶编号
	*/
	private String shipNo;
	/**
	* 父主键
	*/
	private Long parentId;
	/**
	* 祖级列表
	*/
	private String ancestors;
	/**
	* 资源类型
	*/
	private Integer type;
	/**
	* 资源名称
	*/
	private String resourceName;


}
