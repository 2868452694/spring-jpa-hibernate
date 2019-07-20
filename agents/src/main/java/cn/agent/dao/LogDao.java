package cn.agent.dao;

import cn.agent.pojo.Appaddress;
import cn.agent.pojo.Log;
import cn.agent.pojo.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Date;


/**
 * 使用update和delete的时候一定要加上@Modifying这个注解，在这里使用到事务所有必须加上事务如果不加则会Bug" 爆炸~~~"
 * Query注解中加入nativeQuery=true 属性表示使用原生sql，否则默认是hql（实体类查）
 */
@RepositoryDefinition(domainClass= Log.class,idClass=Long.class)
public
interface LogDao extends JpaRepository<Log,Long> {

    /**
     *根据用户查询日志信息
     * @param users
     * @param pageable
     * @return
     */
    Page<Log> queryLogByUsers(Users users, Pageable pageable);

    /**
     * 根据用户和操作时间查询日志信息
     * @param users
     * @param logtime
     * @param pageable
     * @return
     */
    Page<Log> queryLogByUsersAndLogtime(Users users, Date logtime, Pageable pageable);
}
