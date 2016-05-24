package com.bartoszlipinski.trigger.sample.activity;

import android.app.Activity;
import android.os.Bundle;

import com.bartoszlipinski.trigger.Trigger;
import com.bartoszlipinski.trigger.sample.R;

/**
 * Created by Bartosz Lipinski
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String result;

        final Trigger.WithParam.WithResult<Boolean, String> withParamWithResult =
                Trigger.WithParam.WithResult.create(new Trigger.WithParam.WithResult.OnTriggered<Boolean, String>() {
                    @Override
                    public String onTriggered(Boolean param) {
                        return executeCommandWithResult(param);
                    }
                });
        result = withParamWithResult.pull(true);

        final Trigger.WithParam.NoResult<Boolean> withParamNoResult =
                Trigger.WithParam.NoResult.create(new Trigger.WithParam.NoResult.OnTriggered<Boolean>() {
                    @Override
                    public void onTriggered(Boolean param) {
                        executeCommandWithout(param);
                    }
                });
        withParamNoResult.pull(true);

        final Trigger.NoParam.WithResult<String> noParamWithResult =
                Trigger.NoParam.WithResult.create(new Trigger.NoParam.WithResult.OnTriggered<String>() {
                    @Override
                    public String onTriggered() {
                        return executeCommandWithResult();
                    }
                });
        result = noParamWithResult.pull();

        final Trigger.NoParam.NoResult noParamNoResult =
                Trigger.NoParam.NoResult.create(new Trigger.NoParam.NoResult.OnTriggered() {
                    @Override
                    public void onTriggered() {
                        executeCommandWithoutResult();
                    }
                });
        noParamNoResult.pull();
    }

    private String executeCommandWithResult(Boolean param) {
        return "test " + param;
    }

    private void executeCommandWithout(Boolean param) {
        //do something with the param
    }

    private String executeCommandWithResult() {
        return "test";
    }

    private void executeCommandWithoutResult() {
        //do something
    }
}
