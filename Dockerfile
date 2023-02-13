#FROM openjdk:8
#需要先通过本地maven打包具体jar包，account版本与pom中version保持一致
#该文件为部署本地docker中的配置
#使用openjdk8
FROM openjdk:8
#将target中打包的文件映射到docker中
ADD target/hello-world-*.jar /hello-world.jar
#查看是否存在hello-world.jar文件
RUN bash -c 'touch /hello-world.jar'
#容器启动后执行的命令，执行java -jar运行jar包，设置环境变量，时区，jvm
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Duser.timezone=GMT+08","-Xmx256m","-Xms256m","/hello-world.jar"]