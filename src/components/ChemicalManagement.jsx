import React, { useState } from 'react';

const initForm = {
  chemicalId: '', manufacturerName: '', chemicalName: '', casNumber: '',
  contentRate: '', msdsPath: '', analysisPath: '', status: '등록',
};

export default function ChemicalManagement() {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState(initForm);
  const [data, setData] = useState([]);
  const [search, setSearch] = useState({ chemicalName: '', manufacturerName: '', casNumber: '' });
  const [alert, setAlert] = useState('');

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.chemicalId || !form.chemicalName) return;
    setData([{ ...form, createdAt: new Date().toLocaleDateString('ko-KR') }, ...data]);
    setForm(initForm);
    setAlert('화학물질이 성공적으로 등록되었습니다.');
    setTimeout(() => setAlert(''), 3000);
  };

  const handleDelete = (idx) => setData(data.filter((_, i) => i !== idx));

  const filtered = data.filter((d) =>
    (!search.chemicalName || d.chemicalName.includes(search.chemicalName)) &&
    (!search.manufacturerName || d.manufacturerName.includes(search.manufacturerName)) &&
    (!search.casNumber || d.casNumber.includes(search.casNumber))
  );

  return (
    <div>
      <h2 className="page-title">화학물질 관리</h2>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>화학물질 등록</button>
        <button className={`tab-btn ${tab === 'search' ? 'active' : ''}`} onClick={() => setTab('search')}>화학물질 조회</button>
      </div>

      {tab === 'register' && (
        <div className="card">
          <div className="card-title">화학물질 정보 등록</div>
          {alert && <div className="alert alert-success">{alert}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group">
                <label>화학물질 ID <span className="required">*</span></label>
                <input className="form-control" name="chemicalId" value={form.chemicalId} onChange={handleChange} placeholder="화학물질 ID" required />
              </div>
              <div className="form-group">
                <label>화학물질명 <span className="required">*</span></label>
                <input className="form-control" name="chemicalName" value={form.chemicalName} onChange={handleChange} placeholder="화학물질명" required />
              </div>
              <div className="form-group">
                <label>제조사명</label>
                <input className="form-control" name="manufacturerName" value={form.manufacturerName} onChange={handleChange} placeholder="제조사명" />
              </div>
              <div className="form-group">
                <label>CAS 번호</label>
                <input className="form-control" name="casNumber" value={form.casNumber} onChange={handleChange} placeholder="CAS 번호 (예: 7732-18-5)" />
              </div>
              <div className="form-group">
                <label>함량률 (%)</label>
                <input className="form-control" name="contentRate" value={form.contentRate} onChange={handleChange} placeholder="함량률" />
              </div>
              <div className="form-group">
                <label>상태</label>
                <select className="form-control" name="status" value={form.status} onChange={handleChange}>
                  <option>등록</option><option>검토중</option><option>승인</option><option>폐기</option>
                </select>
              </div>
              <div className="form-group">
                <label>MSDS 경로</label>
                <input className="form-control" name="msdsPath" value={form.msdsPath} onChange={handleChange} placeholder="MSDS 파일 경로" />
              </div>
              <div className="form-group">
                <label>분석표 경로</label>
                <input className="form-control" name="analysisPath" value={form.analysisPath} onChange={handleChange} placeholder="분석표 파일 경로" />
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
          <div className="card-title">화학물질 정보 조회</div>
          <div className="search-bar">
            <input className="form-control" placeholder="화학물질명 검색" value={search.chemicalName} onChange={(e) => setSearch({ ...search, chemicalName: e.target.value })} />
            <input className="form-control" placeholder="제조사명 검색" value={search.manufacturerName} onChange={(e) => setSearch({ ...search, manufacturerName: e.target.value })} />
            <input className="form-control" placeholder="CAS 번호 검색" value={search.casNumber} onChange={(e) => setSearch({ ...search, casNumber: e.target.value })} />
          </div>
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th><th>화학물질명</th><th>제조사</th><th>CAS 번호</th>
                  <th>함량률</th><th>MSDS</th><th>상태</th><th>등록일</th><th>관리</th>
                </tr>
              </thead>
              <tbody>
                {filtered.length === 0 ? (
                  <tr><td colSpan={9} className="empty-state">등록된 화학물질이 없습니다.</td></tr>
                ) : filtered.map((d, i) => (
                  <tr key={i}>
                    <td>{d.chemicalId}</td><td>{d.chemicalName}</td><td>{d.manufacturerName}</td>
                    <td>{d.casNumber}</td><td>{d.contentRate}%</td>
                    <td>{d.msdsPath ? <span className="badge badge-approved">있음</span> : <span className="badge badge-inactive">없음</span>}</td>
                    <td><span className={`badge ${d.status === '승인' ? 'badge-active' : d.status === '검토중' ? 'badge-pending' : 'badge-approved'}`}>{d.status}</span></td>
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
