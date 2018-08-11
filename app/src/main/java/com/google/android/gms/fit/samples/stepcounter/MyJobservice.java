package com.google.android.gms.fit.samples.stepcounter;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.google.android.gms.fit.samples.common.logger.Log;

public class MyJobservice extends JobService {
    private final String TAG = getClass().getName();
    private boolean jobCancelled = false;
    @Override
    public boolean onStartJob(JobParameters params) {
        doSomething(params);
        return true;
    }

    private void doSomething(final JobParameters parameters){
        new Thread(new Runnable() {
            @Override
            public void run() {
              for(int i = 0; i < 10; i++){
                  if(jobCancelled){
                      return;
                  }

                  Log.e(TAG, "run = "+i);
                  try {
                      Thread.sleep(1000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }

              jobFinished(parameters, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG, "Job cancelled before complete");
        jobCancelled = true;
        return false;
    }
}
