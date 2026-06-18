import { createEntityRepository } from '../../../shared/infrastructure/createEntityRepository';

export const inspectionRepository = createEntityRepository({
  storageKey: 'oose.inspection.categories',
  initialDataUrl: '/data/inspection/inspectionCategories.json',
  idField: 'inspectionCategoryId',
});
