package cn.agent.dao;

import cn.agent.pojo.Keyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;


/**
 * 使用update和delete的时候一定要加上@Modifying这个注解，在这里使用到事务所有必须加上事务如果不加则会Bug" 爆炸~~~"
 * Query注解中加入nativeQuery=true 属性表示使用原生sql，否则默认是hql（实体类查）
 */
@RepositoryDefinition(domainClass= Keyword.class,idClass=Long.class)
public
interface KeywordDao extends JpaRepository<Keyword,Long> {
    //错误标记: 需要设置成模糊对查询 Containing SQL Error: 1425, SQLState: 22019
    Page<Keyword> queryKeywordsByKwname(String kwname, Pageable pageable);
}
