package br.com.varela.erp.web.clients.dtos;

import br.com.varela.erp.core.models.Client;
import br.com.varela.erp.core.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ClientViewModel {

    private Long id;
    private String name;
    private String email;
    private String phone;

    public String getPhone() {
        return StringUtils.formatPhone(phone);
    }

    public static ClientViewModel of(Client client) {
        return ClientViewModel.builder()
            .id(client.getId())
            .name(client.getName())
            .email(client.getEmail())
            .phone(client.getPhone())
            .build();
        // return new ClientViewModel(
        //     client.getId(),
        //     client.getName(),
        //     client.getEmail(),
        //     client.getPhone()
        // );
    }
}
