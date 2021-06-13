package org.kzhou.user.sentinelV1;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
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
public class SentinelHelloWorldControllerV2 {

    private Logger logger = LoggerFactory.getLogger(SentinelHelloWorldControllerV2.class);

    @Autowired
    private SentinelHelloWorldService service;

    @PostConstruct
    public void init(){
        ArrayList<FlowRule> flowRules1 = new ArrayList<>();
        List<FlowRule> flowRules = flowRules1;

        FlowRule flowRule2 = new FlowRule();
        flowRule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule2.setRefResource("helloSentinelV2");
        flowRule2.setCount(1);
        flowRules.add(flowRule2);

        FlowRuleManager.loadRules(flowRules);
    }

    @RequestMapping("/helloSentinelV2")
    @SentinelResource(value = "helloSentinelV2",blockHandler = "lowerMethod")
    public String testHelloSentinelV1(){
        service.doService();
        return "ok!";
    }

    public String lowerMethod(){
        return "走了降级方法！！！";
    }
}
