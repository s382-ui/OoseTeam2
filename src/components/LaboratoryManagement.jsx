import React, { useState, useEffect } from 'react';

const GRADES = ['A', 'B', 'C', 'D'];

const initForm = {
  labId: '', labName: '', buildingName: '', floor: '', roomNo: '',
  departmentName: '', managerId: '', managerName: '', contactNo: '',
  managementGrade: 'A', isActive: '사용',
};

export default function LaboratoryManagement() {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState(initForm);
  const [data, setData] = useState(() => {
    try { return JSON.parse(localStorage.getItem('oss_labs')) || []; } catch { return []; }
  });
  const [search, setSearch] = useState({ labName: '', buildingName: '', departmentName: '' });
  const [alert, setAlert] = useState('');

  useEffect(() => { localStorage.setItem('oss_labs', JSON.stringify(data)); }, [data]);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.labId || !form.labName) return;
    setData([{ ...form, createdAt: new Date().toLocaleDateString('ko-KR') }, ...data]);
    setForm(initForm);
    setAlert('연구실이 성공적으로 등록되었습니다.');
    setTimeout(() => setAlert(''), 3000);
  };

  const handleDelete = (idx) => setData(data.filter((_, i) => i !== idx));

  const filtered = data.filter((d) =>
    (!search.labName || d.labName.includes(search.labName)) &&
    (!search.buildingName || d.buildingName.includes(search.buildingName)) &&
    (!search.departmentName || d.departmentName.includes(search.departmentName))
  );

  return (
    <div>
      <h2 className="page-title">연구실 관리</h2>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>연구실 등록</button>
        <button className={`tab-btn ${tab === 'search' ? 'active' : ''}`} onClick={() => setTab('search')}>연구실 조회</button>
      </div>

      {tab === 'register' && (
        <div className="card">
          <div className="card-title">연구실 정보 등록</div>
          {alert && <div className="alert alert-success">{alert}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group">
                <label>연구실 ID <span className="required">*</span></label>
                <input className="form-control" name="labId" value={form.labId} onChange={handleChange} placeholder="연구실 ID" required />
              </div>
              <div className="form-group">
                <label>연구실명 <span className="required">*</span></label>
                <input className="form-control" name="labName" value={form.labName} onChange={handleChange} placeholder="연구실명" required />
              </div>
              <div className="form-group">
                <label>건물명</label>
                <input className="form-control" name="buildingName" value={form.buildingName} onChange={handleChange} placeholder="건물명" />
              </div>
              <div className="form-group">
                <label>층</label>
                <input className="form-control" name="floor" value={form.floor} onChange={handleChange} placeholder="층" />
              </div>
              <div className="form-group">
                <label>호실</label>
                <input className="form-control" name="roomNo" value={form.roomNo} onChange={handleChange} placeholder="호실" />
              </div>
              <div className="form-group">
                <label>소속 부서</label>
                <input className="form-control" name="departmentName" value={form.departmentName} onChange={handleChange} placeholder="소속 부서" />
              </div>
              <div className="form-group">
                <label>관리자 ID</label>
                <input className="form-control" name="managerId" value={form.managerId} onChange={handleChange} placeholder="관리자 ID" />
              </div>
              <div className="form-group">
                <label>관리자명</label>
                <input className="form-control" name="managerName" value={form.managerName} onChange={handleChange} placeholder="관리자명" />
              </div>
              <div className="form-group">
                <label>연락처</label>
                <input className="form-control" name="contactNo" value={form.contactNo} onChange={handleChange} placeholder="연락처" />
              </div>
              <div className="form-group">
                <label>관리 등급</label>
                <select className="form-control" name="managementGrade" value={form.managementGrade} onChange={handleChange}>
                  {GRADES.map((g) => <option key={g}>관리등급 {g}</option>)}
                </select>
              </div>
              <div className="form-group">
                <label>사용 여부</label>
                <select className="form-control" name="isActive" value={form.isActive} onChange={handleChange}>
                  <option>사용</option><option>미사용</option>
                </select>
              </div>
            </div>
            <div className="form-actions">
              <button type="button" className="btn btn-secondary" onClick={() => setForm(initForm)}>초기화</button>
              <button type="submit" className="btn btn-primary">등록</button>
            </div>
          </form>
        </div>
      )}

      {tab === 'search' && (
        <div className="card">
          <div className="card-title">연구실 정보 조회</div>
          <div className="search-bar">
            <input className="form-control" placeholder="연구실명 검색" value={search.labName} onChange={(e) => setSearch({ ...search, labName: e.target.value })} />
            <input className="form-control" placeholder="건물명 검색" value={search.buildingName} onChange={(e) => setSearch({ ...search, buildingName: e.target.value })} />
            <input className="form-control" placeholder="소속 부서 검색" value={search.departmentName} onChange={(e) => setSearch({ ...search, departmentName: e.target.value })} />
          </div>
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>연구실 ID</th><th>연구실명</th><th>건물</th><th>층/호실</th>
                  <th>소속부서</th><th>관리자</th><th>연락처</th><th>관리등급</th><th>사용여부</th><th>등록일</th><th>관리</th>
                </tr>
              </thead>
              <tbody>
                {filtered.length === 0 ? (
                  <tr><td colSpan={11} className="empty-state">등록된 연구실이 없습니다.</td></tr>
                ) : filtered.map((d, i) => (
                  <tr key={i}>
                    <td>{d.labId}</td><td>{d.labName}</td><td>{d.buildingName}</td>
                    <td>{d.floor}층 {d.roomNo}호</td><td>{d.departmentName}</td>
                    <td>{d.managerName}</td><td>{d.contactNo}</td><td>{d.managementGrade}</td>
                    <td><span className={`badge ${d.isActive === '사용' ? 'badge-active' : 'badge-inactive'}`}>{d.isActive}</span></td>
                    <td>{d.createdAt}</td>
                    <td><button className="btn btn-danger btn-sm" onClick={() => handleDelete(i)}>삭제</button></td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
}
