package org.kzhou.user.sentinelV1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SentinelHelloWorldService {

    private Logger logger = LoggerFactory.getLogger(SentinelHelloWorldService.class);

    public void doService(){
        logger.info("执行业务代码！");
    }

}
