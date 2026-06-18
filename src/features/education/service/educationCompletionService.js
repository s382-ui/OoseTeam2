import { createEducationCompletionResult } from '../domain/EducationCompletionResult';
import {
  educationCompletionResultRepository,
  educationReferenceRepository,
} from '../infrastructure/educationRepository';

export const educationCompletionService = {
  async register(input) {
    const result = createEducationCompletionResult(input);

    if (await educationCompletionResultRepository.findById(result.completionResultId)) {
      throw new Error(`이미 사용 중인 이수결과 ID입니다: ${result.completionResultId}`);
    }
    if (!(await educationReferenceRepository.findResearcherById(result.researcherId))) {
      throw new Error(`등록되지 않은 연구활동종사자 ID입니다: ${result.researcherId}`);
    }
    if (!(await educationReferenceRepository.findOpeningById(result.openingId))) {
      throw new Error(`등록되지 않은 교육개설정보 ID입니다: ${result.openingId}`);
    }

    await educationCompletionResultRepository.save(result);
  },

  async list() {
    const results = await educationCompletionResultRepository.findAll();
    return Promise.all(results.map(async (result) => {
      const researcher = await educationReferenceRepository.findResearcherById(result.researcherId);
      const opening = await educationReferenceRepository.findOpeningById(result.openingId);
      const course = opening
        ? await educationReferenceRepository.findCourseById(opening.courseId)
        : null;

      return {
        ...result,
        researcherName: researcher?.userName ?? '미등록 사용자',
        courseName: course?.courseName ?? '미등록 과정',
        educationType: course?.educationType ?? '',
      };
    }));
  },
};
