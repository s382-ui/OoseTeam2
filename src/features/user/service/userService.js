import { createCrudService } from '../../../shared/service/createCrudService';
import { createUser } from '../domain/User';
import { userRepository } from '../infrastructure/userRepository';

export const userService = createCrudService({
  repository: userRepository,
  createEntity: createUser,
  idField: 'userId',
});
