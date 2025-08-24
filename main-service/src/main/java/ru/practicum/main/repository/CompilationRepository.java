package ru.practicum.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.main.model.Compilation;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
}
