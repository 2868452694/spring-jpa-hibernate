package cn.agent.service.impl;

import cn.agent.dao.UsersDao;
import cn.agent.pojo.Users;
import cn.agent.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class UsersServiceImple implements UsersService {
    /**
     * 创建dao层对象
     */
    @Autowired
    private UsersDao usersDao;

    /**
     * 修改用户信息
     * @param users 条件
     * @return
     */
    @Override
    public
    boolean update(Users users) {
        Users user=usersDao.save(users);
        if(users.getPassword()==user.getPassword()||users.getPassword().equals(user.getPassword())){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public
    List<Users> findUserListByUsername(String username) {
        return usersDao.findUserssByUsername( username );
    }

    @Override
    public
    boolean insert(Users users) {
        return false;
    }

    @Override
    public
    List<Users> findAllUsers(Users users) {
        return null;
    }

    /**
     * 根据条件查询
     * @param users   用户条件
     * @param pageSum 页数
     * @param pageSize
     * @return
     */
    @Override
    public Page<Users> findPageUsers(Users users, int pageSum,int pageSize) {
        Pageable pageable= PageRequest.of(pageSum==0?0:pageSum,pageSize);
        Page<Users> users1=null;
        if(users==null){
            return usersDao.findAll( pageable );
        }
        if(users!=null && (users.getUsername()==null||users.getUsername().equals("")) && users.getRole().getRoleid()==0){
            users1=usersDao.queryUsersByEnable(users.getEnable(),pageable);
        }else if(users!=null && (users.getUsername()!=null|| !users.getUsername().equals("")) && users.getRole().getRoleid()==0){
            users1=usersDao.queryUsersByUsernameAndEnable(users.getUsername(),users.getEnable(),pageable);
        }else if(users!=null && (users.getUsername()==null||users.getUsername().equals("")) && users.getRole().getRoleid()!=0){
            users1=usersDao.queryUsersByRoleAndEnable(users.getRole(),users.getEnable(),pageable);
        }else{
            users1=usersDao.queryUsersByUsernameAndRoleAndEnable(users.getUsername(),users.getRole(),users.getEnable(),pageable);
        }
        return users1;
    }

    @Override
    public
    Long getCount(Users users) {
        return null;
    }

    /**
     * 根据id查询用户信息
     * @param id 条件
     * @return
     */
    @Override
    public
    Users findById(Long id) {
        return usersDao.findById( id ).get();
    }

    @Override
    public
    boolean delete(Users users) {
        return false;
    }

    /**
     * 根据用户名查询用户信息，实现登录功能
     * @param username
     * @return
     */
    @Override
    public Users findUsersByUsername(String username) {
        return usersDao.findUsersByUsername(username);
    }

}
