package com.application.template.mapper.appUser;

import com.application.template.entity.appUser.AppUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AppUserMapper extends BaseMapper<AppUser> {

    @Select("select * from app_user where id = #{id}")
    AppUser findUserById(Integer id);

    @Select("select id from app_user where telephone = #{telephone}")
    Integer findIdByTelephone(String telephone);

    @Select("select id from app_user where email = #{email}")
    Integer findIdByEmail(String email);

    @Select("select email from app_user where username = #{username}")
    String findEmailByUsername(String username);

    @Select(value = "select * from app_user where username = #{username}")
    AppUser findUserByUsername(String username);

    @Select(value = "select * from app_user where email = #{email}")
    AppUser findUserByEmail(String email);

    @Select(value = "select username from app_user where email = #{email}")
    String findUsernameByEmail(String email);

    AppUser findUserWithOrganizationById(Integer id);
}
