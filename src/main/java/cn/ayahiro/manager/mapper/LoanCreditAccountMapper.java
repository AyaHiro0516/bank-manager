package cn.ayahiro.manager.mapper;

import cn.ayahiro.manager.model.LoanCreditAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("loanCreditAccountMapper")
public interface LoanCreditAccountMapper extends AccountMapper {
    @Select({"SELECT * FROM loancreditaccount WHERE userName= #{userName} AND passWord= #{passWord}"})
    LoanCreditAccount findUser(@Param("userName") String username, @Param("passWord") String password);

    @Select({"SELECT * FROM loancreditaccount WHERE userName= #{userName}"})
    LoanCreditAccount getUser(@Param("userName") String username);

    @Select({"SELECT * FROM loancreditaccount"})
    List<LoanCreditAccount> getAllUsers();
}