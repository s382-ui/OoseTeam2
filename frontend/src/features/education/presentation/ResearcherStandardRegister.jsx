import React, { useState } from 'react';
import { researcherStandardService } from '../api/educationApi';

const initialForm = {
  researcherCategoryId: '', categoryName: '', description: '', active: 'Y',
  completionStandardId: '', requiredHours: '', effectiveFrom: '', effectiveTo: '',
};

export default function ResearcherStandardRegister() {
  const [form, setForm] = useState(initialForm);
  const [message, setMessage] = useState({ type: '', text: '' });

  const change = (event) => setForm({ ...form, [event.target.name]: event.target.value });

  const submit = async (event) => {
    event.preventDefault();
    try {
      await researcherStandardService.register(form);
      setForm(initialForm);
      setMessage({ type: 'success', text: '연구활동종사자 분류와 이수기준이 등록되었습니다.' });
    } catch (error) {
      setMessage({ type: 'error', text: error.message });
    }
  };

  return (
    <div className="card">
      <div className="card-title">연구활동종사자 및 이수기준 설정 등록</div>
      {message.text && <div className={`alert alert-${message.type}`}>{message.text}</div>}
      <form onSubmit={submit}>
        <div className="form-grid">
          <div className="form-group"><label>분류 ID <span className="required">*</span></label><input className="form-control" name="researcherCategoryId" value={form.researcherCategoryId} onChange={change} required /></div>
          <div className="form-group"><label>분류명 <span className="required">*</span></label><input className="form-control" name="categoryName" value={form.categoryName} onChange={change} required /></div>
          <div className="form-group"><label>분류 설명</label><input className="form-control" name="description" value={form.description} onChange={change} /></div>
          <div className="form-group"><label>사용 여부</label><select className="form-control" name="active" value={form.active} onChange={change}><option value="Y">Y</option><option value="N">N</option></select></div>
          <div className="form-group"><label>이수기준 ID <span className="required">*</span></label><input className="form-control" name="completionStandardId" value={form.completionStandardId} onChange={change} required /></div>
          <div className="form-group"><label>필수 이수시간 <span className="required">*</span></label><input className="form-control" type="number" min="1" name="requiredHours" value={form.requiredHours} onChange={change} required /></div>
          <div className="form-group"><label>적용 시작일 <span className="required">*</span></label><input className="form-control" type="date" name="effectiveFrom" value={form.effectiveFrom} onChange={change} required /></div>
          <div className="form-group"><label>적용 종료일 <span className="required">*</span></label><input className="form-control" type="date" name="effectiveTo" value={form.effectiveTo} onChange={change} required /></div>
        </div>
        <div className="form-actions">
          <button type="button" className="btn btn-secondary" onClick={() => setForm(initialForm)}>초기화</button>
          <button type="submit" className="btn btn-primary">등록</button>
        </div>
      </form>
    </div>
  );
}
