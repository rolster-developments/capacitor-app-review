export interface RolsterCapacitorAppReviewPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
