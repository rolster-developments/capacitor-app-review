import { registerPlugin } from '@capacitor/core';
import type { AppReviewPlugin } from './definitions';

const AppReview = registerPlugin<AppReviewPlugin>('AppReview', {
  web: () => import('./web').then(({ AppReviewWeb }) => new AppReviewWeb())
});

export * from './definitions';
export { AppReview };
