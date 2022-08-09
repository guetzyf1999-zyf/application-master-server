package com.application.template.mapper.appUser;

import com.application.template.entity.appUser.AppUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AppUserMapper extends BaseMapper<AppUser> {

    AppUser findUserById(Integer id);

    Integer findIdByTelephone(String telephone);

    Integer findIdByEmail(String email);

    @Select("select email from app_user where username = #{username}")
    String findEmailByUsername(String username);

    @Select(value = "select * from app_user where username = #{username}")
    AppUser findUserByUsername(String username);

    @Select(value = "select username from app_user where email = #{email}")
    String findUsernameByEmail(String email);
}
