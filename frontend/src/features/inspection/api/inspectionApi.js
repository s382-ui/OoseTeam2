import { createRestService } from '../../../shared/api/createRestService';

export const inspectionService = createRestService('/inspection-categories', {
  serialize: (category) => ({
    ...category,
    useYn: category.useYn === true || category.useYn === 'true',
  }),
});
