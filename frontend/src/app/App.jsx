import React, { useState } from 'react';
import '../App.css';
import '../styles/layered.css';
import { menuConfig } from './menuConfig';

export default function App() {
  const [activeMenu, setActiveMenu] = useState(menuConfig[0].id);
  const ActivePage = menuConfig.find((menu) => menu.id === activeMenu).Component;

  return (
    <div className="app-layout">
      <aside className="sidebar">
        <div className="sidebar-header">
          <h1>연구실<br />안전관리 시스템</h1>
        </div>
        <nav className="sidebar-nav">
          {menuConfig.map((menu) => (
            <button
              key={menu.id}
              className={`nav-item ${activeMenu === menu.id ? 'active' : ''}`}
              onClick={() => setActiveMenu(menu.id)}
            >
              <span className="nav-label">{menu.label}</span>
              <span className="nav-sub">{menu.subsystemId}</span>
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
