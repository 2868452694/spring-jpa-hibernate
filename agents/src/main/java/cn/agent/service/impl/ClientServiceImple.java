package cn.agent.service.impl;

import cn.agent.dao.ClientDao;
import cn.agent.pojo.Client;
import cn.agent.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class ClientServiceImple implements ClientService {

    @Autowired
    private
    ClientDao clientDao;

    @Override
    public
    Client update(Client client) {
        return clientDao.saveAndFlush( client );
    }

    @Override
    public
    Client insert(Client client) {
        return clientDao.saveAndFlush( client );
    }

    @Override
    public
    List<Client> findAllClient(Client client) {
        return null;
    }

    @Override
    public
    Page<Client> findPageClient(Client client, int pageSum) {
        return null;
    }

    @Override
    public
    Long getCount(Client client) {
        return null;
    }

    @Override
    public
    Client findById(Long id) {
        return clientDao.findById( id ).get();
    }

    @Override
    public
    boolean delete(Client client) {
        return false;
    }
}
