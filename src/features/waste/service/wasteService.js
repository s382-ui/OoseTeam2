import { createCrudService } from '../../../shared/service/createCrudService';
import { createWasteCategory } from '../domain/WasteCategory';
import { wasteRepository } from '../infrastructure/wasteRepository';

export const wasteService = createCrudService({
  repository: wasteRepository,
  createEntity: createWasteCategory,
  idField: 'categoryId',
});
