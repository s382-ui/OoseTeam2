import React, { useState } from 'react';
import { educationCompletionService } from '../service/educationCompletionService';

const initialForm = {
  completionResultId: '', researcherId: '', openingId: '', learningResultId: '',
  logId: '', completionDate: '', recognizedHours: '', completionStatus: '이수',
  manualRegistration: 'Y',
};

export default function CompletionResultRegister() {
  const [form, setForm] = useState(initialForm);
  const [message, setMessage] = useState({ type: '', text: '' });

  const change = (event) => setForm({ ...form, [event.target.name]: event.target.value });

  const submit = async (event) => {
    event.preventDefault();
    try {
      await educationCompletionService.register(form);
      setForm(initialForm);
      setMessage({ type: 'success', text: '교육이수 결과가 등록되었습니다.' });
    } catch (error) {
      setMessage({ type: 'error', text: error.message });
    }
  };

  return (
    <div className="card">
      <div className="card-title">교육이수 결과 등록</div>
      {message.text && <div className={`alert alert-${message.type}`}>{message.text}</div>}
      <form onSubmit={submit}>
        <div className="form-grid">
          <div className="form-group"><label>교육이수결과 ID <span className="required">*</span></label><input className="form-control" name="completionResultId" value={form.completionResultId} onChange={change} required /></div>
          <div className="form-group"><label>연구활동종사자 ID <span className="required">*</span></label><input className="form-control" name="researcherId" value={form.researcherId} onChange={change} required /></div>
          <div className="form-group"><label>교육개설정보 ID <span className="required">*</span></label><input className="form-control" name="openingId" value={form.openingId} onChange={change} required /></div>
          <div className="form-group"><label>학습결과 ID</label><input className="form-control" name="learningResultId" value={form.learningResultId} onChange={change} placeholder="없으면 비워두세요" /></div>
          <div className="form-group"><label>안전교육일지 ID</label><input className="form-control" name="logId" value={form.logId} onChange={change} placeholder="없으면 비워두세요" /></div>
          <div className="form-group"><label>이수일 <span className="required">*</span></label><input className="form-control" type="date" name="completionDate" value={form.completionDate} onChange={change} required /></div>
          <div className="form-group"><label>인정시간 <span className="required">*</span></label><input className="form-control" type="number" min="1" name="recognizedHours" value={form.recognizedHours} onChange={change} required /></div>
          <div className="form-group"><label>이수 상태</label><select className="form-control" name="completionStatus" value={form.completionStatus} onChange={change}><option>이수</option><option>미이수</option><option>진행중</option></select></div>
          <div className="form-group"><label>수동 등록 여부</label><select className="form-control" name="manualRegistration" value={form.manualRegistration} onChange={change}><option value="Y">Y</option><option value="N">N</option></select></div>
        </div>
        <div className="form-actions">
          <button type="button" className="btn btn-secondary" onClick={() => setForm(initialForm)}>초기화</button>
          <button type="submit" className="btn btn-primary">등록</button>
        </div>
      </form>
    </div>
  );
}
