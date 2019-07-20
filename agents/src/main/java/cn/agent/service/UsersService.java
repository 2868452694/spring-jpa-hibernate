package cn.agent.service;

import cn.agent.pojo.Users;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface UsersService {
    /**
     * 修改用户
     *
     * @param users 条件
     * @return 是否修改成功
     */
    boolean update(Users users);

    /**
     * 模糊查询用户名
     * @param username
     * @return
     */
    List<Users> findUserListByUsername(String username);
    /**
     * 添加用户
     *
     * @param users 条件
     * @return 是否添加成功
     */
    boolean insert(Users users);

    /**
     * 根据条件获取满足条件的用户?
     *
     * @param users 条件
     * @return 用户list
     */
    List<Users> findAllUsers(Users users);

    /**
     * 根据条件获取指定页数的用户对象
     *
     * @param users   用户条件
     * @param pageSum 页数
     * @return 用户list
     */
    Page<Users> findPageUsers(Users users, int pageSum,int pageSize);

    /**
     * 获取满足条件的记录数
     *
     * @param users 条件  条件为null时获取所有
     * @return 记录个数
     */
    Long getCount(Users users);

    /**
     * 根据id查询用户
     *
     * @param id 条件
     * @return 用户
     */
    Users findById(Long id);

    /**
     * 删除用户
     *
     * @param users 条件
     * @return 是否删除成功
     */
    boolean delete(Users users);

    /**
     * 根据用户名查询用户信息，实现登录功能
     * @param username
     * @return
     */
    Users findUsersByUsername(String username);

}