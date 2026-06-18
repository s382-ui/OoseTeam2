import React, { useState, useEffect } from 'react';

const TYPES = ['폐액', '고형 폐기물', '폐유기용매', '폐산', '폐알칼리', '혼합폐기물'];

const initForm = {
  categoryCode: '', categoryName: '', categoryType: '폐액',
  propertyInfo: '', disposalMethod: '', relatedLaw: '', isActive: '사용',
};

export default function WasteManagement() {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState(initForm);
  const [data, setData] = useState(() => {
    try { return JSON.parse(localStorage.getItem('oss_wastes')) || []; } catch { return []; }
  });
  const [search, setSearch] = useState({ categoryName: '', categoryType: '' });
  const [alert, setAlert] = useState('');

  useEffect(() => { localStorage.setItem('oss_wastes', JSON.stringify(data)); }, [data]);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.categoryCode || !form.categoryName) return;
    setData([{ ...form, createdAt: new Date().toLocaleDateString('ko-KR') }, ...data]);
    setForm(initForm);
    setAlert('폐기물 분류가 성공적으로 등록되었습니다.');
    setTimeout(() => setAlert(''), 3000);
  };

  const handleDelete = (idx) => setData(data.filter((_, i) => i !== idx));

  const filtered = data.filter((d) =>
    (!search.categoryName || d.categoryName.includes(search.categoryName)) &&
    (!search.categoryType || d.categoryType === search.categoryType)
  );

  return (
    <div>
      <h2 className="page-title">폐기물 관리</h2>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>폐기물 분류 등록</button>
        <button className={`tab-btn ${tab === 'search' ? 'active' : ''}`} onClick={() => setTab('search')}>폐기물 분류 조회</button>
      </div>

      {tab === 'register' && (
        <div className="card">
          <div className="card-title">폐기물 분류 정보 등록</div>
          {alert && <div className="alert alert-success">{alert}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group">
                <label>분류 코드 <span className="required">*</span></label>
                <input className="form-control" name="categoryCode" value={form.categoryCode} onChange={handleChange} placeholder="분류 코드 (예: WC001)" required />
              </div>
              <div className="form-group">
                <label>분류명 <span className="required">*</span></label>
                <input className="form-control" name="categoryName" value={form.categoryName} onChange={handleChange} placeholder="분류명" required />
              </div>
              <div className="form-group">
                <label>분류 유형</label>
                <select className="form-control" name="categoryType" value={form.categoryType} onChange={handleChange}>
                  {TYPES.map((t) => <option key={t}>{t}</option>)}
                </select>
              </div>
              <div className="form-group">
                <label>사용 여부</label>
                <select className="form-control" name="isActive" value={form.isActive} onChange={handleChange}>
                  <option>사용</option><option>미사용</option>
                </select>
              </div>
              <div className="form-group full-width">
                <label>특성 정보</label>
                <textarea className="form-control" name="propertyInfo" value={form.propertyInfo} onChange={handleChange} placeholder="폐기물 특성 정보 입력" rows={3} />
              </div>
              <div className="form-group full-width">
                <label>처리 방법</label>
                <textarea className="form-control" name="disposalMethod" value={form.disposalMethod} onChange={handleChange} placeholder="처리 방법 입력" rows={3} />
              </div>
              <div className="form-group full-width">
                <label>관련 법규</label>
                <input className="form-control" name="relatedLaw" value={form.relatedLaw} onChange={handleChange} placeholder="관련 법규명" />
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
          <div className="card-title">폐기물 분류 조회</div>
          <div className="search-bar">
            <input className="form-control" placeholder="분류명 검색" value={search.categoryName} onChange={(e) => setSearch({ ...search, categoryName: e.target.value })} />
            <select className="form-control" value={search.categoryType} onChange={(e) => setSearch({ ...search, categoryType: e.target.value })} style={{ width: 160 }}>
              <option value="">유형 전체</option>
              {TYPES.map((t) => <option key={t}>{t}</option>)}
            </select>
          </div>
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>분류 코드</th><th>분류명</th><th>유형</th><th>특성 정보</th>
                  <th>처리 방법</th><th>관련 법규</th><th>사용여부</th><th>등록일</th><th>관리</th>
                </tr>
              </thead>
              <tbody>
                {filtered.length === 0 ? (
                  <tr><td colSpan={9} className="empty-state">등록된 폐기물 분류가 없습니다.</td></tr>
                ) : filtered.map((d, i) => (
                  <tr key={i}>
                    <td>{d.categoryCode}</td><td>{d.categoryName}</td><td>{d.categoryType}</td>
                    <td style={{ maxWidth: 150, overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>{d.propertyInfo}</td>
                    <td style={{ maxWidth: 150, overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>{d.disposalMethod}</td>
                    <td>{d.relatedLaw}</td>
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
