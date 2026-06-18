import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { chemicalService } from '../service/chemicalService';

const initialForm = {
  chemicalId: '', chemicalName: '', casNumber: '', manufacturerName: '',
  quantity: '', unit: 'L', storageLocation: '', msdsYn: 'Y', status: '사용',
};

export default function ChemicalManagement() {
  return (
    <EntityManagementPage
      title="화학물질 관리"
      registerTitle="화학물질 등록"
      inquiryTitle="화학물질 조회"
      initialForm={initialForm}
      idField="chemicalId"
      service={chemicalService}
      searchFields={['chemicalId', 'chemicalName', 'casNumber', 'manufacturerName']}
      fields={[
        { name: 'chemicalId', label: '화학물질 ID', required: true },
        { name: 'chemicalName', label: '화학물질명', required: true },
        { name: 'casNumber', label: 'CAS 번호' },
        { name: 'manufacturerName', label: '제조사' },
        { name: 'quantity', label: '수량', type: 'number', min: 0 },
        { name: 'unit', label: '단위', options: ['L', 'mL', 'kg', 'g'] },
        { name: 'storageLocation', label: '보관 위치' },
        { name: 'msdsYn', label: 'MSDS 여부', options: ['Y', 'N'] },
        { name: 'status', label: '상태', options: ['사용', '폐기', '보관'] },
      ]}
      columns={[
        { key: 'chemicalId', label: 'ID' },
        { key: 'chemicalName', label: '화학물질명' },
        { key: 'casNumber', label: 'CAS 번호' },
        { key: 'manufacturerName', label: '제조사' },
        { key: 'quantity', label: '수량' },
        { key: 'unit', label: '단위' },
        { key: 'storageLocation', label: '보관 위치' },
        { key: 'msdsYn', label: 'MSDS' },
        { key: 'status', label: '상태' },
      ]}
    />
  );
}
