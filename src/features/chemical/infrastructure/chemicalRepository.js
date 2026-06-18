import { createEntityRepository } from '../../../shared/infrastructure/createEntityRepository';

export const chemicalRepository = createEntityRepository({
  storageKey: 'oose.chemical.chemicals',
  initialDataUrl: '/data/chemical/chemicals.json',
  idField: 'chemicalId',
});
