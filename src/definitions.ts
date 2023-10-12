export interface AppReviewPlugin {
  requestReview(): Promise<void>;
  openStoreScreen(): Promise<void>;
}
