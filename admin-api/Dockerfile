# Docker image for springboot file run
# VERSION 0.0.1
# Author: lijinchun
# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER lijinchun <bluesky89@aliyun.com>
#允许指定端口转发
EXPOSE 8080
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp 
# 将jar包添加到容器中并更名为app.jar
ADD target/admin-api.jar admin-api.jar
# 运行jar包
RUN bash -c 'touch /admin-api.jar'
#ENTRYPOINT ["java","-jar","admin-api.jar"]

ENV JAVA_OPTS=""
ENV JAVA_OPT_SECURITY="-Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS $JAVA_OPT_SECURITY -jar /admin-api.jar" ]

#docker run -d --name admin-api lijinchun/admin-api:dev