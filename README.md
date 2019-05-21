## 简介
---
这是本鸟的第一个SpringBoot demo，以该demo为基本框架学习一些SpringBootd基础的技术栈，为了使用而使用，会尽可能多的杂糅各种功能模块。

## 进度日志
---
1. 手动刷新页面   --> 另一个submit 按钮   有bug: 必须submit一次之后才生效  
原因：登录完成后请求/login/result  JQ获取失败(application/json)  提交之后请求/do_business JQ才获取成功
    -->  待填坑 --> 已解决(路径问题，将/login/result改为 /result   RESTful API?)
2. 密码MD5加密   -->已实现
3. 引用常用正则     将validation从formbean中分类出来  用户名  密码  邮箱 正浮点数  身份证号  -->已实现
4. ajax 输入userName马上知道是否已经注册   -->  已用按钮实现  --> 已实现
5. 注册页面有小bug  可用ajax解决 
6. 登录时输入验证码 kaptcha  --> 已实现
7. 登录5次失败封号一天（先做一个demo）数据库添加miss_time和isAllow  定时任务重置isAllow  -->已实现
8. 注册时需要邮箱验证码 --> 已实现

## 页面效果
---
#### 使用框架
- 总体页面: [MDUI](https://www.mdui.org/docs/ "MDUI")
- 弹出提示框: [Tippy.js](https://atomiks.github.io/tippyjs/ "Tippy.js")

##### index
![index](页面截图/index.png 'index')
##### register
![register](页面截图/register.png 'register')
##### login
![login](页面截图/login.png 'login')
##### business
![business](页面截图/business.jpg 'business')
