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

public class ClientForm {

    private String name;

    private String email;

    private String phone;

    public Client toClient()
    {
        return Client.builder()
        .name(name)
        .email(email)
        .phone(StringUtils.cleanPhone(phone))
        .build();
    }

    public static ClientForm of(Client client) {
        return ClientForm.builder()
            .name(client.getName())
            .email(client.getEmail())
            .phone( StringUtils.formatPhone(client.getPhone()))
            .build();
    }
}
