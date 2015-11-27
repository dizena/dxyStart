
dxyStart介绍
1，官方网站API

 http://www.dizena.com/docapi


      dxyStart是一个综合性的互联网架构设计产品，采用java语言开发，支持分布式集群应用，支持 方便快捷的CARD操作开发，支持Restful的交互，支持XML、JSON的数据接口。全面支持多数据源，关系型以及非关系型的数据源提 供标准化的操作。原生支持MySQL,MongoDB,Redis，自由配置、方便快捷。

      dxyStart基于java开发，由主流的开源项目支撑。依托Spring、Apache Shiro、 Mybatis 三大主流JAVA技术框架，系统管理使用bootstrap可视化。层次结构分明，方便快捷的扩展和增加功能。原生拥有用户登录注册，邮件发送，角色权限， 资源配置等功能。文档和代码规范通俗易懂，便于学习。是企业架构的优选产品。欢迎牛人高人做一些安全、功能、设计上的优化和补充。 如果你也想参考学习或者帮助完善，请联系我们。

    dxyStart不冗余，简单明了，对于架构师和开发者都能很快的修改和适应，模块化的区分让增删模块更加的快速。

github下载地址：https://github.com/dizena/dxyStart
下载：aattools.jar
安装：mvn install:install-file -Dfile=aattools.jar -DgroupId=com.aat -DartifactId=atools -Dversion=1.0 -Dpackaging=jar
下载：Sdk4JWeibo.jar
安装：mvn install:install-file -Dfile=Sdk4JWeibo.jar -DgroupId=com.sina -DartifactId=weibosdk -Dversion=1.0 -Dpackaging=jar
下载：客户端Oauth2文件
概述

    为什么叫做dxyStart?也许大家觉得可笑，这是源自我的一个梦想，它的英文是dizena，我称之为“淡香雅”。都知道google是谷歌 的英文名称，这个词不是词典里原生的，是创造出来的。谷歌创始人谢尔盖·布林和拉里·佩奇选用了“googol”一词，指的是10的100次幂，代表在互 联网上可以获得的海量信息。但是当他们将这一项目提交给投资者后，他们收到的支票上面的名字却写成了“Google”，他们也“将错就错”将这一名字沿用 至今。还有一个开源的大数据基础架构也是如此，字典里并没有这个词，它就是hadoop，这个技术让互联网更智能。淡香雅是我的梦想，梦想的英文名字是 dizena，字典里并没有这个词，我相信这个词会被认可的。dxyStart是技术架构基础，是dizena项目的一部分，我相信dxyStart是可 以为企业为互联网建设作为一个基础的，感兴趣吗？还等什么，开始研究吧。
架构思想

    对于学习软件工程的同学来说，了解软件的设计架构师基础。我们的经验和习惯告诉我们，分模块分层次是有利于开发和维护的。在大型的设计中用 SOA、系统建设采用分布式集群等等。在这里我们讨论比较小的，就是项目模块分层。对，我们就讨论分层这个小粒度。MVC的分层是最广泛的分层形式，讲究 高内聚低耦合。什么是MVC？MVC全名是Model View Controller，是模型(model)－视图(view)－控制器(controller)的缩写，一种软件设计典范，用一种业务逻辑、数据、界面 显示分离的方法组织代码，将业务逻辑聚集到一个部件里面，在改进和个性化定制界面及用户交互的同时，不需要重新编写业务逻辑。MVC被独特的发展起来用于 映射传统的输入、处理和输出功能在一个逻辑的图形化用户界面的结构中。基于这个思想，软件的设计师演化了更多的形式，在JAVA中我就遇到很多的类 型，pojo,model,entity,bean这是模型的层次都能整出四个不同的写法，dao, persistence, repository是数据库操作的写法，比较一致的是service，都知道业务实现、事务在这里。对于接口交互写法也很多 web,controller,ctrl,view等等。当然我们dxyStart也有自己的一套写法，我们模块下面是bean、dao、 service、web.controller，其中web里面还有filter,taglib等等，之所以如此命名，是因为排序，文件夹按照首子母的 hashCode排序，所以我们希望分层不仅分开层次逻辑，还要像流水一样，从上到下，行云流水。
数据库规范


第一条：数据库表名规范

1，每个表的名字是：模块_表名，例如：cms_user，cms指模块名称，user是该模块的模型user。
2，如果表名较长，需要两个以上的单词或者拼音，采用驼峰命名法,不需要再用下划线。比如：cctv_projectSupport。
第二条：字段属性规范

1，每一个属性字段采用驼峰命名法则，不需要再用下划线,首字母小写。比如：userId,payUser;
2，每个表的属性增加以下属性:ts(datetime),num1(int),str1(varchar),str2(varchar) 四个字段。
3，如果业务很多，不确定性加大，每个表的属性增加以下属性:ts(datetime),num1(int),num2(int),str1(varchar),str2(varchar),str3(varchar) 七个字段。
第三条：数据类型

除了金融项目外，我们在一般的业务建设中，使用下面几种数据的类型（MySQL范例）。

整数型：int,bigint
浮点型：decimal(15,2)
字符串：varchar
大文本：text
日期：datetime



代码规范


生成代码规则
com.aat.dxfy.base  com.企业.项目.模块
Base{bean,dao,service,web} 模块的结构
Bean里面有多个实体类型，是实现base模块的内容
Dao里的XyzDao.xml都是持久层，其数据源由，xxx-spring-myql-base.xml提供；
如果有其他的模块，比如：com.aat.dxfy.shop,则增加一个xxx-spring-mysql-shop.xml提供；
与base,shop同级别的是，utils包；此处事公共的；
Package里面要有说明，暴露的方法，比如：BaseController是所有的继承；
每一个model.xlsm的衍生模型，都是一个模块，比如：dxfy_base.xlsm,base_shop.xlsm;
其表结构：base_user;shop_user;
生成代码的时候，解析模块和表名；


代码规则说明
Java代码命名规则：com.cctv.ejinr.news;
含义：com是代表企业，也有org开头代表开源。
      cctv是企业的名称，
      ejinr是项目名称，
      news是模块名称。
在news里面会有：bean，dao，service，web 四个包，代表模型,数据持久层，业务层，网络交互层。其中封装好的层次，依次执行。
JAVA 命名规范：
类名：大写字母开头，驼峰式命名。

属性：小写字母开头，驼峰式命名。


注释规则说明

类名注释规范，时间、作者、联系方式、版本、功能、使用说明、更新说明等。

/**

 * @time 2014-01-05

 * @author xingle

 * @email 1066031245@qq.com

 * @version v1.0

 * @function 公共的常量

 * @info java  

 * @update

 */



前端规范

1，前端设计模块化
2，JavaScript全部放在body尾部，加速执行。
技术汇总


1、后端
    核心框架：Spring Framework
    安全框架：Apache Shiro
    视图框架：Spring MVC
    任务调度：Spring Task
    持久层框架：MyBatis
    数据库连接池：Alibaba Druid
    缓存框架：Ehcache、Redis
    日志管理：logback

2、前端Flatlab
    JS框架：jQuery
    CSS框架：Bootstrap
    富文本：kindeditor
    下拉选择框：autoMultiSelect  Or  autoTxt
    树结构控件：bootstrap-treeview

    日期控件： bootstrap-datetimepicker
3、平台

    服务器中间件：支持应用服务器中间件 有Tomcat、Glassfish、Jboss、WebLogic、WebSphere。
    数据库支持：MySQL、Oracle、Sql Server、Sqllite等
    NoSQL:mongoDB、redis、memcached等
    开发环境：Java EE、Eclipse、Maven、SVN


