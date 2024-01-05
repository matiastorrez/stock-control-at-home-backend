package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request.ConfirmRegistrationTokenRequest;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.request.NewUserConfirmsRegistration;
import com.stockcontrolathome.authentication.dto.confirmregistrationtoken.response.ConfirmRegistrationTokenResponse;
import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;
import com.stockcontrolathome.authentication.enums.ConfirmRegistrationTokenState;
import com.stockcontrolathome.authentication.exception.ConfirmRegistrationTokenNotFoundException;
import com.stockcontrolathome.authentication.mapper.ConfirmRegistrationTokenMapper;
import com.stockcontrolathome.authentication.service.*;
import com.stockcontrolathome.authentication.utils.codegenerator.CodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final NotificationService notificationService;
    private final ConfirmRegistrationTokenService confirmRegistrationTokenService;
    private final AuditoryConfirmRegistrationTokenService auditoryConfirmRegistrationTokenService;
    private final CodeGenerator codeGenerator;

    private final ConfirmRegistrationTokenMapper confirmRegistrationTokenMapper;

    @Override
    @Transactional
    public void registerUser(RegisterUserRequest registerUserRequest) {
        this.userService.registerUser(registerUserRequest);
        String token = this.codeGenerator.generateRandomCode();

        LocalDateTime now = LocalDateTime.now();
        ConfirmRegistrationTokenRequest confirmRegistrationTokenRequest = ConfirmRegistrationTokenRequest.builder()
                .token(token)
                .createdDate(now)
                .expiredDate(now.plusDays(1))
                .confirmationDate(null)
                .state(ConfirmRegistrationTokenState.FALTA_CONFIRMAR)
                .email(registerUserRequest.getEmail())
                .build();

        this.confirmRegistrationTokenService.createConfirmRegistrationToken(confirmRegistrationTokenRequest);

        notificationService.send("Registro",registerUserRequest.getEmail(), buildRegistrationEmailMessage(registerUserRequest.getEmail(), token));

    }

    @Override
    @Transactional
    public void confirmRegistration(NewUserConfirmsRegistration newUserConfirmsRegistration) {

        ConfirmRegistrationTokenResponse confirmRegistrationTokenResponse = this.confirmRegistrationTokenService.getConfirmRegistrationTokenByTokenAndEmailAndState(newUserConfirmsRegistration.getToken(), newUserConfirmsRegistration.getEmail(), ConfirmRegistrationTokenState.FALTA_CONFIRMAR);

        if (LocalDateTime.now().isAfter(confirmRegistrationTokenResponse.getExpiredDate())) {
            throw new ConfirmRegistrationTokenNotFoundException("El token " + newUserConfirmsRegistration.getToken() + " vinculado a este email: "+ newUserConfirmsRegistration.getEmail() + " expiró, para obtener un nuevo codigo debe iniciar sesion");
        }

        this.userService.modifyUserToConfirmRegister(newUserConfirmsRegistration.getEmail());

        this.auditoryConfirmRegistrationTokenService.saveAuditoryConfirmRegistrationToken(this.confirmRegistrationTokenMapper.confirmRegistrationTokenEntityToConfirmRegistrationTokenResponse(confirmRegistrationTokenResponse));

        this.confirmRegistrationTokenService.deleteUsedRegistrationConfirmationToken(newUserConfirmsRegistration.getToken(), newUserConfirmsRegistration.getEmail());




    }

    private String buildRegistrationEmailMessage(String name, String token) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirma tu registro</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hola " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Gracias por estar registrandote en nuestra aplicacion. Para terminar este proceso te pedimos que coloques el siguiente codigo en la aplicacion: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">"+ token +"</p></blockquote>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}
