import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { laboratoryService } from '../api/laboratoryApi';

const initialForm = {
  labId: '', labName: '', buildingName: '', floor: '', roomNo: '',
  departmentName: '', managerId: '', managerName: '', contactNo: '',
  managementGrade: 'A', isActive: '사용',
};

export default function LaboratoryManagement() {
  return (
    <EntityManagementPage
      title="연구실 관리"
      registerTitle="연구실 정보 등록"
      inquiryTitle="연구실 정보 조회"
      initialForm={initialForm}
      idField="labId"
      service={laboratoryService}
      searchFields={['labId', 'labName', 'buildingName', 'departmentName']}
      fields={[
        { name: 'labId', label: '연구실 ID', required: true },
        { name: 'labName', label: '연구실명', required: true },
        { name: 'buildingName', label: '건물명' },
        { name: 'floor', label: '층' },
        { name: 'roomNo', label: '호실' },
        { name: 'departmentName', label: '소속 부서' },
        { name: 'managerId', label: '관리자 ID' },
        { name: 'managerName', label: '관리자명' },
        { name: 'contactNo', label: '연락처' },
        { name: 'managementGrade', label: '관리 등급', options: ['A', 'B', 'C', 'D'] },
        { name: 'isActive', label: '사용 여부', options: ['사용', '미사용'] },
      ]}
      columns={[
        { key: 'labId', label: '연구실 ID' },
        { key: 'labName', label: '연구실명' },
        { key: 'buildingName', label: '건물' },
        { key: 'floor', label: '층' },
        { key: 'roomNo', label: '호실' },
        { key: 'departmentName', label: '소속 부서' },
        { key: 'managerName', label: '관리자' },
        { key: 'managementGrade', label: '등급' },
        { key: 'isActive', label: '사용 여부' },
      ]}
    />
  );
}
