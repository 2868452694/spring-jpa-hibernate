package cn.agent.service;

import cn.agent.pojo.Role;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface RoleService {
    /**
     * 修改角色
     *
     * @param role 条件
     * @return 是否修改成功
     */
    Role update(Role role);

    /**
     * 添加角色
     *
     * @param role 条件
     * @return 是否添加成功
     */
    Role insert(Role role);

    /**
     * 根据条件获取满足条件的角色
     *
     * @param role 条件
     * @return 角色list
     */
    List<Role> findAllRole(Role role);

    /**
     * 根据条件获取指定页数的角色对象
     * @param role 角色条件  可以为空 为空时查询所有数据
     * @param pageSum 页数
     * @return 角色list
     */
    Page<Role> findPageRole(Role role, int pageSum);

    /**
     * 获取满足条件的记录数
     * @param role 条件  条件为null时获取所有
     * @return 记录个数
     */
    Long getCount(Role role);
    /**
     * 根据id查询角色
     *
     * @param id 条件
     * @return 角色
     */
    Role findById(Long id);

    /**
     * 删除角色
     *
     * @param role 条件
     * @return 是否删除成功
     */
    boolean delete(Role role);

    public List<Role> findAllRoleInfo();
}