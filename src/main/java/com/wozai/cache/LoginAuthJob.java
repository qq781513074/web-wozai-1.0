package com.wozai.cache;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by zengzihao on 2014/3/25.
 */
public class LoginAuthJob extends QuartzJobBean {
    LoginAuthTaskJob runMeTask;
    private void setLoginAuthTask(LoginAuthTaskJob runMeTask){
        this.runMeTask = runMeTask;
    }
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        runMeTask.authCheck();
    }
}
