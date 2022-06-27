package org.springblade.desk.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.vessel.entity.Policy;
import org.springblade.vessel.mapper.PolicyMapper;
import org.springblade.vessel.service.PolicyService;
import org.springblade.vessel.vo.PolicyVO;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : zzz
 * @version : 1.0
 * @Email : 1430060359@qq.com
 */
@Service
public class PolicyServiceImpl extends ServiceImpl<PolicyMapper, Policy> implements PolicyService {


	@Override
	public IPage<PolicyVO> selectPolicyPage(IPage<PolicyVO> page, PolicyVO policy) {
		// 自定义查验
		return page.setRecords(baseMapper.selectPolicyPage(page, policy));
	}
}
