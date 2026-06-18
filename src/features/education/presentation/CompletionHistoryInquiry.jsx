import React, { useEffect, useMemo, useState } from 'react';
import { educationCompletionService } from '../service/educationCompletionService';

export default function CompletionHistoryInquiry() {
  const [data, setData] = useState([]);
  const [search, setSearch] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    educationCompletionService.list().then(setData).catch((requestError) => setError(requestError.message));
  }, []);

  const filtered = useMemo(() => data.filter((item) => {
    const keyword = search.toLowerCase();
    return !keyword
      || item.completionResultId.toLowerCase().includes(keyword)
      || item.researcherId.toLowerCase().includes(keyword)
      || item.researcherName.toLowerCase().includes(keyword)
      || item.courseName.toLowerCase().includes(keyword);
  }), [data, search]);

  return (
    <div className="card">
      <div className="card-title">교육이수 내역 조회</div>
      {error && <div className="alert alert-error">{error}</div>}
      <div className="search-bar"><input className="form-control" value={search} onChange={(event) => setSearch(event.target.value)} placeholder="이수결과 ID, 종사자, 과정명 검색" /></div>
      <div className="table-wrapper">
        <table>
          <thead><tr><th>이수결과 ID</th><th>연구활동종사자</th><th>개설 ID</th><th>과정</th><th>유형</th><th>이수일</th><th>인정시간</th><th>상태</th></tr></thead>
          <tbody>
            {filtered.length === 0
              ? <tr><td colSpan={8} className="empty-state">등록된 교육이수 내역이 없습니다.</td></tr>
              : filtered.map((item) => (
                <tr key={item.completionResultId}>
                  <td>{item.completionResultId}</td><td>{item.researcherId} ({item.researcherName})</td>
                  <td>{item.openingId}</td><td>{item.courseName}</td><td>{item.educationType}</td>
                  <td>{item.completionDate}</td><td>{item.recognizedHours}시간</td><td>{item.completionStatus}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
