package com.rolster.capacitor.appreview;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.getcapacitor.PluginCall;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

public class AppReview {
    public void requestReview(final PluginCall call, final AppCompatActivity activity) {
        final ReviewManager manager = ReviewManagerFactory.create(activity);

        var request = manager.requestReviewFlow();

        request.addOnFailureListener(e -> {
            Log.e("AppReview", e.getMessage());
            call.reject("Request review failed", e);
        });

        request.addOnCompleteListener(task -> {
           if (task.isSuccessful()) {
               var reviewInfo = task.getResult();
               var flow = manager.launchReviewFlow(activity, reviewInfo);

               flow.addOnCompleteListener(task1 -> {
                   Log.i("AppReview", "Request review complete");
                   call.resolve();
               });

               flow.addOnSuccessListener(unused -> {
                   Log.i("AppReview", "Request review successful");
                   call.resolve();
               });

               flow.addOnFailureListener(e -> {
                   Log.e("AppReview", e.getMessage());
                   call.reject("Request review flow failed", e);
               });
           } else {
               Log.e("AppReview", "Request review task Failed");
               call.reject("Request review task Failed");
           }
        });
    }
}
