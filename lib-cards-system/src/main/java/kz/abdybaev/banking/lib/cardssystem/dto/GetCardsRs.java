package kz.abdybaev.banking.lib.cardssystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
public class GetCardsRs {
    private final Set<GetCardItemRs> cards;
}
