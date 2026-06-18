import { createCrudService } from '../../../shared/service/createCrudService';
import { createChecklistItem } from '../domain/ChecklistItem';
import { checklistRepository } from '../infrastructure/checklistRepository';

export const checklistService = createCrudService({
  repository: checklistRepository,
  createEntity: createChecklistItem,
  idField: 'itemId',
});
