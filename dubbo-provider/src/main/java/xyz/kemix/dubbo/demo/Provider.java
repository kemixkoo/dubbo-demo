package xyz.kemix.dubbo.demo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kemix Koo
 *
 */
public class Provider {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
		context.start();
		System.out.println("服务成功启动完成！");

		System.out.println("\n请按任意键退出服务");
		System.in.read();
	}
}
