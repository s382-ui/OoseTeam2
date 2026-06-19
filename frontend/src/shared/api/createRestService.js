import { apiRequest } from './httpClient';

export function createRestService(endpoint, { serialize = (entity) => entity } = {}) {
  return {
    list: () => apiRequest(endpoint),
    register: (entity) => apiRequest(endpoint, {
      method: 'POST',
      body: JSON.stringify(serialize(entity)),
    }),
    remove: (id) => apiRequest(`${endpoint}/${encodeURIComponent(id)}`, {
      method: 'DELETE',
    }),
  };
}
