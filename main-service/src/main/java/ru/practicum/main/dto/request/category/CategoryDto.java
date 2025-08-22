package ru.practicum.main.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @NotBlank(message = "Поле name обязательно для заполнения")
    @Size(min = 1, max = 50, message = "Длина должна быть от 1 до 50 символов")
    private String name;
}