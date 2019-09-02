package cn.ayahiro.manager.mapper;

import cn.ayahiro.manager.model.CreditAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("creditAccountMapper")
public interface CreditAccountMapper extends AccountMapper {
    @Select({"SELECT * FROM creditaccount WHERE userName= #{userName} AND passWord= #{passWord}"})
    CreditAccount findUser(@Param("userName") String username, @Param("passWord") String password);

    @Select({"SELECT * FROM creditaccount WHERE userName= #{userName}"})
    CreditAccount getUser(@Param("userName") String username);

    @Select({"SELECT * FROM creditaccount"})
    List<CreditAccount> getAllUsers();
}