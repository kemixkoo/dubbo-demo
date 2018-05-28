package xyz.kemix.dubbo.demo.service;

import java.util.List;

/**
 * @author Kemix Koo
 *
 */
public interface PermissionService {
	List<String> getPermissions(Long id);
}
