# logger-util
## 日志工具：日志等级调整等
  我个人参考对美团的日志调整项目做了相应的调整，适应自己的当前情况，在下一个文章做具体的介绍。
## 相关日志学习文章：
* [深入理解Logger日志——简介](https://www.cnblogs.com/zhouguanglin/p/13803918.html)
* [深入理解Logger日志——框架绑定原理](https://www.cnblogs.com/zhouguanglin/p/13804013.html)
* [深入理解Logger日志——日志等级动态调整](https://www.cnblogs.com/zhouguanglin/p/13809806.html)


### 项目结构图：
  ![Image text](https://img2020.cnblogs.com/blog/1190778/202010/1190778-20201014171934548-893888604.png)

图片地址：https://img2020.cnblogs.com/blog/1190778/202010/1190778-20201014171934548-893888604.png
    
### 具体使用方式
    引入jar包，这个包是个人的，可以根据个人喜爱，随意操作，放在maven私有仓库中

        <dependency>
            <groupId>com.ninebot.apr</groupId>
            <artifactId>logger</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        
### 测试中调用：
  ![Image text](https://img2020.cnblogs.com/blog/1190778/202010/1190778-20201014173706426-2098082132.png)

图片地址：https://img2020.cnblogs.com/blog/1190778/202010/1190778-20201014173706426-2098082132.png
 
