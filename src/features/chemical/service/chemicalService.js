import { createCrudService } from '../../../shared/service/createCrudService';
import { createChemical } from '../domain/Chemical';
import { chemicalRepository } from '../infrastructure/chemicalRepository';

export const chemicalService = createCrudService({
  repository: chemicalRepository,
  createEntity: createChemical,
  idField: 'chemicalId',
});
