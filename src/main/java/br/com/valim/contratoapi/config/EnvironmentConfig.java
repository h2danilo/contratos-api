package br.com.valim.contratoapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EnvironmentConfig {
    @Value("${docusign.basepath}")
    private String docusignBasepath;

    @Value("${docusign.setOAuthBasePath}")
    private String  docusignSetOAuthBasePath;

    @Value("${docusign.pathPrivateKey}")
    private String  docusignPathPrivateKey;

    @Value("${docusign.chaveIntegracao}")
    private String  docusignChaveIntegracao;

    @Value("${docusign.userId}")
    private String  docusignUserId;
}
