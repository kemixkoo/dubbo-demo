package xyz.kemix.dubbo.demo.impl;

import java.util.ArrayList;
import java.util.List;

import xyz.kemix.dubbo.demo.service.HelloService;
import xyz.kemix.dubbo.demo.service.PermissionService;

/**
 * @author Kemix Koo
 *
 */
public class DemoServiceImpl implements HelloService, PermissionService {

	@Override
	public String sayHello(String name) {
		System.out.println("init : " + name);
		return "hello " + name;

	}

	@Override
	public List<String> getPermissions(Long id) {
		List<String> demo = new ArrayList<String>();
		demo.add(String.format("Permission_%d", id - 1));
		demo.add(String.format("Permission_%d", id));
		demo.add(String.format("Permission_%d", id + 1));
		return demo;
	}

}
