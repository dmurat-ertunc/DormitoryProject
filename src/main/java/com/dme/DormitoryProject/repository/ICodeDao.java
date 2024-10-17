package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ICodeDao {

    public static final String HASH_KEY = "Code";
    @Autowired
    private RedisTemplate template;

    public Code save(Code code){
        template.opsForHash().put(HASH_KEY,code.getId(),code);
        return code;
    }

    public List<Code> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Code findProductById(int id){
        return (Code) template.opsForHash().get(HASH_KEY,id);
    }
}
