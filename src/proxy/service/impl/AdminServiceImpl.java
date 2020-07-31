package proxy.service.impl;

import proxy.service.AdminService;

/**
 * @author number47
 * @date 2020/6/3 15:50
 * @description
 */
public class AdminServiceImpl implements AdminService {
	@Override
	public void update() {
		System.out.println("更新呢");
	}

	@Override
	public Object find() {
		System.out.println("查看数据");
		return new Object();
	}
}
