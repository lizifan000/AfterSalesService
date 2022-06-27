package org.springblade.vessel.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.vessel.entity.Policy;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0.0
 * @description
 * @since 2022/4/24 20:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PolicyVO extends Policy {
	private static final long serialVersionUID = 1L;


	/***  图片附件 数组*/
	private List<String> pictureList;
}
