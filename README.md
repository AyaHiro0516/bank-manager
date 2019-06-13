## 简介
---
这是本鸟的第一个SpringBoot demo，以该demo为基本框架学习一些SpringBoot基础的技术栈，为了使用而使用，会尽可能多的杂糅各种功能模块。

## 进度日志
---
1. 手动刷新页面
2. 密码非明文存储
3. 引用常用正则  将validation从formbean中分类出来
4. 输入userName马上知道是否已经注册
5. 业务页面填坑 Thymeleaf的内联js注入input标签的值
6. 登录时输入验证码 kaptcha
7. 登录5次失败封号一天 数据库添加miss_time和isAllow  定时任务重置isAllow
8. 注册时需要邮箱验证码
9. 初步集成shiro作api权限控制
10. 添加Excel下载功能, 导出账户信息
11. pagehelper插件使用失败= =，无奈用stream的操作手动模拟了个伪分页效果，可批量删除
12. 添加日志记录

## 页面效果
---
#### 使用框架
- 前端渲染: [Thymeleaf](https://www.jianshu.com/p/f79a98173677 "Thymeleaf")
- 页面美化: [MDUI](https://www.mdui.org/docs/ "MDUI")
- 弹出提示框: [Tippy.js](https://atomiks.github.io/tippyjs/ "Tippy.js")
- js: Jquery基本操作, ajax.

##### index
![index](页面截图/index.png 'index')
##### register
![register](页面截图/register.png 'register')
##### login
![login](页面截图/login.png 'login')
##### business
![business](页面截图/business.png 'business')
##### management
![management](页面截图/management.png 'management')

### 本地测试(玩耍)步骤
---
环境：JDK1.8、Maven、MySql5.6+  
1. ```git clone git@github.com:AyaHiro0516/bank-manager.git```
2. 创建数据库setraining, 编码为UTF-8. 运行setraining.sql建表
3. 修改application.yml里datasource部分，改成本地的username、password、url
4. 运行test.java.cn.ayahiro.manager.service.RegisterServiceTest生成用户数据, password为qqqqq
5. 添加管理者账户：4个用户表中任选一个填好必要数据后，再在allowcheckbean表中增加对应记录，role字段设为：admin,user   permission字段设为：update,delete
6. 想看日志效果，可以把logback.xml的appender标签和root标签中有关"file"部分的注释去掉，日志记录在根目录logback/logfile.log
7. 运行main.java.cn.ayahiro.manager.BankManagerApplication中的main方法，浏览器输入http://localhost:8080/index
