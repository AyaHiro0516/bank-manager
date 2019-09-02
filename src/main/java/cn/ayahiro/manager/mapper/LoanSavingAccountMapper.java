package cn.ayahiro.manager.mapper;

import cn.ayahiro.manager.model.LoanSavingAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("loanSavingAccountMapper")
public interface LoanSavingAccountMapper extends AccountMapper {
    @Select({"SELECT * FROM loansavingaccount WHERE userName= #{userName} AND passWord= #{passWord}"})
    LoanSavingAccount findUser(@Param("userName") String username, @Param("passWord") String password);

    @Select({"SELECT * FROM loansavingaccount WHERE userName= #{userName}"})
    LoanSavingAccount getUser(@Param("userName") String username);

    @Select({"SELECT * FROM loansavingaccount"})
    List<LoanSavingAccount> getAllUsers();
}
