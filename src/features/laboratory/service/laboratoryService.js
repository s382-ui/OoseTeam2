import { createCrudService } from '../../../shared/service/createCrudService';
import { createLaboratory } from '../domain/Laboratory';
import { laboratoryRepository } from '../infrastructure/laboratoryRepository';

export const laboratoryService = createCrudService({
  repository: laboratoryRepository,
  createEntity: createLaboratory,
  idField: 'labId',
});
