package org.kzhou.user.sentinelV1;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SentinelHelloWorldControllerV1 {

    private Logger logger = LoggerFactory.getLogger(SentinelHelloWorldControllerV1.class);

    @Autowired
    private SentinelHelloWorldService service;

    @PostConstruct
    public void init(){
        List<FlowRule> flowRules = new ArrayList<>();

        FlowRule flowRule = new FlowRule();
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setResource("helloSentinelV1");
        flowRule.setCount(1);
        flowRules.add(flowRule);

        FlowRuleManager.loadRules(flowRules);
    }

    @RequestMapping("/helloSentinelV1")
    public String testHelloSentinelV1(){
        Entry entry = null;
        try {
            entry  = SphU.entry("helloSentinelV1");
            service.doService();
        }catch (Exception e){
            logger.warn("helloSentinelV1接口被限流了！");
            return "helloSentinelV1接口被限流了！";
        }finally {
            if(entry!=null){
                entry.exit();
            }
        }
        return "ok!";
    }

}
