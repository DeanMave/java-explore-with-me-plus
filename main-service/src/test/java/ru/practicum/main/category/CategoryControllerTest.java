package ru.practicum.main.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.main.controller.CategoryController;
import ru.practicum.main.dto.request.category.NewCategoryDto;
import ru.practicum.main.dto.response.category.CategoryDto;
import ru.practicum.main.service.interfaces.CategoryService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryService service;

    private final Long catId = 1L;

    private NewCategoryDto request;

    @BeforeEach
    void setUp(){
        request = new NewCategoryDto("Test category");
    }

    @Test
    void addCategory_shouldReturnOkAndAddedCategory() throws Exception {
        CategoryDto response = new CategoryDto(catId, "Test category");

        when(service.addCategory(any(NewCategoryDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(catId))
                .andExpect(jsonPath("$.name").value("Test category"));
    }

    @Test
    void deleteCategory_shouldReturnOk() throws Exception {
        doNothing().when(service).deleteCategory(anyLong());

        mockMvc.perform(delete("/admin/categories/{catId}", catId))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteCategory(catId);
    }
}
