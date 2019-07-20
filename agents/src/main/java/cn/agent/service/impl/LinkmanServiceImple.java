package cn.agent.service.impl;

import cn.agent.dao.LinkmanDao;
import cn.agent.pojo.Linkman;
import cn.agent.service.LinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class LinkmanServiceImple implements LinkmanService {

    @Autowired
    private
    LinkmanDao linkmanDao;

    @Override
    public
    Linkman update(Linkman linkman) {
        return linkmanDao.saveAndFlush( linkman );
    }

    @Override
    public
    Linkman insert(Linkman linkman) {
        return linkmanDao.saveAndFlush( linkman );
    }

    @Override
    public
    List<Linkman> findAllLinkman(Linkman linkman) {
        return null;
    }

    @Override
    public
    List<Linkman> findLinkmanByClientId(Long clientId) {
        return linkmanDao.findLinkmanByClientid( clientId );
    }

    @Override
    public
    Page<Linkman> findPageLinkman(Linkman linkman, int pageSum) {
        return null;
    }

    @Override
    public
    Long getCount(Linkman linkman) {
        return null;
    }

    @Override
    public
    Linkman findById(Long id) {
        return null;
    }

    @Override
    public
    Linkman delById(Long id) {
        linkmanDao.deleteById( id );
        return null;
    }

    @Override
    public
    boolean delete(Linkman linkman) {
        return false;
    }
}
