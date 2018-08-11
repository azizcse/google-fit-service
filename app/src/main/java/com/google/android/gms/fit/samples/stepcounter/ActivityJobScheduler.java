package com.google.android.gms.fit.samples.stepcounter;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.fit.samples.common.logger.Log;

public class ActivityJobScheduler extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_job_schedular);
    }

    public void onJobStart(View view){
        ComponentName componentName = new ComponentName(this, MyJobservice.class);

        JobInfo jobInfo = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(1 * 60* 1000)
                .build();

        JobScheduler jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int result = jobScheduler.schedule(jobInfo);

        if(result == JobScheduler.RESULT_SUCCESS){
            Log.e("MyJobservice","Success");
        }else {
            Log.e("MyJobservice","Job failed");
        }
    }

    public void onJobCaneled(View view){
        JobScheduler jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(123);
    }
}
