package com.nttdata.BancaProdMoveActive.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@ToString
@EqualsAndHashCode(of = {"identityNumber"})
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "moveActive")
public class MoveActive {
    @Id
    private String idMoveActive;

    @NotNull
    private String numberContract;

    @NotNull
    private String amount;

    @NotNull
    private String operationType;

    @NotNull
    private LocalDate dateRegister;
}
