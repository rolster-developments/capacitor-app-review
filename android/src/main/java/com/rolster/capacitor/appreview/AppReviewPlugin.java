package com.rolster.capacitor.appreview;

import android.content.Intent;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "AppReview")
public class AppReviewPlugin extends Plugin {

    private AppReview implementation = new AppReview();

    @PluginMethod
    public void requestReview(PluginCall call) {
        final AppCompatActivity activity = getActivity();

        implementation.requestReview(call, activity);

        call.resolve();
    }

    @PluginMethod
    public void openStoreScreen(PluginCall call) {
        final String packageName = getActivity().getPackageName();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

        getActivity().startActivity(intent);

        call.resolve();
    }
}
