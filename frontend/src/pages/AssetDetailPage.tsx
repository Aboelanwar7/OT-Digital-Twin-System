import { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { getAssetById, getLatestReading } from '../api/client';
import type { Asset, SensorReading } from '../types';
import { StatusBadge } from '../components/StatusBadge';
import { SensorReadingCard } from '../components/SensorReadingCard';
import { ArrowLeft } from 'lucide-react';

export const AssetDetailPage: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [asset, setAsset] = useState<Asset | null>(null);
  const [reading, setReading] = useState<SensorReading | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!id) return;

    const fetchAssetData = async () => {
      try {
        const assetData = await getAssetById(Number(id));
        setAsset(assetData);
        const readingData = await getLatestReading(Number(id));
        setReading(readingData);
      } catch (error) {
        console.error('Failed to fetch asset data:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchAssetData();
    const interval = setInterval(fetchAssetData, 5000); // poll for new readings

    return () => clearInterval(interval);
  }, [id]);

  if (loading) {
    return <div className="text-center py-10">Loading asset details...</div>;
  }

  if (!asset) {
    return <div className="text-center py-10 text-red-500">Asset not found.</div>;
  }

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="mb-6">
        <Link to="/" className="text-blue-600 hover:text-blue-800 flex items-center">
          <ArrowLeft className="w-4 h-4 mr-1" /> Back to Assets
        </Link>
      </div>

      <div className="bg-white shadow overflow-hidden sm:rounded-lg mb-8">
        <div className="px-4 py-5 sm:px-6 flex justify-between items-center">
          <div>
            <h3 className="text-lg leading-6 font-medium text-gray-900">Asset Information</h3>
            <p className="mt-1 max-w-2xl text-sm text-gray-500">Details and configuration.</p>
          </div>
          <StatusBadge status={asset.status} />
        </div>
        <div className="border-t border-gray-200 px-4 py-5 sm:p-0">
          <dl className="sm:divide-y sm:divide-gray-200">
            <div className="py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
              <dt className="text-sm font-medium text-gray-500">Asset Name</dt>
              <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{asset.name}</dd>
            </div>
            <div className="py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
              <dt className="text-sm font-medium text-gray-500">Type</dt>
              <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{asset.type}</dd>
            </div>
            <div className="py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
              <dt className="text-sm font-medium text-gray-500">Asset ID</dt>
              <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{asset.id}</dd>
            </div>
          </dl>
        </div>
      </div>

      <SensorReadingCard reading={reading} />
    </div>
  );
};
