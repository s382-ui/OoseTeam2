import { createEntityRepository } from '../../../shared/infrastructure/createEntityRepository';

export const wasteRepository = createEntityRepository({
  storageKey: 'oose.waste.wasteCategories',
  initialDataUrl: '/data/waste/wasteCategories.json',
  idField: 'categoryId',
});
