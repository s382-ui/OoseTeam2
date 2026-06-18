export function createCrudService({ repository, createEntity, idField }) {
  return {
    async list() {
      return repository.findAll();
    },

    async register(input) {
      const entity = createEntity(input);
      if (await repository.findById(entity[idField])) {
        throw new Error(`이미 사용 중인 ID입니다: ${entity[idField]}`);
      }
      return repository.save(entity);
    },

    async remove(id) {
      if (!(await repository.findById(id))) {
        throw new Error(`삭제할 데이터를 찾을 수 없습니다: ${id}`);
      }
      await repository.remove(id);
    },
  };
}
