package org.springblade.vessel.controller;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.Policy;
import org.springblade.vessel.vo.PolicyVO;

import java.util.Objects;

/**
 * @author Ricky
 * @version 1.0.0
 * @since 2022/4/24 23:16
 */
public class PolicyWrapper extends BaseEntityWrapper<Policy, PolicyVO> {

	public static PolicyWrapper build() {
		return new PolicyWrapper();
	}

	@Override
	public PolicyVO entityVO(Policy entity) {
		PolicyVO policyVO = Objects.requireNonNull(BeanUtil.copy(entity, PolicyVO.class));
		if(Func.isNotEmpty(entity.getPictureAttachment())){
			policyVO.setPictureList(Func.toStrList(entity.getPictureAttachment()));
		}
		return policyVO;
	}
}
