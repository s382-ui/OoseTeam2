const USER_HEADERS = [
  'userId',
  'userName',
  'department',
  'role',
  'contact',
  'email',
  'accountStatus',
];

function parseLine(line) {
  const values = [];
  let value = '';
  let quoted = false;

  for (let index = 0; index < line.length; index += 1) {
    const character = line[index];
    if (character === '"') {
      if (quoted && line[index + 1] === '"') {
        value += '"';
        index += 1;
      } else {
        quoted = !quoted;
      }
    } else if (character === ',' && !quoted) {
      values.push(value.trim());
      value = '';
    } else {
      value += character;
    }
  }
  values.push(value.trim());
  return values;
}

export async function parseUserCsv(file) {
  const text = (await file.text()).replace(/^\uFEFF/, '');
  const lines = text.split(/\r?\n/).filter((line) => line.trim());
  if (lines.length < 2) throw new Error('헤더와 사용자 데이터가 포함된 CSV 파일이 필요합니다.');

  const headers = parseLine(lines[0]);
  const missingHeaders = USER_HEADERS.filter((header) => !headers.includes(header));
  if (missingHeaders.length) {
    throw new Error(`필수 열이 없습니다: ${missingHeaders.join(', ')}`);
  }

  return lines.slice(1).map((line, index) => {
    const values = parseLine(line);
    const row = Object.fromEntries(headers.map((header, columnIndex) => [header, values[columnIndex] ?? '']));
    const missingValues = ['userId', 'userName', 'department', 'role', 'contact', 'accountStatus']
      .filter((header) => !row[header]);
    if (missingValues.length) {
      throw new Error(`${index + 2}행의 필수값이 누락되었습니다: ${missingValues.join(', ')}`);
    }
    return row;
  });
}

export function downloadUserCsvTemplate() {
  const example = ['20260001', '홍길동', '화학공학과', '연구원', '010-1234-5678', 'hong@example.com', '활성'];
  const blob = new Blob([`\uFEFF${USER_HEADERS.join(',')}\r\n${example.join(',')}\r\n`], {
    type: 'text/csv;charset=utf-8',
  });
  const url = URL.createObjectURL(blob);
  const anchor = document.createElement('a');
  anchor.href = url;
  anchor.download = 'user-bulk-template.csv';
  anchor.click();
  URL.revokeObjectURL(url);
}
