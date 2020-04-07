package com.geektcp.alpha.scheduler.api.core.route.strategy;

import com.geektcp.alpha.scheduler.api.core.route.ExecutorRouter;
<<<<<<< HEAD:alpha-scheduler/alpha-scheduler-api/src/main/java/com/geektcp/alpha/scheduler/api/core/route/strategy/ExecutorRouteRandom.java
import com.geektcp.alpha.scheduler.core.biz.model.ReturnT;
import com.geektcp.alpha.scheduler.core.biz.model.TriggerParam;
=======
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;
>>>>>>> upstream/master:alpha-scheduler/alpha-scheduler-api/src/main/java/com/geektcp/alpha/scheduler/api/core/route/strategy/ExecutorRouteRandom.java

import java.util.List;
import java.util.Random;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteRandom extends ExecutorRouter {

    private static Random localRandom = new Random();

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        String address = addressList.get(localRandom.nextInt(addressList.size()));
        return new ReturnT<String>(address);
    }

}
