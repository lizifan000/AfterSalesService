package org.springblade.vessel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;

/**
 * 交船资料表实体类
 *
 * @author Ricky
 * @since 2022-05-25
 */
@Data
@TableName("ship_document_information")
@EqualsAndHashCode(callSuper = true)
public class ShipDocumentInformation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 船舶ID
	*/
	private Long shipId;
	/**
	* 船号
	*/
	private String shipNo;
	/**
	* 所属资源ID
	*/
	private Long resourceId;
	/**
	* 附件地址
	*/
	private String fileUrl;
	/**
	* 附件原名
	*/
	private String fileName;


}
