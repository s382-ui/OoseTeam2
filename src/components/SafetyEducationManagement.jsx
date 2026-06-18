import React, { useState, useEffect } from 'react';

/* ── 1. 연구활동종사자 & 이수기준 ── */
function ResearcherSection() {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState({ researcherId: '', researcherName: '', labName: '', role: '', requiredHours: '', completionDeadline: '' });
  const [data, setData] = useState(() => {
    try { return JSON.parse(localStorage.getItem('oss_edu_researchers')) || []; } catch { return []; }
  });
  const [search, setSearch] = useState('');
  const [alert, setAlert] = useState('');

  useEffect(() => { localStorage.setItem('oss_edu_researchers', JSON.stringify(data)); }, [data]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.researcherId || !form.researcherName) return;
    setData([{ ...form, registeredAt: new Date().toLocaleDateString('ko-KR') }, ...data]);
    setForm({ researcherId: '', researcherName: '', labName: '', role: '', requiredHours: '', completionDeadline: '' });
    setAlert('연구활동종사자 및 이수기준이 등록되었습니다.');
    setTimeout(() => setAlert(''), 3000);
  };

  const filtered = data.filter((d) => !search || d.researcherName.includes(search) || d.labName.includes(search));

  return (
    <>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>등록</button>
        <button className={`tab-btn ${tab === 'search' ? 'active' : ''}`} onClick={() => setTab('search')}>조회</button>
      </div>
      {tab === 'register' && (
        <div className="card">
          <div className="card-title">연구활동종사자 및 이수기준 등록</div>
          {alert && <div className="alert alert-success">{alert}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group">
                <label>종사자 ID <span className="required">*</span></label>
                <input className="form-control" value={form.researcherId} onChange={(e) => setForm({ ...form, researcherId: e.target.value })} placeholder="종사자 ID" required />
              </div>
              <div className="form-group">
                <label>이름 <span className="required">*</span></label>
                <input className="form-control" value={form.researcherName} onChange={(e) => setForm({ ...form, researcherName: e.target.value })} placeholder="이름" required />
              </div>
              <div className="form-group">
                <label>소속 연구실</label>
                <input className="form-control" value={form.labName} onChange={(e) => setForm({ ...form, labName: e.target.value })} placeholder="소속 연구실" />
              </div>
              <div className="form-group">
                <label>역할</label>
                <select className="form-control" value={form.role} onChange={(e) => setForm({ ...form, role: e.target.value })}>
                  <option>연구책임자</option><option>연구원</option><option>대학원생</option><option>학부생</option>
                </select>
              </div>
              <div className="form-group">
                <label>이수 기준 시간 (h)</label>
                <input className="form-control" type="number" value={form.requiredHours} onChange={(e) => setForm({ ...form, requiredHours: e.target.value })} placeholder="필요 이수 시간" />
              </div>
              <div className="form-group">
                <label>이수 기한</label>
                <input className="form-control" type="date" value={form.completionDeadline} onChange={(e) => setForm({ ...form, completionDeadline: e.target.value })} />
              </div>
            </div>
            <div className="form-actions">
              <button type="submit" className="btn btn-primary">등록</button>
            </div>
          </form>
        </div>
      )}
      {tab === 'search' && (
        <div className="card">
          <div className="card-title">연구활동종사자 및 이수기준 조회</div>
          <div className="search-bar">
            <input className="form-control" placeholder="이름 또는 연구실 검색" value={search} onChange={(e) => setSearch(e.target.value)} />
          </div>
          <div className="table-wrapper">
            <table>
              <thead><tr><th>종사자 ID</th><th>이름</th><th>소속 연구실</th><th>역할</th><th>이수기준(h)</th><th>이수기한</th><th>등록일</th><th>관리</th></tr></thead>
              <tbody>
                {filtered.length === 0
                  ? <tr><td colSpan={8} className="empty-state">등록된 데이터가 없습니다.</td></tr>
                  : filtered.map((d, i) => (
                    <tr key={i}>
                      <td>{d.researcherId}</td><td>{d.researcherName}</td><td>{d.labName}</td>
                      <td>{d.role}</td><td>{d.requiredHours}h</td><td>{d.completionDeadline}</td><td>{d.registeredAt}</td>
                      <td><button className="btn btn-danger btn-sm" onClick={() => setData(data.filter((_, j) => j !== i))}>삭제</button></td>
                    </tr>
                  ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </>
  );
}

/* ── 2. 안전교육일지 ── */
function EducationLogSection() {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState({ logId: '', labName: '', educationDate: '', topic: '', instructor: '', duration: '', attendees: '' });
  const [data, setData] = useState(() => {
    try { return JSON.parse(localStorage.getItem('oss_edu_logs')) || []; } catch { return []; }
  });
  const [search, setSearch] = useState('');
  const [alert, setAlert] = useState('');

  useEffect(() => { localStorage.setItem('oss_edu_logs', JSON.stringify(data)); }, [data]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.logId || !form.topic) return;
    setData([{ ...form, registeredAt: new Date().toLocaleDateString('ko-KR') }, ...data]);
    setForm({ logId: '', labName: '', educationDate: '', topic: '', instructor: '', duration: '', attendees: '' });
    setAlert('안전교육일지가 등록되었습니다.');
    setTimeout(() => setAlert(''), 3000);
  };

  const filtered = data.filter((d) => !search || d.topic.includes(search) || d.labName.includes(search));

  return (
    <>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>등록</button>
        <button className={`tab-btn ${tab === 'search' ? 'active' : ''}`} onClick={() => setTab('search')}>조회</button>
      </div>
      {tab === 'register' && (
        <div className="card">
          <div className="card-title">안전교육일지 등록</div>
          {alert && <div className="alert alert-success">{alert}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group">
                <label>일지 ID <span className="required">*</span></label>
                <input className="form-control" value={form.logId} onChange={(e) => setForm({ ...form, logId: e.target.value })} placeholder="일지 ID" required />
              </div>
              <div className="form-group">
                <label>교육 주제 <span className="required">*</span></label>
                <input className="form-control" value={form.topic} onChange={(e) => setForm({ ...form, topic: e.target.value })} placeholder="교육 주제" required />
              </div>
              <div className="form-group">
                <label>소속 연구실</label>
                <input className="form-control" value={form.labName} onChange={(e) => setForm({ ...form, labName: e.target.value })} placeholder="소속 연구실" />
              </div>
              <div className="form-group">
                <label>교육 일자</label>
                <input className="form-control" type="date" value={form.educationDate} onChange={(e) => setForm({ ...form, educationDate: e.target.value })} />
              </div>
              <div className="form-group">
                <label>강사명</label>
                <input className="form-control" value={form.instructor} onChange={(e) => setForm({ ...form, instructor: e.target.value })} placeholder="강사명" />
              </div>
              <div className="form-group">
                <label>교육 시간 (h)</label>
                <input className="form-control" type="number" value={form.duration} onChange={(e) => setForm({ ...form, duration: e.target.value })} placeholder="교육 시간" />
              </div>
              <div className="form-group">
                <label>참석 인원</label>
                <input className="form-control" type="number" value={form.attendees} onChange={(e) => setForm({ ...form, attendees: e.target.value })} placeholder="참석 인원 수" />
              </div>
            </div>
            <div className="form-actions">
              <button type="submit" className="btn btn-primary">등록</button>
            </div>
          </form>
        </div>
      )}
      {tab === 'search' && (
        <div className="card">
          <div className="card-title">안전교육일지 조회</div>
          <div className="search-bar">
            <input className="form-control" placeholder="교육 주제 또는 연구실 검색" value={search} onChange={(e) => setSearch(e.target.value)} />
          </div>
          <div className="table-wrapper">
            <table>
              <thead><tr><th>일지 ID</th><th>교육 주제</th><th>연구실</th><th>교육일자</th><th>강사</th><th>교육시간</th><th>참석인원</th><th>등록일</th><th>관리</th></tr></thead>
              <tbody>
                {filtered.length === 0
                  ? <tr><td colSpan={9} className="empty-state">등록된 데이터가 없습니다.</td></tr>
                  : filtered.map((d, i) => (
                    <tr key={i}>
                      <td>{d.logId}</td><td>{d.topic}</td><td>{d.labName}</td><td>{d.educationDate}</td>
                      <td>{d.instructor}</td><td>{d.duration}h</td><td>{d.attendees}명</td><td>{d.registeredAt}</td>
                      <td><button className="btn btn-danger btn-sm" onClick={() => setData(data.filter((_, j) => j !== i))}>삭제</button></td>
                    </tr>
                  ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </>
  );
}

/* ── 3. 수료증 발급 ── */
function CertificateSection() {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState({ certId: '', researcherName: '', courseTitle: '', completionDate: '', issueDate: '', issuedBy: '' });
  const [data, setData] = useState(() => {
    try { return JSON.parse(localStorage.getItem('oss_edu_certs')) || []; } catch { return []; }
  });
  const [search, setSearch] = useState('');
  const [alert, setAlert] = useState('');

  useEffect(() => { localStorage.setItem('oss_edu_certs', JSON.stringify(data)); }, [data]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.certId || !form.researcherName) return;
    setData([{ ...form, registeredAt: new Date().toLocaleDateString('ko-KR') }, ...data]);
    setForm({ certId: '', researcherName: '', courseTitle: '', completionDate: '', issueDate: '', issuedBy: '' });
    setAlert('수료증이 발급 등록되었습니다.');
    setTimeout(() => setAlert(''), 3000);
  };

  const filtered = data.filter((d) => !search || d.researcherName.includes(search) || d.courseTitle.includes(search));

  return (
    <>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>발급 등록</button>
        <button className={`tab-btn ${tab === 'search' ? 'active' : ''}`} onClick={() => setTab('search')}>발급 조회</button>
      </div>
      {tab === 'register' && (
        <div className="card">
          <div className="card-title">수료증 발급 등록</div>
          {alert && <div className="alert alert-success">{alert}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group">
                <label>수료증 ID <span className="required">*</span></label>
                <input className="form-control" value={form.certId} onChange={(e) => setForm({ ...form, certId: e.target.value })} placeholder="수료증 ID" required />
              </div>
              <div className="form-group">
                <label>대상자 이름 <span className="required">*</span></label>
                <input className="form-control" value={form.researcherName} onChange={(e) => setForm({ ...form, researcherName: e.target.value })} placeholder="대상자 이름" required />
              </div>
              <div className="form-group">
                <label>과정명</label>
                <input className="form-control" value={form.courseTitle} onChange={(e) => setForm({ ...form, courseTitle: e.target.value })} placeholder="교육 과정명" />
              </div>
              <div className="form-group">
                <label>이수 완료일</label>
                <input className="form-control" type="date" value={form.completionDate} onChange={(e) => setForm({ ...form, completionDate: e.target.value })} />
              </div>
              <div className="form-group">
                <label>발급일</label>
                <input className="form-control" type="date" value={form.issueDate} onChange={(e) => setForm({ ...form, issueDate: e.target.value })} />
              </div>
              <div className="form-group">
                <label>발급 기관</label>
                <input className="form-control" value={form.issuedBy} onChange={(e) => setForm({ ...form, issuedBy: e.target.value })} placeholder="발급 기관" />
              </div>
            </div>
            <div className="form-actions">
              <button type="submit" className="btn btn-primary">발급 등록</button>
            </div>
          </form>
        </div>
      )}
      {tab === 'search' && (
        <div className="card">
          <div className="card-title">수료증 발급 조회</div>
          <div className="search-bar">
            <input className="form-control" placeholder="대상자 이름 또는 과정명 검색" value={search} onChange={(e) => setSearch(e.target.value)} />
          </div>
          <div className="table-wrapper">
            <table>
              <thead><tr><th>수료증 ID</th><th>대상자</th><th>과정명</th><th>이수완료일</th><th>발급일</th><th>발급기관</th><th>등록일</th><th>관리</th></tr></thead>
              <tbody>
                {filtered.length === 0
                  ? <tr><td colSpan={8} className="empty-state">등록된 데이터가 없습니다.</td></tr>
                  : filtered.map((d, i) => (
                    <tr key={i}>
                      <td>{d.certId}</td><td>{d.researcherName}</td><td>{d.courseTitle}</td>
                      <td>{d.completionDate}</td><td>{d.issueDate}</td><td>{d.issuedBy}</td><td>{d.registeredAt}</td>
                      <td><button className="btn btn-danger btn-sm" onClick={() => setData(data.filter((_, j) => j !== i))}>삭제</button></td>
                    </tr>
                  ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </>
  );
}

/* ── 4. 학습 결과 ── */
function LearningResultSection() {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState({ resultId: '', researcherName: '', courseTitle: '', score: '', status: '이수', completedDate: '' });
  const [data, setData] = useState(() => {
    try { return JSON.parse(localStorage.getItem('oss_edu_learnings')) || []; } catch { return []; }
  });
  const [search, setSearch] = useState('');
  const [alert, setAlert] = useState('');

  useEffect(() => { localStorage.setItem('oss_edu_learnings', JSON.stringify(data)); }, [data]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.resultId || !form.researcherName) return;
    setData([{ ...form, registeredAt: new Date().toLocaleDateString('ko-KR') }, ...data]);
    setForm({ resultId: '', researcherName: '', courseTitle: '', score: '', status: '이수', completedDate: '' });
    setAlert('학습 결과가 등록되었습니다.');
    setTimeout(() => setAlert(''), 3000);
  };

  const filtered = data.filter((d) => !search || d.researcherName.includes(search));

  return (
    <>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>등록</button>
        <button className={`tab-btn ${tab === 'search' ? 'active' : ''}`} onClick={() => setTab('search')}>조회</button>
      </div>
      {tab === 'register' && (
        <div className="card">
          <div className="card-title">학습 결과 등록</div>
          {alert && <div className="alert alert-success">{alert}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group">
                <label>결과 ID <span className="required">*</span></label>
                <input className="form-control" value={form.resultId} onChange={(e) => setForm({ ...form, resultId: e.target.value })} placeholder="결과 ID" required />
              </div>
              <div className="form-group">
                <label>학습자 이름 <span className="required">*</span></label>
                <input className="form-control" value={form.researcherName} onChange={(e) => setForm({ ...form, researcherName: e.target.value })} placeholder="학습자 이름" required />
              </div>
              <div className="form-group">
                <label>과정명</label>
                <input className="form-control" value={form.courseTitle} onChange={(e) => setForm({ ...form, courseTitle: e.target.value })} placeholder="과정명" />
              </div>
              <div className="form-group">
                <label>점수</label>
                <input className="form-control" type="number" value={form.score} onChange={(e) => setForm({ ...form, score: e.target.value })} placeholder="점수 (0~100)" min={0} max={100} />
              </div>
              <div className="form-group">
                <label>이수 상태</label>
                <select className="form-control" value={form.status} onChange={(e) => setForm({ ...form, status: e.target.value })}>
                  <option>이수</option><option>미이수</option><option>진행중</option>
                </select>
              </div>
              <div className="form-group">
                <label>완료일</label>
                <input className="form-control" type="date" value={form.completedDate} onChange={(e) => setForm({ ...form, completedDate: e.target.value })} />
              </div>
            </div>
            <div className="form-actions">
              <button type="submit" className="btn btn-primary">등록</button>
            </div>
          </form>
        </div>
      )}
      {tab === 'search' && (
        <div className="card">
          <div className="card-title">학습 결과 조회</div>
          <div className="search-bar">
            <input className="form-control" placeholder="학습자 이름 검색" value={search} onChange={(e) => setSearch(e.target.value)} />
          </div>
          <div className="table-wrapper">
            <table>
              <thead><tr><th>결과 ID</th><th>학습자</th><th>과정명</th><th>점수</th><th>이수상태</th><th>완료일</th><th>등록일</th><th>관리</th></tr></thead>
              <tbody>
                {filtered.length === 0
                  ? <tr><td colSpan={8} className="empty-state">등록된 데이터가 없습니다.</td></tr>
                  : filtered.map((d, i) => (
                    <tr key={i}>
                      <td>{d.resultId}</td><td>{d.researcherName}</td><td>{d.courseTitle}</td>
                      <td>{d.score}점</td>
                      <td><span className={`badge ${d.status === '이수' ? 'badge-active' : d.status === '진행중' ? 'badge-pending' : 'badge-inactive'}`}>{d.status}</span></td>
                      <td>{d.completedDate}</td><td>{d.registeredAt}</td>
                      <td><button className="btn btn-danger btn-sm" onClick={() => setData(data.filter((_, j) => j !== i))}>삭제</button></td>
                    </tr>
                  ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </>
  );
}

/* ── 5. 교육이수 결과 ── */
function CompletionResultSection() {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState({ completionId: '', researcherName: '', totalHours: '', completedHours: '', completionRate: '', period: '', isCompleted: 'Y' });
  const [data, setData] = useState(() => {
    try { return JSON.parse(localStorage.getItem('oss_edu_completions')) || []; } catch { return []; }
  });
  const [search, setSearch] = useState('');
  const [alert, setAlert] = useState('');

  useEffect(() => { localStorage.setItem('oss_edu_completions', JSON.stringify(data)); }, [data]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.completionId || !form.researcherName) return;
    setData([{ ...form, registeredAt: new Date().toLocaleDateString('ko-KR') }, ...data]);
    setForm({ completionId: '', researcherName: '', totalHours: '', completedHours: '', completionRate: '', period: '', isCompleted: 'Y' });
    setAlert('교육이수 결과가 등록되었습니다.');
    setTimeout(() => setAlert(''), 3000);
  };

  const filtered = data.filter((d) => !search || d.researcherName.includes(search));

  return (
    <>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>등록</button>
        <button className={`tab-btn ${tab === 'search' ? 'active' : ''}`} onClick={() => setTab('search')}>조회</button>
      </div>
      {tab === 'register' && (
        <div className="card">
          <div className="card-title">교육이수 결과 등록</div>
          {alert && <div className="alert alert-success">{alert}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group">
                <label>이수결과 ID <span className="required">*</span></label>
                <input className="form-control" value={form.completionId} onChange={(e) => setForm({ ...form, completionId: e.target.value })} placeholder="이수결과 ID" required />
              </div>
              <div className="form-group">
                <label>대상자 이름 <span className="required">*</span></label>
                <input className="form-control" value={form.researcherName} onChange={(e) => setForm({ ...form, researcherName: e.target.value })} placeholder="대상자 이름" required />
              </div>
              <div className="form-group">
                <label>기준 이수 시간 (h)</label>
                <input className="form-control" type="number" value={form.totalHours} onChange={(e) => setForm({ ...form, totalHours: e.target.value })} placeholder="기준 시간" />
              </div>
              <div className="form-group">
                <label>실제 이수 시간 (h)</label>
                <input className="form-control" type="number" value={form.completedHours} onChange={(e) => setForm({ ...form, completedHours: e.target.value })} placeholder="이수 시간" />
              </div>
              <div className="form-group">
                <label>이수율 (%)</label>
                <input className="form-control" type="number" value={form.completionRate} onChange={(e) => setForm({ ...form, completionRate: e.target.value })} placeholder="이수율" min={0} max={100} />
              </div>
              <div className="form-group">
                <label>교육 기간</label>
                <input className="form-control" value={form.period} onChange={(e) => setForm({ ...form, period: e.target.value })} placeholder="예: 2026년 상반기" />
              </div>
              <div className="form-group">
                <label>이수 완료 여부</label>
                <select className="form-control" value={form.isCompleted} onChange={(e) => setForm({ ...form, isCompleted: e.target.value })}>
                  <option value="Y">완료</option><option value="N">미완료</option>
                </select>
              </div>
            </div>
            <div className="form-actions">
              <button type="submit" className="btn btn-primary">등록</button>
            </div>
          </form>
        </div>
      )}
      {tab === 'search' && (
        <div className="card">
          <div className="card-title">교육이수 결과 조회</div>
          <div className="search-bar">
            <input className="form-control" placeholder="대상자 이름 검색" value={search} onChange={(e) => setSearch(e.target.value)} />
          </div>
          <div className="table-wrapper">
            <table>
              <thead><tr><th>ID</th><th>대상자</th><th>기준(h)</th><th>이수(h)</th><th>이수율</th><th>교육기간</th><th>완료여부</th><th>등록일</th><th>관리</th></tr></thead>
              <tbody>
                {filtered.length === 0
                  ? <tr><td colSpan={9} className="empty-state">등록된 데이터가 없습니다.</td></tr>
                  : filtered.map((d, i) => (
                    <tr key={i}>
                      <td>{d.completionId}</td><td>{d.researcherName}</td><td>{d.totalHours}h</td>
                      <td>{d.completedHours}h</td><td>{d.completionRate}%</td><td>{d.period}</td>
                      <td><span className={`badge ${d.isCompleted === 'Y' ? 'badge-active' : 'badge-inactive'}`}>{d.isCompleted === 'Y' ? '완료' : '미완료'}</span></td>
                      <td>{d.registeredAt}</td>
                      <td><button className="btn btn-danger btn-sm" onClick={() => setData(data.filter((_, j) => j !== i))}>삭제</button></td>
                    </tr>
                  ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </>
  );
}

/* ── Main 안전교육 관리 ── */
const subSections = [
  { id: 'researcher', label: '연구활동종사자/이수기준', Component: ResearcherSection },
  { id: 'log', label: '안전교육일지', Component: EducationLogSection },
  { id: 'certificate', label: '수료증 발급', Component: CertificateSection },
  { id: 'learning', label: '학습 결과', Component: LearningResultSection },
  { id: 'completion', label: '교육이수 결과', Component: CompletionResultSection },
];

export default function SafetyEducationManagement() {
  const [activeSection, setActiveSection] = useState('researcher');
  const active = subSections.find((s) => s.id === activeSection);

  return (
    <div>
      <h2 className="page-title">안전교육 관리</h2>
      <div className="sub-tabs">
        {subSections.map((s) => (
          <button
            key={s.id}
            className={`sub-tab-btn ${activeSection === s.id ? 'active' : ''}`}
            onClick={() => setActiveSection(s.id)}
          >
            {s.label}
          </button>
        ))}
      </div>
      <active.Component />
    </div>
  );
}
