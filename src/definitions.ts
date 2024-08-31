import { PluginListenerHandle } from '@capacitor/core';

export type AppReviewEvent = 'appReviewEvent';

export type AppReviewStatus =
  | 'appReviewComplete'
  | 'appReviewSuccess'
  | 'appReviewFailure'
  | 'appReviewError';

interface AppReviewResponse {
  status: AppReviewStatus;
  msgError?: string;
}

type AppReviewCallback = (response: AppReviewResponse) => void;

export interface AppReviewPlugin {
  request(): Promise<void>;

  openStore(): Promise<void>;

  addListener(
    event: AppReviewEvent,
    callback: AppReviewCallback
  ): PluginListenerHandle;
}
