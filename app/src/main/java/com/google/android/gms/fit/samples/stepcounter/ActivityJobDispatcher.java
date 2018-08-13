package com.google.android.gms.fit.samples.stepcounter;

/*
 *  ****************************************************************************
 *  * Created by : Md. Azizul Islam on 8/13/2018 at 6:12 PM.
 *  * Email : azizul@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Azizul Islam on 8/13/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

public class ActivityJobDispatcher extends AppCompatActivity{
    private  final String JOB_TAG = "MyJobDispatcherService1";
    private FirebaseJobDispatcher mDispatcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_schedular);
        mDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
    }

    public void onJobStart(View view){

        Job myJob = mDispatcher.newJobBuilder()
                .setService(MyJobDispatcherService.class)
                .setTag(JOB_TAG)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(0, 20))
                .setReplaceCurrent(false)
                .setConstraints(Constraint.DEVICE_CHARGING)
                .build();
        mDispatcher.mustSchedule(myJob);
        Toast.makeText(this, "Job dispatch", Toast.LENGTH_LONG).show();

    }
    public void onJobCaneled(View view){
        if ("".equals(JOB_TAG)) {
            mDispatcher.cancelAll();
        } else {
            mDispatcher.cancel(JOB_TAG);
        }
    }

}
