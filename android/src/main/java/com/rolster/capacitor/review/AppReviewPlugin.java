package com.rolster.capacitor.review;

import android.content.Intent;
import android.net.Uri;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "AppReview")
public class AppReviewPlugin extends Plugin implements AppReviewResolve {
    @PluginMethod
    public void request(PluginCall call) {
        AppReview.request(getActivity(), this);

        call.resolve();
    }

    @PluginMethod
    public void openStore(PluginCall call) {
        final String packageName = getActivity().getPackageName();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

        getActivity().startActivity(intent);

        call.resolve();
    }

    @Override
    public void onComplete(String status) {
        JSObject result = new JSObject();
        result.put("status", status);

        notifyListeners("appReviewEvent", result);
    }

    @Override
    public void onFailure(String status, String message) {
        JSObject result = new JSObject();
        result.put("status", status);
        result.put("msgError", message);

        notifyListeners("appReviewEvent", result);
    }
}
