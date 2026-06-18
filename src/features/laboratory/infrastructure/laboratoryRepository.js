import { createEntityRepository } from '../../../shared/infrastructure/createEntityRepository';

export const laboratoryRepository = createEntityRepository({
  storageKey: 'oose.laboratory.laboratories',
  initialDataUrl: '/data/laboratory/laboratories.json',
  idField: 'labId',
});
