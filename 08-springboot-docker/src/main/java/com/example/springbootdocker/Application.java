package com.example.springbootdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

//何为docker
    //docker是一个开源的应用容器引擎，基于go语言并遵从apache2.0协议开源，docker可以让开发者打包他们的应用以及依赖包到一个轻量级，可移植的容器中，然后发布到任何流行的linux机器上
    //也可以实现虚拟化
    //容器是完全使用沙箱机制，互相之间不会有任何接口，更重要的是容器性能开销极低
    //docker支持将软件编译成一个镜像，然后在镜像中，各种软件做好配置，将镜像发布出去，其他使用者可以直接使用这个镜像，运行中的这个镜像称为容器，容器启动是非常快速的，类似windows系统
    //里面的ghost操作系统，安装好后什么都有了

//docker核心概念
    //docker镜像（Images）:docker镜像是用于创建docker容器的模板
    //docker容器（Container）：容器是独立运行的一个或一组应用
    //docker客户端（Clinet）：客户端通过命令行或者其他工具使用docker api（https://docs.docker.com/reference/）与docker的守护进程通信
    //docker主机（Host）：一个物理或者虚拟的机器用于执行docker守护进程和容器
    //docker仓库（Registry）：docker仓库用来保存镜像，可以理解为代码控制中的代码仓库，docker hub(https://hub.docker.com)提供了庞大的镜像集合使用

//使用docker的步骤
    //1:安装docker
    //2:去docker仓库找到这个软件对应的镜像
    //3:使用docker运行这个镜像，这个镜像就会生成一个docker容器
    //4:对容器的启动停止就是对软件的启动停止

//安装虚拟机
    //vmware  virtualbox(小巧，oracle提供的免费版)
    //导入虚拟机文件cento7.ova
    //双击启动虚拟机，使用root/123456登录
    //使用linux客户端链接linux服务器进行命令操作
    //设置虚拟机的网络：桥接网络=》选好网卡=》接入网线
        //service network restart  重启虚拟机网络 centos7
    //查看IP地址: ip addr

//虚拟机上安装docker
    //1:查看centos版本：docker要求centos系统的内核版本高于3.10 =》 uname -r
    //2:升级软件安装包及内核（选做）：yum update
    //3:安装docker:yum install docker
    //4:启动docker:systemctl start docker
    //5:将docker服务设置为开机启动：systemctl enable docker
        //docker -v 查看dockers版本号
        //systemctl stop docker 停止docker

//常用操作
    //1:镜像操作
        //检索： docker search 关键字 eg:docker search redis ;我们经常去docker hub上检索镜像的详细信息，如镜像的TAG
        //拉去： docker pull 镜像名：tag  eg: docker pull redis:latest ; ：tag是可选的，tag表示标签，多为软件的版本，默认是latest
        //列表： docler images ;查看所有本地镜像
        //删除： docker rmi image_id ;删除指定的本地镜像
    //2:容器操作
        //软件镜像（qq安装程序）-》运行镜像--》产生一个容器（正在运行的qq软件），操作步骤如下：
                //1:搜索镜像： docker search tomcat
                //2:拉取镜像： docker pull tomcat
                //3:根据镜像启动容器：docker run --name mytomcat10 -d tomcat:latest=>docker run -d -p 8888:8080 --name mytomcat tomcat
                //4:查看运行中的容器：docker ps ->可到网页查看该tomcat是否运行： http://虚拟机ip:端口，目前无法访问，因为没有做端口映射
                    //关于映射后访问页面报错：Docker的Tomcat启动报错HTTP状态404-未找到“源服务器未能找到目标资源的表示或者是不愿公开一个已经存在的资源表示”解决方案
                    //http://events.jianshu.io/p/f8f1c16c0acc
                    //总的来说：出现这个错误的原因是因为tomcat没有访问到任何JSP页面，进入tomcat容器查看webapps文件夹：docker exec –it tomcat /bin/bash
                        //cd webapps ,进入webapps文件夹发现里面什么都没有，tomcat从这里面找JSP的
                        //复制webapps.dist文件夹的内容到webapps文件夹；cp -r webapps.dist/* webapps
                        //再次登录页面查看，浏览器输入[ip]:8080 (每次重新启动docker访问都需要重新设置)
                //5:停止运行中的容器：docker stop mytomcat10
                //6:查看所有的容器： docker ps -a
                //7:查看防火墙状态：service firewalld status,关闭防火墙：service firewalld stop
        //运行： docker run --name container name -d image-name  eg:docker run --name myredis -d redis
                //--name:自定义容器名称, -d:后台运行, image-name:指定镜像模板
        //列表： docker ps（查看运行中的容器）
                //加上-a,可以查看所有容器
        //停止： docker stop container-name/container-id
                //停止当前你运行的容器
        //启动： docker start container-name/container-id
                //启动容器
        //删除： docker rm container-id
                //删除指定容器
        //端口映射： -p 6379:6379 ; eg: docker run -d -p 6379:6379 --name myredis docker.io/redis
                //-p:主机端口映射到容器内部的端口
        //容器日志： docker logs container-name/container-id
                //查看docker的日志
        //更多命令：参见官网：https://docs.docker.com/
        //注意：一个镜像可以启动多个容器：docker run -d =p 8888:8080 tomcat   docker run -d -p 8889:8080 tomcat  docker run -d =p 8887:8080 tomcat
                //可能报错（启动docker之后操作了防火墙状态，导致docker链被清除，解决办法）输入指令  systemctl restart docker     重启docker服务及可重新生成自定义链DOCKER
                                                                                                                    //或者修改防火墙状态为原样

//预先安装的软件：mysql redis rabbitmq elasticsearch
    //启动mysql的时候报错
    //2022-05-30 09:14:42+00:00 [ERROR] [Entrypoint]: Database is uninitialized and password option is not specified
        //    You need to specify one of the following:
        //    - MYSQL_ROOT_PASSWORD
        //    - MYSQL_ALLOW_EMPTY_PASSWORD
        //    - MYSQL_RANDOM_ROOT_PASSWORD
    //正确的启动方法：docker run --name mysql01 -e MYSQL_ROOT_PASSWORD=2012WananXJH -d mysql
        //启动之后使用windows的mysql客户端连接一下虚拟机的mysql服务器试试,连接不上说明虚拟机的3306端口未开放，先停掉容易，并开放端口映射
        //docker run -p 3306:3306 --name mysql02 -e MYSQL_ROOT_PASSWORD=2012WananXJH -d mysql
        //设置字符集
            // docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
        //设置北极配置文件位置
            //$docker run --name some-mysql -v /my/custom:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag
        //详情请看docker镜像中的官方文档