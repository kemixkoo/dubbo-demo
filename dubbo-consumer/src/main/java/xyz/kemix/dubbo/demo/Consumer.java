package xyz.kemix.dubbo.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import xyz.kemix.dubbo.demo.service.PermissionService;

/**
 * @author Kemix Koo
 *
 */
public class Consumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
		context.start();
		System.out.println("开始调用远程服务...");
		
		PermissionService permissionService = context.getBean(PermissionService.class);
		System.out.print("服务返回： :"+permissionService.getPermissions(1L));
	}
}
