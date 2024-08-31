package com.rolster.capacitor.review;

interface AppReviewResolve {
  public void onComplete(String status);

  public void onFailure(String status, String message);
}
