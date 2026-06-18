import { createEntityRepository } from '../../../shared/infrastructure/createEntityRepository';

export const checklistRepository = createEntityRepository({
  storageKey: 'oose.inspection.checklistItems',
  initialDataUrl: '/data/inspection/checklistItems.json',
  idField: 'itemId',
});
