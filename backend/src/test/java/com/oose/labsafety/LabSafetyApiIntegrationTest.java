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
