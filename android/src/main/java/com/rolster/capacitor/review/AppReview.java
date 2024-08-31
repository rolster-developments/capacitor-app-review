package com.rolster.capacitor.review;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

import com.rolster.capacitor.review.types.AppReviewStatus;

public class AppReview {
    public static void request(final AppCompatActivity activity, final AppReviewResolve resolve) {
        final ReviewManager manager = ReviewManagerFactory.create(activity);

        var requestReview = manager.requestReviewFlow();

        requestReview.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                var reviewFlow = manager.launchReviewFlow(activity, task.getResult());

                reviewFlow.addOnCompleteListener(unused -> {
                    resolve.onComplete(AppReviewStatus.COMPLETE);
                });

                reviewFlow.addOnSuccessListener(unused -> {
                    resolve.onComplete(AppReviewStatus.SUCCESS);
                });

                reviewFlow.addOnFailureListener(error -> {
                    resolve.onFailure(AppReviewStatus.ERROR, error.getMessage());
                });
            } else {
                resolve.onFailure(AppReviewStatus.FAILURE, "Request review task Failed");
            }
        });

        requestReview.addOnFailureListener(error -> {
            resolve.onFailure(AppReviewStatus.ERROR, error.getMessage());
        });
    }
}
