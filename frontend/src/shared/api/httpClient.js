const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

export async function apiRequest(path, options = {}) {
  const response = await fetch(`${API_BASE_URL}${path}`, {
    ...options,
    headers: {
      'Content-Type': 'application/json',
      ...options.headers,
    },
  });

  if (response.status === 204) return null;

  const body = await response.json().catch(() => ({}));
  if (!response.ok) {
    throw new Error(body.message || '서버 요청을 처리할 수 없습니다.');
  }
  return body;
}
