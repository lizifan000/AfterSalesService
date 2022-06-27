package org.springblade.vessel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.vessel.entity.Policy;
import org.springblade.vessel.vo.PolicyVO;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : zzz
 * @version : 1.0
 * @Email : 1430060359@qq.com
 */
public interface PolicyService extends IService<Policy> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param policy
	 * @return
	 */
	IPage<PolicyVO> selectPolicyPage(IPage<PolicyVO> page, PolicyVO policy);
}
