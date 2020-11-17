# yeblog-backend
YeBlog 后端，集成 Shiro,Redis, Email, Mybatis  
博客前台：[yeblog-web](https://github.com/yeyeck/yeblog-web)  
后台管理：[yeblog-admin](https://github.com/yeyeck/yeblog-admin)  
在线实例：[www.yeyeck.com](https://www.yeyeck.com)
# 目前实现的功能
- [x] 博客功能
   - [x] 支持 Markdown 编辑
   - [x] 博主编辑、发布和删除文章
   - [x] 文章评论，审核功能
   - [x] 编辑缓存功能，意外退出会保留编辑内容
- [ ] 邮箱功能
   - [x] 博主开启/关闭邮箱功能
   - [ ] 评论回复邮件提醒
- [x] SEO相关
   - [x] SSR支持
   - [x] 自定义站点名称、副标题、关键字和首页描述
   - [x] 文章详情页支持关键字编辑，标题以“文章标题——站点名称”的结构，描述为文章摘要
   - [x] 导航栏支持顶部导航和底部导航
   - [x] 添加友链功能

## 1. 配置开发环境， 在虚拟机上用 docker 安装 redis 和 mysql
### 1.1 安装 mysql
```bash
docker pull mysql:5.7 
docker run -p 3306:3306 -d --name mysql --env MYSQL_ROOT_PASSWORD=password mysql:5.7
```
### 1.2 安装 redis
```bash
docker pull redis
docker run -d -p 6379:6379 --name redis redis --requirepass password 
```
### 1.3 配置 application-dev.yml， 需要修改以下属性
1. mysql的ip地址，设置为虚拟机的ip
2. mysql的password
3. reds的host，设置为虚拟机的ip
4. redis的password
5. 本地运行的时候，将applocation.yml的 active 属性设置为 dev。 pro 是打包用来生成 docker image 的。
