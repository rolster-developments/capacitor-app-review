import { WebPlugin } from '@capacitor/core';

import type { RolsterCapacitorAppReviewPlugin } from './definitions';

export class RolsterCapacitorAppReviewWeb extends WebPlugin implements RolsterCapacitorAppReviewPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
