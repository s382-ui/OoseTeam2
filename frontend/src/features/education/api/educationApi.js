import { apiRequest } from '../../../shared/api/httpClient';

export const researcherStandardService = {
  list: () => apiRequest('/education/researcher-standards'),
  register: (form) => apiRequest('/education/researcher-standards', {
    method: 'POST',
    body: JSON.stringify({
      ...form,
      requiredHours: Number(form.requiredHours),
      active: form.active === 'Y',
    }),
  }),
};

export const educationCompletionService = {
  list: () => apiRequest('/education/completion-results'),
  register: (form) => apiRequest('/education/completion-results', {
    method: 'POST',
    body: JSON.stringify({
      ...form,
      recognizedHours: Number(form.recognizedHours),
      manualRegistration: form.manualRegistration === 'Y',
    }),
  }),
};
