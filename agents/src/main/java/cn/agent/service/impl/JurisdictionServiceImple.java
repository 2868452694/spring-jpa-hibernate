package cn.agent.service.impl;

import cn.agent.dao.JurisdictionDao;
import cn.agent.pojo.Jurisdiction;
import cn.agent.service.JurisdictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class JurisdictionServiceImple implements JurisdictionService {

    @Autowired
    private
    JurisdictionDao jurisdictionDao;

    @Override
    public
    boolean update(Jurisdiction jurisdiction) {
        return false;
    }

    @Override
    public
    boolean insert(Jurisdiction jurisdiction) {
        return false;
    }

    @Override
    public
    List<Jurisdiction> findAllJurisdiction(Jurisdiction jurisdiction) {
        if(jurisdiction==null){
            return jurisdictionDao.findAll();
        }
        return null;

    }

    @Override
    public
    Page<Jurisdiction> findPageJurisdiction(Jurisdiction jurisdiction, int pageSum) {
        return null;
    }

    @Override
    public
    Long getCount(Jurisdiction jurisdiction) {
        return null;
    }

    @Override
    public
    Jurisdiction findById(Long id) {
        return jurisdictionDao.findById( id ).get();
    }

    @Override
    public
    boolean delete(Jurisdiction jurisdiction) {
        return false;
    }
}
