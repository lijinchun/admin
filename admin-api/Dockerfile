# Docker image for springboot file run
# VERSION 0.0.1
# Author: lijinchun
# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER lijinchun <bluesky89@aliyun.com>
#允许指定端口转发
EXPOSE 8080
EXPOSE 8000
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp 
# 将jar包添加到容器中并更名为app.jar
ADD target/admin-api.jar admin-api.jar
# 运行jar包
RUN bash -c 'touch /admin-api.jar'

ENV JAVA_OPTS=""
# security.egd eg: "-Djava.security.egd=file:/dev/./urandom"
ENV JAVA_OPT_SECURITY=""
# remote debug eg: "-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000"
ENV JAVA_OPT_DEBUG=""
# file encoding default UTF-8
ENV JAVA_OPT_FILE_ENCODING="UTF-8"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS $JAVA_OPT_SECURITY -Dfile.encoding=$JAVA_OPT_FILE_ENCODING -jar $JAVA_OPT_DEBUG /admin-api.jar" ]

#docker run -d -p 8080:8080 -p 8000:8000 --name admin-api lijinchun/admin-api:dev