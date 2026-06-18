import React, { useMemo, useState } from 'react';
import { useEntityCollection } from '../shared/hooks/useEntityCollection';

export default function EntityManagementPage({
  title,
  registerTitle,
  inquiryTitle,
  initialForm,
  idField,
  fields,
  columns,
  searchFields,
  service,
}) {
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState(initialForm);
  const [search, setSearch] = useState('');
  const [notice, setNotice] = useState('');
  const { data, loading, error, setError, register, remove } = useEntityCollection(service);

  const filtered = useMemo(() => {
    const keyword = search.trim().toLowerCase();
    if (!keyword) return data;
    return data.filter((record) =>
      searchFields.some((field) =>
        String(record[field] ?? '').toLowerCase().includes(keyword)
      )
    );
  }, [data, search, searchFields]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await register(form);
      setForm(initialForm);
      setError('');
      setNotice('등록이 완료되었습니다.');
    } catch (submitError) {
      setNotice('');
      setError(submitError.message);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm('선택한 데이터를 삭제하시겠습니까?')) return;
    try {
      await remove(id);
    } catch (deleteError) {
      setError(deleteError.message);
    }
  };

  return (
    <div>
      <h2 className="page-title">{title}</h2>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>등록</button>
        <button className={`tab-btn ${tab === 'inquiry' ? 'active' : ''}`} onClick={() => setTab('inquiry')}>조회</button>
      </div>

      {tab === 'register' && (
        <div className="card">
          <div className="card-title">{registerTitle}</div>
          {notice && <div className="alert alert-success">{notice}</div>}
          {error && <div className="alert alert-error">{error}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-grid">
              {fields.map((field) => (
                <div className="form-group" key={field.name}>
                  <label>
                    {field.label}
                    {field.required && <span className="required"> *</span>}
                  </label>
                  {field.options ? (
                    <select
                      className="form-control"
                      value={form[field.name]}
                      onChange={(event) => setForm({ ...form, [field.name]: event.target.value })}
                    >
                      {field.options.map((option) => (
                        <option key={option.value ?? option} value={option.value ?? option}>
                          {option.label ?? option}
                        </option>
                      ))}
                    </select>
                  ) : (
                    <input
                      className="form-control"
                      type={field.type ?? 'text'}
                      value={form[field.name]}
                      placeholder={field.placeholder ?? field.label}
                      min={field.min}
                      max={field.max}
                      required={field.required}
                      onChange={(event) => setForm({ ...form, [field.name]: event.target.value })}
                    />
                  )}
                </div>
              ))}
            </div>
            <div className="form-actions">
              <button type="button" className="btn btn-secondary" onClick={() => setForm(initialForm)}>초기화</button>
              <button type="submit" className="btn btn-primary">등록</button>
            </div>
          </form>
        </div>
      )}

      {tab === 'inquiry' && (
        <div className="card">
          <div className="card-title">{inquiryTitle}</div>
          {error && <div className="alert alert-error">{error}</div>}
          <div className="search-bar">
            <input className="form-control" value={search} onChange={(event) => setSearch(event.target.value)} placeholder="검색어를 입력하세요" />
          </div>
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  {columns.map((column) => <th key={column.key}>{column.label}</th>)}
                  <th>관리</th>
                </tr>
              </thead>
              <tbody>
                {loading ? (
                  <tr><td colSpan={columns.length + 1} className="empty-state">데이터를 불러오는 중입니다.</td></tr>
                ) : filtered.length === 0 ? (
                  <tr><td colSpan={columns.length + 1} className="empty-state">등록된 데이터가 없습니다.</td></tr>
                ) : filtered.map((record) => (
                  <tr key={record[idField]}>
                    {columns.map((column) => (
                      <td key={column.key}>
                        {column.render ? column.render(record[column.key], record) : String(record[column.key] ?? '')}
                      </td>
                    ))}
                    <td><button className="btn btn-danger btn-sm" onClick={() => handleDelete(record[idField])}>삭제</button></td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
}
