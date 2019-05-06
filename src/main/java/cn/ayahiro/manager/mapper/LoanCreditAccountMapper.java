package cn.ayahiro.manager.mapper;

import cn.ayahiro.manager.model.LoanCreditAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("loanCreditAccountMapper")
public interface LoanCreditAccountMapper extends AccountMapper{
    @Select({"SELECT * FROM LoanCreditAccount WHERE userName= #{userName} AND passWord= #{passWord}"})
    LoanCreditAccount findUser(@Param("userName") String username, @Param("passWord") String password);

    @Select({"SELECT * FROM LoanCreditAccount WHERE userName= #{userName}"})
    LoanCreditAccount getUser(@Param("userName") String username);
}
