package cn.agent.service;

import cn.agent.pojo.Client;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface ClientService {

    /**
     * 修改客户信息
     *
     * @param client 客户信息
     * @return 是否修改成功
     */
    Client update(Client client);

    /**
     * 添加客户信息
     *
     * @param client  客户信息
     * @return 是否添加成功
     */
    Client insert(Client client);

    /**
     * 根据条件获取满足条件的客户信息
     *
     * @param client 条件
     * @return 客户信息list
     */
    List<Client> findAllClient(Client client);

    /**
     * 根据条件获取指定页数的客户信息对象
     * @param client 客户条件
     * @param pageSum 页数
     * @return 客户信息list
     */
    Page<Client> findPageClient(Client client, int pageSum);

    /**
     * 获取满足条件的记录数
     * @param client 条件  条件为null时获取所有
     * @return 记录个数
     */
    Long getCount(Client client);
    /**
     * 根据id查询客户信息
     *
     * @param id 条件
     * @return 客户信息
     */
    Client findById(Long id);

    /**
     * 删除客户信息
     *
     * @param client 客户信息
     * @return 是否删除成功
     */
    boolean delete(Client client);

}
