import { createRestService } from '../../../shared/api/createRestService';
import { apiRequest } from '../../../shared/api/httpClient';

export const userService = {
  ...createRestService('/users'),
  registerAll: (users) => apiRequest('/users/bulk', {
    method: 'POST',
    body: JSON.stringify(users),
  }),
};
