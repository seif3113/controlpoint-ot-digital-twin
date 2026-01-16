export interface Asset {
  id: number;
  name: string;
  type: string;
  status: "RUNNING" | "STOPPED" | "ALARM";
}

export interface CreateAsset {
  name: string;
  type: string;
  status: "RUNNING" | "STOPPED" | "ALARM";
}
