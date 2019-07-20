package cn.agent.service.impl;

import cn.agent.dao.TypesDao;
import cn.agent.pojo.Types;
import cn.agent.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("typeService")
public
class TypesServiceImple implements TypesService {
    @Autowired
    private  TypesDao typesDao;
    @Override
    public
    boolean update(Types types) {
        Types types1=typesDao.save(types);
        if(types1!=null){
            return  true;
        }
        return false;
    }

    @Override
    public
    boolean insert(Types types) {
            Types types1=typesDao.save(types);
            if(types1!=null){
                return true;
            }
        return  false;
    }

    /**
     * 根据条件查询类型信息
     * @param types 条件
     * @return
     */
    @Override
    public
    List<Types> findAllTypes(Types types) {
        return null;
    }

    @Override
    public
    Page<Types> findPageTypes(Types types, int pageSum) {
        return null;
    }

    @Override
    public
    Long getCount(Types types) {
        return null;
    }

    @Override
    public
    Types findById(Long id) {
        return typesDao.findById( id ).get();
    }

    @Override
    public
    boolean delete(Types types) {
        typesDao.delete(types);
        Optional<Types> types1=typesDao.findById(types.getTypeid());
        if(types1.isPresent()){
            return false;
        }
        return true;
    }

    /**
     * 根据父类id查询类型信息
     * @param parentid
     * @return
     */
    @Override
    public List<Types> findTypesByParentid(Long parentid) {
        return typesDao.findTypesByParentidAndExist(parentid,0l);
    }
}
