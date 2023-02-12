package es.iesrafaelalberti.clasesspring2223;

import es.iesrafaelalberti.clasesspring2223.repositories.CellRepository;
import es.iesrafaelalberti.clasesspring2223.repositories.PrisonerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClasesSpring2223ApplicationTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    PrisonerRepository prisonerRepository;
    @Autowired
    CellRepository cellRepository;

    @Test
    void contextLoads() {
        assert cellRepository.count() == 18;
        assert prisonerRepository.count() == 8;
    }


    @Test
    void listTest() throws Exception {
        mvc.perform(get("/prisoners/").contentType(MediaType.APPLICATION_JSON))
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
        mvc.perform(get("/prisoners/1/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("bob"))
                .andExpect(jsonPath("$.age").value(19))
                .andExpect(jsonPath("$.yearsLeft").value(23));
        // test result
    }
    @Test
    void updateTest() throws Exception {
        // get test prisoner (method get on url /prisoners/1/)
        // create test prisoner
        String testPrisoner = "{\"name\": \"lewis\", \"age\": 24, \"yearsLeft\": 6}";
        // modify attributes
        // method put on url /prisoners/1/
        mvc.perform(put("/prisoners/2/")
                        .contentType(MediaType.APPLICATION_JSON)
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
        mvc.perform(delete("/prisoners/1/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // test result
        // test number of prisoners is correct (-1)
        assert prisonerRepository.count() == prisonerCount - 1;
    }
}
