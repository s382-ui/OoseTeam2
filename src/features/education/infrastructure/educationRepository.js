import { createEntityRepository } from '../../../shared/infrastructure/createEntityRepository';
import { userRepository } from '../../user/infrastructure/userRepository';

export const researcherCategoryRepository = createEntityRepository({
  storageKey: 'oose.education.researcherCategories',
  initialDataUrl: '/data/education/researcherCategories.json',
  idField: 'researcherCategoryId',
});

export const completionStandardRepository = createEntityRepository({
  storageKey: 'oose.education.completionStandards',
  initialDataUrl: '/data/education/completionStandards.json',
  idField: 'completionStandardId',
});

export const educationCourseRepository = createEntityRepository({
  storageKey: 'oose.education.educationCourses',
  initialDataUrl: '/data/education/educationCourses.json',
  idField: 'courseId',
});

export const educationOpeningRepository = createEntityRepository({
  storageKey: 'oose.education.educationOpenings',
  initialDataUrl: '/data/education/educationOpenings.json',
  idField: 'openingId',
});

export const educationCompletionResultRepository = createEntityRepository({
  storageKey: 'oose.education.completionResults',
  initialDataUrl: '/data/education/educationCompletionResults.json',
  idField: 'completionResultId',
});

export const educationReferenceRepository = {
  findResearcherById: (id) => userRepository.findById(id),
  findOpeningById: (id) => educationOpeningRepository.findById(id),
  findCourseById: (id) => educationCourseRepository.findById(id),
};
