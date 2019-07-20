package cn.agent.service.impl;

import cn.agent.dao.KeywordDao;
import cn.agent.pojo.Keyword;
import cn.agent.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordDao keywordDao;
    @Override
    public
    boolean update(Keyword keyword) {
        if(keywordDao.existsById( keyword.getKwid() )){
            Keyword keywordResult=keywordDao.saveAndFlush( keyword );
            return keywordResult==null;
        }
        return false;

    }

    @Override
    public
    boolean insert(Keyword keyword) {
        return false;
    }

    @Override
    public
    List<Keyword> findAllKeyword(Keyword keyword) {
        return null;
    }

    @Override
    public
    Page<Keyword> findPageKeyword(String kwname, Integer pageSum) {
        Pageable pageable = PageRequest.of( pageSum==0?0:pageSum,5 );
        Page<Keyword> keywords=null;
        //参数是否为空
        if(kwname==null || kwname.length()<=0){ //没有条件限制
            keywords=keywordDao.findAll(pageable);
        }else{//存在条件限制后
            keywords=keywordDao.queryKeywordsByKwname(kwname,pageable);
        }
        return keywords;
    }

    @Override
    public
    Long getCount(String kwname) {
        return null;
    }

    @Override
    public
    Keyword findById(Long id) {
        return keywordDao.findById( id ).get();
    }

    @Override
    public
    boolean delete(Long id) {
        keywordDao.deleteById( id );
        return !keywordDao.existsById( id );
    }
}
