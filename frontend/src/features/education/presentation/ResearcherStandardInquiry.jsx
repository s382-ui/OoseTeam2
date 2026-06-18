import React, { useEffect, useMemo, useState } from 'react';
import { researcherStandardService } from '../api/educationApi';

export default function ResearcherStandardInquiry() {
  const [data, setData] = useState([]);
  const [search, setSearch] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    researcherStandardService.list().then(setData).catch((requestError) => setError(requestError.message));
  }, []);

  const filtered = useMemo(() => data.filter((item) => {
    const keyword = search.toLowerCase();
    return !keyword
      || item.researcherCategoryId.toLowerCase().includes(keyword)
      || item.categoryName.toLowerCase().includes(keyword);
  }), [data, search]);

  return (
    <div className="card">
      <div className="card-title">연구활동종사자 및 이수기준 설정 조회</div>
      {error && <div className="alert alert-error">{error}</div>}
      <div className="search-bar"><input className="form-control" value={search} onChange={(event) => setSearch(event.target.value)} placeholder="분류 ID 또는 분류명 검색" /></div>
      <div className="table-wrapper">
        <table>
          <thead><tr><th>분류 ID</th><th>분류명</th><th>이수기준 ID</th><th>필수시간</th><th>적용 시작일</th><th>적용 종료일</th><th>사용 여부</th></tr></thead>
          <tbody>
            {filtered.length === 0
              ? <tr><td colSpan={7} className="empty-state">등록된 이수기준이 없습니다.</td></tr>
              : filtered.map((item) => (
                <tr key={item.completionStandardId}>
                  <td>{item.researcherCategoryId}</td><td>{item.categoryName}</td><td>{item.completionStandardId}</td>
                  <td>{item.requiredHours}시간</td><td>{item.effectiveFrom}</td><td>{item.effectiveTo}</td><td>{item.active ? 'Y' : 'N'}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
