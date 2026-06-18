import React, { useState } from 'react';
import ResearcherStandardRegister from './ResearcherStandardRegister';
import ResearcherStandardInquiry from './ResearcherStandardInquiry';
import CompletionResultRegister from './CompletionResultRegister';
import CompletionHistoryInquiry from './CompletionHistoryInquiry';

const useCases = [
  { id: 'standard-register', label: '이수기준 설정 등록', Component: ResearcherStandardRegister },
  { id: 'standard-inquiry', label: '이수기준 설정 조회', Component: ResearcherStandardInquiry },
  { id: 'result-register', label: '교육이수 결과 등록', Component: CompletionResultRegister },
  { id: 'history-inquiry', label: '교육이수 내역 조회', Component: CompletionHistoryInquiry },
];

export default function SafetyEducationManagement() {
  const [activeUseCase, setActiveUseCase] = useState(useCases[0].id);
  const ActiveComponent = useCases.find((item) => item.id === activeUseCase).Component;

  return (
    <div>
      <h2 className="page-title">안전교육 관리</h2>
      <div className="sub-tabs">
        {useCases.map((item) => (
          <button
            key={item.id}
            className={`sub-tab-btn ${activeUseCase === item.id ? 'active' : ''}`}
            onClick={() => setActiveUseCase(item.id)}
          >
            {item.label}
          </button>
        ))}
      </div>
      <ActiveComponent />
    </div>
  );
}
