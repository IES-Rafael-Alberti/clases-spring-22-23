package es.iesrafaelalberti.clasesspring2223;

import es.iesrafaelalberti.clasesspring2223.repositories.CellRepository;
import es.iesrafaelalberti.clasesspring2223.repositories.PrisonerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class ClasesSpring2223ApplicationTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    PrisonerRepository prisonerRepository;
    @Autowired
    CellRepository cellRepository;

    String tokenAdmin;
    @BeforeAll
    void getToken() throws Exception {
        tokenAdmin = authenticate("javier", "pestillo");
    }

    @Test
    void contextLoads() {
        //assert cellRepository.count() == 2090;
        //assert prisonerRepository.count() == 10568;
    }


    String authenticate(String user, String password) throws Exception {
        MvcResult result = mvc.perform(post("/token")
                                            .with(httpBasic(user, password)))
                                        .andExpect(status().isOk())
                                        .andReturn();

        return result.getResponse().getContentAsString();
    }

    @Test
    void securityTest() throws Exception {
        // no auth
        mvc.perform(post("/prisoners/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        // get user with no ADMIN role
        String tokenUser = authenticate("farra", "farra");
        // test prisoner
        String testPrisoner = "{\"name\": \"lewis\", \"age\": 24, \"yearsLeft\": 6}";
        // user is not ADMIN
        mvc.perform(post("/prisoners/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + tokenUser)
                        .content(testPrisoner))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void listTest() throws Exception {
        mvc.perform(get("/prisoners/")
                           .contentType(MediaType.APPLICATION_JSON)
                           .header("Authorization", "Bearer " + tokenAdmin))
                       .andExpect(status().isOk())
                       .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                       .andExpect(jsonPath("$[0].name").value("bob"))
                       .andExpect(jsonPath("$[0].age").value(19))
                       .andExpect(jsonPath("$[0].yearsLeft").value(23));
    }

    @Test
    void creationTest() throws Exception {
        // HACK: stores repository count (for execution alone)
        long prisonerCount = prisonerRepository.count();
        // create test prisoner
        String testPrisoner = "{\"name\": \"lewis\", \"age\": 24, \"yearsLeft\": 6}";
        // method post on url /prisoners/
        mvc.perform(post("/prisoners/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + tokenAdmin)
                        .content(testPrisoner))
                .andExpect(status().isOk()) // test result
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("lewis"))
                .andExpect(jsonPath("$.age").value(24))
                .andExpect(jsonPath("$.yearsLeft").value(6));
        // test number of prisoners is correct (+1)
        assert prisonerRepository.count() == prisonerCount + 1;
    }
    @Test
    void detailTest() throws Exception {
        // get test prisoner (method get on url /prisoners/1/)
        mvc.perform(get("/prisoners/1/").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("bob"))
                .andExpect(jsonPath("$.age").value(19))
                .andExpect(jsonPath("$.yearsLeft").value(23));
        // test result
    }
    @Test
    void updateTest() throws Exception {
        // get test prisoner (method get on url /prisoners/2/)
        // create test prisoner
        String testPrisoner = "{\"name\": \"lewis\", \"age\": 24, \"yearsLeft\": 6}";
        // modify attributes
        // method put on url /prisoners/2/
        mvc.perform(put("/prisoners/2/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + tokenAdmin)
                        .content(testPrisoner))
                    .andExpect(status().isOk()) // test result
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.name").value("lewis"))
                    .andExpect(jsonPath("$.age").value(24))
                    .andExpect(jsonPath("$.yearsLeft").value(6));
        // test result
    }
    @Test
    void deleteTest() throws Exception {
        // HACK: stores repository count (for execution alone)
        long prisonerCount = prisonerRepository.count();
        // method delete on url /prisoners/1/
        mvc.perform(delete("/prisoners/3/").contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // test result
        // test number of prisoners is correct (-1)
        assert prisonerRepository.count() == prisonerCount - 1;
    }
}
