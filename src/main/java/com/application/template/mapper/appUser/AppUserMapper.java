package com.application.template.mapper.appUser;

import com.application.template.entity.appUser.AppUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AppUserMapper extends BaseMapper<AppUser> {

    AppUser findUserById(Integer id);

    Integer findIdByTelephone(String telephone);

    Integer findIdByUsername(String username);

    AppUser findUserByUsername(String username);
}
