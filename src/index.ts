import { registerPlugin } from '@capacitor/core';

import type { RolsterCapacitorAppReviewPlugin } from './definitions';

const RolsterCapacitorAppReview = registerPlugin<RolsterCapacitorAppReviewPlugin>('RolsterCapacitorAppReview', {
  web: () => import('./web').then(m => new m.RolsterCapacitorAppReviewWeb()),
});

export * from './definitions';
export { RolsterCapacitorAppReview };
