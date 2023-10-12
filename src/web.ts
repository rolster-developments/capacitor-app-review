import { WebPlugin } from '@capacitor/core';
import type { AppReviewPlugin } from './definitions';

export class AppReviewWeb extends WebPlugin implements AppReviewPlugin {
  requestReview(): Promise<void> {
    return Promise.resolve();
  }

  openStoreScreen(): Promise<void> {
    return Promise.resolve();
  }
}
