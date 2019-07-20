package cn.agent.service;

import cn.agent.pojo.Appaddress;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface AppaddressService {
    /**
     * 修改APP地址
     * @param appaddress APP地址条件
     * @return 是否修改成功
     */
    boolean update(Appaddress appaddress);

    /**
     * 添加APP地址
     * @param appaddress APP地址条件
     * @return 是否添加成功
     */
    boolean insert(Appaddress appaddress);

    /**
     * 根据条件获取满足条件的APP地址
     * @param appaddress 条件
     * @return APP地址list
     */
    List<Appaddress> findAllAppaddress(Appaddress appaddress);
    /**
     * 根据条件获取指定页数的APP地址对象
     * @param appaddress 条件
     * @param pageSum 页数
     * @return APP地址list
     */
    Page<Appaddress> findPageAppaddress(Appaddress appaddress, int pageSum);


    /**
     * 根据id查询APP地址
     * @param id 条件
     * @return APP地址
     */
    Appaddress findById(Long id);

    /**
     * 删除APP地址
     * @param appaddress APP地址条件
     * @return 是否删除成功
     */
    boolean delete(Appaddress appaddress);


}
