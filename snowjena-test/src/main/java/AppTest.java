import com.github.onblog.commoon.entity.RateLimiterRule;
import com.github.onblog.commoon.entity.RateLimiterRuleBuilder;
import com.github.onblog.core.limiter.RateLimiter;
import com.github.onblog.core.limiter.RateLimiterFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author shillu
 * @version 1.0
 * @data 2022/5/16 17:12
 * @description
 */
public class AppTest {
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 本地限流
     */
    @Test
    public void test1() {

        // 1.配置规则 - snowjean-commoon.entity.RateLimiterRule;
        RateLimiterRule rateLimiterRule = new RateLimiterRuleBuilder()
                .setLimit(1)
                .setPeriod(1)
                .setUnit(TimeUnit.SECONDS) //每秒令牌数为1
                .build();

        // 2.工厂模式生产限流器
        RateLimiter limiter = RateLimiterFactory.of(rateLimiterRule);

        // 3.使用
        while (true) {
            if (limiter.tryAcquire()) {
                logger.info("ok");
            }
        }
    }

}

