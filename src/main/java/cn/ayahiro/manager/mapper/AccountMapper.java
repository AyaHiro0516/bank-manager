package cn.ayahiro.manager.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("accountMapper")
public interface AccountMapper {
    @Insert("INSERT INTO ${accountType} (userId, passWord, personId, userName, email, address, balance) " +
            "VALUES (#{userId},#{passWord},#{personId},#{userName},#{email},#{address},#{balance})")
    void register(@Param("accountType") String accountType,
                  @Param("userId") long userId, @Param("passWord") String passWord, @Param("personId") String personId,
                  @Param("userName") String userName, @Param("email") String email, @Param("address") String address,
                  @Param("balance") String balance);


    @Update("UPDATE ${accountType} SET balance= #{amount} WHERE userName= #{userName}")
    void upDateBalance(@Param("accountType") String accountType,
                       @Param("userName") String username, @Param("amount") double amount);

    @Update("UPDATE ${accountType} SET ceiling= #{amount} WHERE userName= #{userName}")
    void upDateCeiling(@Param("accountType") String accountType,
                       @Param("userName") String username, @Param("amount") double amount);

    @Update("UPDATE ${accountType} SET loan= #{amount} WHERE userName= #{userName}")
    void upDateLoan(@Param("accountType") String accountType,
                    @Param("userName") String username, @Param("amount") double amount);
}
