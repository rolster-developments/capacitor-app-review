package com.rolster.capacitor.review;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

public class AppReview {
    private AppReviewResolve resolve;

    public AppReview(AppReviewResolve resolve) {
        this.resolve = resolve;
    }

    public void request(final AppCompatActivity activity) {
        final ReviewManager manager = ReviewManagerFactory.create(activity);

        var requestReview = manager.requestReviewFlow();

        requestReview.addOnFailureListener(error -> {
            resolve.onFailure(error.getMessage());
        });

        requestReview.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                var reviewFlow = manager.launchReviewFlow(activity, task.getResult());

                reviewFlow.addOnCompleteListener(unused -> {
                    resolve.onComplete();
                });
                
                reviewFlow.addOnFailureListener(error -> {
                    resolve.onFailure(error.getMessage());
                });
            } else {
                resolve.onFailure("Request review task Failed");
            }
        });
    }
}
