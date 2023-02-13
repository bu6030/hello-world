#!/bin/bash
#部署前本地先maven package打包
#本地部署到本机docker
echo ========开始执行本地Docker部署========
#version是从pom.xml中的<version>0.0.1-SNAPSHOT</version>中取出来的，所以应该是0.0.1
CURRENT_V=$(echo $line | grep 'SNAPSHOT' pom.xml | awk '{split($0,a,"-"); print a[1]}'| awk '{split($0,a,">"); print a[2]}')
echo ========当前版本$CURRENT_V========
#如果docker中已经存在该版本或者运行着的版本，停止，删除服务，删除镜像
echo ========停止以及删除旧版本========
#停止在运行的版本服务
docker stop hello-world-$CURRENT_V
#删除刚刚停止的服务
docker rm hello-world-$CURRENT_V
#删除image镜像
docker rmi hello-world/$CURRENT_V

echo ========开始build新版本$NEW_V========
#build出新的镜像
docker build -t hello-world/$CURRENT_V .

echo ========开始部署新版本$NEW_V========
#启动服务，暴露8082，8081端口，设置环境变量sqllite.db.file
#映射本地hello-world.db到docker中，防止每次部署数据库回档到代码中的版本
#设置时区时间
docker run -d -p 8082:8082 -p 8081:8081 \
--env sqllite.db.file=/hello-world.db \
-v hello-world.db:/hello-world.db \
--name hello-world-$CURRENT_V -v /etc/localtime:/etc/localtime hello-world/$CURRENT_V
echo ========本地Docker部署完成========