package com.oose.labsafety;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Path;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LabSafetyApiIntegrationTest {
    private static final Path TEST_DATA_DIRECTORY = Path.of(
            System.getProperty("java.io.tmpdir"),
            "labsafety-test-" + UUID.randomUUID());

    @DynamicPropertySource
    static void configureDataDirectory(DynamicPropertyRegistry registry) {
        registry.add("labsafety.data-directory", TEST_DATA_DIRECTORY::toString);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loadsInitialUsersFromJson() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value("admin001"));
    }

    @Test
    void loadsSddChemicalFieldsFromJson() throws Exception {
        mockMvc.perform(get("/api/chemicals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].manufacturerName").value("시연화학"))
                .andExpect(jsonPath("$[0].contentRate").value("99.5%"))
                .andExpect(jsonPath("$[0].createdAt").value("2026-01-10"));
    }

    @Test
    void registersUsersInBulk() throws Exception {
        mockMvc.perform(post("/api/users/bulk")
                        .contentType("application/json")
                        .content("""
                                [
                                  {
                                    "userId": "bulk001",
                                    "userName": "대량등록1",
                                    "department": "안전공학과",
                                    "role": "연구원",
                                    "contact": "010-1000-1000",
                                    "email": "bulk1@example.com",
                                    "accountStatus": "활성"
                                  },
                                  {
                                    "userId": "bulk002",
                                    "userName": "대량등록2",
                                    "department": "안전공학과",
                                    "role": "대학원생",
                                    "contact": "010-2000-2000",
                                    "email": "bulk2@example.com",
                                    "accountStatus": "활성"
                                  }
                                ]
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].userId").value("bulk001"))
                .andExpect(jsonPath("$[1].userId").value("bulk002"));
    }

    @Test
    void rejectsDuplicatedIdsInBulkUserRequest() throws Exception {
        mockMvc.perform(post("/api/users/bulk")
                        .contentType("application/json")
                        .content("""
                                [
                                  {
                                    "userId": "bulk-duplicate",
                                    "userName": "중복1",
                                    "department": "안전공학과",
                                    "role": "연구원",
                                    "contact": "010-3000-3000",
                                    "accountStatus": "활성"
                                  },
                                  {
                                    "userId": "bulk-duplicate",
                                    "userName": "중복2",
                                    "department": "안전공학과",
                                    "role": "연구원",
                                    "contact": "010-4000-4000",
                                    "accountStatus": "활성"
                                  }
                                ]
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("업로드 파일에 중복된 사용자 ID가 있습니다: bulk-duplicate"));
    }

    @Test
    void rejectsLaboratoryForUnknownManager() throws Exception {
        mockMvc.perform(post("/api/laboratories")
                        .contentType("application/json")
                        .content("""
                                {
                                  "labId": "LAB-INVALID",
                                  "labName": "검증 연구실",
                                  "buildingName": "공학관",
                                  "floor": "9",
                                  "roomNo": "901",
                                  "departmentName": "안전공학과",
                                  "managerId": "missing-user",
                                  "managerName": "없는 사용자",
                                  "contactNo": "02-0000-0000",
                                  "managementGrade": "A",
                                  "isActive": "Y"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("등록되지 않은 연구실 책임자 ID입니다: missing-user"));
    }

    @Test
    void rejectsDuplicatedChemicalCasNumber() throws Exception {
        mockMvc.perform(post("/api/chemicals")
                        .contentType("application/json")
                        .content("""
                                {
                                  "chemicalId": "CHEM-DUPLICATE",
                                  "manufacturerName": "다른 제조사",
                                  "chemicalName": "에탄올 중복",
                                  "casNumber": "64-17-5",
                                  "contentRate": "95%",
                                  "status": "ACTIVE"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("이미 등록된 CAS 번호입니다: 64-17-5"));
    }

    @Test
    void rejectsCompletionResultForUnknownResearcher() throws Exception {
        mockMvc.perform(post("/api/education/completion-results")
                        .contentType("application/json")
                        .content("""
                                {
                                  "completionResultId": "ECR-INVALID",
                                  "researcherId": "missing-user",
                                  "openingId": "EO001",
                                  "completionDate": "2026-06-18",
                                  "recognizedHours": 3,
                                  "completionStatus": "이수",
                                  "manualRegistration": true
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("등록되지 않은 연구활동종사자 ID입니다: missing-user"));
    }
}
