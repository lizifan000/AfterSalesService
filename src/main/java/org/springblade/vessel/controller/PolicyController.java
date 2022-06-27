package org.springblade.vessel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.vessel.entity.Policy;
import org.springblade.vessel.vo.PolicyVO;
import org.springblade.vessel.service.PolicyService;
import org.springblade.vessel.wrapper.PolicyWrapper;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : zzz
 * @version : 1.0
 * @Email : 1430060359@qq.com
 */
@RestController
@RequestMapping("policy")
@AllArgsConstructor
public class PolicyController {

	private PolicyService policyService;


	@PostMapping("/save")
	public R save(@RequestBody Policy policy) {
		return R.status(policyService.save(policy));
	}

	@PostMapping("/update")
	public R update(@RequestBody Policy policy) {
		return R.status(policyService.updateById(policy));
	}

	@PostMapping("/remove")
	public R remove(@RequestParam String ids) {
		return R.status(policyService.removeByIds(Func.toLongList(ids)));
	}

	@GetMapping("/detail")
	public R<Policy> detail(Integer id) {
		Policy detail = policyService.getById(id);
		return R.data(detail);
	}

	@GetMapping("/page")
	public R<IPage<PolicyVO>> page(@ApiIgnore PolicyVO policy, Query query) {
		IPage<PolicyVO> pages = policyService.selectPolicyPage(Condition.getPage(query), policy);
		return R.data(pages);
	}

	@GetMapping("/list")
	public R<IPage<PolicyVO>> list(@ApiIgnore @RequestParam Map<String, Object> policy, Query query) {
		QueryWrapper<Policy> queryWrapper = Condition.getQueryWrapper(policy, Policy.class);
		IPage<Policy> pages = policyService.page(Condition.getPage(query), queryWrapper);
		return R.data(PolicyWrapper.build().pageVO(pages));
	}

}
