package cn.ayahiro.manager.mapper;

import cn.ayahiro.manager.model.SavingAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("savingAccountMapper")
public interface SavingAccountMapper extends AccountMapper{
    @Select({"SELECT * FROM SavingAccount WHERE userName= #{userName} AND passWord= #{passWord}"})
    SavingAccount findUser(@Param("userName") String username, @Param("passWord") String password);

    @Select({"SELECT * FROM SavingAccount WHERE userName= #{userName}"})
    SavingAccount getUser(@Param("userName") String username);
}
