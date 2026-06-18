export async function loadJsonData(url) {
  const response = await fetch(url);
  if (!response.ok) {
    throw new Error(`초기 데이터 파일을 불러올 수 없습니다: ${url}`);
  }

  const data = await response.json();
  if (!Array.isArray(data)) {
    throw new Error(`초기 데이터는 배열이어야 합니다: ${url}`);
  }
  return data;
}
