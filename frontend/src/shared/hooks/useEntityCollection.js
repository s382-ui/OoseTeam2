import { useCallback, useEffect, useState } from 'react';

export function useEntityCollection(service) {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const refresh = useCallback(async () => {
    setLoading(true);
    try {
      setData(await service.list());
      setError('');
    } catch (requestError) {
      setError(requestError.message);
    } finally {
      setLoading(false);
    }
  }, [service]);

  useEffect(() => {
    refresh();
  }, [refresh]);

  const register = async (form) => {
    await service.register(form);
    await refresh();
  };

  const remove = async (id) => {
    await service.remove(id);
    await refresh();
  };

  return { data, loading, error, setError, register, remove, refresh };
}
