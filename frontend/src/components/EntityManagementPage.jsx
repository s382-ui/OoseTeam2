import React, { useMemo, useState } from 'react';
import { useEntityCollection } from '../shared/hooks/useEntityCollection';

function displayValue(value) {
  if (typeof value === 'boolean') return value ? 'Y' : 'N';
  return String(value ?? '');
}

export default function EntityManagementPage({
  title,
  registerTitle,
  inquiryTitle,
  initialForm,
  idField,
  fields,
  columns,
  searchFields = [],
  searchFilters,
  service,
  copyable = false,
  bulkRegistration,
}) {
  const filters = searchFilters ?? searchFields.map((name) => ({
    name,
    label: fields.find((field) => field.name === name)?.label ?? name,
  }));
  const [tab, setTab] = useState('register');
  const [form, setForm] = useState(initialForm);
  const [search, setSearch] = useState({});
  const [notice, setNotice] = useState('');
  const [selected, setSelected] = useState(null);
  const [bulkFile, setBulkFile] = useState(null);
  const [bulkRows, setBulkRows] = useState([]);
  const [bulkError, setBulkError] = useState('');
  const { data, loading, error, setError, register, refresh } = useEntityCollection(service);

  const filtered = useMemo(() => data.filter((record) =>
    filters.every((filter) => {
      const condition = String(search[filter.name] ?? '').trim().toLowerCase();
      if (!condition) return true;
      const value = displayValue(record[filter.name]).toLowerCase();
      return filter.exact ? value === condition : value.includes(condition);
    })
  ), [data, filters, search]);

  const changeForm = (field, value) => {
    setForm((current) => ({ ...current, [field.name]: value }));
  };

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

  const copyRecord = (record) => {
    const copied = Object.keys(initialForm).reduce((result, key) => ({
      ...result,
      [key]: key === idField ? '' : (record[key] ?? initialForm[key]),
    }), {});
    setForm(copied);
    setNotice('선택한 항목을 복사했습니다. 새 ID를 입력해 등록하세요.');
    setTab('register');
  };

  const selectBulkFile = async (file) => {
    setBulkFile(file);
    setBulkRows([]);
    setBulkError('');
    if (!file) return;
    try {
      setBulkRows(await bulkRegistration.parse(file));
    } catch (parseError) {
      setBulkError(parseError.message);
    }
  };

  const submitBulk = async () => {
    try {
      await bulkRegistration.submit(bulkRows);
      await refresh();
      setBulkFile(null);
      setBulkRows([]);
      setBulkError('');
      setNotice(`${bulkRows.length}명의 사용자 등록이 완료되었습니다.`);
      setTab('inquiry');
    } catch (submitError) {
      setBulkError(submitError.message);
    }
  };

  const resetSearch = () => {
    setSearch({});
    setSelected(null);
  };

  return (
    <div>
      <h2 className="page-title">{title}</h2>
      <div className="tabs">
        <button className={`tab-btn ${tab === 'register' ? 'active' : ''}`} onClick={() => setTab('register')}>등록</button>
        {bulkRegistration && (
          <button className={`tab-btn ${tab === 'bulk' ? 'active' : ''}`} onClick={() => setTab('bulk')}>대량 등록</button>
        )}
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
                <div className={`form-group ${field.fullWidth ? 'full-width' : ''}`} key={field.name}>
                  <label>
                    {field.label}
                    {field.required && <span className="required"> *</span>}
                  </label>
                  {field.options ? (
                    <select
                      className="form-control"
                      value={String(form[field.name])}
                      onChange={(event) => changeForm(field, event.target.value)}
                    >
                      {field.options.map((option) => (
                        <option key={String(option.value ?? option)} value={String(option.value ?? option)}>
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
                      onChange={(event) => changeForm(field, event.target.value)}
                    />
                  )}
                  {field.helpText && <span className="field-help">{field.helpText}</span>}
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

      {tab === 'bulk' && bulkRegistration && (
        <div className="card">
          <div className="card-title">{bulkRegistration.title}</div>
          <p className="card-description">{bulkRegistration.description}</p>
          {bulkError && <div className="alert alert-error">{bulkError}</div>}
          <div className="upload-panel">
            <input
              className="form-control"
              type="file"
              accept=".csv,text/csv"
              onChange={(event) => selectBulkFile(event.target.files[0])}
            />
            <button type="button" className="btn btn-secondary" onClick={bulkRegistration.downloadTemplate}>CSV 양식 다운로드</button>
          </div>
          {bulkFile && !bulkError && (
            <div className="alert alert-success">{bulkFile.name}: {bulkRows.length}행 검증 완료</div>
          )}
          {bulkRows.length > 0 && (
            <div className="table-wrapper compact-table">
              <table>
                <thead><tr>{bulkRegistration.previewColumns.map((column) => <th key={column.key}>{column.label}</th>)}</tr></thead>
                <tbody>
                  {bulkRows.slice(0, 5).map((row, index) => (
                    <tr key={`${row[idField]}-${index}`}>
                      {bulkRegistration.previewColumns.map((column) => <td key={column.key}>{displayValue(row[column.key])}</td>)}
                    </tr>
                  ))}
                </tbody>
              </table>
              {bulkRows.length > 5 && <p className="preview-note">앞의 5행만 미리 표시합니다.</p>}
            </div>
          )}
          <div className="form-actions">
            <button type="button" className="btn btn-secondary" onClick={() => selectBulkFile(null)}>초기화</button>
            <button type="button" className="btn btn-primary" disabled={!bulkRows.length || Boolean(bulkError)} onClick={submitBulk}>일괄 등록</button>
          </div>
        </div>
      )}

      {tab === 'inquiry' && (
        <>
          <div className="card">
            <div className="card-title">{inquiryTitle}</div>
            {notice && <div className="alert alert-success">{notice}</div>}
            {error && <div className="alert alert-error">{error}</div>}
            <div className="search-grid">
              {filters.map((filter) => (
                <div className="form-group" key={filter.name}>
                  <label>{filter.label}</label>
                  {filter.options ? (
                    <select
                      className="form-control"
                      value={search[filter.name] ?? ''}
                      onChange={(event) => setSearch({ ...search, [filter.name]: event.target.value })}
                    >
                      <option value="">전체</option>
                      {filter.options.map((option) => (
                        <option key={String(option.value ?? option)} value={String(option.value ?? option)}>
                          {option.label ?? option}
                        </option>
                      ))}
                    </select>
                  ) : (
                    <input
                      className="form-control"
                      value={search[filter.name] ?? ''}
                      placeholder={`${filter.label} 검색`}
                      onChange={(event) => setSearch({ ...search, [filter.name]: event.target.value })}
                    />
                  )}
                </div>
              ))}
              <div className="search-actions">
                <button type="button" className="btn btn-secondary" onClick={resetSearch}>필터 초기화</button>
              </div>
            </div>
            <div className="result-summary">조회 결과 {filtered.length}건</div>
            <div className="table-wrapper">
              <table>
                <thead>
                  <tr>
                    {columns.map((column) => <th key={column.key}>{column.label}</th>)}
                    <th>기능</th>
                  </tr>
                </thead>
                <tbody>
                  {loading ? (
                    <tr><td colSpan={columns.length + 1} className="empty-state">데이터를 불러오는 중입니다.</td></tr>
                  ) : filtered.length === 0 ? (
                    <tr><td colSpan={columns.length + 1} className="empty-state">조회된 데이터가 없습니다.</td></tr>
                  ) : filtered.map((record) => (
                    <tr key={record[idField]} className={selected?.[idField] === record[idField] ? 'selected-row' : ''}>
                      {columns.map((column) => (
                        <td key={column.key}>
                          {column.render ? column.render(record[column.key], record) : displayValue(record[column.key])}
                        </td>
                      ))}
                      <td>
                        <div className="row-actions">
                          <button className="btn btn-secondary btn-sm" onClick={() => setSelected(record)}>상세</button>
                          {copyable && <button className="btn btn-secondary btn-sm" onClick={() => copyRecord(record)}>복사</button>}
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>

          {selected && (
            <div className="card detail-card">
              <div className="card-title">상세 정보</div>
              <div className="detail-grid">
                {fields.map((field) => (
                  <div className="detail-item" key={field.name}>
                    <span>{field.label}</span>
                    <strong>{displayValue(selected[field.name]) || '-'}</strong>
                  </div>
                ))}
              </div>
            </div>
          )}
        </>
      )}
    </div>
  );
}
