package cn.agent.service.impl;

import cn.agent.controller.AppAddressController;
import cn.agent.dao.AppaddressDao;
import cn.agent.pojo.Appaddress;
import cn.agent.service.AppaddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public
class AppaddressServiceImple implements AppaddressService {
    @Autowired
    private AppaddressDao appaddressDao;

    @Override
    public
    boolean update(Appaddress appaddress) {
        Appaddress appaddress1=appaddressDao.save(appaddress);
        if(appaddress1!=null){
            return true;
        }
        return false;
    }

    @Override
    public
    boolean insert(Appaddress appaddress) {
        return false;
    }

    @Override
    public
    List<Appaddress> findAllAppaddress(Appaddress appaddress) {
        return null;
    }

    @Override
    public
    Page<Appaddress> findPageAppaddress(Appaddress appaddress, int pageSum) {
        return null;
    }

    @Override
    public
    Appaddress findById(Long id) {
        Appaddress appaddress=null;
        Optional<Appaddress> list= appaddressDao.findById(id);
        if(list.isPresent()){
            appaddress =list.get();
        }
        return appaddress;
    }

    @Override
    public
    boolean delete(Appaddress appaddress) {
        return false;
    }
}
