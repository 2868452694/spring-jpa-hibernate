package cn.agent.service.impl;

import cn.agent.dao.RoleDao;
import cn.agent.pojo.Role;
import cn.agent.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class RoleServiceImple implements RoleService {

    @Autowired
    private
    RoleDao roleDao;
    @Override
    public
    Role update(Role role) {
        return roleDao.saveAndFlush( role );
    }

    @Override
    public
    Role insert(Role role) {
        return roleDao.saveAndFlush( role );
    }

    @Override
    public
    List<Role> findAllRole(Role role) {
        return null;
    }

    @Override
    public
    Page<Role> findPageRole(Role role, int pageSum) {
        if(role==null){
            return roleDao.findAll( PageRequest.of( pageSum,5 ) );
        }else{
            return null;
        }
    }

    @Override
    public
    Long getCount(Role role) {
        return null;
    }

    @Override
    public
    Role findById(Long id) {
        return roleDao.findById( id ).get();
    }

    @Override
    public
    boolean delete(Role role) {
         roleDao.delete( role );
        return roleDao.existsById( role.getRoleid() );
    }
    /**
     * 查询全部的角色信息
     * @return
     */
    public List<Role> findAllRoleInfo(){
        return roleDao.findAll();
    }

}
