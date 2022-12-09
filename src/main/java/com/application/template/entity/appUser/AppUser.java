package com.application.template.entity.appUser;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.application.template.dto.login.RegisterBody;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("app_user")
public class AppUser implements UserDetails {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private List<AppUserAuthorities> authorities;
    private String username; //SpringSecurity 框架的原因,这里username就指用户账户,即account
    private String nickName;
    private String password;
    private String telephone;
    private String email;
    private Date registerDate;
    // 表示判断账户是否过期
    private boolean accountNonExpired;
    // 表示判断账户是否被锁定
    private boolean accountNonLocked;
    // 表示凭证{密码}是否过期
    private boolean credentialsNonExpired;
    // 表示当前用户是否可用
    private boolean enabled;
    @TableField(exist = false)
    private List<AffiliatedOrganization> affiliatedOrganizations;

    public AppUser() {}

    public AppUser(RegisterBody registerBody) {
        String hashedPassword = BCrypt.hashpw(registerBody.getPassword(), BCrypt.gensalt());
        this.setPassword(hashedPassword);
        this.setNickName(registerBody.getNickname());
        this.setTelephone(registerBody.getPhoneNumber());
        this.setRegisterDate(new Date());
        this.setEmail(registerBody.getEmail());
        this.setAccountNonExpired(true);
        this.setAccountNonLocked(true);
        this.setCredentialsNonExpired(true);
        this.setEnabled(true);
    }

    public AppUser(Integer id, String username, String nickName, String password, String telephone, String email,
                   Date registerDate, boolean accountNonExpired, boolean accountNonLocked,
                   boolean credentialsNonExpired, boolean enabled, List<AffiliatedOrganization> affiliatedOrganizations) {
        this.id = id;
        this.username = username;
        this.nickName = nickName;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
        this.registerDate = registerDate;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.affiliatedOrganizations = affiliatedOrganizations;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setAuthorities(List<AppUserAuthorities> authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AffiliatedOrganization> getAffiliatedOrganizations() {
        return affiliatedOrganizations;
    }

    public void setAffiliatedOrganizations(List<AffiliatedOrganization> affiliatedOrganizations) {
        this.affiliatedOrganizations = affiliatedOrganizations;
    }
}
