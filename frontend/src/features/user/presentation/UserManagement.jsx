import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { downloadUserCsvTemplate, parseUserCsv } from '../../../shared/csv/userCsv';
import { userService } from '../api/userApi';

const initialForm = {
  userId: '', userName: '', department: '', role: '연구원',
  contact: '', email: '', accountStatus: '활성',
};

const fields = [
  { name: 'userId', label: '사용자 ID(학번/사번)', required: true },
  { name: 'userName', label: '성명', required: true },
  { name: 'department', label: '소속', required: true },
  { name: 'role', label: '역할', required: true, options: ['관리자', '연구책임자', '연구원', '대학원생', '학부생', '외부인'] },
  { name: 'contact', label: '연락처', required: true, placeholder: '010-0000-0000' },
  { name: 'email', label: '이메일', type: 'email' },
  { name: 'accountStatus', label: '계정 상태', required: true, options: ['활성', '비활성', '잠금'] },
];

export default function UserManagement() {
  return (
    <EntityManagementPage
      title="사용자 관리"
      registerTitle="사용자 정보 등록"
      inquiryTitle="사용자 정보 조회"
      initialForm={initialForm}
      idField="userId"
      service={userService}
      fields={fields}
      searchFilters={[
        { name: 'userId', label: '사용자 ID' },
        { name: 'userName', label: '성명' },
        { name: 'department', label: '소속' },
        { name: 'role', label: '역할', options: ['관리자', '연구책임자', '연구원', '대학원생', '학부생', '외부인'], exact: true },
        { name: 'accountStatus', label: '계정 상태', options: ['활성', '비활성', '잠금'], exact: true },
      ]}
      columns={[
        { key: 'userId', label: '사용자 ID' },
        { key: 'userName', label: '성명' },
        { key: 'department', label: '소속' },
        { key: 'role', label: '역할' },
        { key: 'contact', label: '연락처' },
        { key: 'accountStatus', label: '계정 상태' },
        { key: 'registeredAt', label: '등록일시' },
      ]}
      bulkRegistration={{
        title: '사용자 대량 등록',
        description: '양식에 맞춘 CSV 파일을 업로드하면 필수값과 중복 ID를 검증한 뒤 한 번에 등록합니다.',
        parse: parseUserCsv,
        submit: userService.registerAll,
        downloadTemplate: downloadUserCsvTemplate,
        previewColumns: [
          { key: 'userId', label: '사용자 ID' },
          { key: 'userName', label: '성명' },
          { key: 'department', label: '소속' },
          { key: 'role', label: '역할' },
        ],
      }}
    />
  );
}
