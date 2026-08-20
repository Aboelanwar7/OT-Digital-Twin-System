import axios from 'axios';
import type { Asset, SensorReading } from '../types';

// Use a relative URL so that the request is handled by Vite's proxy in dev,
// or Nginx's proxy in production (Docker).
const apiClient = axios.create({
  baseURL: '/api',
});

export const getAssets = async (): Promise<Asset[]> => {
  const response = await apiClient.get('/assets');
  return response.data;
};

export const getAssetById = async (id: number): Promise<Asset> => {
  const response = await apiClient.get(`/assets/${id}`);
  return response.data;
};

export const getLatestReading = async (id: number): Promise<SensorReading | null> => {
  const response = await apiClient.get(`/assets/${id}/readings/latest`);
  return response.data;
};
