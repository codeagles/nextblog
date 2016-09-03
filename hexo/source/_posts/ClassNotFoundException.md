---
title: Struts2配置ClassNotFoundException
date: 2016-09-02 00:00:49
categories: 学习
tags: JAVA
---
Struts2配置 ClassNotFoundException: rg.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilt 问题解决如下：
<!--more-->
练习Struts2时，配置web.xml：

``
 <filter>   
   <filter-name>struts2</filter-name>   
   	<font color=red>
	<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
	</font>
   </filter> 
 	<filter-mapping>   
   <filter-name>struts2</filter-name>   
   <url-pattern>/*</url-pattern>   
 	</filter-mapping> 
``
然后运行项目的时候有时就会出现如下信息，去网上找了跟多都没有用，朋友也遇到这个问题，很多机器遇到这个问题但是解决办法都不一样，在这里做一下总结。
###错误信息如下：<font color=red>
严重: Exception starting filter struts2
java.lang.ClassNotFoundException: org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter
at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1360)
at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1206)
at org.apache.catalina.core.ApplicationFilterConfig.getFilter(ApplicationFilterConfig.java:249)
at org.apache.catalina.core.ApplicationFilterConfig.setFilterDef(ApplicationFilterConfig.java:397)
at org.apache.catalina.core.ApplicationFilterConfig.<init>(ApplicationFilterConfig.java:108)
at org.apache.catalina.core.StandardContext.filterStart(StandardContext.java:3709)
at org.apache.catalina.core.StandardContext.start(StandardContext.java:4356)
at org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1045)
at org.apache.catalina.core.StandardHost.start(StandardHost.java:719)
...</font>

在解决之前，请先<font color=red>按住ctrl+鼠标左键</font> 点击<filter-class><u>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</u></filter-class> 

中间的类(会出现下划线)，看能不能找到该类，如果能请用下面的方法，如果不能，那就是struts-core核心包出错了，重新导入这个包，再试。

解决办法（当然请排除代码写错了或者单词大小写错误之类的人为原因）

如果是MyEclipse9，那么不要偷懒，<font color=red>如果直接buildPath，然后Add  Ex. .jar导入本地jar包的话，可能会出问题。</font>那么解决方案，在WEB-INF目录下的lib文件夹中，将包复制粘贴进去即可。（可能原因：Tomcat可能识别项目本地的jar包。。不太懂。。但是好使了。）
导入还不好使，清空tomcat中<font color=red>webapps文件夹中所有struts2的项目，一定要清空，</font>可能出现项目冲突导致出现这个问题。
如果是M有Eclipse2014（个人是这个版本，但是好像10以后都差不多，没试过），如果在lib中导入不好使，那么用自带的struts开发包，操作：右键项目->MyEclipse->Project Facets->Install ..  struts这个选项就行。
还有一种情况就是，新建项目，导包，可能好使。
最后以上都不好使，将Tomcat版，有人6.0不好使，降到5.0就可以了，不过我个人是8.0没遇到过不兼容还是莫名其妙的问题。
<font color=blue>
还有这种就是版本问题，我用的是2.3所以没测试过，

因为从Struts2.1.3开始，将废弃ActionContextCleanUp过滤器，而在StrutsPrepareAndExecuteFilter过滤器中包含相应的功能。在web.xml中做修改红色字体部分。struts2中StrutsPrepareAndExecuteFilter 异常如果是2.1.3之前的版本用<font color=red>org.apache.struts2.dispatcher.FilterDispatcher</font>，否则用<font color=red>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter。</font> </font>

<font color=gray>
以上这些方法，一大部分是亲测，都好使，因为调试过很多机器，上网找了很多，但是方法都不一样，自己用了一些好使，就都写出来了，如果以后还有新方法，我会回来追加，如果有其他解决办法，也请留言，我会后续补充。</font>