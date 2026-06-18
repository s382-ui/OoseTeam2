export function createEntity(input, schema) {
  const entity = {};

  Object.entries(schema).forEach(([field, rule]) => {
    const rawValue = input[field];
    const value = typeof rawValue === 'string' ? rawValue.trim() : rawValue;

    if (rule.required && (value === '' || value === null || value === undefined)) {
      throw new Error(`${rule.label}은(는) 필수입니다.`);
    }

    if (rule.type === 'number' && value !== '' && value !== null && value !== undefined) {
      const numberValue = Number(value);
      if (!Number.isFinite(numberValue)) {
        throw new Error(`${rule.label}은(는) 숫자여야 합니다.`);
      }
      if (rule.min !== undefined && numberValue < rule.min) {
        throw new Error(`${rule.label}은(는) ${rule.min} 이상이어야 합니다.`);
      }
      entity[field] = numberValue;
      return;
    }

    if (rule.type === 'boolean') {
      entity[field] = value === true || value === 'true' || value === 'Y' || value === '사용';
      return;
    }

    entity[field] = value ?? '';
  });

  return entity;
}
