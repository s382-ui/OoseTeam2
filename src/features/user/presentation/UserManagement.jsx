import React from 'react';
import EntityManagementPage from '../../../components/EntityManagementPage';
import { userService } from '../service/userService';

const initialForm = {
  userId: '', userName: '', department: '', role: '연구원',
  contact: '', email: '', accountStatus: '활성',
};

export default function UserManagement() {
  return (
    <EntityManagementPage
      title="사용자 관리"
      registerTitle="사용자 정보 등록"
      inquiryTitle="사용자 정보 조회"
      initialForm={initialForm}
      idField="userId"
      service={userService}
      searchFields={['userId', 'userName', 'department', 'role']}
      fields={[
        { name: 'userId', label: '사용자 ID', required: true },
        { name: 'userName', label: '이름', required: true },
        { name: 'department', label: '소속 부서' },
        { name: 'role', label: '역할', options: ['관리자', '연구책임자', '연구원', '대학원생', '학부생', '외부인'] },
        { name: 'contact', label: '연락처' },
        { name: 'email', label: '이메일', type: 'email' },
        { name: 'accountStatus', label: '계정 상태', options: ['활성', '비활성', '잠금'] },
      ]}
      columns={[
        { key: 'userId', label: '사용자 ID' },
        { key: 'userName', label: '이름' },
        { key: 'department', label: '소속 부서' },
        { key: 'role', label: '역할' },
        { key: 'contact', label: '연락처' },
        { key: 'email', label: '이메일' },
        { key: 'accountStatus', label: '계정 상태' },
        { key: 'registeredAt', label: '등록일' },
      ]}
    />
  );
}
