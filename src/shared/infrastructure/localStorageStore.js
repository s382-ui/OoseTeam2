export const localStorageStore = {
  has(key) {
    return localStorage.getItem(key) !== null;
  },

  read(key) {
    const value = localStorage.getItem(key);
    if (value === null) return [];

    try {
      const parsed = JSON.parse(value);
      return Array.isArray(parsed) ? parsed : [];
    } catch {
      throw new Error(`저장된 데이터(${key})를 읽을 수 없습니다.`);
    }
  },

  write(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
  },
};
