package org.springblade.vessel.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.tool.node.INode;
import org.springblade.vessel.entity.ShipInformationResource;

import java.util.ArrayList;
import java.util.List;

/**
 * 船舶资源管理表视图实体类
 *
 * @author Ricky
 * @since 2022-05-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShipInformationResourceVO extends ShipInformationResource implements INode<ShipInformationResourceVO> {
	private static final long serialVersionUID = 1L;

	/**
	 * 类型名称
	 */
	String typeName;

	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<ShipInformationResourceVO> children;

	/**
	 * 是否有子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Boolean hasChildren;

	@Override
	public List<ShipInformationResourceVO> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		return this.children;
	}
}
