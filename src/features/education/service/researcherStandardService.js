import { createResearcherCategory } from '../domain/ResearcherCategory';
import { createCompletionStandard } from '../domain/CompletionStandard';
import {
  completionStandardRepository,
  researcherCategoryRepository,
} from '../infrastructure/educationRepository';

export const researcherStandardService = {
  async register(input) {
    const category = createResearcherCategory(input);
    const standard = createCompletionStandard({
      ...input,
      researcherCategoryId: category.researcherCategoryId,
    });

    if (await researcherCategoryRepository.findById(category.researcherCategoryId)) {
      throw new Error(`이미 사용 중인 분류 ID입니다: ${category.researcherCategoryId}`);
    }
    if (await completionStandardRepository.findById(standard.completionStandardId)) {
      throw new Error(`이미 사용 중인 이수기준 ID입니다: ${standard.completionStandardId}`);
    }

    await researcherCategoryRepository.save(category);
    await completionStandardRepository.save(standard);
  },

  async list() {
    const [categories, standards] = await Promise.all([
      researcherCategoryRepository.findAll(),
      completionStandardRepository.findAll(),
    ]);

    return standards.map((standard) => {
      const category = categories.find(
        (item) => item.researcherCategoryId === standard.researcherCategoryId
      );
      return { ...standard, categoryName: category?.categoryName ?? '미등록 분류', active: category?.active ?? false };
    });
  },
};
