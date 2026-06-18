import React, { useState, useEffect } from 'react';

const initForm = {
  categoryCode: '', categoryName: '', categoryDetail: '', useYn: 'Y',
};

export default function InspectionManagement() {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState(initForm);
  const [data, setData] = useState(() => {
    try { return JSON.parse(localStorage.getItem('oss_inspections')) || []; } catch { return []; }
  });
  const [search, setSearch] = useState({ categoryName: '', useYn: '' });
  const [alert, setAlert] = useState('');

  useEffect(() => { localStorage.setItem('oss_inspections', JSON.stringify(data)); }, [data]);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.categoryCode || !form.categoryName) return;
    setData([{ ...form, createdAt: new Date().toLocaleDateString('ko-KR') }, ...data]);
    setForm(initForm);
    setAlert('점검분야 분류가 성공적으로 등록되었습니다.');
    setTimeout(() => setAlert(''), 3000);
  };

  const handleDelete = (idx) => setData(data.filter((_, i) => i !== idx));

  const filtered = data.filter((d) =>
    (!search.categoryName || d.categoryName.includes(search.categoryName)) &&
    (!search.useYn || d.useYn === search.useYn)
  );

  return (
    <div>
      <h2 className="page-title">점검 관리</h2>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>점검분야 분류 등록</button>
        <button className={`tab-btn ${tab === 'search' ? 'active' : ''}`} onClick={() => setTab('search')}>점검분야 분류 조회</button>
      </div>

      {tab === 'register' && (
        <div className="card">
          <div className="card-title">점검분야 분류 등록</div>
          {alert && <div className="alert alert-success">{alert}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group">
                <label>분류 코드 <span className="required">*</span></label>
                <input className="form-control" name="categoryCode" value={form.categoryCode} onChange={handleChange} placeholder="분류 코드 (예: IC001)" required />
              </div>
              <div className="form-group">
                <label>분류명 <span className="required">*</span></label>
                <input className="form-control" name="categoryName" value={form.categoryName} onChange={handleChange} placeholder="점검분야 분류명" required />
              </div>
              <div className="form-group">
                <label>사용 여부</label>
                <select className="form-control" name="useYn" value={form.useYn} onChange={handleChange}>
                  <option value="Y">사용</option><option value="N">미사용</option>
                </select>
              </div>
              <div className="form-group full-width">
                <label>분류 상세 설명</label>
                <textarea className="form-control" name="categoryDetail" value={form.categoryDetail} onChange={handleChange} placeholder="점검분야 상세 설명 입력" rows={4} />
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
          <div className="card-title">점검분야 분류 조회</div>
          <div className="search-bar">
            <input className="form-control" placeholder="분류명 검색" value={search.categoryName} onChange={(e) => setSearch({ ...search, categoryName: e.target.value })} />
            <select className="form-control" value={search.useYn} onChange={(e) => setSearch({ ...search, useYn: e.target.value })} style={{ width: 140 }}>
              <option value="">사용여부 전체</option>
              <option value="Y">사용</option>
              <option value="N">미사용</option>
            </select>
          </div>
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>분류 코드</th><th>분류명</th><th>상세 설명</th><th>사용여부</th><th>등록일</th><th>관리</th>
                </tr>
              </thead>
              <tbody>
                {filtered.length === 0 ? (
                  <tr><td colSpan={6} className="empty-state">등록된 점검분야 분류가 없습니다.</td></tr>
                ) : filtered.map((d, i) => (
                  <tr key={i}>
                    <td>{d.categoryCode}</td><td>{d.categoryName}</td>
                    <td style={{ maxWidth: 300, overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>{d.categoryDetail}</td>
                    <td><span className={`badge ${d.useYn === 'Y' ? 'badge-active' : 'badge-inactive'}`}>{d.useYn === 'Y' ? '사용' : '미사용'}</span></td>
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
