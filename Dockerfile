FROM openjdk:13-alpine3.10
MAINTAINER yeyeck<yeyeck@sina.cn>
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories
RUN apk add -U tzdata
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
ADD system/* /tmp/yeblog/system/
WORKDIR /usr/local/app
ADD yeblog-*.jar  yeblog.jar
ENV JAVA_OPTS=""
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar yeblog.jar
EXPOSE 8090


