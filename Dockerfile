FROM openjdk:8u212-jre-alpine3.9
MAINTAINER yeyeck<yeyeck@sina.cn>
ADD system/* /tmp/yeblog/system/
WORKDIR /usr/local/app
ADD yeblog-*.jar  yeblog.jar
RUN apk add -U tzdata
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Xms256m -Xmx256m -Xmn128m","-jar","yeblog.jar"]
EXPOSE 8090

