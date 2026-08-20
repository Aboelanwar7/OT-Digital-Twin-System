import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AssetListPage } from './pages/AssetListPage';
import { AssetDetailPage } from './pages/AssetDetailPage';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-100 font-sans">
        <nav className="bg-white shadow-sm border-b border-gray-200">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div className="flex justify-between h-16">
              <div className="flex">
                <div className="flex-shrink-0 flex items-center">
                  <span className="text-xl font-bold text-blue-600 tracking-tight">ControlPoint</span>
                  <span className="ml-2 text-xl font-medium text-gray-700">Digital Twin</span>
                </div>
              </div>
            </div>
          </div>
        </nav>
        
        <main>
          <Routes>
            <Route path="/" element={<AssetListPage />} />
            <Route path="/assets/:id" element={<AssetDetailPage />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
