package dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarsDto {
    private CarDto[] cars;
}
