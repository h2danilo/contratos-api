package br.com.valim.contratoapi.docusign;

import br.com.valim.contratoapi.config.EnvironmentConfig;
import br.com.valim.contratoapi.docusign.dtos.SignerDto;
import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.auth.OAuth.OAuthToken;
import com.docusign.esign.client.auth.OAuth.UserInfo;
import com.docusign.esign.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@Log4j2
@RequiredArgsConstructor
public class DSEnvelope {

    private final EnvironmentConfig docusignEnvironment;


    public String createEnvelope(ArrayList<SignerDto> listSigner, String docId, String docName, String docBase64) throws IOException, ApiException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // Setup OAuth token
        // Get access token and accountId
        ApiClient apiClient = new ApiClient(docusignEnvironment.getDocusignBasepath());
        apiClient.setOAuthBasePath(docusignEnvironment.getDocusignSetOAuthBasePath());
        ArrayList<String> scopes = new ArrayList<String>();
        scopes.add("signature");
        scopes.add("impersonation");
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(docusignEnvironment.getDocusignPathPrivateKey()));
        OAuthToken oAuthToken = apiClient.requestJWTUserToken(
                docusignEnvironment.getDocusignChaveIntegracao(),
                docusignEnvironment.getDocusignUserId(),
                scopes,
                privateKeyBytes,
                3600);
        String accessToken = oAuthToken.getAccessToken();
        UserInfo userInfo = apiClient.getUserInfo(accessToken);
        String accountId = userInfo.getAccounts().get(0).getAccountId();
        apiClient.addDefaultHeader("Authorization", "Bearer " + accessToken);

        EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);

        // Create envelopeDefinition object
        EnvelopeDefinition envelope = new EnvelopeDefinition();
        envelope.setEmailSubject("Por favor, assine o Documento ("+docName.trim()+") - Tagui Brasil LTDA");

        // Set recipients - Signatarios
        ArrayList<Signer> signers = new ArrayList<Signer>();

        Integer xInit = 9;
        Integer yInit = 800;

        for (int counter = 0; counter < listSigner.size(); counter++) {
            //System.out.println(listSigner.get(counter));

            Integer recipient = counter + 1;

            // Create tabs object
            Tabs tabs = new Tabs();

            SignHere signHere = new SignHere();
            signHere.setDocumentId(docId);
            signHere.setPageNumber("1");
            signHere.setXPosition(Integer.toString(xInit));
            signHere.setYPosition(Integer.toString(yInit));
            signHere.tooltip("Favor, clicar aqui para assinar.");
            signHere.setRecipientId(Integer.toString(recipient));

            tabs.addSignHereTabsItem(signHere);
            tabs.setSignHereTabs(tabs.getSignHereTabs());

            Signer signer = new Signer();
            signer.setEmail(listSigner.get(counter).getEmail());
            signer.setName(listSigner.get(counter).getName());
            signer.setRecipientId(Integer.toString(recipient));
            signer.setTabs(tabs);

            signers.add(signer);

            xInit += 70;
        }

        //Set Recipients
        Recipients recipients = new Recipients();
        recipients.setSigners(signers);

        //Set Recipients into envelope
        envelope.setRecipients(recipients);

        // Add document
        Document document = new Document();
        document.setDocumentBase64(docBase64);
        document.setName(docName+".pdf");
        document.setFileExtension("pdf");
        document.setDocumentId(docId);
        envelope.setDocuments(Arrays.asList(document));

        //envelope.setStatus("sent");
        envelope.setStatus("created");

        EnvelopeSummary results = envelopesApi.createEnvelope(accountId, envelope);
        return results.getEnvelopeId();

    }
}
