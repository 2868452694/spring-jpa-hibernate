package cn.agent.service.impl;

import cn.agent.dao.ServicetimeDao;
import cn.agent.pojo.Role;
import cn.agent.pojo.Servicetime;
import cn.agent.service.ServicetimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public
class ServicetimeServiceImple implements ServicetimeService {
    @Autowired
    private ServicetimeDao servicetimeDao;
    public ServicetimeDao getServicetimeDao() {
        return servicetimeDao;
    }

    public void setServicetimeDao(ServicetimeDao servicetimeDao) {
        this.servicetimeDao = servicetimeDao;
    }

    @Override
    public
    boolean update(Servicetime servicetime) {
        Servicetime servicetime1=servicetimeDao.save(servicetime);
        if(servicetime1!=null){
            return true;
        }
        return false;
    }

    @Override
    public
    boolean insert(Servicetime servicetime) {
        return false;
    }

    @Override
    public
    List<Servicetime> findAllServicetime(Servicetime servicetime) {
        return null;
    }

    @Override
    public
    Page<Servicetime> findPageServicetime(Servicetime servicetime, int pageSum) {
        return null;
    }

    @Override
    public
    Long getCount(Servicetime servicetime) {
        return null;
    }

    @Override
    public
    Servicetime findById(Long id) {
        return servicetimeDao.findById( id ).get();
    }

    @Override
    public
    boolean delete(Servicetime servicetime) {
        return false;
    }


}
