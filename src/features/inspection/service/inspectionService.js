import { createCrudService } from '../../../shared/service/createCrudService';
import { createInspectionCategory } from '../domain/InspectionCategory';
import { inspectionRepository } from '../infrastructure/inspectionRepository';

export const inspectionService = createCrudService({
  repository: inspectionRepository,
  createEntity: createInspectionCategory,
  idField: 'inspectionCategoryId',
});
