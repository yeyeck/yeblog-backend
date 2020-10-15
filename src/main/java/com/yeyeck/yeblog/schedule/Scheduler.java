package com.yeyeck.yeblog.schedule;

import com.yeyeck.yeblog.dao.IRedisDao;
import com.yeyeck.yeblog.mapper.ArticleMapper;
import com.yeyeck.yeblog.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class Scheduler {

    private IRedisDao redisDao;

    private ArticleMapper articleMapper;

    public Scheduler(IRedisDao redisDao, ArticleMapper articleMapper) {
        this.redisDao = redisDao;
        this.articleMapper = articleMapper;
    }

    /**
     * 每天凌晨 0点过 5秒 执行
     */
    @Scheduled(cron = "5 0 0 * * ?")
    public void articleStatistic () {
        log.info("########开始统计今日文章访问量########");
        String pattern = "ARTICLE-" + KeyUtils.yesterday() + "*";
        Set<String> keys = redisDao.keys(pattern);
        for (String key : keys) {
            Integer viewsToday = redisDao.hyperLogLogSize(key).intValue();
            Integer id = Integer.valueOf(key.split("-")[2]);
            // 入库
            articleMapper.addViews(id, viewsToday);
            // 删除缓存
            redisDao.delete(key);
        }
        log.info("########今日文章访问量统计完毕########");
    }


}
