package cn.ayahiro.manager.mapper;

import cn.ayahiro.manager.model.formbean.AllowCheckBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("allowCheckBeanMapper")
public interface AllowCheckBeanMapper {
    @Select({"SELECT * FROM AllowCheckBean WHERE userName= #{userName}"})
    AllowCheckBean getBeanByUserName(@Param("userName") String username);

    @Update("UPDATE AllowCheckBean SET missNum= #{missNum} WHERE userName= #{userName}")
    void upDateMissNum(@Param("missNum") double amount, @Param("userName") String username);

    @Update("UPDATE AllowCheckBean SET missNum= #{missNum}")
    void upDateAllMissNum(@Param("missNum") double amount);

    @Update("UPDATE AllowCheckBean SET isAllow= #{isAllow} WHERE userName= #{userName}")
    void upDateIsAllow(@Param("isAllow") boolean isAllow, @Param("userName") String username);

    @Update("UPDATE AllowCheckBean SET isAllow= #{isAllow}")
    void upDateAllIsAllow(@Param("isAllow") boolean isAllow);

    @Insert("INSERT INTO AllowCheckBean (userName, missNum, isAllow) VALUES (#{userName},#{missNum},#{isAllow})")
    void registerBean(@Param("userName") String userName, @Param("missNum") int missNum, @Param("isAllow") boolean isAllow);
}
