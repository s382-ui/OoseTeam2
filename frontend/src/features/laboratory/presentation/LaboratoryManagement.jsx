import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { laboratoryService } from '../api/laboratoryApi';

const initialForm = {
  labId: '', labName: '', buildingName: '', floor: '', roomNo: '',
  departmentName: '', managerId: '', managerName: '', contactNo: '',
  managementGrade: 'A', isActive: 'Y',
};

const fields = [
  { name: 'labId', label: '연구실 ID', required: true },
  { name: 'labName', label: '연구실명', required: true },
  { name: 'buildingName', label: '건물명', required: true },
  { name: 'floor', label: '층', required: true },
  { name: 'roomNo', label: '호실', required: true },
  { name: 'departmentName', label: '소속 학과/부서', required: true },
  { name: 'managerId', label: '책임자 사용자 ID', required: true },
  { name: 'managerName', label: '책임자명', required: true },
  { name: 'contactNo', label: '대표 연락처' },
  { name: 'managementGrade', label: '안전관리 등급', required: true, options: ['A', 'B', 'C', 'D'] },
  { name: 'isActive', label: '사용 여부', required: true, options: [{ value: 'Y', label: '사용' }, { value: 'N', label: '미사용' }] },
];

export default function LaboratoryManagement() {
  return (
    <EntityManagementPage
      title="연구실 관리"
      registerTitle="연구실 관리 등록"
      inquiryTitle="연구실 관리 조회"
      initialForm={initialForm}
      idField="labId"
      service={laboratoryService}
      fields={fields}
      searchFilters={[
        { name: 'labName', label: '연구실명' },
        { name: 'buildingName', label: '건물명' },
        { name: 'roomNo', label: '호실' },
        { name: 'departmentName', label: '소속 학과/부서' },
        { name: 'managerName', label: '책임자명' },
        { name: 'isActive', label: '사용 여부', options: [{ value: 'Y', label: '사용' }, { value: 'N', label: '미사용' }], exact: true },
      ]}
      columns={[
        { key: 'labId', label: '연구실 ID' },
        { key: 'labName', label: '연구실명' },
        { key: 'buildingName', label: '건물' },
        { key: 'floor', label: '층' },
        { key: 'roomNo', label: '호실' },
        { key: 'departmentName', label: '소속' },
        { key: 'managerName', label: '책임자' },
        { key: 'managementGrade', label: '등급' },
        { key: 'isActive', label: '사용 여부', render: (value) => value === 'Y' ? '사용' : '미사용' },
      ]}
    />
  );
}
