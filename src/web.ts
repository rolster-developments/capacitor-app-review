import { WebPlugin } from '@capacitor/core';
import type { AppReviewPlugin } from './definitions';

export class AppReviewWeb extends WebPlugin implements AppReviewPlugin {
  request(): Promise<void> {
    return Promise.resolve();
  }

  openStore(): Promise<void> {
    return Promise.resolve();
  }
}
