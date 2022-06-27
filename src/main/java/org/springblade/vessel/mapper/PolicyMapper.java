package org.springblade.vessel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.vessel.entity.Policy;
import org.springblade.vessel.vo.PolicyVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : zzz
 * @version : 1.0
 * @Email : 1430060359@qq.com
 */
public interface PolicyMapper extends BaseMapper<Policy> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param policy
	 * @return
	 */
	List<PolicyVO> selectPolicyPage(IPage page, PolicyVO policy);
}
