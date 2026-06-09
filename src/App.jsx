import React, { useState } from 'react';
import './App.css';
import UserManagement from './components/UserManagement';
import LaboratoryManagement from './components/LaboratoryManagement';
import ChemicalManagement from './components/ChemicalManagement';
import WasteManagement from './components/WasteManagement';
import ChecklistManagement from './components/ChecklistManagement';
import InspectionManagement from './components/InspectionManagement';
import SafetyEducationManagement from './components/SafetyEducationManagement';

const menus = [
  { id: 'user', label: '사용자 관리', sub: 'DSS-001' },
  { id: 'lab', label: '연구실 관리', sub: 'DSS-002' },
  { id: 'chemical', label: '화학물질 관리', sub: 'DSS-003' },
  { id: 'waste', label: '폐기물 관리', sub: 'DSS-004' },
  { id: 'checklist', label: '일상점검 관리', sub: 'DSS-005' },
  { id: 'inspection', label: '점검 관리', sub: 'DSS-006' },
  { id: 'education', label: '안전교육 관리', sub: 'DSS-007' },
];

const pageMap = {
  user: UserManagement,
  lab: LaboratoryManagement,
  chemical: ChemicalManagement,
  waste: WasteManagement,
  checklist: ChecklistManagement,
  inspection: InspectionManagement,
  education: SafetyEducationManagement,
};

export default function App() {
  const [activeMenu, setActiveMenu] = useState('user');

  const ActivePage = pageMap[activeMenu];

  return (
    <div className="app-layout">
      <aside className="sidebar">
        <div className="sidebar-header">
          <h1>연구실<br />안전관리 시스템</h1>
        </div>
        <nav className="sidebar-nav">
          {menus.map((m) => (
            <button
              key={m.id}
              className={`nav-item ${activeMenu === m.id ? 'active' : ''}`}
              onClick={() => setActiveMenu(m.id)}
            >
              <span className="nav-label">{m.label}</span>
              <span className="nav-sub">{m.sub}</span>
            </button>
          ))}
        </nav>
      </aside>
      <main className="main-content">
        <ActivePage />
      </main>
    </div>
  );
}
