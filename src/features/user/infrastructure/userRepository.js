import { createEntityRepository } from '../../../shared/infrastructure/createEntityRepository';

export const userRepository = createEntityRepository({
  storageKey: 'oose.user.users',
  initialDataUrl: '/data/user/users.json',
  idField: 'userId',
});
