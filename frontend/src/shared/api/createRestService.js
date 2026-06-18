import { apiRequest } from './httpClient';

export function createRestService(endpoint) {
  return {
    list: () => apiRequest(endpoint),
    register: (entity) => apiRequest(endpoint, {
      method: 'POST',
      body: JSON.stringify(entity),
    }),
    remove: (id) => apiRequest(`${endpoint}/${encodeURIComponent(id)}`, {
      method: 'DELETE',
    }),
  };
}
