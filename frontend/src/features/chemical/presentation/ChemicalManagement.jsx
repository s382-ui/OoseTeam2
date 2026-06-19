import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { chemicalService } from '../api/chemicalApi';

const initialForm = {
  chemicalId: '', manufacturerName: '', chemicalName: '', casNumber: '',
  contentRate: '', msdsPath: '', analysisPath: '', status: 'ACTIVE',
};

const fields = [
  { name: 'chemicalId', label: '화학물질 ID', required: true },
  { name: 'manufacturerName', label: '제조사명', required: true },
  { name: 'chemicalName', label: '화학물질명', required: true },
  { name: 'casNumber', label: 'CAS 번호', placeholder: '예: 64-17-5' },
  { name: 'contentRate', label: '성분 함유량', placeholder: '예: 99.5%' },
  { name: 'msdsPath', label: 'MSDS 파일 경로', helpText: '현재 JSON 저장 방식에서는 업로드된 문서의 경로 또는 파일명을 기록합니다.' },
  { name: 'analysisPath', label: '성분분석표 파일 경로' },
  { name: 'status', label: '상태', required: true, options: [{ value: 'ACTIVE', label: '정상' }, { value: 'DELETED', label: '삭제' }] },
];

export default function ChemicalManagement() {
  return (
    <EntityManagementPage
      title="화학물질 관리"
      registerTitle="화학물질 등록"
      inquiryTitle="화학물질 조회"
      initialForm={initialForm}
      idField="chemicalId"
      service={chemicalService}
      fields={fields}
      searchFilters={[
        { name: 'manufacturerName', label: '제조사명' },
        { name: 'chemicalName', label: '화학물질명' },
        { name: 'casNumber', label: 'CAS 번호' },
        { name: 'status', label: '상태', options: [{ value: 'ACTIVE', label: '정상' }, { value: 'DELETED', label: '삭제' }], exact: true },
      ]}
      columns={[
        { key: 'chemicalId', label: '화학물질 ID' },
        { key: 'manufacturerName', label: '제조사명' },
        { key: 'chemicalName', label: '화학물질명' },
        { key: 'casNumber', label: 'CAS 번호' },
        { key: 'contentRate', label: '함유량' },
        { key: 'status', label: '상태', render: (value) => value === 'ACTIVE' ? '정상' : '삭제' },
        { key: 'createdAt', label: '등록일시' },
      ]}
    />
  );
}
