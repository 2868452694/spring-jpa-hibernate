package cn.agent.service.impl;

import cn.agent.dao.PortalDao;
import cn.agent.pojo.Portal;
import cn.agent.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class PortalServiceImple implements PortalService {

    @Autowired
    private  PortalDao portalDao;
    @Override
    public
    Portal update(Portal portal) {
        return portalDao.saveAndFlush( portal );
    }
    @Override
    public
    Portal insert(Portal portal) {
        return portalDao.saveAndFlush( portal );
    }
    @Override
    public
    List<Portal> findAllPortal(Portal portal) {
        return null;
    }

    @Override
    public
    Page<Portal> findPagePortal(Portal portal, int pageSum) {
        return null;
    }

    @Override
    public
    Long getCount(Portal portal) {
        return null;
    }

    @Override
    public
    Portal findById(Long id) {
        return portalDao.findById( id ).get();
    }

    @Override
    public
    boolean delete(Portal portal) {
        return false;
    }
}
