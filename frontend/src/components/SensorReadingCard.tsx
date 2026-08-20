import type { SensorReading } from '../types';
import { Thermometer, Gauge, Clock } from 'lucide-react';

export const SensorReadingCard: React.FC<{ reading: SensorReading | null }> = ({ reading }) => {
  if (!reading) {
    return <div className="p-4 bg-white shadow rounded-lg text-gray-500 text-center">No sensor readings available yet.</div>;
  }

  return (
    <div className="bg-white shadow rounded-lg p-6">
      <h3 className="text-lg font-medium text-gray-900 mb-4 border-b pb-2">Latest Sensor Reading</h3>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        <div className="flex items-center p-4 bg-orange-50 rounded-lg">
          <Thermometer className="w-8 h-8 text-orange-500 mr-3" />
          <div>
            <p className="text-sm font-medium text-orange-800">Temperature</p>
            <p className="text-2xl font-bold text-orange-900">{reading.temperature.toFixed(2)} °C</p>
          </div>
        </div>
        <div className="flex items-center p-4 bg-blue-50 rounded-lg">
          <Gauge className="w-8 h-8 text-blue-500 mr-3" />
          <div>
            <p className="text-sm font-medium text-blue-800">Pressure</p>
            <p className="text-2xl font-bold text-blue-900">{reading.pressure.toFixed(2)} Bar</p>
          </div>
        </div>
        <div className="flex items-center p-4 bg-gray-50 rounded-lg">
          <Clock className="w-8 h-8 text-gray-500 mr-3" />
          <div>
            <p className="text-sm font-medium text-gray-800">Last Updated</p>
            <p className="text-sm font-bold text-gray-900">{new Date(reading.timestamp).toLocaleTimeString()}</p>
          </div>
        </div>
      </div>
    </div>
  );
};
