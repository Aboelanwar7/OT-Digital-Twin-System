import { useEffect, useState } from 'react';
import { getAssets } from '../api/client';
import type { Asset } from '../types';
import { AssetTable } from '../components/AssetTable';

export const AssetListPage: React.FC = () => {
  const [assets, setAssets] = useState<Asset[]>([]);
  const [loading, setLoading] = useState(true);

  const fetchAssets = async () => {
    try {
      const data = await getAssets();
      setAssets(data);
    } catch (error) {
      console.error('Failed to fetch assets:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchAssets();
    const interval = setInterval(fetchAssets, 5000); // refresh every 5s for status updates
    return () => clearInterval(interval);
  }, []);

  if (loading) {
    return <div className="text-center py-10">Loading assets...</div>;
  }

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="md:flex md:items-center md:justify-between mb-6">
        <div className="flex-1 min-w-0">
          <h2 className="text-2xl font-bold leading-7 text-gray-900 sm:text-3xl sm:truncate">
            Industrial Assets
          </h2>
        </div>
      </div>
      <AssetTable assets={assets} />
    </div>
  );
};
