package com.rolster.capacitor.appreview;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.getcapacitor.PluginCall;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class AppReview {
    public void requestReview(final PluginCall call, final AppCompatActivity activity) {
        final ReviewManager manager = ReviewManagerFactory.create(activity);

        Task<ReviewInfo> request = manager.requestReviewFlow();

        request.addOnFailureListener(
            new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    e.printStackTrace();
                    call.reject("Request review failed", e);
                }
            }
        );
        
        request.addOnCompleteListener(
            new OnCompleteListener<ReviewInfo>() {
                @Override
                public void onComplete(Task<ReviewInfo> task) {
                    if (task.isSuccessful()) {
                        ReviewInfo reviewInfo = task.getResult();
                        Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                        flow.addOnCompleteListener(
                            new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    Log.i("RateApp", "Request review flow finished");
                                    call.resolve();
                                }
                            }
                        );
                        flow.addOnSuccessListener(
                            new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void result) {
                                    call.resolve();
                                }
                            }
                        );
                        flow.addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    e.printStackTrace();
                                    call.reject("Request review flow Failed", e);
                                }
                            }
                        );
                    } else {
                        // There was some problem, continue regardless of the result.
                        call.reject("Request review task Failed");
                    }
                }
            }
        );
    }
}
