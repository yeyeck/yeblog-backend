/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.31 : Database - yeblog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yeblog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `yeblog`;

/*Table structure for table `t_article` */

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author_id` int(11) NOT NULL DEFAULT '1',
  `title` varchar(200) COLLATE utf8mb4_bin NOT NULL,
  `abstract_text` varchar(150) COLLATE utf8mb4_bin NOT NULL,
  `views` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `t_article` */

insert  into `t_article`(`id`,`author_id`,`title`,`abstract_text`,`views`,`status`,`create_time`,`update_time`) values (1,1,'Hello, YeBlog','这是一篇 sample',0,1,'2020-09-18 13:09:55','2020-09-18 13:09:55');

/*Table structure for table `t_article_comment` */

DROP TABLE IF EXISTS `t_article_comment`;

CREATE TABLE `t_article_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author_id` int(11) NOT NULL COMMENT '所属user id',
  `article_id` int(11) NOT NULL COMMENT '所属 article',
  `content` varchar(300) COLLATE utf8mb4_bin NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `t_article_comment` */

/*Table structure for table `t_article_comment_reply` */

DROP TABLE IF EXISTS `t_article_comment_reply`;

CREATE TABLE `t_article_comment_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author_id` int(11) NOT NULL COMMENT '所属用户id',
  `article_id` int(11) NOT NULL COMMENT '所属文章id',
  `comment_id` int(11) NOT NULL COMMENT '父评论id',
  `reply_to` int(11) NOT NULL COMMENT '评论用户id',
  `content` varchar(400) COLLATE utf8mb4_bin NOT NULL COMMENT '评论内容',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `t_article_comment_reply` */

/*Table structure for table `t_article_content` */

DROP TABLE IF EXISTS `t_article_content`;

CREATE TABLE `t_article_content` (
  `id` int(11) NOT NULL COMMENT '文章ID',
  `md` mediumtext COLLATE utf8mb4_bin NOT NULL COMMENT '文章内容 MD格式',
  `html` mediumtext COLLATE utf8mb4_bin NOT NULL COMMENT '文章内容 HTML格式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `t_article_content` */

insert  into `t_article_content`(`id`,`md`,`html`) values (1,'YeBlog是一个基于 Springboot 和 Vue 的前后端分离博客项目。后台文章编辑使用 Markdown。\n项目地址\n- 前台展示：[yeblog/web](https://github.com/yeyeck/yeblog-web)\n- 后台管理：[yeblog/admin](https://github.com/yeyeck/admin)\n- 后端：[yeblog-backend](https://github.com/yeyeck/yeblog-backend)\n\n# C\n```c\n# include<stadio.h>\n\nint main()\n{\n    print(\"Hello, World!\");\n    return 0;\n}\n```\n\n# java\n```java\nclass HelloWorld {\n    public static void main(String[] args){\n        System.out.println(\"Hello, World\");\n    }\n}\n```\n# Python\n```python\ndef sum(a, b):\n  return a + b\nprint(\"sum of 1+2: \", sum(1, 2))\n``` \n# HTML\n```html\n<div>\n  <a href=\"www.yeyeck.com\">yeblog</a>\n</div>\n<script>\n$(\"#id\").click(function(){\n  consolo.log(\"Hello, World!\")\n})\n</script>\n<style>\n.yeblog {\n  width: 120px;\n  height: 120px;\n  padding: 0 0 0 0;\n}\n</style>\n```\n# Katex\n$x^2 + y^2 = z^2$','<p>YeBlog是一个基于 Springboot 和 Vue 的前后端分离博客项目。后台文章编辑使用 Markdown。<br />\n项目地址</p>\n<ul>\n<li>前台展示：<a href=\"https://github.com/yeyeck/yeblog-web\" target=\"_blank\">yeblog/web</a></li>\n<li>后台管理：<a href=\"https://github.com/yeyeck/admin\" target=\"_blank\">yeblog/admin</a></li>\n<li>后端：<a href=\"https://github.com/yeyeck/yeblog-backend\" target=\"_blank\">yeblog-backend</a></li>\n</ul>\n<h1><a id=\"C_6\"></a>C</h1>\n<pre><div class=\"hljs\"><code class=\"lang-c\"><span class=\"hljs-meta\"># <span class=\"hljs-meta-keyword\">include</span><span class=\"hljs-meta-string\">&lt;stadio.h&gt;</span></span>\n\n<span class=\"hljs-function\"><span class=\"hljs-keyword\">int</span> <span class=\"hljs-title\">main</span><span class=\"hljs-params\">()</span>\n</span>{\n    print(<span class=\"hljs-string\">&quot;Hello, World!&quot;</span>);\n    <span class=\"hljs-keyword\">return</span> <span class=\"hljs-number\">0</span>;\n}\n</code></div></pre>\n<h1><a id=\"java_17\"></a>java</h1>\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">HelloWorld</span> </span>{\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">main</span><span class=\"hljs-params\">(String[] args)</span></span>{\n        System.out.println(<span class=\"hljs-string\">&quot;Hello, World&quot;</span>);\n    }\n}\n</code></div></pre>\n<h1><a id=\"Python_25\"></a>Python</h1>\n<pre><div class=\"hljs\"><code class=\"lang-python\"><span class=\"hljs-function\"><span class=\"hljs-keyword\">def</span> <span class=\"hljs-title\">sum</span>(<span class=\"hljs-params\">a, b</span>):</span>\n  <span class=\"hljs-keyword\">return</span> a + b\nprint(<span class=\"hljs-string\">&quot;sum of 1+2: &quot;</span>, sum(<span class=\"hljs-number\">1</span>, <span class=\"hljs-number\">2</span>))\n</code></div></pre>\n<h1><a id=\"HTML_31\"></a>HTML</h1>\n<pre><div class=\"hljs\"><code class=\"lang-html\"><span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">div</span>&gt;</span>\n  <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">a</span> <span class=\"hljs-attr\">href</span>=<span class=\"hljs-string\">&quot;www.yeyeck.com&quot;</span>&gt;</span>yeblog<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">a</span>&gt;</span>\n<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">div</span>&gt;</span>\n<span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">script</span>&gt;</span><span class=\"javascript\">\n$(<span class=\"hljs-string\">&quot;#id&quot;</span>).click(<span class=\"hljs-function\"><span class=\"hljs-keyword\">function</span>(<span class=\"hljs-params\"></span>)</span>{\n  consolo.log(<span class=\"hljs-string\">&quot;Hello, World!&quot;</span>)\n})\n</span><span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">script</span>&gt;</span>\n<span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">style</span>&gt;</span><span class=\"css\">\n<span class=\"hljs-selector-class\">.yeblog</span> {\n  <span class=\"hljs-attribute\">width</span>: <span class=\"hljs-number\">120px</span>;\n  <span class=\"hljs-attribute\">height</span>: <span class=\"hljs-number\">120px</span>;\n  <span class=\"hljs-attribute\">padding</span>: <span class=\"hljs-number\">0</span> <span class=\"hljs-number\">0</span> <span class=\"hljs-number\">0</span> <span class=\"hljs-number\">0</span>;\n}\n</span><span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">style</span>&gt;</span>\n</code></div></pre>\n<h1><a id=\"Katex_49\"></a>Katex</h1>\n<p><span class=\"katex\"><span class=\"katex-mathml\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\"><semantics><mrow><msup><mi>x</mi><mn>2</mn></msup><mo>+</mo><msup><mi>y</mi><mn>2</mn></msup><mo>=</mo><msup><mi>z</mi><mn>2</mn></msup></mrow><annotation encoding=\"application/x-tex\">x^2 + y^2 = z^2</annotation></semantics></math></span><span class=\"katex-html\" aria-hidden=\"true\"><span class=\"base\"><span class=\"strut\" style=\"height:0.897438em;vertical-align:-0.08333em;\"></span><span class=\"mord\"><span class=\"mord mathdefault\">x</span><span class=\"msupsub\"><span class=\"vlist-t\"><span class=\"vlist-r\"><span class=\"vlist\" style=\"height:0.8141079999999999em;\"><span style=\"top:-3.063em;margin-right:0.05em;\"><span class=\"pstrut\" style=\"height:2.7em;\"></span><span class=\"sizing reset-size6 size3 mtight\"><span class=\"mord mtight\">2</span></span></span></span></span></span></span></span><span class=\"mspace\" style=\"margin-right:0.2222222222222222em;\"></span><span class=\"mbin\">+</span><span class=\"mspace\" style=\"margin-right:0.2222222222222222em;\"></span></span><span class=\"base\"><span class=\"strut\" style=\"height:1.008548em;vertical-align:-0.19444em;\"></span><span class=\"mord\"><span class=\"mord mathdefault\" style=\"margin-right:0.03588em;\">y</span><span class=\"msupsub\"><span class=\"vlist-t\"><span class=\"vlist-r\"><span class=\"vlist\" style=\"height:0.8141079999999999em;\"><span style=\"top:-3.063em;margin-right:0.05em;\"><span class=\"pstrut\" style=\"height:2.7em;\"></span><span class=\"sizing reset-size6 size3 mtight\"><span class=\"mord mtight\">2</span></span></span></span></span></span></span></span><span class=\"mspace\" style=\"margin-right:0.2777777777777778em;\"></span><span class=\"mrel\">=</span><span class=\"mspace\" style=\"margin-right:0.2777777777777778em;\"></span></span><span class=\"base\"><span class=\"strut\" style=\"height:0.8141079999999999em;vertical-align:0em;\"></span><span class=\"mord\"><span class=\"mord mathdefault\" style=\"margin-right:0.04398em;\">z</span><span class=\"msupsub\"><span class=\"vlist-t\"><span class=\"vlist-r\"><span class=\"vlist\" style=\"height:0.8141079999999999em;\"><span style=\"top:-3.063em;margin-right:0.05em;\"><span class=\"pstrut\" style=\"height:2.7em;\"></span><span class=\"sizing reset-size6 size3 mtight\"><span class=\"mord mtight\">2</span></span></span></span></span></span></span></span></span></span></span></p>\n');

/*Table structure for table `t_article_label` */

DROP TABLE IF EXISTS `t_article_label`;

CREATE TABLE `t_article_label` (
  `article_id` int(11) NOT NULL,
  `label_id` int(11) NOT NULL,
  PRIMARY KEY (`article_id`,`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `t_article_label` */

insert  into `t_article_label`(`article_id`,`label_id`) values (1,1);

/*Table structure for table `t_label` */

DROP TABLE IF EXISTS `t_label`;

CREATE TABLE `t_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `NAME` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `t_label` */

insert  into `t_label`(`id`,`name`,`create_time`) values (1,'YeBlog','2020-09-18');

/*Table structure for table `t_settings` */

DROP TABLE IF EXISTS `t_settings`;

CREATE TABLE `t_settings` (
  `id` tinyint(4) NOT NULL,
  `json_value` json NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `t_settings` */

insert  into `t_settings`(`id`,`json_value`,`update_time`) values (1,'{\"domain\": \"\", \"record\": \"\", \"keywords\": \"JAVA, VUE, SpringBoot, 前后端分离\", \"siteName\": \"YeBlog\", \"recordUrl\": \"\", \"description\": \"YeBlog————一个简单轻量的个人博客\"}','2020-08-27 06:35:34'),(3,'{\"host\": \"\", \"opened\": false, \"password\": \"\", \"username\": \"\"}','2020-07-17 07:02:25');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(80) COLLATE utf8mb4_bin NOT NULL,
  `nickname` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `salt` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `header` varchar(130) COLLATE utf8mb4_bin DEFAULT '/api/file/header/default-header.png',
  `role` enum('admin','author','member') COLLATE utf8mb4_bin NOT NULL,
  `create_time` date NOT NULL,
  `update_time` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`nickname`,`password`,`salt`,`header`,`role`,`create_time`,`update_time`) values (1,'Admin','Admin','ddba1766cf73a08904b96beac66c2022','39cf8b89afd2430388f9f07603c98a6e',NULL,'admin','2020-09-18','2020-09-18');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
