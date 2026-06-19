import { createRestService } from '../../../shared/api/createRestService';

export const checklistService = createRestService('/checklist-items', {
  serialize: (item) => ({
    ...item,
    score: Number(item.score),
    requiredYn: item.requiredYn === true || item.requiredYn === 'true',
    useYn: item.useYn === true || item.useYn === 'true',
  }),
});
