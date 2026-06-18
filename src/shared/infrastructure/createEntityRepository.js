import { loadJsonData } from './jsonDataLoader';
import { localStorageStore } from './localStorageStore';

export function createEntityRepository({ storageKey, initialDataUrl, idField }) {
  async function initialize() {
    if (localStorageStore.has(storageKey)) return;
    const initialData = await loadJsonData(initialDataUrl);
    localStorageStore.write(storageKey, initialData);
  }

  return {
    async findAll() {
      await initialize();
      return localStorageStore.read(storageKey);
    },

    async findById(id) {
      const records = await this.findAll();
      return records.find((record) => record[idField] === id) ?? null;
    },

    async save(entity) {
      const records = await this.findAll();
      localStorageStore.write(storageKey, [entity, ...records]);
      return entity;
    },

    async remove(id) {
      const records = await this.findAll();
      localStorageStore.write(
        storageKey,
        records.filter((record) => record[idField] !== id)
      );
    },
  };
}
