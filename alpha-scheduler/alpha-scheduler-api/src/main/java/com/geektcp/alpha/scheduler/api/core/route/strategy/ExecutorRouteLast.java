package com.geektcp.alpha.scheduler.api.core.route.strategy;

import com.geektcp.alpha.scheduler.api.core.route.ExecutorRouter;
<<<<<<< HEAD:alpha-scheduler/alpha-scheduler-api/src/main/java/com/geektcp/alpha/scheduler/api/core/route/strategy/ExecutorRouteLast.java
import com.geektcp.alpha.scheduler.core.biz.model.ReturnT;
import com.geektcp.alpha.scheduler.core.biz.model.TriggerParam;
=======
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;
>>>>>>> upstream/master:alpha-scheduler/alpha-scheduler-api/src/main/java/com/geektcp/alpha/scheduler/api/core/route/strategy/ExecutorRouteLast.java

import java.util.List;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteLast extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        return new ReturnT<String>(addressList.get(addressList.size()-1));
    }

}
