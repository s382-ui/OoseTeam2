import React, { useState, useEffect } from 'react';

const ROLES = ['관리자', '연구책임자', '연구원', '대학원생', '학부생', '외부인'];
const STATUS = ['활성', '비활성', '잠금'];

const initForm = {
  userId: '', userName: '', department: '', role: '연구원',
  contact: '', email: '', accountStatus: '활성',
};

export default function UserManagement() {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState(initForm);
  const [data, setData] = useState(() => {
    try { return JSON.parse(localStorage.getItem('oss_users')) || []; } catch { return []; }
  });
  const [search, setSearch] = useState({ userName: '', department: '', role: '' });
  const [alert, setAlert] = useState('');

  useEffect(() => { localStorage.setItem('oss_users', JSON.stringify(data)); }, [data]);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.userId || !form.userName) return;
    const newRow = { ...form, registeredAt: new Date().toLocaleDateString('ko-KR') };
    setData([newRow, ...data]);
    setForm(initForm);
    setAlert('사용자가 성공적으로 등록되었습니다.');
    setTimeout(() => setAlert(''), 3000);
  };

  const handleDelete = (idx) => setData(data.filter((_, i) => i !== idx));

  const filtered = data.filter((d) =>
    (!search.userName || d.userName.includes(search.userName)) &&
    (!search.department || d.department.includes(search.department)) &&
    (!search.role || d.role === search.role)
  );

  return (
    <div>
      <h2 className="page-title">사용자 관리</h2>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>사용자 등록</button>
        <button className={`tab-btn ${tab === 'search' ? 'active' : ''}`} onClick={() => setTab('search')}>사용자 조회</button>
      </div>

      {tab === 'register' && (
        <div className="card">
          <div className="card-title">사용자 정보 등록</div>
          {alert && <div className="alert alert-success">{alert}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group">
                <label>사용자 ID <span className="required">*</span></label>
                <input className="form-control" name="userId" value={form.userId} onChange={handleChange} placeholder="사용자 ID 입력" required />
              </div>
              <div className="form-group">
                <label>이름 <span className="required">*</span></label>
                <input className="form-control" name="userName" value={form.userName} onChange={handleChange} placeholder="이름 입력" required />
              </div>
              <div className="form-group">
                <label>소속 부서</label>
                <input className="form-control" name="department" value={form.department} onChange={handleChange} placeholder="소속 부서 입력" />
              </div>
              <div className="form-group">
                <label>역할</label>
                <select className="form-control" name="role" value={form.role} onChange={handleChange}>
                  {ROLES.map((r) => <option key={r}>{r}</option>)}
                </select>
              </div>
              <div className="form-group">
                <label>연락처</label>
                <input className="form-control" name="contact" value={form.contact} onChange={handleChange} placeholder="연락처 입력" />
              </div>
              <div className="form-group">
                <label>이메일</label>
                <input className="form-control" type="email" name="email" value={form.email} onChange={handleChange} placeholder="이메일 입력" />
              </div>
              <div className="form-group">
                <label>계정 상태</label>
                <select className="form-control" name="accountStatus" value={form.accountStatus} onChange={handleChange}>
                  {STATUS.map((s) => <option key={s}>{s}</option>)}
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
          <div className="card-title">사용자 정보 조회</div>
          <div className="search-bar">
            <input className="form-control" placeholder="이름 검색" value={search.userName} onChange={(e) => setSearch({ ...search, userName: e.target.value })} />
            <input className="form-control" placeholder="부서 검색" value={search.department} onChange={(e) => setSearch({ ...search, department: e.target.value })} />
            <select className="form-control" value={search.role} onChange={(e) => setSearch({ ...search, role: e.target.value })} style={{ width: 140 }}>
              <option value="">역할 전체</option>
              {ROLES.map((r) => <option key={r}>{r}</option>)}
            </select>
          </div>
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>사용자 ID</th><th>이름</th><th>소속 부서</th><th>역할</th>
                  <th>연락처</th><th>이메일</th><th>계정 상태</th><th>등록일</th><th>관리</th>
                </tr>
              </thead>
              <tbody>
                {filtered.length === 0 ? (
                  <tr><td colSpan={9} className="empty-state">등록된 사용자가 없습니다.</td></tr>
                ) : filtered.map((d, i) => (
                  <tr key={i}>
                    <td>{d.userId}</td><td>{d.userName}</td><td>{d.department}</td><td>{d.role}</td>
                    <td>{d.contact}</td><td>{d.email}</td>
                    <td><span className={`badge ${d.accountStatus === '활성' ? 'badge-active' : 'badge-inactive'}`}>{d.accountStatus}</span></td>
                    <td>{d.registeredAt}</td>
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
