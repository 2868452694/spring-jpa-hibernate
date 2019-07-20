package cn.agent.dao;

import cn.agent.pojo.Appaddress;
import cn.agent.pojo.Types;
import cn.agent.pojo.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;


/**
 * 使用update和delete的时候一定要加上@Modifying这个注解，在这里使用到事务所有必须加上事务如果不加则会Bug" 爆炸~~~"
 * Query注解中加入nativeQuery=true 属性表示使用原生sql，否则默认是hql（实体类查）
 */
@RepositoryDefinition(domainClass= Types.class,idClass=Long.class)
public
interface TypesDao extends JpaRepository<Types,Long> {

    /**
     * 根据父类id查询类型信息
     * @param parentid
     * @return
     */
    public List<Types> findTypesByParentidAndExist(Long parentid, Long exist);
}
