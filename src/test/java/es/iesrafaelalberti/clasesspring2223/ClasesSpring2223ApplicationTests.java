package es.iesrafaelalberti.clasesspring2223;

import es.iesrafaelalberti.clasesspring2223.repositories.CellRepository;
import es.iesrafaelalberti.clasesspring2223.repositories.PrisonerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        assert prisonerRepository.count() == 7;
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
        // create test prisoner
        // method post on url /prisoners/
        // test result
        // test number of prisoners is correct (+1)
    }
    @Test
    void detailTest() throws Exception {
        // get test prisoner (method get on url /prisoners/1/)
        // test result
    }
    @Test
    void updateTest() throws Exception {
        // get test prisoner (method get on url /prisoners/1/)
        // modify attributes
        // method put on url /prisoners/1/
        // test result
    }
    @Test
    void deleteTest() throws Exception {
        // method delete on url /prisoners/1/
        // test result
        // test number of prisoners is correct (-1)
    }
}
