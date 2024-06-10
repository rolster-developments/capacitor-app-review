export interface AppReviewPlugin {
  request(): Promise<void>;
  openStoreScreen(): Promise<void>;
}
