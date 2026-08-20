import type { AssetStatus } from '../types';

export const StatusBadge: React.FC<{ status: AssetStatus }> = ({ status }) => {
  const colors = {
    RUNNING: 'bg-green-100 text-green-800',
    STOPPED: 'bg-gray-100 text-gray-800',
    ALARM: 'bg-red-100 text-red-800',
  };

  return (
    <span className={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full ${colors[status]}`}>
      {status}
    </span>
  );
};
