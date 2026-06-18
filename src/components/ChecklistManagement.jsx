import React, { useState, useEffect } from 'react';

const INSPECTION_TYPES = ['일상점검', '정기점검', '특별점검'];

const initForm = {
  itemId: '', itemName: '', categoryName: '', score: '',
  inspectionType: '일상점검', requiredYn: 'Y', useYn: 'Y',
};

export default function ChecklistManagement() {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState(initForm);
  const [data, setData] = useState(() => {
    try { return JSON.parse(localStorage.getItem('oss_checklists')) || []; } catch { return []; }
  });
  const [search, setSearch] = useState({ itemName: '', categoryName: '', inspectionType: '' });
  const [alert, setAlert] = useState('');

  useEffect(() => { localStorage.setItem('oss_checklists', JSON.stringify(data)); }, [data]);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.itemId || !form.itemName) return;
    setData([{ ...form, createdAt: new Date().toLocaleDateString('ko-KR') }, ...data]);
    setForm(initForm);
    setAlert('체크리스트 항목이 성공적으로 등록되었습니다.');
    setTimeout(() => setAlert(''), 3000);
  };

  const handleDelete = (idx) => setData(data.filter((_, i) => i !== idx));

  const filtered = data.filter((d) =>
    (!search.itemName || d.itemName.includes(search.itemName)) &&
    (!search.categoryName || d.categoryName.includes(search.categoryName)) &&
    (!search.inspectionType || d.inspectionType === search.inspectionType)
  );

  return (
    <div>
      <h2 className="page-title">일상점검 / 체크리스트 관리</h2>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>체크리스트 항목 등록</button>
        <button className={`tab-btn ${tab === 'search' ? 'active' : ''}`} onClick={() => setTab('search')}>체크리스트 항목 조회</button>
      </div>

      {tab === 'register' && (
        <div className="card">
          <div className="card-title">체크리스트 항목 등록</div>
          {alert && <div className="alert alert-success">{alert}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group">
                <label>항목 ID <span className="required">*</span></label>
                <input className="form-control" name="itemId" value={form.itemId} onChange={handleChange} placeholder="항목 ID (예: CI001)" required />
              </div>
              <div className="form-group">
                <label>항목명 <span className="required">*</span></label>
                <input className="form-control" name="itemName" value={form.itemName} onChange={handleChange} placeholder="항목명" required />
              </div>
              <div className="form-group">
                <label>분류명</label>
                <input className="form-control" name="categoryName" value={form.categoryName} onChange={handleChange} placeholder="분류명 (예: 화학물질 관리)" />
              </div>
              <div className="form-group">
                <label>점수</label>
                <input className="form-control" type="number" name="score" value={form.score} onChange={handleChange} placeholder="점수" min={0} max={100} />
              </div>
              <div className="form-group">
                <label>점검 유형</label>
                <select className="form-control" name="inspectionType" value={form.inspectionType} onChange={handleChange}>
                  {INSPECTION_TYPES.map((t) => <option key={t}>{t}</option>)}
                </select>
              </div>
              <div className="form-group">
                <label>필수 여부</label>
                <select className="form-control" name="requiredYn" value={form.requiredYn} onChange={handleChange}>
                  <option value="Y">필수</option><option value="N">선택</option>
                </select>
              </div>
              <div className="form-group">
                <label>사용 여부</label>
                <select className="form-control" name="useYn" value={form.useYn} onChange={handleChange}>
                  <option value="Y">사용</option><option value="N">미사용</option>
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
          <div className="card-title">체크리스트 항목 조회</div>
          <div className="search-bar">
            <input className="form-control" placeholder="항목명 검색" value={search.itemName} onChange={(e) => setSearch({ ...search, itemName: e.target.value })} />
            <input className="form-control" placeholder="분류명 검색" value={search.categoryName} onChange={(e) => setSearch({ ...search, categoryName: e.target.value })} />
            <select className="form-control" value={search.inspectionType} onChange={(e) => setSearch({ ...search, inspectionType: e.target.value })} style={{ width: 160 }}>
              <option value="">점검유형 전체</option>
              {INSPECTION_TYPES.map((t) => <option key={t}>{t}</option>)}
            </select>
          </div>
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>항목 ID</th><th>항목명</th><th>분류명</th><th>점수</th>
                  <th>점검유형</th><th>필수여부</th><th>사용여부</th><th>등록일</th><th>관리</th>
                </tr>
              </thead>
              <tbody>
                {filtered.length === 0 ? (
                  <tr><td colSpan={9} className="empty-state">등록된 체크리스트 항목이 없습니다.</td></tr>
                ) : filtered.map((d, i) => (
                  <tr key={i}>
                    <td>{d.itemId}</td><td>{d.itemName}</td><td>{d.categoryName}</td>
                    <td>{d.score}점</td><td>{d.inspectionType}</td>
                    <td><span className={`badge ${d.requiredYn === 'Y' ? 'badge-pending' : 'badge-approved'}`}>{d.requiredYn === 'Y' ? '필수' : '선택'}</span></td>
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
