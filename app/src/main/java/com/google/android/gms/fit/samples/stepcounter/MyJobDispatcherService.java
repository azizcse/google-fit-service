package com.google.android.gms.fit.samples.stepcounter;

/*
 *  ****************************************************************************
 *  * Created by : Md. Azizul Islam on 8/13/2018 at 6:14 PM.
 *  * Email : azizul@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Azizul Islam on 8/13/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.app.job.JobParameters;
import android.util.Log;

import com.firebase.jobdispatcher.JobService;

import java.text.DateFormat;
import java.util.Date;

public class MyJobDispatcherService extends JobService {

    private static final String TAG = "MyJobDispatcherService";

    @Override
    public boolean onStartJob(com.firebase.jobdispatcher.JobParameters job) {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Log.e(TAG, "Started and execute"+currentDateTimeString);
        return false;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        Log.d(TAG, "Job cancelled!");
        return false;
    }
}
